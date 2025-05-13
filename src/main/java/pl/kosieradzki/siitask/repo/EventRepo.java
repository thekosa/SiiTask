package pl.kosieradzki.siitask.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.kosieradzki.siitask.model.Event;

public interface EventRepo extends JpaRepository<Event, Integer> {
}