package com.mourato.aquarium.model;

public enum FoodType {
    BIG_FISH_FOOD("big_fish_food"),
    MEDIUM_FISH_FOOD("medium_fish_food"),
    SMALL_FISH_FOOD("small_fish_food");
    @SuppressWarnings("unused")
    private String food;
    private FoodType(String food) {
        this.food = food;
    }
    
    public static FoodType valueOfName(String value){
        for (FoodType f : FoodType.values()) {
            if(f.toString().equalsIgnoreCase(value)){
                return f;
            }
        }
        return null;
    }
}
