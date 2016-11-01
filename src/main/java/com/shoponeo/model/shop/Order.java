package com.shoponeo.model.shop;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Tomasz on 01.11.2016.
 */
@Entity
@Table(name = "ZAMOWIENIE")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ZAMOW_ID")
    private int id;

    @Column(name = "PRICE")
    private double price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ZAMOW_ITEM",
            joinColumns = {@JoinColumn(name = "ZAMOW_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", nullable = false, updatable = false)})
    private Set<Item> items;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    public Order() {
    }

    public Order(double price) {
        this.price = price;
        this.items = new HashSet<>();
    }

    public Order(double price, Set<Item> items) {
        this.price = price;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", items=" + items +
                '}';
    }
}
