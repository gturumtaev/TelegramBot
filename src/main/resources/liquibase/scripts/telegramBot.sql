-- liquibase formatted sql

--changeset gturumtaev:1

CREATE TABLE notification_task(
    id SERIAL PRIMARY KEY,
    chatId BIGINT NOT NULL,
    textNotification TEXT NOT NULL,
    taskTime TIMESTAMP NOT NULL,
    note TEXT
);
