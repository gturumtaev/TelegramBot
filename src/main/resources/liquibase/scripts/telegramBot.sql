-- liquibase formatted sql

-- changeset gturumtaev:1

CREATE TABLE notification(
    id BIGSERIAL PRIMARY KEY,
    chat_id BIGSERIAL NOT NULL,
    notification_text TEXT NOT NULL,
    task_time TIMESTAMP NOT NULL,
    note TEXT
);
