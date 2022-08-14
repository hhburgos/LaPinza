package com.cninfotech.template.model;

public class AdminProduct {
    int orderNumber;
    String productName;
    String productTime;
    String productPrice;
    String productPaymentMethod;
    String productDeliveryInfo;
    String productTotalCount;
    int image;


    public AdminProduct(int orderNumber, String productName, String productTime, String productPrice, String productPaymentMethod, String productDeliveryInfo, String productTotalCount,int image) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.productTime = productTime;
        this.productPrice = productPrice;
        this.productPaymentMethod = productPaymentMethod;
        this.productDeliveryInfo = productDeliveryInfo;
        this.productTotalCount = productTotalCount;
        this.image = image;
    }

    public AdminProduct(String productName, String productPrice, String productTotalCount,int image) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productTotalCount = productTotalCount;
        this.image = image;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductTime() {
        return productTime;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public String getProductPaymentMethod() {
        return productPaymentMethod;
    }

    public String getProductDeliveryInfo() {
        return productDeliveryInfo;
    }

    public String getProductTotalCount() {
        return productTotalCount;
    }

    public int getImage() {
        return image;
    }
}
