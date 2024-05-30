package com.zchalmers.LearnSwiftBackend.Service;

import com.zchalmers.LearnSwiftBackend.Repositories.FoodCategoryRepository;
import com.zchalmers.LearnSwiftBackend.Repositories.FoodRecipeRepository;
import com.zchalmers.LearnSwiftBackend.Repositories.FoodItemRepository;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodCategoryRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodItemRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodRecipeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {

    @Autowired
    private FoodCategoryRepository categoryRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private FoodRecipeRepository recipeRepository;

    public MainService(FoodCategoryRepository categoryRepository, FoodItemRepository foodItemRepository, FoodRecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.foodItemRepository = foodItemRepository;
        this.recipeRepository = recipeRepository;
    }

    public void addCategory(FoodCategoryRecord record){
        categoryRepository.save(record);
    }
    public void addItem(FoodItemRecord record){
        foodItemRepository.save(record);
    }

    public void addListCategory(ArrayList<FoodCategoryRecord> category){
        categoryRepository.saveAll(category);
    }
    public void addListItem(ArrayList<FoodItemRecord> foodItem){
        foodItemRepository.saveAll(foodItem);
    }
    public void addFoodRecipe(FoodRecipeRecord recipeRecord){
        recipeRepository.save(recipeRecord);
    }
    @Async
    public void deleteAll(){
        categoryRepository.deleteAll();
        foodItemRepository.deleteAll();
        recipeRepository.deleteAll();
    }
    public List<FoodRecipeRecord> getRecipes(){ return recipeRepository.findAll();}
    public List<FoodCategoryRecord> getCategories(){
        return categoryRepository.findAll();
    }
    public List<FoodItemRecord> getFoodItems(){
        return foodItemRepository.findAll();
    }


    public List<FoodItemRecord> getFoodItemsByCategoryId(String categoryId){
        return foodItemRepository.findByCategoryId(categoryId);
    }
    public FoodRecipeRecord getRecipeByFoodItemId(String id){
        return recipeRepository.findByFoodItemId(id);
    }

    public int getRepositoryCount(){

        int repositoryCount = 0;
        try {
            repositoryCount += categoryRepository.count() +
                    foodItemRepository.count() +
                    recipeRepository.count();
        }
        catch(Exception e){
            return 0;
        }
        return repositoryCount;

    }
}
