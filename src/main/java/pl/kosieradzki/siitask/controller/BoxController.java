package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosieradzki.siitask.model.Box;
import pl.kosieradzki.siitask.model.Donation;
import pl.kosieradzki.siitask.model.Event;
import pl.kosieradzki.siitask.repo.BoxRepo;
import pl.kosieradzki.siitask.repo.DonationRepo;
import pl.kosieradzki.siitask.repo.EventRepo;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/boxes")
public class BoxController {
    private final BoxRepo boxRepo;
    private final EventRepo eventRepo;
    private final DonationRepo donationRepo;

    public BoxController(BoxRepo boxRepo, EventRepo eventRepo, DonationRepo donationRepo) {
        this.boxRepo = boxRepo;
        this.eventRepo = eventRepo;
        this.donationRepo = donationRepo;
    }

    @PostMapping("/new")
    public Box createBox(@RequestBody Box box) {
        return boxRepo.save(box);
    }

    @PostMapping("/{boxId}/new-donation")
    public Donation createBox(@RequestBody Donation donation, @PathVariable int boxId) {
        Box box = boxRepo.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found"));
        donation.setBox(box);
        return donationRepo.save(donation);
    }

    @GetMapping
    public List<Box> getAll() {
        return boxRepo.findAll();
    }

    @PutMapping("/{boxId}/assign-event/{eventId}")
    public boolean assignEventToBox(@PathVariable int boxId, @PathVariable long eventId) {
        Box box = boxRepo.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found"));
        if (!box.getBoxAmount().equals(BigDecimal.ZERO)) {
            Event event = eventRepo.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
            box.setEvent(event);
            boxRepo.save(box);
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/{boxId}/unregister")
    public Box unregisterBox(@PathVariable int boxId) {
        Box box = boxRepo.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found"));
        box.setBoxAmount(BigDecimal.ZERO);
        donationRepo.deleteAllByBox(box);
        return boxRepo.save(box);
    }

    @DeleteMapping("/{boxId}")
    public void deleteBox(@PathVariable int boxId) {
        boxRepo.deleteById(boxId);
    }

}
