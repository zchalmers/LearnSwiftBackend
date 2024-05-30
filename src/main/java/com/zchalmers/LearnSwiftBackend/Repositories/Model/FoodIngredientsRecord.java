package com.zchalmers.LearnSwiftBackend.Repositories.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "food-ingredients")
public class FoodIngredientsRecord {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "ingredient-name")
    private String ingredientName;

    @Column(name = "measurement")
    private String measurement;

    public FoodIngredientsRecord() {
    }

    public FoodIngredientsRecord(String ingredientName, String measurement) {
        this.id = UUID.randomUUID().toString();
        this.ingredientName = ingredientName;
        this.measurement = measurement;
    }

    public String getId() {
        return id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
