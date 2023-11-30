package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class TelegramBotService {
    private final TelegramBot telegramBot;

    public TelegramBotService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendNotification (Long chatId, String notificationText) {
        SendMessage sendMessage = new SendMessage(chatId, notificationText);
        telegramBot.execute(sendMessage);
    }
}
