--liquibase formatted sql

--changeset mbortsova:2024-03-14--002-load-comments-data
insert into comments(text, task_id)
values
    ('Comment 1', 1),
    ('Comment 2', 1),
    ('Comment 3', 2);