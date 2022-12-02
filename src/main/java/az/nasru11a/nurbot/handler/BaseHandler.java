package az.nasru11a.nurbot.handler;

import az.nasru11a.nurbot.config.BotConfig;
import az.nasru11a.nurbot.dto.UserDto;
import az.nasru11a.nurbot.service.TopicService;
import az.nasru11a.nurbot.service.impl.BotServiceImpl;
import az.nasru11a.nurbot.service.impl.QuestionServiceImpl;
import az.nasru11a.nurbot.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class BaseHandler extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final BotServiceImpl botService;
    private final QuestionServiceImpl questionService;
    private final TopicService topicService;
    private final UserServiceImpl userService;

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        log.info("Update received!");
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            String username = update.getMessage().getChat().getUserName();
            String firstName = update.getMessage().getChat().getFirstName();
            String lastName = update.getMessage().getChat().getLastName();
            UserDto userDto = generateUserDto(username, firstName, lastName);

            log.info("Message is " + userMessage);

            switch (userMessage) {
                case "/start":
                    execute(botService.generateStartMessage(update));
                    userService.register(userDto);
                    break;
                case "Mənə sual ver":
                    execute(questionService.generatePoll(update));
                    break;
                case "Mövzular üzrə":
                    execute(topicService.sendTopicsList(update));
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    private UserDto generateUserDto(String username, String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return UserDto.builder()
                .username(username)
                .fullName(fullName)
                .build();
    }

}
