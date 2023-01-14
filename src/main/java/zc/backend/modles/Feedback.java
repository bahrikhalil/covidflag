package zc.backend.modles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {
    @NonNull
    @Id
    private String name ;
    @NonNull
    private  String  email  ;
    @NonNull
    private  String  feedback  ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event events;
}
