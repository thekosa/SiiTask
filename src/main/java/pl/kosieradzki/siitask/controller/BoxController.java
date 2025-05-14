package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosieradzki.siitask.model.Box;
import pl.kosieradzki.siitask.model.Donation;
import pl.kosieradzki.siitask.model.Event;
import pl.kosieradzki.siitask.repo.BoxRepo;
import pl.kosieradzki.siitask.repo.DonationRepo;
import pl.kosieradzki.siitask.repo.EventRepo;
import pl.kosieradzki.siitask.util.CurrencyConverter;

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
        boxRepo.save(box);
        return donationRepo.save(donation);
    }

    @GetMapping
    public List<Box> getAll() {
        return boxRepo.findAll();
    }

    @PutMapping("/{boxId}/assign-event/{eventId}")
    public boolean assignBoxToEvent(@PathVariable int boxId, @PathVariable int eventId) {
        Box box = boxRepo.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found"));
        if (box.isEmpty()) {
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
        List<Donation> donations = donationRepo.findAllBybox(box);
        for (Donation donation : donations) {
            donation.setIsActive(0);
            donationRepo.save(donation);
        }
        return boxRepo.save(box);
    }

    @DeleteMapping("/{boxId}")
    public void deleteBox(@PathVariable int boxId) {
        Box box = boxRepo.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found"));
        box.setIsActive(0);
        boxRepo.save(box);
    }

    @PutMapping("/transfer/{boxId}")
    public Event transferBox(@PathVariable int boxId) {
        Box box = boxRepo.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found"));
        List<Donation> donations = box.getDonations();
        Event event = box.getEvent();
        BigDecimal newAmount = event.getAccountAmount();
        for (Donation donation : donations) {
            newAmount = newAmount.add(CurrencyConverter.convert(donation.getAmount(), donation.getCurrency(), event.getCurrency()));
        }
        event.setAccountAmount(newAmount);
        box.getDonations().clear();
        boxRepo.save(box);
        return eventRepo.save(event);
    }

    @PutMapping("/{eventId}/transfer-all")
    public Event transferAllBoxes(@PathVariable int eventId) {
        Event event = eventRepo.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found"));
        List<Box> boxes = event.getBoxes();
        for (Box box : boxes) {
            transferBox(box.getId());
        }
        return event;
    }
}
