package com.cninfotech.template.model;

public class Category {
    private String categoryName;
    private String categorySlogan;
    private int categoryImg;

    public Category(String categoryName, String categorySlogan, int categoryImg) {
        this.categoryName = categoryName;
        this.categorySlogan = categorySlogan;
        this.categoryImg = categoryImg;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategorySlogan() {
        return categorySlogan;
    }

    public int getCategoryImg() {
        return categoryImg;
    }
}
