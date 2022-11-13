package com.covid.ratingservice.controller;


import com.covid.ratingservice.models.EventRating;
import com.covid.ratingservice.models.Ratings;
import com.covid.ratingservice.repo.JustRating;
import com.covid.ratingservice.repo.RatingRepo;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api")
@CrossOrigin("*")
@RestController
public class RatingController {

    @Autowired
    private RatingRepo ratingRepo;

    @Autowired
    private JustRating justRating;

    @GetMapping("{id}")
    public Optional<EventRating> saveRating(@PathVariable int id){

       // EventRating ev1 =new EventRating(1);
       // ev1.getEventRating().add(new Ratings(3,9));
             return ratingRepo.findById(id);

    }

    @GetMapping("rating1")
    public List<Ratings> getRatings(){
        return justRating.findAll();
    }

    @PostMapping("postcomment")
    public Ratings postComment(@RequestBody Ratings rating){
        return justRating.save(rating);
    }

    @GetMapping("rating1/{id}")
    public Optional<Ratings> getRating(@PathVariable int id){
        return justRating.findById(id);
    }


  /*  @PostMapping("{eventname}")
    @Transactional
    public void addRating(@PathVariable String eventname, @RequestBody Ratings ratings){
        EventRating eventRating = ratingRepo.findByeventName(eventname);
        eventRating.getEventRating().add(ratings);
    }
*/

    @PostMapping("create")
    public void addEventRating(@RequestBody EventRating eventRating){
        ratingRepo.save(eventRating);
    }
}
