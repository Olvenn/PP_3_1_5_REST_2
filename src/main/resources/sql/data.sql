create table roles
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table users
(
    id        bigint auto_increment
        primary key,
    email     varchar(255) not null,
    name      varchar(30)  not null,
    password  varchar(255) null,
    last_name varchar(255) not null,
    login     varchar(255) not null
);

create table users_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK2o0jvgh89lemvvo17cbqvdxaa
        foreign key (user_id) references users (id),
    constraint FKj6m8fwv7oqv74fcehir1a9ffy
        foreign key (role_id) references roles (id)
);

insert into users (id, email, name, password, last_name, login)
values (1, 'us@user.ru', 'User1', '$2a$10$yajEi6vm7IH7cdohioBlguoV0FUUNOQ/UXg40Op9a.AydnOHi8FT2', 'User1', 'User1'),
       (2, 'admin@admin.ru', 'Admin', '$2a$10$z0SS6PIrvykHtxSm9pgNI.xrYgInRFHwpjqTroo15kU/aFRqkjvm.', 'Admin', 'Admin'),
       (3, 'user@user.ru', 'User', '$2a$10$1g/fCOyC7EVU/diymmz2EutTxvUo9NxEZSBiDKTGYHnANZxKiqw2S', 'User', 'User');

insert into roles(name) values ('ROLE_USER', 'ROLE_ADMIN');
insert into user_role (users_id, roles_id) VALUES (1, 1);
insert into user_role (users_id, roles_id) VALUES (2, 2);
