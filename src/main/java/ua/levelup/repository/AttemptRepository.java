package ua.levelup.repository;

import org.springframework.data.repository.CrudRepository;
import ua.levelup.domain.Attempt;
import ua.levelup.domain.User;

import java.util.List;

public interface AttemptRepository extends CrudRepository<Attempt, Integer> {
    List<Attempt> findByUser(User user);
    Attempt findLastByUser(User user);
}
