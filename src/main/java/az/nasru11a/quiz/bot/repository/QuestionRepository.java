package az.nasru11a.quiz.bot.repository;

import az.nasru11a.quiz.bot.domain.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Override
    Optional<Question> findById(Long id);

    Optional<Question> findByQuestion(String question);

    @Query(nativeQuery = true, value = "select * from questions order by random() limit 1")
    Optional<Question> getRandomQuestion();

    Optional<List<Question>> findQuestionsByTopicId(Long parentTopicId, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from questions where topic_id=:topic_id")
    Optional<List<Question>> findQuestionsByTopicId(@Param(value = "topic_id") Long parentTopicId);

}
