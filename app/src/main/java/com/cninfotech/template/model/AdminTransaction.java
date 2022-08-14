package com.cninfotech.template.model;

public class AdminTransaction {
    String activity;
    String totalCash;
    String cash;
    String itemDetail;
    String deliveryInfo;
    String cashInfo;

    public AdminTransaction(String activity, String totalCash, String cash, String itemDetail, String deliveryInfo, String cashInfo) {
        this.activity = activity;
        this.totalCash = totalCash;
        this.cash = cash;
        this.itemDetail = itemDetail;
        this.deliveryInfo = deliveryInfo;
        this.cashInfo = cashInfo;
    }

    public String getActivity() {
        return activity;
    }

    public String getTotalCash() {
        return totalCash;
    }

    public String getCash() {
        return cash;
    }

    public String getItemDetail() {
        return itemDetail;
    }

    public String getDeliveryInfo() {
        return deliveryInfo;
    }

    public String getCashInfo() {
        return cashInfo;
    }
}
