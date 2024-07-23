--liquibase formatted sql

--changeset mbortsova:2024-07-23--002-load-tasks_statuses-priorities-data
insert into statuses(name)
values
    ('ANALYZE'),
    ('IN_PROGRESS'),
    ('CODE_REVIEW'),
    ('TEST'),
    ('PRODUCTION');

insert into priorities(name)
values
    ('LOW'),
    ('MEDIUM'),
    ('HIGH');

update tasks set status_id = 1;
update tasks set priority_id = 1;