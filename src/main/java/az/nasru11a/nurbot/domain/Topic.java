package az.nasru11a.nurbot.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topics")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Topic implements Comparable{

    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "parent_topic_id")
    Long parentTopicId;

    @Column(name = "topic", nullable = false)
    String topic;

    @Column(name = "topic_full_name", nullable = false)
    String topicFullName;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Question> questions;

    @Override
    public int compareTo(@NotNull Object o) {
        return 0;
    }
}
