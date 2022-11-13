package com.covid.ratingservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRating {

    @Id
    private int eventId;
    private String eventName;

    public EventRating(int eventId) {
        this.eventId = eventId;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Ratings> eventRating = new ArrayList<>();

    public List<Ratings> getEventRating() {
        return eventRating;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
