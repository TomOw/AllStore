package com.shoponeo.model.shop;

import java.util.List;

public class Cart {

    private List<Item> items;

    private double sum;

    public Cart() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
