package com.licenta.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String imageUrl;

    private int calories;

    private boolean loseWeightType;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFood> users = new ArrayList<>();

    public boolean isLoseWeightType() {
        return loseWeightType;
    }

    public void setLoseWeightType(boolean loseWeightType) {
        this.loseWeightType = loseWeightType;
    }

    public Food(Long id, String name, String imageUrl, boolean loseWeightType, List<UserFood> users) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.loseWeightType = loseWeightType;
        this.users = users;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<UserFood> getUsers() {
        return users;
    }

    public void setUsers(List<UserFood> users) {
        this.users = users;
    }

    public Food() {
    }
}
