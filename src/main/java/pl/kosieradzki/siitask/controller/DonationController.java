package pl.kosieradzki.siitask.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kosieradzki.siitask.repo.DonationRepo;

@RestController
@RequestMapping("/donation")
public class DonationController {
    private final DonationRepo repository;

    public DonationController(DonationRepo repository) {
        this.repository = repository;
    }
}