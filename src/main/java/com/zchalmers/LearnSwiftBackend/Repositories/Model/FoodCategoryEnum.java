package com.zchalmers.LearnSwiftBackend.Repositories.Model;

public enum FoodCategoryEnum {
    APPETIZERS("Appetizers"),
    MAIN("Main"),
    DESSERTS("Desserts"),
    DRINKS("Drinks");
    private final String label;
    FoodCategoryEnum(String label) {
        this.label = label;
    }
}
