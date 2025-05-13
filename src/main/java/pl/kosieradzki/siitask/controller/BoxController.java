package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosieradzki.siitask.model.Box;
import pl.kosieradzki.siitask.model.Event;
import pl.kosieradzki.siitask.repo.BoxRepo;
import pl.kosieradzki.siitask.repo.EventRepo;

import java.util.List;

@RestController
@RequestMapping("/boxes")
public class BoxController {
    private final BoxRepo boxRepo;
    private final EventRepo eventRepo;

    public BoxController(BoxRepo boxRepo, EventRepo eventRepo) {
        this.boxRepo = boxRepo;
        this.eventRepo = eventRepo;
    }

    @PostMapping("/new")
    public Box createBox(@RequestBody Box box) {
        return boxRepo.save(box);
    }

    @GetMapping
    public List<Box> getAll() {
        return boxRepo.findAll();
    }

}
