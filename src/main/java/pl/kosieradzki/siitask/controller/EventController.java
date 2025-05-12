package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosieradzki.siitask.model.Event;
import pl.kosieradzki.siitask.repo.EventRepo;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    private final EventRepo repository;

    public EventController(EventRepo repository) {
        this.repository = repository;
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return repository.save(event);
    }

    @GetMapping
    public List<Event> getAll() {
        return repository.findAll();
    }
}
