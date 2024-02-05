create table if not exists company
(
    id              varchar(36)    not null primary key unique,
    name            varchar(120)   not null,
    cnpj            varchar(40)    not null,
    transaction_tax     decimal(13, 2) not null
);

create table if not exists customer
(
    id              varchar(36)    not null primary key unique,
    name            varchar(120)   not null,
    cpf             varchar(40)    not null
);

create table if not exists transaction
(
    id              varchar(36)    not null primary key unique,
    customer_id     varchar(36)    not null,
    company_id      varchar(36)    not null,
    type            varchar(20)    not null
);

alter table transaction
    add constraint company_fk
        foreign key (company_id) references company (id);

alter table transaction
    add constraint customer_fk
        foreign key (customer_id) references customer (id);
