package com.shoponeo.model.shop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shoponeo.model.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "STORE")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @Column(name = "NUMBER")
    private String number;

    @Column(name = "POSTAL_CODE")
    private String postalCode;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Item> items;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "store", cascade = CascadeType.ALL)
    private List<User> owners;

    public Store() {
    }

    public Store(String name, double rating, int noOfOrdersMade, int noOfRates) {
        this.name = name;
        this.rating = rating;
        this.noOfOrdersMade = noOfOrdersMade;
        this.noOfRates = noOfRates;
        this.items = new HashSet<>();
    }

    public Store(String name, double rating, int noOfOrdersMade, int noOfRates, Set<Item> items) {
        this.name = name;
        this.rating = rating;
        this.noOfOrdersMade = noOfOrdersMade;
        this.noOfRates = noOfRates;
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        if (this.items == null) {
            this.items = new HashSet<>();
            this.items.add(item);
        } else {
            this.items.add(item);
        }
    }

    public void addOrder(Order order) {
        if (this.orders == null) {
            this.orders = new HashSet<>();
            this.orders.add(order);
        } else {
            this.orders.add(order);
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", noOfOrdersMade=" + noOfOrdersMade +
                ", noOfRates=" + noOfRates +
                ", items=" + items +
                '}';
    }
}
