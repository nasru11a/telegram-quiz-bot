package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.domain.Question;
import az.nasru11a.nurbot.dto.QuestionDto;
import az.nasru11a.nurbot.repository.QuestionRepository;
import az.nasru11a.nurbot.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final ModelMapper mapper;
    private final QuestionRepository questionRepository;

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
                .build();
    }

    Boolean doesQuestionAlreadyExist(String question) {
        return questionRepository
                .findByQuestion(question)
                .isPresent();
    }
}
