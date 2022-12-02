package az.nasru11a.nurbot.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "topics")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "parent_topic_id")
    Long parentTopicId;

    @Column(name = "topic", nullable = false)
    String topic;

    @OneToMany(mappedBy = "id", cascade = CascadeType.PERSIST)
    List<Question> questions;
}
