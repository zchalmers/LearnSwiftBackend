package com.zchalmers.LearnSwiftBackend.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodCategoryRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodItemRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodRecipeRecord;
import com.zchalmers.LearnSwiftBackend.Service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private MainService mainService;
    private final ObjectMapper objectMapper;
    public MainController(MainService mainService, ObjectMapper objectMapper) {
        this.mainService = mainService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/foodCategoriesList")
    public ResponseEntity<List<FoodCategoryRecord>> getCategoriesList(){
        List<FoodCategoryRecord> categories = mainService.getCategories();
        return ResponseEntity.ok(categories);
    }

//    @GetMapping("/foodItems/{id}")
//    public ResponseEntity<List<FoodItemRecord>> getFoodItemByCategoryId(@PathVariable String id) {
//        List<FoodItemRecord> foodItemList = mainService.getFoodItemsByCategoryId(id);
//        return ResponseEntity.ok(foodItemList);
//    }

    @GetMapping("/foodItems")
    public ResponseEntity<List<FoodItemRecord>> getFoodItems(@RequestParam String id){
        List<FoodItemRecord> foodItemList = mainService.getFoodItemsByCategoryId(id);
        return ResponseEntity.ok(foodItemList);
    }
    @GetMapping("/foodRecipe")
    public ResponseEntity<FoodRecipeRecord> getFoodRecipeByFoodItemId(@RequestParam String id){
        FoodRecipeRecord foodRecipeRecord = mainService.getRecipeByFoodItemId(id);
        return ResponseEntity.ok(foodRecipeRecord);
    }


    private <T> ResponseEntity<String> jsonMapper(List<T> list){
        try {
            String json = objectMapper.writeValueAsString(list);
            return ResponseEntity.ok(json);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(500).body("Error converting to JSON: " + e.getMessage());
        }
    }
}
