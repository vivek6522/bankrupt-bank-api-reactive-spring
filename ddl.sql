-- PostgreSQL
drop table if exists transaction_statuses;
drop table if exists transactions;
drop table if exists accounts;

create table accounts (
  account_id serial primary key,
  account_number varchar(32) unique not null,
  account_type varchar(32) not null,
  balance_in_cents bigint default 0,
  preferred boolean default FALSE,
  customer_id varchar(64) not null
);

create index on accounts (customer_id);

create table transactions (
  transaction_id serial primary key,
  transaction_reference varchar(64) unique not null,
  source int references accounts not null,
  target int references accounts not null,
  amount_in_cents bigint not null,
  description varchar(128)
);

create index on transactions (source, target);

create table transaction_statuses (
  transaction_status_id serial primary key,
  transaction_id int references transactions not null,
  status varchar(32) not null,
  recorded timestamp with time zone default (CURRENT_TIMESTAMP at time zone 'UTC')
);

create index on transaction_statuses (transaction_id);
