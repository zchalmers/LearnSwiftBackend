//package com.zchalmers.LearnSwiftBackend.DeprecatedData;
//
//
//import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodCategoryRecord;
//import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodIngredientsRecord;
//import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodItemRecord;
//import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodRecipeRecord;
//import com.zchalmers.LearnSwiftBackend.Service.MainService;
//import jakarta.annotation.PostConstruct;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class InputData {
//    private boolean initialized = false;
//
//    @Autowired
//    MainService mainService;
//
//    public InputData(MainService mainService) {
//        this.mainService = mainService;
//    }
//
//    @PostConstruct
//    @Transactional
//    public void init() {
//        if (!initialized) {
//            // Check if the database has data
//
//            if (mainService.getRepositoryCount() == 0) {
//                // If database is empty, load data
//                loadDataIntoDatabase();
//            } else {
//                // Database is already populated
//                System.out.println("Database already contains data.");
//            }
//            initialized = true;
//        }
//    }
//
//    private void loadDataIntoDatabase() {
//        // Load initial data into the database
//        // You can perform your data loading logic here
//        ArrayList<FoodCategoryRecord> categoryList = new ArrayList<>();
//        ArrayList<FoodItemRecord> foodItemList = new ArrayList<>();
//
//        String fileName = "C:\\Users\\zchal\\CODING\\LearnSwiftBackend\\src\\main\\java\\com\\zchalmers\\LearnSwiftBackend\\Service\\data.txt"; // Change this to the path of your text file
//
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            String line;
//            FoodCategoryRecord current = null;
//            while ((line = br.readLine()) != null) {
//                if (line.startsWith("*")){
//                    line = br.readLine();
//                    current = new FoodCategoryRecord(line.trim());
//                    mainService.addCategory(current);
//                }
//                else if (line.startsWith("&")) {
//                    String foodName = br.readLine();
//                    String description = br.readLine();
//                    String[] recipe = br.readLine().split(",");
//                    List<FoodIngredientsRecord> ingredientsList = new ArrayList<>();
//                    for (String s : recipe){
//                        String[] ingredients = s.split("!");
//                        System.out.println("RECIPE AFTER ! SPLIT");
//                        System.out.println(ingredients[1]);
//                        System.out.println(ingredients[0]);
//
//                        if (ingredients.length >= 2) {
//                            String ingredientName = ingredients[1].trim();
//                            String ingredientAmount = ingredients[0].trim();
//                            FoodIngredientsRecord ingredientRecord = new FoodIngredientsRecord(ingredientName, ingredientAmount);
//                            ingredientsList.add(ingredientRecord);
//                        } else {
//                            // Handle invalid ingredient format
//                            System.err.println("Invalid ingredient format: " + s);
//                        }
//                    }
//
//                    FoodItemRecord foodItemRecord = new FoodItemRecord(foodName, current, description);
//                    mainService.addItem(foodItemRecord);
//
//                    FoodRecipeRecord recipeRecord = new FoodRecipeRecord(foodItemRecord, ingredientsList);
//                    System.out.println("BEFORE RECIPE SAVE");
//                    System.out.println(recipeRecord.toString());
//                    mainService.addFoodRecipe(recipeRecord);
//
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            mainService.deleteAll();
//            System.exit(1);
//        }
//
//
//
//
//
//    }
//
//}
