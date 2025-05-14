package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.*;
import pl.kosieradzki.siitask.model.Box;
import pl.kosieradzki.siitask.model.Donation;
import pl.kosieradzki.siitask.repo.BoxRepo;
import pl.kosieradzki.siitask.repo.DonationRepo;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {
    private final DonationRepo donationRepo;
    private final BoxRepo boxRepo;

    public DonationController(DonationRepo donationRepo, BoxRepo boxRepo) {
        this.donationRepo = donationRepo;
        this.boxRepo = boxRepo;
    }

    @GetMapping
    public List<Donation> getDonations() {
        return donationRepo.findAll();
    }

    @GetMapping("/{boxId}")
    public List<Donation> getDonationsByBoxId(@PathVariable int boxId) {
        return donationRepo.getDonationsByBoxId(boxId);
    }

    @PostMapping("/{boxId}/new")
    public Donation createDonation(@PathVariable int boxId,@RequestBody Donation donation) {
        Box box = boxRepo.findById(boxId).orElseThrow(() -> new RuntimeException("Box not found"));
        donation.setBox(box);
        return donationRepo.save(donation);
    }
}