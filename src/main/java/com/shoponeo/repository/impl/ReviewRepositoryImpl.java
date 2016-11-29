package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Review;
import com.shoponeo.repository.ItemRepository;
import com.shoponeo.repository.ReviewRepository;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomasz on 01.11.2016.
 */
@Repository
@Transactional
public class ReviewRepositoryImpl implements ReviewRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Item addReviewToItem(Item item, Review review, List<Item> itemList) {
        for (Item item1 :
                itemList) {
            int noOfReviews = item1.getNoOfReviews();
            double avgRating = (item1.getAvgRating() * noOfReviews + review.getRating()) / (noOfReviews + 1);
            avgRating = Math.round(avgRating * 100.0) / 100.0;
            item1.setAvgRating(avgRating);
            item1.setNoOfReviews(item1.getNoOfReviews() + 1);
            entityManager.merge(item1);
        }
        item.addReview(review);
        review.setItem(item);
        entityManager.merge(item);
        return item;
    }

    @Override
    public List<Review> getReviewsByItemName(String name) {
        Query query = entityManager.createQuery("select review from Review review inner join review.item as item where item.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public long getNoOfReviewsByItemName(String name) {
        Query query = entityManager.createQuery("select count(review) from Review review inner join review.item as item where item.name = :name");
        query.setParameter("name", name);
        return (long) query.getResultList().get(0);

    }
}
