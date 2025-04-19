package az.nasru11a.quiz.bot.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDto {

    Long id;

    @NotNull
    String username;

    String fullName;
}
