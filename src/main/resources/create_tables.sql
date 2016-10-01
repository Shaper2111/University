create table groups
(
  id bigint primary key not null identity,
  number integer not null,
  department varchar(50) not null
);
/* With CREATE INDEX UNIQUE foreign key constraint generates error:
[42529][-5529] a UNIQUE constraint does not exist on referenced
columns: GROUPS in statement, so ...*/
alter table groups add constraint "groups_number_unique" unique
(number);

create table students
(
  Id bigint primary key not null identity,
  firstname varchar(50) not null,
  midname varchar(50) not null,
  lastname varchar(50) not null,
  birthdate date not null,
  groupnumber integer not null,
  constraint students_groups_number_fk foreign key (groupnumber)
  references  groups(number) on delete restrict on update cascade
);
