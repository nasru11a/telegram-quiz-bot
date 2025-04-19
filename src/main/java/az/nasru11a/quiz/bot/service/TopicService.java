package az.nasru11a.quiz.bot.service;

import az.nasru11a.quiz.bot.dto.TopicDto;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface TopicService {

    void create(TopicDto dto);

//    EditMessageText executeControlButton(Update update);

    void createBulkTopics(List<TopicDto> topicDtoList);
    SendMessage getParentTopics(Update update);
    EditMessageText getChildTopicsOfParentTopic(Update update);
    EditMessageText returnParentTopics(Update update);
    EditMessageText getNextChildTopics(Update update);
    EditMessageText getPreviousChildTopics(Update update);
    Boolean isLastPage(Update update);
    Boolean isFirstPage(Update update);
    AnswerCallbackQuery announceLastPageMessage(Update update);
    AnswerCallbackQuery announceFirstPageMessage(Update update);
}
