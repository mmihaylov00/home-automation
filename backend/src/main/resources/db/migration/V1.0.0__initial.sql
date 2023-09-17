create table actions
(
    id             blob         not null,
    created_date   datetime     not null,
    deleted        boolean      not null default false,
    modified_date  datetime,
    type           varchar(255) not null,
    fail_action_id blob,
    next_action_id blob,
    task_id        blob,
    primary key (id)
);

create table device_groups
(
    device_id bigint not null,
    group_id  bigint not null
);

create table devices
(
    id            bigint       not null,
    created_date  datetime     not null,
    deleted       boolean      not null default false,
    modified_date datetime,
    mac_address   varchar(255) not null,
    name          varchar(255) not null,
    provider      varchar(255) not null,
    type          varchar(255) not null,
    primary key (id)
);

create table executed_tasks
(
    id                     bigint       not null,
    created_date           datetime     not null,
    deleted                boolean      not null default false,
    modified_date          datetime,
    additional_information varchar(255),
    status                 varchar(255) not null,
    current_action_id      blob         not null,
    task_id                blob         not null,
    triggered_by_id        blob         not null,
    primary key (id)
);

create table groups
(
    id            bigint       not null,
    created_date  datetime     not null,
    deleted       boolean      not null default false,
    modified_date datetime,
    name          varchar(255) not null,
    primary key (id)
);

create table hibernate_sequence
(
    next_val bigint
);

insert into hibernate_sequence
values (1);

create table properties
(
    id            bigint       not null,
    created_date  datetime     not null,
    deleted       boolean      not null default false,
    modified_date datetime,
    key           varchar(255) not null,
    type          varchar(255) not null,
    value         varchar(255) not null,
    action_id     blob         not null,
    primary key (id)
);

create table tasks
(
    id               blob         not null,
    created_date     datetime     not null,
    deleted          boolean      not null default false,
    modified_date    datetime,
    name             varchar(255) not null,
    single_execution boolean      not null,
    trigger          varchar(255) not null,
    trigger_value    varchar(255) not null,
    created_by_id    blob         not null,
    start_action_id  blob,
    primary key (id)
);

create table users
(
    id                 blob         not null,
    created_date       datetime     not null,
    deleted            boolean      not null default false,
    modified_date      datetime,
    device_information varchar(255) not null,
    is_admin           boolean      not null,
    mac_address        varchar(255) not null,
    name               varchar(255) not null,
    primary key (id)
);