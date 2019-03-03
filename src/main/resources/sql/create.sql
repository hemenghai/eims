drop table enterprise;

create table enterprise (
  enterprise_id varchar(128) primary key,
  enterprise_name varchar(128) unique not null,
  enterprise_number varchar(128) unique not null,
  enterprise_scale varchar(128),

  legal_person varchar(128) not null,
  establishment_time varchar(32) not null,
  contact_number varchar(64) not null,

  industry_category varchar(64),
  industry_name varchar(64),
  industry_chain varchar(64),

  main_business1 varchar(128),
  main_business2 varchar(128),

  create_time timestamp,
  update_time timestamp
);