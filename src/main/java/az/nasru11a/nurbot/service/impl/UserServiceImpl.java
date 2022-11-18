package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.domain.User;
import az.nasru11a.nurbot.dto.UserDto;
import az.nasru11a.nurbot.repository.UserRepository;
import az.nasru11a.nurbot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    @Override
    public void register(UserDto userDto) {
        if (!checkIfUserAlreadyRegistered(userDto.getUsername())) {
            User user = mapper.map(userDto, User.class);
            userRepository.save(user);
        }
    }

    private Boolean checkIfUserAlreadyRegistered(String username) {
        return userRepository.getUserByUsername(username).isPresent();
    }
}
