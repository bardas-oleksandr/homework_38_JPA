package ua.levelup.repository;

import org.springframework.data.repository.CrudRepository;
import ua.levelup.domain.Topic;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
}
