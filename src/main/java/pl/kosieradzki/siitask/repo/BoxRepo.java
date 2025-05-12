package pl.kosieradzki.siitask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosieradzki.siitask.model.Box;

public interface BoxRepo extends JpaRepository<Box, Integer> {
}
