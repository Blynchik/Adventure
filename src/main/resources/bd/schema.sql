drop table if exists last_name;
drop table if exists first_name;
drop table if exists hero;
drop table if exists role;
drop table if exists users;

create table users
(
    id            int generated by default as identity primary key,
    name          varchar                 not null unique,
    registered_at timestamp default now() not null
);

create table role
(
    user_id int     not null,
    role    varchar not null,
    constraint roles_idx unique (user_id, role),
    foreign key (user_id) references users (id) on delete cascade
);


create table hero
(
    id             int generated by default as identity primary key,
    user_id        int                     references users (id) on delete set null,
    first_name     varchar(20)             not null,
    last_name      varchar(50)             not null,
    created_at     timestamp default now() not null
);

create table first_name
(
    id int generated by default as identity  primary key,
    first_name varchar not null unique
);

create table last_name
(
    id int generated by default as identity  primary key,
    last_name varchar not null unique
);