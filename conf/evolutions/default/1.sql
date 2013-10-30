# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table Comment (
  id                        integer not null,
  content                   varchar(255),
  post_id                   integer,
  member_id                 integer,
  constraint pk_Comment primary key (id))
;

create table Member (
  id                        integer not null,
  nom                       varchar(255),
  prenom                    varchar(255),
  login                     varchar(255),
  email                     varchar(255),
  mot_passe                 varchar(255),
  constraint pk_Member primary key (id))
;

create table Post (
  id                        integer not null,
  title                     varchar(255),
  content                   varchar(255),
  post_date                 timestamp,
  nom                       integer,
  member_id                 integer,
  constraint pk_Post primary key (id))
;

create table Wall (
  id                        integer not null,
  nom                       varchar(255),
  constraint pk_Wall primary key (id))
;

create sequence Comment_seq;

create sequence Member_seq;

create sequence Post_seq;

create sequence Wall_seq;

alter table Comment add constraint fk_Comment_post_1 foreign key (post_id) references Post (id) on delete restrict on update restrict;
create index ix_Comment_post_1 on Comment (post_id);
alter table Comment add constraint fk_Comment_member_2 foreign key (member_id) references Member (id) on delete restrict on update restrict;
create index ix_Comment_member_2 on Comment (member_id);
alter table Post add constraint fk_Post_wall_3 foreign key (nom) references Wall (id) on delete restrict on update restrict;
create index ix_Post_wall_3 on Post (nom);
alter table Post add constraint fk_Post_member_4 foreign key (member_id) references Member (id) on delete restrict on update restrict;
create index ix_Post_member_4 on Post (member_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists Comment;

drop table if exists Member;

drop table if exists Post;

drop table if exists Wall;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists Comment_seq;

drop sequence if exists Member_seq;

drop sequence if exists Post_seq;

drop sequence if exists Wall_seq;

