package com.cninfotech.template.model;

public class Product {
    private String title;
    private int image;
    private String price;
    private String discount;
    private String discountPercent;
    private float rating;
    private String size;
    private String color;
    private String slogan;
    private String quantity;

    public Product(String title, int image, String price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public Product(String title, int image, String price, String quantity) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String title, int image, String price, String size, String color, String discount, String discountPercent) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.size = size;
        this.color = color;
        this.discount = discount;
        this.discountPercent = discountPercent;
    }

    public Product(String title, int image, String price, String color, String discount, String discountPercent,float rating) {
        this.title = title;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.discountPercent = discountPercent;
        this.color = color;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getSlogan() {
        return slogan;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public float getRating() {
        return rating;
    }

    public String getQuantity() {
        return quantity;
    }
}
