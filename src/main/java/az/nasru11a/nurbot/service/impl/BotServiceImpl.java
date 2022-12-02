package az.nasru11a.nurbot.service.impl;

import az.nasru11a.nurbot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final String START_MESSAGE = "Salam. Mən Nur. Sənə suallar verib imtahana hazırlaşmaqda kömək edəcəm";
    private final String BUTTON_1 = "Mənə sual ver";
    private final String BUTTON_2 = "Mövzular üzrə";

    @Override
    public SendMessage generateStartMessage(Update update) {
        return SendMessage.builder()
                .chatId(update.getMessage().getChatId())
                .text(START_MESSAGE)
                .replyMarkup(returnInitialKeyboardMarkup())
                .build();
    }

    private ReplyKeyboardMarkup returnInitialKeyboardMarkup() {
        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow row;

        row = new KeyboardRow();
        row.add(BUTTON_1);
        row.add(BUTTON_2);
        keyboardRowList.add(row);

        return ReplyKeyboardMarkup.builder()
                .keyboard(keyboardRowList)
                .resizeKeyboard(true)
                .build();
    }

}
