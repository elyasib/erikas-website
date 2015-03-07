# --- !Ups
CREATE TABLE table1(
  id integer,
  name character(10)
);

# --- !Downs
DROP TABLE IF EXISTS table1;
