package az.nasru11a.nurbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotService{
    SendMessage generateStartMessage(Update update);
}
