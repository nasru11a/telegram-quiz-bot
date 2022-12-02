package az.nasru11a.nurbot.dto;

import az.nasru11a.nurbot.domain.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicDto {

    @NotNull
    Long parentTopicId;

    @NotNull
    String topic;

    List<Question> questions;
}
