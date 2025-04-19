package az.nasru11a.quiz.bot.service.impl;

import az.nasru11a.quiz.bot.domain.User;
import az.nasru11a.quiz.bot.dto.UserDto;
import az.nasru11a.quiz.bot.repository.UserRepository;
import az.nasru11a.quiz.bot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Override
    public void register(UserDto userDto) {
        if (!checkIfUserAlreadyRegistered(userDto.getUsername())) {
            log.info("User " + userDto.getUsername() + " is registered");
            User user = mapper.map(userDto, User.class);
            userRepository.save(user);
        }
    }

    private Boolean checkIfUserAlreadyRegistered(String username) {
        return userRepository.getUserByUsername(username).isPresent();
    }
}
