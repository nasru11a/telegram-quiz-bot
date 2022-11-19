package az.nasru11a.nurbot.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class UserDto {

    @NotNull
    String username;

    String fullName;
}
