-- --liquibase formatted sql
--
-- --changeset mbortsova:2024-07-19--003-load-tasks-data
insert into tasks(title, description, created_by, updated_by, assigned_to, created_at, updated_at)
values ('Task title - 1', 'descrp', 1, 1, 3, '2024-07-15', '2024-07-19'),
       ('Task title - 2', 'descrp 2', 2, 2, 3, '2024-05-10', '2024-06-12'),
       ('Task title - 3', 'descrp 3', 2, 1, 4, '2024-07-19', '2024-07-19');
