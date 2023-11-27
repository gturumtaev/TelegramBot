-- liquibase formatted sql

--changeset gturumtaev:1

CREATE TABLE notification_task(
    id BIGSERIAL PRIMARY KEY,
    chat_id BIGINT NOT NULL,
    notification_text TEXT NOT NULL,
    task_time TIMESTAMP NOT NULL,
    note TEXT
);
