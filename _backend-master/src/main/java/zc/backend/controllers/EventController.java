package zc.backend.controllers;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import zc.backend.modles.Event;
import zc.backend.modles.EventInfo;
import zc.backend.modles.EventRating;
import zc.backend.services.EventServiceImpl;
import  java.util.List ;

@RequestMapping("/api/event")
@RestController
@CrossOrigin("*")
@Slf4j
public class EventController {

    private  final EventServiceImpl eventService;

    @Autowired
    private  RestTemplate restTemplate;

    @Value("${rating_url}")
    String ratingService;

    @Autowired
    public  EventController(EventServiceImpl service){


        this.eventService=service ;
    }

    @RequestMapping("ratings/{id}")
    public EventRating getEventRatings(@PathVariable int id){

      //  RestTemplate restTemplate = new RestTemplate();
        EventRating eventRating= this.restTemplate.getForObject("http://"+ratingService+":8890/api/"+id, EventRating.class);

        assert eventRating != null;
      //  return eventRating.getRatings().get(1).getRating();
         return new EventRating(id,eventRating.getEventRating());
    }

    @GetMapping("/all")
    public  List<Event> getAllEvent(){
        return eventService.getAllEvent();

    }
    @GetMapping("/ra")
    public  String getra(){
        return "ratingService";
    }

    @PostMapping("addevent")
    public  Event createEvent(@RequestBody Event event){

        return eventService.createEvent(event);
    }


    @PutMapping("{id}")
    public  Event updateEvent( @PathVariable  Integer id,@RequestBody Event event){
        return  eventService.updateEvent(id, event);
    }


    @DeleteMapping("{id}")
   public  void deleteEvent(@PathVariable Integer id){
         eventService.deleteEvent(id);
    }


    @PostMapping("/eventing/{eventName}")
    public  void assignInfoToEvent( @PathVariable  String eventName , @RequestBody  EventInfo eventInfo){
          eventService.assignInfoToEvent(eventName, eventInfo);
    }
}
