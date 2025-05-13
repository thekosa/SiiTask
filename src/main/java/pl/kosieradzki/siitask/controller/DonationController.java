package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosieradzki.siitask.model.Donation;
import pl.kosieradzki.siitask.repo.DonationRepo;

import java.util.List;

@RestController
@RequestMapping("/donations")
public class DonationController {
    private final DonationRepo donationRepo;

    public DonationController(DonationRepo donationRepo) {
        this.donationRepo = donationRepo;
    }

    @GetMapping
    public List<Donation> getDonations() {
        return donationRepo.findAll();
    }

    @GetMapping("/{boxId}")
    public List<Donation> getDonationsByBoxId(@PathVariable int boxId) {
        return donationRepo.getDonationsByBoxId(boxId);
    }
}