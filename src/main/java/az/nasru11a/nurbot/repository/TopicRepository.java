package az.nasru11a.nurbot.repository;

import az.nasru11a.nurbot.domain.Topic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findTopicByTopic(String topic);
    @Query("select t.id from Topic t where t.topic=:topic")
    Optional<Long> getIdByTopic(@Param("topic") String topic);
    @Query("select t from Topic t where t.topicFullName like :topic%")
    Optional<List<Topic>> getChildTopicsByTopicName(@Param("topic") String topic, Pageable pageable);
    Optional<List<Topic>> findByParentTopicId(Long parentTopicId, Pageable pageable);
}
