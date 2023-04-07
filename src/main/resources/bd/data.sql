insert into users (name)
values ('@BlynchikV');

insert into role(user_id, role)
values (1, 'ADMIN');

insert into adventurer(user_id, first_name, last_name,
                       strength, agility, constitution,
                       intelligence, wisdom, charisma,
                       evil_good, chaotic_lawful, money)
values (1, 'Random', 'Random',
        1, 1, 1, 1, 1, 1, 50, 50, 0);

