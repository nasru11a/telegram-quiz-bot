package az.nasru11a.nurbot.domain;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "topic", nullable = false)
    String topic;

    @Column(name = "type", nullable = false)
    String type;

    @Column(name = "question", nullable = false)
    String question;

    @Type(type = "list-array")
    @Column(name = "options", columnDefinition = "varchar[]", nullable = false)
    List<String> options;

    @Type(type = "list-array")
    @Column(name = "correct_options_ids", columnDefinition = "bigint[]", nullable = false)
    List<Long> correctOptionsIds;

    @Column(name = "explanation", nullable = false)
    String explanation;
}
