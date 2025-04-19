package az.nasru11a.quiz.bot.service.impl;

import az.nasru11a.quiz.bot.service.BotUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

@Service
public class BotUtilsImpl implements BotUtils {
    @Override
    public InlineKeyboardButton createKeyboardButton(String text, String callbackData) {
        return InlineKeyboardButton.builder()
                .text(text)
                .callbackData(callbackData)
                .build();
    }
}
