package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.Notification;
import pro.sky.telegrambot.repository.NotificationRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Pattern PATTERN = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private NotificationRepository notificationRepository;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String newMessage = update.message().text();
            Long chatId = update.message().chat().id();
            if (newMessage != null && newMessage.equals("/start")) {
                String welcomeMessage = "Приветствую! Отправь сообщение в формате 01.01.2022 20:00 Сделать домашнюю работу";
                SendMessage sendMessage = new SendMessage(chatId, welcomeMessage);
                telegramBot.execute(sendMessage);
            } else {
                Matcher matcher = PATTERN.matcher(newMessage);
                if (matcher.matches()) {
                    String dataTime = matcher.group(1);
                    String notificationText = matcher.group(3);

                    Notification notification = new Notification();
                    notification.setNotificationText(notificationText);
                    notification.setDateTime(LocalDateTime.parse(dataTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
                    notification.setChatId(chatId.intValue());
                    notificationRepository.save(notification);
                    String botAnswerTrue = "Напоминание будет отправлено в назначенное время";
                    SendMessage sendMessage = new SendMessage(chatId, botAnswerTrue);
                    telegramBot.execute(sendMessage);
                }else {
                    String botAnswerFalse = "Сообщение направлено не в верном формате! Отправьте сообщение в соответствии с заданным форматом";
                    SendMessage sendMessage = new SendMessage(chatId, botAnswerFalse);
                    telegramBot.execute(sendMessage);
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
