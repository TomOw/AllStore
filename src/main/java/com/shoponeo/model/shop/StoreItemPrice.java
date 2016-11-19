package com.shoponeo.model.shop;

/**
 * Created by Tomasz on 19.11.2016.
 */
public class StoreItemPrice {

    private String storeName;

    private int storeId;

    private int itemId;

    private double itemPrice;

    public StoreItemPrice() {
    }

    public StoreItemPrice(String storeName, int storeId, int itemId, double itemPrice) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "StoreItemPrice{" +
                "storeName='" + storeName + '\'' +
                ", storeId=" + storeId +
                ", itemId=" + itemId +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
