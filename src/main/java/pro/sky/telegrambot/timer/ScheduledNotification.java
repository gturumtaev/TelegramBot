package pro.sky.telegrambot.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.repository.NotificationRepository;
import pro.sky.telegrambot.service.TelegramBotService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class ScheduledNotification {

    private final NotificationRepository notificationRepository;
    private TelegramBotService telegramBotService;

    public ScheduledNotification(NotificationRepository notificationRepository, TelegramBotService telegramBotService) {
        this.notificationRepository = notificationRepository;
        this.telegramBotService = telegramBotService;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void findNotificationFromDb() {
        notificationRepository.findByTaskTime(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .forEach(e -> {
                    telegramBotService.sendNotification(e.getChatId(), e.getNotificationText());
                });
    }

}
