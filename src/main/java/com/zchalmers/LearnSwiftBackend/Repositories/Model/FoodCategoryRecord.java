package com.zchalmers.LearnSwiftBackend.Repositories.Model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "food-categories")
public class FoodCategoryRecord {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;


    public FoodCategoryRecord() {
    }

    public FoodCategoryRecord(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
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

}
