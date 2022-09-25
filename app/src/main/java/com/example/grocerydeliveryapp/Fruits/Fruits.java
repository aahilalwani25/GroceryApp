package com.example.grocerydeliveryapp.Fruits;

import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Fruits {

    String fruitsName, description;
    int cost;
    byte[] drawableId;

    public String getFruitsName() {
        return fruitsName;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public byte[] getDrawableId() {
        return drawableId;
    }

    public Fruits(String fruitsName, int cost, String description, byte[] drawableId) {
        this.fruitsName = fruitsName;
        this.description = description;
        this.cost = cost;
        this.drawableId = drawableId;
    }
}
