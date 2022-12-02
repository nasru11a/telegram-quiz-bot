package az.nasru11a.nurbot.domain;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@Table(name = "questions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;

    @Column(name = "question", nullable = false)
    String question;

    @Type(type = "list-array")
    @Column(name = "options", columnDefinition = "varchar[]", nullable = false)
    List<String> options;

    @Column(name = "correct_option_id" , nullable = false)
    Integer correctOptionId;

    @Column(name = "explanation", nullable = false)
    String explanation;
}
