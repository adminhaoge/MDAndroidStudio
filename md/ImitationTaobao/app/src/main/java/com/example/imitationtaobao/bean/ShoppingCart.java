package com.example.imitationtaobao.bean;

public class ShoppingCart extends WaresHot {
    private int count;
    private boolean isChecked = false;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}

