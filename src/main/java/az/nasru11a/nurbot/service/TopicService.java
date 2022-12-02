package az.nasru11a.nurbot.service;

import az.nasru11a.nurbot.dto.TopicDto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface TopicService {

    void create(TopicDto dto);

    void createBulkTopics(List<TopicDto> topicDtoList);

    SendMessage sendTopicsList(Update update);
}
