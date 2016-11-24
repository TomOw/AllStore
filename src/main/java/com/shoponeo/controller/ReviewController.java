package com.shoponeo.controller;

import com.shoponeo.model.shop.Item;
import com.shoponeo.model.shop.Review;
import com.shoponeo.repository.ItemRepository;
import com.shoponeo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Tomasz on 22.11.2016.
 */
@RestController
@RequestMapping(value = "/review")
public class ReviewController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @RequestMapping(value = "/add/{itemId}", method = RequestMethod.POST)
    public Review addReview(@RequestBody Review review, @PathVariable("itemId") int itemId) {
        Item item = itemRepository.getItemById(itemId);
        reviewRepository.addReviewToItem(item, review);
        return review;
    }

    @RequestMapping(value = "/{itemName}")
    public List<Review> getReviewsByItemName(@PathVariable("itemName") String name) {
        return reviewRepository.getReviewsByItemName(name);
    }

    @RequestMapping(value = "/no/{itemName}")
    public long getNoOfReviewsByItemName(@PathVariable("itemName") String name) {
        return reviewRepository.getNoOfReviewsByItemName(name);
    }
}
