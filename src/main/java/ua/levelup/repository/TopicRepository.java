package ua.levelup.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.levelup.domain.Topic;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    @Query("select distinct t from Topic t left join fetch t.questionList")
    List<Topic> findAllWithQuestions();
}
