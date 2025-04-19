package az.nasru11a.quiz.bot.dto;

import az.nasru11a.quiz.bot.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    Long id;

    @NotNull
    Topic topic;

    @NotNull
    String question;

    @NotNull
    List<String> options;

    @NotNull
    Integer correctOptionId;

    @NotNull
    String explanation;
}
