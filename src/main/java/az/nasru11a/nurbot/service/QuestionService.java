package az.nasru11a.nurbot.service;

import az.nasru11a.nurbot.dto.QuestionDto;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface QuestionService {
    QuestionDto createQuestion(QuestionDto dto);
    SendPoll generatePoll(Update update);
    Boolean topicHasQuestions(Update update);
    EditMessageText sendPreviewMessage(Update update);
    SendPoll prepareAndSendQuiz(Update update);
    DeleteMessage deleteMessage(Update update);
    DeleteMessage endQuiz(Update update);
    SendPoll getNextQuestion(Update update);
    EditMessageReplyMarkup removeReplyMarkup(Update update);

    Boolean lastQuestion(Update update);
}
