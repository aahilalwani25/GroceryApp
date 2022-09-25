package com.example.grocerydeliveryapp.Vegetables;

import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vegetables {

    String vegetableName, description;
    int cost;
    byte[] drawableId;

    public Vegetables(String vegetableName, int cost, String description, byte[] drawableId){
        this.cost=cost;
        this.vegetableName=vegetableName;
        this.description=description;
        this.drawableId=drawableId;
    }

    public String getVegetableName() {
        return vegetableName;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getDrawableId() {
        return drawableId;
    }


}
