package com.example.todoapp.model;

import java.io.Serializable;

public class VisibilityOfListNewTask implements Serializable {

    public int visibility;

    public VisibilityOfListNewTask() {

    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public int isVisibility() {
        return visibility;
    }
}
