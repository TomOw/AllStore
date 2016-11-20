package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Review;
import com.shoponeo.repository.ReviewRepository;
import org.hibernate.sql.Select;
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
    public Review addReviewToItem(Item item, Review review) {
        item.addReview(review);
        review.setItem(item);
        entityManager.merge(item);
        return review;
    }

    @Override
    public List<Review> getReviewsByItemName(String name) {
        Query query = entityManager.createQuery("select review from Review review inner join review.item as item where item.name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }
}
