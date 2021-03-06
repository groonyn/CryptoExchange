# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table api_key (
  id                            bigint auto_increment not null,
  user_id                       varchar(255),
  key                           varchar(255),
  secret_key                    varchar(255),
  constraint pk_api_key primary key (id)
);

create table crypto_exchange_account (
  id                            bigint auto_increment not null,
  trade_account_id              bigint not null,
  account_name                  varchar(255),
  crypto_exchange_name          integer,
  constraint ck_crypto_exchange_account_crypto_exchange_name check ( crypto_exchange_name in (0,1)),
  constraint pk_crypto_exchange_account primary key (id)
);

create table linked_account (
  id                            bigint auto_increment not null,
  user_id                       bigint,
  provider_user_id              varchar(255),
  provider_key                  varchar(255),
  constraint pk_linked_account primary key (id)
);

create table security_role (
  id                            bigint auto_increment not null,
  role_name                     varchar(255),
  constraint pk_security_role primary key (id)
);

create table token_action (
  id                            bigint auto_increment not null,
  token                         varchar(255),
  target_user_id                bigint,
  type                          varchar(2),
  created                       timestamp,
  expires                       timestamp,
  constraint ck_token_action_type check ( type in ('PR','EV')),
  constraint uq_token_action_token unique (token),
  constraint pk_token_action primary key (id)
);

create table trade_account (
  id                            bigint auto_increment not null,
  constraint pk_trade_account primary key (id)
);

create table users (
  id                            bigint auto_increment not null,
  email                         varchar(255),
  password                      varchar(255),
  name                          varchar(255),
  first_name                    varchar(255),
  last_name                     varchar(255),
  last_login                    timestamp,
  active                        boolean,
  email_validated               boolean,
  constraint pk_users primary key (id)
);

create table users_security_role (
  users_id                      bigint not null,
  security_role_id              bigint not null,
  constraint pk_users_security_role primary key (users_id,security_role_id)
);

create table users_user_permission (
  users_id                      bigint not null,
  user_permission_id            bigint not null,
  constraint pk_users_user_permission primary key (users_id,user_permission_id)
);

create table user_permission (
  id                            bigint auto_increment not null,
  value                         varchar(255),
  constraint pk_user_permission primary key (id)
);

alter table crypto_exchange_account add constraint fk_crypto_exchange_account_trade_account_id foreign key (trade_account_id) references trade_account (id) on delete restrict on update restrict;
create index ix_crypto_exchange_account_trade_account_id on crypto_exchange_account (trade_account_id);

alter table linked_account add constraint fk_linked_account_user_id foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_linked_account_user_id on linked_account (user_id);

alter table token_action add constraint fk_token_action_target_user_id foreign key (target_user_id) references users (id) on delete restrict on update restrict;
create index ix_token_action_target_user_id on token_action (target_user_id);

alter table users_security_role add constraint fk_users_security_role_users foreign key (users_id) references users (id) on delete restrict on update restrict;
create index ix_users_security_role_users on users_security_role (users_id);

alter table users_security_role add constraint fk_users_security_role_security_role foreign key (security_role_id) references security_role (id) on delete restrict on update restrict;
create index ix_users_security_role_security_role on users_security_role (security_role_id);

alter table users_user_permission add constraint fk_users_user_permission_users foreign key (users_id) references users (id) on delete restrict on update restrict;
create index ix_users_user_permission_users on users_user_permission (users_id);

alter table users_user_permission add constraint fk_users_user_permission_user_permission foreign key (user_permission_id) references user_permission (id) on delete restrict on update restrict;
create index ix_users_user_permission_user_permission on users_user_permission (user_permission_id);


# --- !Downs

alter table crypto_exchange_account drop constraint if exists fk_crypto_exchange_account_trade_account_id;
drop index if exists ix_crypto_exchange_account_trade_account_id;

alter table linked_account drop constraint if exists fk_linked_account_user_id;
drop index if exists ix_linked_account_user_id;

alter table token_action drop constraint if exists fk_token_action_target_user_id;
drop index if exists ix_token_action_target_user_id;

alter table users_security_role drop constraint if exists fk_users_security_role_users;
drop index if exists ix_users_security_role_users;

alter table users_security_role drop constraint if exists fk_users_security_role_security_role;
drop index if exists ix_users_security_role_security_role;

alter table users_user_permission drop constraint if exists fk_users_user_permission_users;
drop index if exists ix_users_user_permission_users;

alter table users_user_permission drop constraint if exists fk_users_user_permission_user_permission;
drop index if exists ix_users_user_permission_user_permission;

drop table if exists api_key;

drop table if exists crypto_exchange_account;

drop table if exists linked_account;

drop table if exists security_role;

drop table if exists token_action;

drop table if exists trade_account;

drop table if exists users;

drop table if exists users_security_role;

drop table if exists users_user_permission;

drop table if exists user_permission;

