package zc.backend.modles;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.core.SpringVersion;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  infoId ;
    @NonNull
    private Double attitude ;
    @NonNull
    private  Double longitude;
    private  String eventLogo ;
    @JsonFormat( pattern= "dd-MM-yyyy")
    private Date  eventDate ;
  /*  @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;*/
}
