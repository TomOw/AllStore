package com.shoponeo.repository.impl;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Review;
import com.shoponeo.repository.ReviewRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
}
