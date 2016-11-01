package com.shoponeo.model.shop;

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
    private int price;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "DESCRIPTION", length = 10000)
    private String description;

    @Column(name = "PHOTO")
    private String photo;

    @Column(name = "NO_IN_STOCK")
    private int noInStock;

    @Column(name = "AVG_RATING")
    private double avgRating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STORE_ID", nullable = false)
    private Store store;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    public Item() {
    }

    public Item(String name, int price, String category, String description, String photo, int noInStock, double avgRating) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
        this.photo = photo;
        this.noInStock = noInStock;
        this.avgRating = avgRating;
        this.reviews = new HashSet<>();
    }

    public Item(String name, int price, String category, String description, String photo, int noInStock, double avgRating, Store store) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
}