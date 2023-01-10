package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.domain.Question;
import az.nasru11a.nurbot.dto.QuestionDto;
import az.nasru11a.nurbot.repository.QuestionRepository;
import az.nasru11a.nurbot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static az.nasru11a.nurbot.domain.enumaration.QuestionConstants.*;
import static az.nasru11a.nurbot.domain.enumaration.TopicConstants.CHILD_TOPIC_MARK;
import static az.nasru11a.nurbot.domain.enumaration.TopicConstants.EMPTY_REPLACEMENT_STRING;


@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final ModelMapper mapper;
    private final QuestionRepository questionRepository;
    private final BotUtilsImpl botUtils;

    @Override
    public QuestionDto createQuestion(QuestionDto dto) {
        Question question = mapper.map(dto, Question.class);
        if (!doesQuestionAlreadyExist(dto.getQuestion())) {
            questionRepository.save(question);
        } else {
            System.out.println("Question is already exist.");
        }
        return mapper.map(question, QuestionDto.class);
    }

    @Override
    public SendPoll generatePoll(Update update) {
        Question question = questionRepository.getRandomQuestion().get();
        return SendPoll.builder()
                .chatId(update.getMessage().getChatId())
                .type("quiz")
                .question(question.getQuestion())
                .options(question.getOptions())
                .correctOptionId(question.getCorrectOptionId())
                .explanation(question.getExplanation())
                .protectContent(true)
                .build();
    }

    @Override
    public Boolean topicHasQuestions(Update update) {
        String callbackData = update.getCallbackQuery().getData();
        Long childTopicId = Long.parseLong(callbackData.replace(CHILD_TOPIC_MARK.getText(), EMPTY_REPLACEMENT_STRING.getText()));
        Boolean result;
        log.info("Does topic has questions?: ");
        result = questionRepository.findQuestionsByTopicId(childTopicId).get().size() != 0;
        return result;
    }

    @Override
    public EditMessageText sendPreviewMessage(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        String callbackData = update.getCallbackQuery().getData();
        Long childTopicId = Long.parseLong(callbackData.replace(CHILD_TOPIC_MARK.getText(), EMPTY_REPLACEMENT_STRING.getText()));
        Integer numOfQuestions = questionRepository.findQuestionsByTopicId(childTopicId).get().size();
        String textMessage = "Seçdiyiniz mövzu üzrə " + numOfQuestions + " sual tapıldı. \n" +
                "Suallara baxmaq üçün \"Başla\" düyməsini seçin.";
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        prepareInlineKeyboard(inlineKeyboardMarkup, childTopicId);
        return EditMessageText.builder()
                .messageId(messageId)
                .chatId(chatId)
                .text(textMessage)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    public SendPoll prepareAndSendQuiz(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String callbackData = update.getCallbackQuery().getData();
        Long topicId = Long.parseLong(callbackData.replace(START_CDATA.getText(), EMPTY_REPLACEMENT_STRING.getText()));
        Integer page = 0;
        Pageable pageable = PageRequest.of(page,1);
        Question question = questionRepository.findQuestionsByTopicId(topicId, pageable).get().get(0);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        prepareNavigationKeyboard(inlineKeyboardMarkup, topicId, page);
        return SendPoll.builder()
                .chatId(chatId)
                .type("quiz")
                .question(question.getQuestion())
                .options(question.getOptions())
                .correctOptionId(question.getCorrectOptionId())
                .explanation(question.getExplanation())
                .protectContent(true)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    public SendPoll getNextQuestion(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String callbackData = update.getCallbackQuery().getData();
        Integer page = Integer.parseInt(callbackData.substring(0, callbackData.indexOf(NEXT_CDATA.getText())));
        Long topicId = Long.parseLong(callbackData.substring(callbackData.indexOf(NEXT_CDATA.getText()))
                .replace(NEXT_CDATA.getText(), EMPTY_REPLACEMENT_STRING.getText()));
        Pageable pageable = PageRequest.of(page,1);
        Question question = questionRepository.findQuestionsByTopicId(topicId, pageable.next()).get().get(0);
        page = page + 1;
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        prepareNavigationKeyboard(inlineKeyboardMarkup, topicId, page);
        return SendPoll.builder()
                .chatId(chatId)
                .type("quiz")
                .question(question.getQuestion())
                .options(question.getOptions())
                .correctOptionId(question.getCorrectOptionId())
                .explanation(question.getExplanation())
                .protectContent(true)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
    }

    @Override
    public EditMessageReplyMarkup removeReplyMarkup(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

        return EditMessageReplyMarkup.builder()
                .chatId(chatId)
                .messageId(messageId)
                .replyMarkup(null)
                .build();
    }

    @Override
    public Boolean lastQuestion(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String callbackData = update.getCallbackQuery().getData();
        Integer page = Integer.parseInt(callbackData.substring(0, callbackData.indexOf(NEXT_CDATA.getText())));
        Long topicId = Long.parseLong(callbackData.substring(callbackData.indexOf(NEXT_CDATA.getText()))
                .replace(NEXT_CDATA.getText(), EMPTY_REPLACEMENT_STRING.getText()));
        Pageable pageable = PageRequest.of(page,1);
        return questionRepository.findQuestionsByTopicId(topicId, pageable.next()).get().size() == 0;
    }


    @Override
    public DeleteMessage deleteMessage(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        return DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();
    }

    @Override
    public DeleteMessage endQuiz(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
        return DeleteMessage.builder()
                .chatId(chatId)
                .messageId(messageId)
                .build();
    }

    private void prepareNavigationKeyboard(InlineKeyboardMarkup inlineKeyboardMarkup, Long topicId, Integer page) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> topicButtons = new ArrayList<>();
        topicButtons.add(botUtils.createKeyboardButton(NEXT_QUESTION.getText(), page + NEXT_CDATA.getText() + topicId));
        keyboard.add(topicButtons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
    }

    private void prepareInlineKeyboard(InlineKeyboardMarkup inlineKeyboardMarkup, Long childTopicId) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> topicButtons = new ArrayList<>();
        topicButtons.add(botUtils.createKeyboardButton(START_QUIZ.getText(), START_CDATA.getText() + childTopicId));
        keyboard.add(topicButtons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
    }

    private Boolean doesQuestionAlreadyExist(String question) {
        return questionRepository
                .findByQuestion(question)
                .isPresent();
    }
}
