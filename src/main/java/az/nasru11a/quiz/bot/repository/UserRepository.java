package az.nasru11a.quiz.bot.repository;

import az.nasru11a.quiz.bot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<Boolean> getUserByUsername(String username);
}
