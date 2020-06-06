package com.example.todoapp.model;

public class NewTaskCategory {

    public boolean isChecked;
    public boolean inbox, work, shopping, family, personal;

    public NewTaskCategory() {

    }

    public void setCheckedStatus(boolean checked) {
        this.isChecked = checked;
    }

    public boolean getCheckedStatus() {
        return this.isChecked;
    }

    public void setInboxStatus(boolean checked) {
     this.inbox = checked;
    }

    public void setWorkStatus(boolean checked) {
        this.work = checked;
    }

    public void setShoppingStatus(boolean checked) {
        this.shopping = checked;
    }

    public void setFamilyStatus(boolean checked) {
        this.family = checked;
    }

    public void setPersonalStatus(boolean checked) {
        this.personal = checked;
    }

    public boolean getInboxStatus() {
        return this.inbox;
    }

    public boolean getWorkStatus() {
        return this.work;
    }

    public boolean getShoppingStatus() {
        return this.shopping;
    }

    public boolean getFamilyStatus() {
        return this.family;
    }

    public boolean getPersonalStatus() {
        return this.personal;
    }
}
