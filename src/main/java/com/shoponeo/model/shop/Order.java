package com.shoponeo.model.shop;

import com.shoponeo.model.User;

import javax.persistence.*;
import java.util.*;

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

    @Column(name = "MADE_DATE")
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "ZAMOW_ITEM",
            joinColumns = {@JoinColumn(name = "ZAMOW_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ITEM_ID", nullable = false, updatable = false)})
    private Set<Item> items;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME")
    private User user;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addItem(Item item) {
        if (this.items == null) {
            this.items = new HashSet<>();
            this.items.add(item);
        }
        this.items.add(item);
    }

    public void calculatePrice() {
        double sum = 0;
        for (Item item :
                this.items) {
            sum += item.getPrice();
        }
        this.price = sum;
    }

    public String getStoreName() {
        if (this.store == null) {
            List<Item> items = new ArrayList<>(this.items);
            if (items.size() > 0) {
                return items.get(0).getStoreName();
            }
        } else {
            return this.store.getName();
        }
        return null;
    }

    public String getUsername() {
        return this.user.getUsername();
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
