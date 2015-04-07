# --- !Ups
create table language (
lang_id char(2) primary key,
lang_desc text check (lang_desc <> '')
);

# --- !Downs
DROP TABLE IF EXISTS language;
