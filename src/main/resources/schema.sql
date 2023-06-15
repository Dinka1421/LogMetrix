drop sequence if exists users_seq;
create sequence users_seq start with 1 increment by 5;
create table if not exists users (
    id bigint default users_seq.nextval primary key,
    username varchar(100) not null,
    password varchar(250) not null,
    first_name varchar(250) not null,
    last_name varchar(250) not null
);

drop table if exists filters cascade; 
drop sequence if exists filters_seq;
create sequence filters_seq start with 1 increment by 5;
create table filters (
  id bigint default filters_seq.nextval primary key,
  end_date date, 
  start_date date, 
  client varchar(255) check (client in ('BWA','CTN','LX')), 
  environment varchar(255) check (environment in ('DEV','TEST','INT','PROD')), 
  log varchar(255) check (log in ('SYSTEM','DEBUG','ERROR','DATA'))
);


create table if not exists authority (
    id   identity,
    name varchar(100) not null
);

create table if not exists user_authority (
  user_id bigint not null,
  authority_id bigint not null,
  constraint fk_user foreign key (user_id) references users(id),
  constraint fk_authority foreign key (authority_id) references authority(id)
);