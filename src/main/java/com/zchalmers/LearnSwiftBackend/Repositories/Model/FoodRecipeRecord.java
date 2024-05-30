package com.zchalmers.LearnSwiftBackend.Repositories.Model;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "food-recipes")
public class FoodRecipeRecord {
    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "foodItem_id")
    private FoodItemRecord foodItem;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "food_ingredient_id")
    private List<FoodIngredientsRecord> foodIngredients;

    public FoodRecipeRecord() {
        this.id = UUID.randomUUID().toString();
    }

    public FoodRecipeRecord(FoodItemRecord foodItem, List<FoodIngredientsRecord> foodIngredients) {
        this.id = UUID.randomUUID().toString();
        this.foodItem = foodItem;
        this.foodIngredients = foodIngredients;
    }

    public String getId() {
        return id;
    }

    public FoodItemRecord getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItemRecord foodItem) {
        this.foodItem = foodItem;
    }

    public List<FoodIngredientsRecord> getFoodIngredients() {
        return foodIngredients;
    }

    public void setFoodIngredients(List<FoodIngredientsRecord> foodIngredients) {
        this.foodIngredients = foodIngredients;
    }

    @Override
    public String toString() {
        return "FoodRecipeRecord{" +
                "id='" + id + '\'' +
                ", foodItemRecord=" + foodItem +
                ", foodIngredients=" + foodIngredients.toString() +
                '}';
    }
}
