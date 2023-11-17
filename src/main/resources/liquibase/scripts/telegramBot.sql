-- liquibase formatted sql

--changeset gturumtaev:1

CREATE TABLE notification_task(
    id SERIAL PRIMARY KEY,
    chatId INT NOT NULL,
    textNotification TEXT NOT NULL,
    dataTime TIMESTAMP NOT NULL,
    note TEXT
);
