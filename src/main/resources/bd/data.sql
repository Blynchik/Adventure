insert into users (email, password)
values ('vadimsovetnikov@mail.ru', '{noop}password'),
       ('00sipand@gmail.com', '{noop}password');

insert into role(user_id, role)
values (1, 'ADMIN'),
       (2, 'USER');

insert into adventurer(user_id, first_name, last_name,
                       strength, agility, constitution,
                       intelligence, wisdom, charisma,
                       evil_good, chaotic_lawful, money)
values (1, 'Random', 'Random',
        1, 1, 1, 1, 1, 1, 50, 50, 0);

