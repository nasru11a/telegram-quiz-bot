package az.nasru11a.nurbot.controller;

import az.nasru11a.nurbot.dto.UserDto;
import az.nasru11a.nurbot.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    public void register(UserDto userDto) {
        userService.register(userDto);
    }

}
