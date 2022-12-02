package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.domain.Topic;
import az.nasru11a.nurbot.dto.TopicDto;
import az.nasru11a.nurbot.repository.TopicRepository;
import az.nasru11a.nurbot.service.TopicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final ModelMapper mapper;
    private final TopicRepository topicRepository;

    public SendMessage sendTopicsList(Update update) {
        StringBuilder topicList = new StringBuilder();
        topicRepository.findAll()
                .forEach(topic -> {
                    int rowNum = 1;
                    topicList.append(rowNum + ". " + topic.getTopic() + "\n");
                    rowNum++;
                });
        System.out.println(topicList);
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(topicList.toString())
                .build();
    }

    @Override
    public void create(TopicDto dto) {
        Topic topic = new Topic();
        topic = mapper.map(dto, Topic.class);
        topicRepository.save(topic);

//        if (topicRepository.findTopicByTopic(dto.getTopic()).isEmpty()) {
//            topicRepository.save(mapper.map(dto, Topic.class));
//        } else {
//            System.out.println("Topic " + dto.getTopic() + " already exists.");
//        }
    }

    @Override
    public void createBulkTopics(List<TopicDto> topicDtoList) {
        List<Topic> topicList = topicDtoList.stream()
                .map(topicDto -> mapper.map(topicDto, Topic.class))
                .toList();
        topicRepository.saveAll(topicList);
    }

//    Boolean doesTopicAlreadyExist(String topic) {
//        log.info("Topic is " + topic);
//        return topicRepository
//                .findTopicByTopic(topic)
//                .isPresent();
//    }
}
