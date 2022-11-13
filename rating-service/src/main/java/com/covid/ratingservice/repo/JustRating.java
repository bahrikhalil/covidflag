package com.covid.ratingservice.repo;

import com.covid.ratingservice.models.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JustRating extends JpaRepository<Ratings,Integer> {

}
