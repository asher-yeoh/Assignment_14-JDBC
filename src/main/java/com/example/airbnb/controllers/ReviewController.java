package com.example.airbnb.controllers;

import java.util.List;

import com.example.airbnb.entities.Review;
import com.example.airbnb.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReviewController
 */

@RestController
@RequestMapping(path="/api")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    //http://localhost:8080/api/reviews
    @GetMapping(path="/reviews", produces="application/json")
    public List<Review> displayReviews() {
        return reviewRepository.getAllReviews();
    }

    //get reviews for property with id
    //http://localhost:8080/api/properties/1/reviews
    @GetMapping(path="/properties/{id}/reviews", produces="application/json")
    public List<Review> displayReviewsByPropertyId(@PathVariable("id") int propertyId) {
        return reviewRepository.getReviewsByPropertyId(propertyId);
    }

    //search for reviews with rating greater than x
    //http://localhost:8080/api/reviews_by?rating=1
    @GetMapping(path="/reviews_by", produces="application/json")
    public List<Review> displayRatings(@RequestParam int rating) {
        return reviewRepository.getRatings(rating);
    }

    //update review by property id: rating, remark
    //http://localhost:8080/api/properties/1/reviews
    @PostMapping(value="/properties/{id}/reviews", produces="application/json")
    public Review createReviewByPropertyId(@RequestBody Review review, @PathVariable("id") int propertyId) {
        reviewRepository.createReviewByPropertyId(review, propertyId);
        return review;
    }
    
}