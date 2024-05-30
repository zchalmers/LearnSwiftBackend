package com.zchalmers.LearnSwiftBackend.Repositories.Model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "food-items")
public class FoodItemRecord {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private FoodCategoryRecord category;

    @Column(name = "description")
    private String description;

    @Column(name = "summary", columnDefinition="VARCHAR(1000)")
    private String summary;

    public FoodItemRecord() {
    }

    public FoodItemRecord(String name, FoodCategoryRecord category, String description, String summary) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.category = category;
        this.description = description;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodCategoryRecord getCategory() {
        return category;
    }

    public void setCategory(FoodCategoryRecord category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "FoodItemRecord{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category=" + category.toString() +
                ", description='" + description + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
