package az.nasru11a.nurbot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public interface BotUtils {
    InlineKeyboardButton createKeyboardButton(String text, String callbackData);
}
