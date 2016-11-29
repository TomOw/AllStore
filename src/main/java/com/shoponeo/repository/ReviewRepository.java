package com.shoponeo.repository;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Review;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Tomasz on 01.11.2016.
 */
@Repository
@Transactional
public interface ReviewRepository {

    Item addReviewToItem(Item item, Review review, List<Item> itemList);

    List<Review> getReviewsByItemName(String name);

    long getNoOfReviewsByItemName(String name);
}
