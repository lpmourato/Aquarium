package com.mourato.aquarium.model;

public enum FishType{
    BIG, MEDIUM, SMALL;
    
    public static FishType valueOfIndex(int index){
        for (FishType type : FishType.values()) {
            if(type.ordinal() == index){
                return type;
            }
        }
        return null;
    }
}
