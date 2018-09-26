package ua.levelup.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.levelup.domain.Question;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query("select distinct q from Question q left join fetch q.answerList where q.id = :id")
    Question findWithAnswers(@Param("id") int id);
}