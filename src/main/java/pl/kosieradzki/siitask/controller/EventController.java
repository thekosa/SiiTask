package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosieradzki.siitask.dto.EventReportDto;
import pl.kosieradzki.siitask.model.Event;
import pl.kosieradzki.siitask.repo.EventRepo;

import java.util.ArrayList;
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
    public List<EventReportDto> getAll() {
        List<Event> allEvents = eventRepo.findAll();
        List<EventReportDto> allEventsDto = new ArrayList<>();

        for (Event event : allEvents) {
            allEventsDto.add(new EventReportDto(
                    event.getName(),
                    event.getAccountAmount(),
                    event.getCurrency().toString()
            ));
        }

        return allEventsDto;
    }
}
