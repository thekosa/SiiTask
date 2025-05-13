package pl.kosieradzki.siitask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosieradzki.siitask.model.Box;
import pl.kosieradzki.siitask.model.Donation;

import java.util.List;

public interface DonationRepo extends JpaRepository<Donation, Integer> {
    List<Donation> findAllBybox(Box box);

    void deleteAllByBox(Box box);
}
