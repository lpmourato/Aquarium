package com.mourato.aquarium.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mourato.aquarium.controller.PlaySoundsControl;
public class RepositoryFoods {

    public static final int SIZE = 6;
    private OnFoodRepositoryUpdated foodRepositoryUpdated;
    private static RepositoryFoods instance;
    private List<Food> foods;
    private RepositoryFoods(){
        foods = Collections.synchronizedList(new ArrayList<Food>());
    }
    
    public static RepositoryFoods getInstance() {
        if(instance == null){
            synchronized (RepositoryFoods.class) {
                if(instance == null){
                    instance = new RepositoryFoods();
                }
            }
        }
        return instance;
    }
    
    public List<Food> getFoodList() {
        return foods;
    }
    
    public int getAmountFoodByType(FoodType type) {
        int amount = 0;
        for (Food food : foods) {
            if(food.getFoodType().equals(type)){
                amount++;            
            }
        }
        return amount;
    }
    
    public Food getFood(FoodType foodType) {
        Food f = null;
        for (Food food : foods) {
            if(food.getFoodType().equals(foodType) && food.isAvailable()){
                f = food;
                break;
            }
        }
        return f;
    }
    
    public void changeFoodAvailability() {
        for (Food  food : foods) {
            food.setAvailable(true);
        }
    }
    
    public void removeFood(Food foodToFollow) {
        if((getListSize() > 0 && foods.contains(foodToFollow))
                || (getListSize() > 0 && foodToFollow == null)){
            foods.remove(foodToFollow);
            if(foodRepositoryUpdated != null){
                foodRepositoryUpdated.updateFoodPots();
            }
            int amount = getAmountFoodByType(foodToFollow.getFoodType());
            if(amount == 2){
                PlaySoundsControl.getInstance().play("filling_pot.mp3");
            }
        } 
    }
    
    public void addFood(Food food) {
        if(foods.size() < SIZE){
            if(getAmountFoodByType(food.getFoodType()) < 3){
                foods.add(food);  
            }
            if(foodRepositoryUpdated != null){
                foodRepositoryUpdated.updateFoodPots();
            }
        }
    }

    public int getListSize() {
        return foods.size();
    }

    public void setFoodUnavailable(Food food) {
        Food f = getFood(food.getFoodType());
        if(f.isAvailable() && f.equals(food)){
            f.setAvailable(false);
        }
    }
    
    public void setOnFoodRepositoryUpdated(OnFoodRepositoryUpdated updated){
        foodRepositoryUpdated = updated;
    }
    public interface OnFoodRepositoryUpdated{
        public void updateFoodPots();
        
    }
}
