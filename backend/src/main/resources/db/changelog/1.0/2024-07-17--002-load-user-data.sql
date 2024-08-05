-- --liquibase formatted sql
--
-- --changeset mbortsova:2024-07-17--002-load-user-data
insert into users(full_name, username, password, email)
values ('Boris', 'user', 'user', 'user@email.ru'),
       ('Malen', 'admin', 'admin', 'admin@mail.com');

insert into roles(name)
values ('ROLE_USER'), ('ROLE_ADMIN');

insert into user_roles(user_id, role_id)
values (1, 1), (2, 2);
