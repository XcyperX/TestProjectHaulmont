drop table if exists banks CASCADE;
drop table if exists credits CASCADE;
drop table if exists credits_offer CASCADE;
drop table if exists payment_schedule CASCADE;
drop table if exists users CASCADE;



CREATE TABLE banks
(
    id   varchar(255) not null,
    name varchar(255) not null,
    primary key (id)
);

CREATE TABLE credits
(
    id           varchar(255) not null,
    interes_rate float,
    loan_limit   float,
    bank_id      varchar(255),
    primary key (id)
);

create table credits_offer
(
    id         varchar(255) not null,
    sum_credit float,
    credit_id  varchar(255),
    user_id    varchar(255),
    primary key (id)
);

create table payment_schedule
(
    id              varchar(255) not null,
    body_credit_sum float,
    payment_date    date,
    payment_sum     float,
    percent_sum     float,
    credit_offer_id varchar(255),
    primary key (id)
);

create table users
(
    id              varchar(255) not null,
    email           varchar(255),
    name            varchar(255),
    number_passport varchar(255),
    phone           varchar(255),
    bank_id         varchar(255),
    primary key (id)
);

alter table users add constraint uniq_2425236 unique (email);

alter table credits add constraint fk_53263634 foreign key (bank_id) references banks;

alter table credits_offer add constraint fk_6854645 foreign key (credit_id) references credits;

alter table credits_offer add constraint fk_996347847 foreign key (user_id) references users;

alter table payment_schedule add constraint fk_74567456 foreign key (credit_offer_id) references credits_offer;

alter table users add constraint fk_758273342 foreign key (bank_id) references banks;