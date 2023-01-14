package zc.backend.modles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data


public class Ratings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ratingId;
    private int rating;
    private String eventName;

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    public Ratings(int rating, String eventName) {
        this.rating = rating;
        this.eventName = eventName;
    }


}
