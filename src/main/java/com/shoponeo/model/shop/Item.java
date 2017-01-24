package com.shoponeo.model.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by owczatom on 2016-10-21.
 */
@Entity
@Table(name = "ITEM")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DESCRIPTION", length = 10000)
    private String description;

    @Column(name = "PHOTO", length = 2000)
    private String photo;

    @Column(name = "NO_IN_STOCK")
    private int noInStock;

    @Column(name = "AVG_RATING")
    private double avgRating;

    @Column(name = "VIEWS")
    private int views;

    @Column(name = "NO_OF_REVIEWS")
    private int noOfReviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID", nullable = false)
    private Store store;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "items")
    private Set<Order> orders;

    public Item() {
    }

    public Item(String name, double price, String category, String description, String photo, int noInStock, double avgRating) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.photo = photo;
        this.noInStock = noInStock;
        this.avgRating = avgRating;
        this.reviews = new HashSet<>();
    }

    public Item(String name, double price, String category, String description, String photo, int noInStock, double avgRating, Store store) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.photo = photo;
        this.noInStock = noInStock;
        this.avgRating = avgRating;
        this.store = store;
        this.reviews = new HashSet<>();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getNoInStock() {
        return noInStock;
    }

    public void setNoInStock(int noInStock) {
        this.noInStock = noInStock;
    }

    public double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(double avgRating) {
        this.avgRating = avgRating;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        if (this.reviews == null) {
            this.reviews = new HashSet<>();
            this.reviews.add(review);
        } else {
            this.reviews.add(review);
        }
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order) {
        if (this.orders == null) {
            this.orders = new HashSet<>();
            this.orders.add(order);
        } else {
            this.orders.add(order);
        }
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getNoOfReviews() {
        return noOfReviews;
    }

    public void setNoOfReviews(int noOfReviews) {
        this.noOfReviews = noOfReviews;
    }

    public String getStoreName() {
        return this.store.getName();
    }

    public int getStoreId() {
        return this.store.getId();
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", noInStock=" + noInStock +
                ", avgRating=" + avgRating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return getName() != null ? getName().equals(item.getName()) : item.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}