insert into users (email, password)
values ('vadimsovetnikov@mail.ru', '{noop}password'),
       ('00sipand@gmail.com', '{noop}password');

insert into role(user_id, role)
values (1, 'ADMIN'),
       (2, 'USER');