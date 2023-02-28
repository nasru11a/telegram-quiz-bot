package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static az.nasru11a.nurbot.domain.enumaration.BotConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final BotUtilsImpl botUtils;

    @Override
    public SendMessage sendStartMessage(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(START_MESSAGE.getText())
                .replyMarkup(prepareInitialKeyboard())
                .build();
    }

    private InlineKeyboardMarkup prepareInitialKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> topicButtons = new ArrayList<>();
        topicButtons.add(botUtils.createKeyboardButton(TESTS.getText(),  TESTS_CDATA.getText()));
        keyboard.add(topicButtons);
        return InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
    }
}
