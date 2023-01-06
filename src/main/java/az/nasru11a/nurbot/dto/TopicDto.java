package az.nasru11a.nurbot.dto;

import az.nasru11a.nurbot.domain.Question;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    Long id;

    @NotNull
    Long parentTopicId;

    @NotNull
    String topic;

    List<Question> questions;

}
