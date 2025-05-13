package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosieradzki.siitask.model.Event;
import pl.kosieradzki.siitask.repo.EventRepo;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventRepo eventRepo;

    public EventController(EventRepo eventRepo) {
        this.eventRepo = eventRepo;
    }

    @PostMapping("/new")
    public Event createEvent(@RequestBody Event event) {
        return eventRepo.save(event);
    }

    @GetMapping("/report")
    public List<Event> getAll() {
        return eventRepo.findAll();
    }
}
