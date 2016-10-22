package com.shoponeo.model.shop;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by owczatom on 2016-10-21.
 */
@Entity
@Table(name = "STORE")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STORE_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AVG_RATING")
    private double rating;

    @Column(name = "NO_OF_ORDERS_MADE")
    private int noOfOrdersMade;

    @Column(name = "NO_OF_RATES")
    private int noOfRates;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "store", cascade = CascadeType.ALL)
    private StoreAddress storeAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Item> items;

    public Store() {
    }

    public Store(String name, double rating, int noOfOrdersMade, int noOfRates) {
        this.name = name;
        this.rating = rating;
        this.noOfOrdersMade = noOfOrdersMade;
        this.noOfRates = noOfRates;
        this.items = new HashSet<>();
    }

    public Store(String name, double rating, int noOfOrdersMade, int noOfRates, StoreAddress storeAddress) {
        this.name = name;
        this.rating = rating;
        this.noOfOrdersMade = noOfOrdersMade;
        this.noOfRates = noOfRates;
        this.storeAddress = storeAddress;
        this.items = new HashSet<>();
    }

    public Store(String name, double rating, int noOfOrdersMade, int noOfRates, StoreAddress storeAddress, Set<Item> items) {
        this.name = name;
        this.rating = rating;
        this.noOfOrdersMade = noOfOrdersMade;
        this.noOfRates = noOfRates;
        this.storeAddress = storeAddress;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNoOfOrdersMade() {
        return noOfOrdersMade;
    }

    public void setNoOfOrdersMade(int noOfOrdersMade) {
        this.noOfOrdersMade = noOfOrdersMade;
    }

    public int getNoOfRates() {
        return noOfRates;
    }

    public void setNoOfRates(int noOfRates) {
        this.noOfRates = noOfRates;
    }

    public StoreAddress getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(StoreAddress storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", noOfOrdersMade=" + noOfOrdersMade +
                ", noOfRates=" + noOfRates +
                ", storeAddress=" + storeAddress +
                ", items=" + items +
                '}';
    }
}
