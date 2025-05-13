package pl.kosieradzki.siitask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosieradzki.siitask.model.Donation;

public interface DonationRepo extends JpaRepository<Donation, Integer> {
}
