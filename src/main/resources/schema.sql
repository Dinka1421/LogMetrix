CREATE TABLE IF NOT EXISTS users (
    id IDENTITY,
    email VARCHAR(100),
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    username VARCHAR(100)
);

drop table if exists filters cascade; 
drop sequence if exists filters_seq;
create sequence filters_seq start with 1 increment by 50;
create table filters (
  end_date date, 
  start_date date, 
  id bigint not null, 
  client varchar(255) check (client in ('BWA','CTN','LX')), 
  environment varchar(255) check (environment in ('DEV','TEST','INT','PROD')), 
  log varchar(255) check (log in ('SYSTEM','DEBUG','ERROR','DATA')), 
  primary key (id)
);
