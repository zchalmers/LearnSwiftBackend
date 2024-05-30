package com.zchalmers.LearnSwiftBackend.Repositories;

import com.zchalmers.LearnSwiftBackend.Repositories.Model.FoodCategoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryRecord, String> {
}
