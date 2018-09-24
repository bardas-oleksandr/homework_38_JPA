package ua.levelup.repository;

import org.springframework.data.repository.CrudRepository;
import ua.levelup.domain.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
}
