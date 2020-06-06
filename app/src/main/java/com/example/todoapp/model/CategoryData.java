package com.example.todoapp.model;

import java.io.Serializable;

public class CategoryData implements Serializable {

    String labelName;
    int backgroundColor;


    public CategoryData(String name, int color){
        this.labelName = name;
        this.backgroundColor = color;
    }

    public String getLabelName() {
        return this.labelName;
    }

    public int getBackgroundColor() {
        return this.backgroundColor;
    }

}
