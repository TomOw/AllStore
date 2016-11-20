package com.shoponeo.model.shop;

import javax.persistence.*;

/**
 * Created by Tomasz on 22.10.2016.
 */
@Entity
@Table(name = "REVIEW")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private int id;

    @Column(name = "DESCRIPTION", length = 10000)
    private String description;

    @Column(name = "RATING")
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    public Review() {
    }

    public Review(String description) {
        this.description = description;
    }

    public Review(String description, Item item) {
        this.description = description;
        this.item = item;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
