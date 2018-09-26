package ua.levelup.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.levelup.domain.Attempt;
import ua.levelup.domain.User;

import java.util.List;

public interface AttemptRepository extends CrudRepository<Attempt, Integer> {
    List<Attempt> findByUser(User user);

    @Query("select distinct a from Attempt a left join fetch a.givenAnswerList where a.user = :user")
    List<Attempt> findByUserWithAnswers(@Param("user")User user);
}