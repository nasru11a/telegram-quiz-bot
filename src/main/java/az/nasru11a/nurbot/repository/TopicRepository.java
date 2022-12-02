package az.nasru11a.nurbot.repository;

import az.nasru11a.nurbot.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findTopicByTopic(String topic);

}
