package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosieradzki.siitask.repo.DonationRepo;

@RestController
@RequestMapping("/donations")
public class DonationController {
    private final DonationRepo donationRepo;

    public DonationController(DonationRepo donationRepo) {
        this.donationRepo = donationRepo;
    }
}