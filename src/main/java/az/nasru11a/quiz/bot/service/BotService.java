package az.nasru11a.quiz.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotService{
    SendMessage sendStartMessage(Update update);
}
