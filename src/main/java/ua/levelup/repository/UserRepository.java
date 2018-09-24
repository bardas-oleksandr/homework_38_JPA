package ua.levelup.repository;

import org.springframework.data.repository.CrudRepository;
import ua.levelup.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
}
