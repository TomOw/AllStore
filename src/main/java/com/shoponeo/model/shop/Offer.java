package com.shoponeo.model.shop;

public class Offer implements Comparable {

    private String storeName;

    private int storeId;

    private String storeCountry;

    private String storeCity;

    private String storeStreet;

    private String storeNumber;

    private String storePostalCode;

    private int itemId;

    private double itemPrice;

    private int noInStock;

    public Offer() {
    }

    public Offer(String storeName, int storeId, String storeCountry, String storeCity, String storeStreet, String storeNumber, String storePostalCode, int itemId, double itemPrice, int noInStock) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.storeCountry = storeCountry;
        this.storeCity = storeCity;
        this.storeStreet = storeStreet;
        this.storeNumber = storeNumber;
        this.storePostalCode = storePostalCode;
        this.itemId = itemId;
        this.itemPrice = itemPrice;
        this.noInStock = noInStock;
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

    public String getStoreCountry() {
        return storeCountry;
    }

    public void setStoreCountry(String storeCountry) {
        this.storeCountry = storeCountry;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public void setStoreCity(String storeCity) {
        this.storeCity = storeCity;
    }

    public String getStoreStreet() {
        return storeStreet;
    }

    public void setStoreStreet(String storeStreet) {
        this.storeStreet = storeStreet;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getStorePostalCode() {
        return storePostalCode;
    }

    public void setStorePostalCode(String storePostalCode) {
        this.storePostalCode = storePostalCode;
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

    public int getNoInStock() {
        return noInStock;
    }

    public void setNoInStock(int noInStock) {
        this.noInStock = noInStock;
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

    @Override
    public int compareTo(Object o) {
        return new Double(this.itemPrice).compareTo(((Offer) o).itemPrice);
    }
}
