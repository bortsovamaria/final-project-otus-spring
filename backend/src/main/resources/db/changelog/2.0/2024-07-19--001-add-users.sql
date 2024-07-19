-- --liquibase formatted sql
--
-- --changeset mbortsova:2024-07-19--001-add-users
insert into users(full_name, username, password, email)
values ('IVan', 'ivan', 'ivan', 'ivan@email.ru'),
       ('Olga', 'olga', 'olga', 'olga@mail.com');

insert into user_roles(user_id, role_id)
values (3, 1), (4, 2);
