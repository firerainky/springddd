drop table snack_machine_dto if exists;

create table
    snack_machine_dto (
        id bigint not null,
        five_dollar_count integer not null,
        money_in_transaction decimal(10, 2) not null,
        one_cent_count integer not null,
        one_dollar_count integer not null,
        quarter_count integer not null,
        ten_cent_count integer not null,
        twenty_dollar_count integer not null,
        primary key (id)
    );