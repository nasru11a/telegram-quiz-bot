package az.nasru11a.nurbot.repository;

import az.nasru11a.nurbot.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Override
    Optional<Question> findById(Long id);

    Optional<Question> findByQuestion(String question);

    @Query(nativeQuery = true, value = "select * from questions order by random() limit 1")
    Optional<Question> getRandomQuestion();
}
