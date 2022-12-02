package az.nasru11a.nurbot.service;

import az.nasru11a.nurbot.dto.QuestionDto;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.groupadministration.SetChatPhoto;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public interface QuestionService {
    QuestionDto createQuestion(QuestionDto dto);

    SendPoll generatePoll(Update update);
}
