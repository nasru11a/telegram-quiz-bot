package az.nasru11a.quiz.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface BotUtils {
    InlineKeyboardButton createKeyboardButton(String text, String callbackData);
}
