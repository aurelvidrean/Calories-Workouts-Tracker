package com.licenta.app.dtos;

public class FoodDTO {

    private Long id;
    private String name;
    private String imageUrl;

    private int calories;
    private boolean loseWeightType;

    public FoodDTO() {
    }

    public FoodDTO(Long id, String name, String imageUrl, boolean loseWeightType) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.loseWeightType = loseWeightType;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean isLoseWeightType() {
        return loseWeightType;
    }

    public void setLoseWeightType(boolean loseWeightType) {
        this.loseWeightType = loseWeightType;
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
}
