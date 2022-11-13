package zc.backend.modles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer eventId ;
    @NonNull
    private  String  eventName ;
    @NonNull
    private  String eventDisc ;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<EventInfo> infoList=new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "events")
    private Set<Feedback> feedbackSet;

}
