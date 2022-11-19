package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.config.BotConfig;
import az.nasru11a.nurbot.dto.UserDto;
import az.nasru11a.nurbot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl extends TelegramLongPollingBot implements BotService {

    private final BotConfig botConfig;
    private final UserServiceImpl userService;

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        log.info("Update received!");
        if (update.hasMessage() && update.getMessage().hasText()) {
            String username = update.getMessage().getChat().getUserName();
            String firstName = update.getMessage().getChat().getFirstName();
            String lastName = update.getMessage().getChat().getLastName();
            String userMessage = update.getMessage().getText();
            log.info("Message is " + userMessage);

            UserDto userDto = generateUserDto(username, firstName, lastName);

            switch (userMessage) {
                case "/start":
                    userService.register(userDto);
                    break;
            }
        }
    }

    private UserDto generateUserDto(String username, String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return UserDto.builder()
                .username(username)
                .fullName(fullName)
                .build();
    }
}
