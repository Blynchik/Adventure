insert into users (name)
values ('@BlynchikV');

insert into role(user_id, role)
values (1, 'ADMIN'),
       (1, 'USER');

insert into hero(user_id, first_name, last_name)
values (1, 'Random', 'Random');

insert into first_name(first_name)
values ('Вася'),
       ('Паша'),
       ('Гена'),
       ('Жора');