package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.domain.Question;
import az.nasru11a.nurbot.domain.Topic;
import az.nasru11a.nurbot.dto.TopicDto;
import az.nasru11a.nurbot.repository.QuestionRepository;
import az.nasru11a.nurbot.repository.TopicRepository;
import az.nasru11a.nurbot.service.TopicService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static az.nasru11a.nurbot.domain.enumaration.NavigationConstants.*;
import static az.nasru11a.nurbot.domain.enumaration.TopicConstants.CHILD_TOPIC_MARK;
import static az.nasru11a.nurbot.domain.enumaration.TopicConstants.PARENT_TOPIC_MARK;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final ModelMapper mapper;
    private final TopicRepository topicRepository;
    private final QuestionRepository questionRepository;

    private final static int DEFAULT_PAGE_SIZE = 5;
    private final static int DEFAULT_PAGE_NUMBER = 0;
    private static final String DELIMENTER = " -> ";
    @Getter
    private static final String SEPARATOR = "_";
    private static final String EMPTY_REPLACEMENT_STRING = "";
    @Getter
    private static final String TOPIC_MESSAGE = "Aşağıdaki bölmələrdən birini seçin.";
    private static final String LAST_PAGE_MESSAGE = "Seçdiyiniz bölmə üzrə mövcud olan mözular yekunlaşır." +
            "\nTapmaq istədiyiniz mövzunu digər bölmələrdə axtara bilərsiniz";
    private static final String FIRST_PAGE_MESSAGE = "Birinci hissədəsiniz, mövzuların digər hissələrinə nəzər yetirmək istəyirsinizsə > (növbəti) düyməsini seçin.";

    public SendMessage getParentTopics(Update update) {
        List<Topic> topicList = topicRepository.findAll().stream()
                .filter(topic -> topic.getParentTopicId() == null)
                .toList();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        prepareInlineParentTopicsKeyboard(inlineKeyboardMarkup, topicList);

        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(TOPIC_MESSAGE)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    public EditMessageText getChildTopicsOfParentTopic(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        String callbackData = update.getCallbackQuery().getData();
        Long parentTopicId = Long.parseLong(callbackData.replace(PARENT_TOPIC_MARK.getText(), EMPTY_REPLACEMENT_STRING));
        Pageable pageable = PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
        List<Topic> topicList = topicRepository.findByParentTopicId(parentTopicId, pageable).orElseThrow();
        StringBuilder childTopicsMessage = new StringBuilder();
        prepareChildTopicListMessage(childTopicsMessage, topicList, DEFAULT_PAGE_NUMBER);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        prepareInlineChildTopicsKeyboard(inlineKeyboardMarkup, topicList, parentTopicId, DEFAULT_PAGE_NUMBER);
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(childTopicsMessage.toString())
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    public EditMessageText returnParentTopics(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        List<Topic> topicList = topicRepository.findAll().stream()
                .filter(topic -> topic.getParentTopicId() == null)
                .toList();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        prepareInlineParentTopicsKeyboard(inlineKeyboardMarkup, topicList);
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(TOPIC_MESSAGE)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    public EditMessageText getNextChildTopics(Update update) {
        StringBuilder nextMessageText = new StringBuilder();
        InlineKeyboardMarkup currentInlineKeyboardMarkup = new InlineKeyboardMarkup();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        String callbackData = update.getCallbackQuery().getData();
        Integer currentPageNo = Integer.parseInt(getParsedString(callbackData, "pageNumber"));
        Long parentTopicId = Long.parseLong(getParsedString(callbackData, "parentTopicId"));
        Pageable pageable = PageRequest.of(currentPageNo, DEFAULT_PAGE_SIZE);
        pageable = pageable.next();
        List<Topic> topicList = topicRepository.findByParentTopicId(parentTopicId, pageable)
                .orElseThrow();
        prepareChildTopicListMessage(nextMessageText, topicList, currentPageNo + 1);
        prepareInlineChildTopicsKeyboard(currentInlineKeyboardMarkup, topicList, parentTopicId, currentPageNo + 1);
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(nextMessageText.toString())
                .replyMarkup(currentInlineKeyboardMarkup)
                .build();
    }

    @Override
    public EditMessageText getPreviousChildTopics(Update update) {
        StringBuilder nextMessageText = new StringBuilder();
        InlineKeyboardMarkup currentInlineKeyboardMarkup = new InlineKeyboardMarkup();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        String callbackData = update.getCallbackQuery().getData();
        Integer currentPageNo = Integer.parseInt(getParsedString(callbackData, "pageNumber"));
        Long parentTopicId = Long.parseLong(getParsedString(callbackData, "parentTopicId"));
        Pageable pageable = PageRequest.of(currentPageNo, DEFAULT_PAGE_SIZE);
        pageable = pageable.previousOrFirst();
        List<Topic> topicList = topicRepository.findByParentTopicId(parentTopicId, pageable)
                .orElseThrow();
        prepareChildTopicListMessage(nextMessageText, topicList, currentPageNo - 1);
        prepareInlineChildTopicsKeyboard(currentInlineKeyboardMarkup, topicList, parentTopicId, currentPageNo - 1);
        return EditMessageText.builder()
                .chatId(chatId)
                .messageId(messageId)
                .text(nextMessageText.toString())
                .replyMarkup(currentInlineKeyboardMarkup)
                .build();
    }

    @Override
    public Boolean isFirstPage(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        Integer currentPageNo = Integer.parseInt(getParsedString(callbackData, "pageNumber"));
        return currentPageNo == DEFAULT_PAGE_NUMBER;
    }

    @Override
    public Boolean isLastPage(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        Integer currentPageNo = Integer.parseInt(getParsedString(callbackData, "pageNumber"));
        Long parentTopicId = Long.parseLong(getParsedString(callbackData, "parentTopicId"));
        Pageable pageable = PageRequest.of(currentPageNo + 1, DEFAULT_PAGE_SIZE);
        return topicRepository.findByParentTopicId(parentTopicId, pageable).get().size() == DEFAULT_PAGE_NUMBER;
    }

    @Override
    public AnswerCallbackQuery announceLastPageMessage(Update update) {
        String callbackQueryId = update.getCallbackQuery().getId();
        return AnswerCallbackQuery.builder()
                .callbackQueryId(callbackQueryId)
                .text(LAST_PAGE_MESSAGE)
                .showAlert(true)
                .build();
    }

    @Override
    public AnswerCallbackQuery announceFirstPageMessage(Update update) {
        String callbackQueryId = update.getCallbackQuery().getId();
        return AnswerCallbackQuery.builder()
                .callbackQueryId(callbackQueryId)
                .text(FIRST_PAGE_MESSAGE)
                .showAlert(true)
                .build();
    }

    @Override
    public void create(TopicDto dto) {
        if (!doesTopicAlreadyExist(dto.getTopic())) {
            Topic topic = mapper.map(dto, Topic.class);
            String topicFullName = prepareTopicFullName(dto);
            topic.setTopicFullName(topicFullName);
            log.info("Topic full name is " + topicFullName);
            topicRepository.save(topic);
        } else {
            System.out.println("Topic " + dto.getTopic() + " already exists.");
        }
    }

    @Override
    public void createBulkTopics(List<TopicDto> topicDtoList) {
        List<Topic> topicList = topicDtoList.stream()
                .map(topicDto -> mapper.map(topicDto, Topic.class))
                .toList();
        topicRepository.saveAll(topicList);
    }

    private StringBuilder prepareChildTopicListMessage(StringBuilder childTopicList, List<Topic> topics, Integer pageNo) {
        int rowNum = pageNo * 5 + 1;
        for(Topic topic: topics) {
            childTopicList
                    .append(rowNum++)
                    .append(". ")
                    .append(topic.getTopic())
                    .append("\n");
        }
        return childTopicList;
    }

    private String prepareTopicFullName(TopicDto dto) {
        if (dto.getParentTopicId() != null && topicRepository.findById(dto.getParentTopicId()).isPresent()) {
            return topicRepository.findById(dto.getParentTopicId()).get()
                    .getTopicFullName()
                    .concat(DELIMENTER)
                    .concat(dto.getTopic());
        } else {
            return dto.getTopic();
        }
    }

    private void prepareInlineParentTopicsKeyboard(InlineKeyboardMarkup keyboardMarkup, List<Topic> topicList) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for(Topic topic:topicList) {
            String topicName = topic.getTopic();
            String callbackData = PARENT_TOPIC_MARK.getText().concat(topic.getId().toString());
            List<InlineKeyboardButton> buttons = new ArrayList<>();
            InlineKeyboardButton button = InlineKeyboardButton.builder()
                    .text(topicName)
                    .callbackData(callbackData)
                    .build();
            buttons.add(button);
            keyboard.add(buttons);
        }
        keyboardMarkup.setKeyboard(keyboard);
    }

    private void prepareInlineChildTopicsKeyboard(InlineKeyboardMarkup keyboardMarkup,
                                                  List<Topic> topicList,
                                                  Long parentTopicId,
                                                  Integer pageNo) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> topicButtons = new ArrayList<>();
        int rowNum = pageNo * DEFAULT_PAGE_SIZE + 1;
        for(Topic topic:topicList) {
            String buttonText = String.valueOf(rowNum);
            String callbackData = CHILD_TOPIC_MARK.getText().concat(topic.getId().toString());
            topicButtons.add(createKeyboardButton(buttonText, callbackData));
            rowNum++;
        }
        keyboard.add(topicButtons);
        addControlButtons(keyboard, parentTopicId, pageNo);
        keyboardMarkup.setKeyboard(keyboard);
    }

    private void addControlButtons(List<List<InlineKeyboardButton>> keyboard, Long parentTopicId, Integer pageNumber){
        List<InlineKeyboardButton> controlButtons = new ArrayList<>();
        controlButtons.add(createKeyboardButton(NAVIGATION_BUTTON_PREVIOUS.getText(), NAVIGATION_BUTTON.getText()
                .concat(SEPARATOR)
                .concat(NAVIGATION_BUTTON_PREVIOUS.getText())
                .concat(SEPARATOR)
                .concat(parentTopicId.toString())
                .concat(SEPARATOR)
                .concat(pageNumber.toString())));
        controlButtons.add(createKeyboardButton(NAVIGATION_BUTTON_MAIN.getText(), NAVIGATION_BUTTON.getText()
                .concat(SEPARATOR)
                .concat(NAVIGATION_BUTTON_MAIN.getText())
                .concat(SEPARATOR)
                .concat(parentTopicId.toString())));
        controlButtons.add(createKeyboardButton(NAVIGATION_BUTTON_NEXT.getText(), NAVIGATION_BUTTON.getText()
                .concat(SEPARATOR)
                .concat(NAVIGATION_BUTTON_NEXT.getText())
                .concat(SEPARATOR)
                .concat(parentTopicId.toString())
                .concat(SEPARATOR)
                .concat(pageNumber.toString())));
        keyboard.add(controlButtons);
    }

    private InlineKeyboardButton createKeyboardButton(String text, String callbackData) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }

    private Boolean doesTopicAlreadyExist(String topic) {
        log.info("Topic is " + topic);
        Optional<Topic> topicObj = topicRepository.findTopicByTopic(topic);
        return topicObj.isPresent();
    }

    private String getParsedString(String callbackData, String requiredKey){
        HashMap<String, String> callbackMap = new HashMap<>();
        List<String> keys = List.of("buttonText", "direction", "parentTopicId", "pageNumber");
        List<String> values = List.of(callbackData.split(SEPARATOR));
        int valueIndex = 0;
        for(String key:keys) {
            callbackMap.put(key, values.get(valueIndex++));
        }

        return callbackMap.get(requiredKey);
    }
}
