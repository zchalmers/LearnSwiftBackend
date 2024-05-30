package com.zchalmers.LearnSwiftBackend.Repositories;

import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodItemRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemRecord, String> {
    List<FoodItemRecord> findByCategoryId(String foodCategoryId);
}
