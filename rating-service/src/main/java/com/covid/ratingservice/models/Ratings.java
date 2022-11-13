package com.covid.ratingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Ratings {

    @Id
  //  @GeneratedValue(strategy=GenerationType.AUTO)
    int ratingId;
    int rating;

    public Ratings(int rating) {
        this.rating = rating;
    }
}
