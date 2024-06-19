package com.zchalmers.LearnSwiftBackend.Service;


import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodCategoryRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodIngredientsRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodItemRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodRecipeRecord;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class InputData {
    private boolean initialized = false;

    @Autowired
    MainService mainService;

    public InputData(MainService mainService) {
        this.mainService = mainService;
    }

    @PostConstruct
    @Transactional
    public void init() {
        if (!initialized) {
            // Check if the database has data

            if (mainService.getRepositoryCount() == 0) {
                // If database is empty, load data
                loadDataIntoDatabase();
            } else {
                // Database is already populated
                System.out.println("Database already contains data.");
            }
            initialized = true;
        }
    }

    private void loadDataIntoDatabase() {
        // Load initial data into the database
        // You can perform your data loading logic here
        ArrayList<FoodCategoryRecord> categoryList = new ArrayList<>();
        ArrayList<FoodItemRecord> foodItemList = new ArrayList<>();

        String fileName = "data.txt"; // Change this to the path of your text file
        ClassPathResource resource = new ClassPathResource(fileName);
        try (InputStream inputStream = resource.getInputStream();
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(streamReader)) {

            String line;
            FoodCategoryRecord current = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("*")){
                    line = br.readLine();
                    current = new FoodCategoryRecord(line.trim());
                    mainService.addCategory(current);
                }
                else if (line.startsWith("&")) {
                    String foodName = br.readLine();
                    String description = br.readLine();
                    String[] recipe = br.readLine().split(",");
                    List<FoodIngredientsRecord> ingredientsList = new ArrayList<>();
                    for (String s : recipe){
                        String[] ingredients = s.split("!");

                        if (ingredients.length >= 2) {
                            String ingredientName = ingredients[1].trim();
                            String ingredientAmount = ingredients[0].trim();
                            FoodIngredientsRecord ingredientRecord = new FoodIngredientsRecord(ingredientName, ingredientAmount);
                            ingredientsList.add(ingredientRecord);
                        } else {
                            // Handle invalid ingredient format
                            System.err.println("Invalid ingredient format: " + s);
                        }
                    }
                    String summary = br.readLine().trim();
                    FoodItemRecord foodItemRecord = new FoodItemRecord(foodName, current, description, summary);
                    mainService.addItem(foodItemRecord);

                    FoodRecipeRecord recipeRecord = new FoodRecipeRecord(foodItemRecord, ingredientsList);
                    System.out.println("BEFORE RECIPE SAVE");
                    System.out.println(recipeRecord.toString());
                    mainService.addFoodRecipe(recipeRecord);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            mainService.deleteAll();
            System.exit(1);
        }





    }

}
