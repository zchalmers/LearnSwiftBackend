package com.zchalmers.LearnSwiftBackend.Repositories;

import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodItemRecord;
import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodRecipeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRecipeRepository extends JpaRepository<FoodRecipeRecord, String> {

     FoodRecipeRecord findByFoodItemId(String foodItemId);
}
