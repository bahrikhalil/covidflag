package com.covid.ratingservice.repo;

import com.covid.ratingservice.models.EventRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<EventRating,Integer> {
    EventRating findByeventName(String eventname);
}
