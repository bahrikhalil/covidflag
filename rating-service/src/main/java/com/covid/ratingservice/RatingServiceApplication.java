package com.covid.ratingservice;

import com.covid.ratingservice.models.EventRating;
import com.covid.ratingservice.models.Ratings;
import com.covid.ratingservice.repo.RatingRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication
public class RatingServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(RatingServiceApplication.class, args);

    }
  @Bean
    CommandLineRunner run(RatingRepo ratingRepo) {
        return args -> {


          //  System.out.println(ev1.getEventRating().get(1));
           EventRating ev1 = new EventRating(10);
            EventRating ev2 = new EventRating(20);
            ev1.getEventRating().add(new Ratings(71,40));
            ev2.getEventRating().add(new Ratings(61,2));


            // ev1.getEventRating().add(new Ratings(3,9));
           // ev1.getEventRating().add(new Ratings(2,9));
           // ev2.getEventRating().add(new Ratings(4,10));
            //ev2.getEventRating().add(new Ratings(4,6));
           ratingRepo.save(ev1);
           ratingRepo.save(ev2);



        };
    }


}
