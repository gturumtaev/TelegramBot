package pro.sky.telegrambot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "chat_id")
    private long chatId;
    @Column(name = "notification_text")
    private String notificationText;
    @Column(name = "task_time")
    private LocalDateTime taskTime;
    @Column(name = "note")
    private String note;

    public Notification() {
    }

    public Notification(long chatId, String notificationText, LocalDateTime taskTime, String note) {
        this.chatId = chatId;
        this.notificationText = notificationText;
        this.taskTime = taskTime;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(LocalDateTime taskTime) {
        this.taskTime = taskTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return getChatId() == that.getChatId() && Objects.equals(getId(), that.getId()) && Objects.equals(getNotificationText(), that.getNotificationText()) && Objects.equals(getTaskTime(), that.getTaskTime()) && Objects.equals(getNote(), that.getNote());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getChatId(), getNotificationText(), getTaskTime(), getNote());
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", notificationText='" + notificationText + '\'' +
                ", taskTime=" + taskTime +
                ", note='" + note + '\'' +
                '}';
    }
}
