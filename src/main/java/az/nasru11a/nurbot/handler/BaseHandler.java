package az.nasru11a.nurbot.handler;

import az.nasru11a.nurbot.config.BotConfig;
import az.nasru11a.nurbot.dto.UserDto;
import az.nasru11a.nurbot.service.TopicService;
import az.nasru11a.nurbot.service.impl.BotServiceImpl;
import az.nasru11a.nurbot.service.impl.QuestionServiceImpl;
import az.nasru11a.nurbot.service.impl.TopicServiceImpl;
import az.nasru11a.nurbot.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static az.nasru11a.nurbot.domain.enumaration.NavigationConstants.*;
import static az.nasru11a.nurbot.domain.enumaration.QuestionConstants.*;
import static az.nasru11a.nurbot.domain.enumaration.TopicConstants.CHILD_TOPIC_MARK;
import static az.nasru11a.nurbot.domain.enumaration.TopicConstants.PARENT_TOPIC_MARK;

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
            handleMessage(update);
        } else if (update.hasCallbackQuery()) {
            handleCallbackQuery(update);
        }
    }

    @SneakyThrows
    private void handleMessage(Update update) {
        String userMessage = update.getMessage().getText();
        String username = update.getMessage().getChat().getUserName();
        String firstName = update.getMessage().getChat().getFirstName();
        String lastName = update.getMessage().getChat().getLastName();
        UserDto userDto = generateUserDto(username, firstName, lastName);
        log.info("Message is " + userMessage);

        switch (userMessage) {
            case "/start":
                execute(botService.sendStartMessage(update));
                break;
        }
    }

    @SneakyThrows
    private void handleCallbackQuery(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        String parentTopicMark = PARENT_TOPIC_MARK.getText();
        String childTopicMark = CHILD_TOPIC_MARK.getText();
        String nextButtonText = NAVIGATION_BUTTON.getText().concat(TopicServiceImpl.getSEPARATOR()).concat(NAVIGATION_BUTTON_NEXT.getText());
        String previousButtonText = NAVIGATION_BUTTON.getText().concat(TopicServiceImpl.getSEPARATOR()).concat(NAVIGATION_BUTTON_PREVIOUS.getText());
        String mainButtonText = NAVIGATION_BUTTON.getText().concat(TopicServiceImpl.getSEPARATOR()).concat(NAVIGATION_BUTTON_MAIN.getText());
        String startQuiz = START_CDATA.getText();
        String endQuiz = END_CDATA.getText();
        String nextQuestion = NEXT_CDATA.getText();

        if (callbackData.contains(parentTopicMark)) {
            execute(topicService.getChildTopicsOfParentTopic(update));
        } else if (callbackData.startsWith(nextButtonText)) {
            log.info("Next button is pressed: ");
            if (!topicService.isLastPage(update)) {
                log.info("Page number is validated: ");
                execute(topicService.getNextChildTopics(update));
            } else {
                execute(topicService.announceLastPageMessage(update));
            }
        } else if (callbackData.startsWith(previousButtonText)) {
            log.info("Previous button is pressed: ");
            if (!topicService.isFirstPage(update)) {
                log.info("Page number is validated: ");
                execute(topicService.getPreviousChildTopics(update));
            } else {
                execute(topicService.announceFirstPageMessage(update));
            }
        } else if (callbackData.startsWith(mainButtonText)) {
            log.info("Main button is pressed: ");
            execute(topicService.returnParentTopics(update));
        } else if (callbackData.startsWith(childTopicMark)) {
            log.info("Child topic is selected");
            if(questionService.topicHasQuestions(update)) {
                log.info("Questions exists");
                execute(questionService.sendPreviewMessage(update));
            }
        } else if (callbackData.startsWith(startQuiz)) {
            log.info("Quiz is requested.");
            execute(questionService.deleteMessage(update));
            execute(questionService.prepareAndSendQuiz(update));
        } else if (callbackData.startsWith(endQuiz)) {
            execute(questionService.endQuiz(update));
            log.info("Ending quiz.");
        } else if (callbackData.contains(nextQuestion)) {
            log.info("Next question is loading...");
            execute(questionService.removeReplyMarkup(update));
            if(!questionService.lastQuestion(update)) {
                execute(questionService.getNextQuestion(update));
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
