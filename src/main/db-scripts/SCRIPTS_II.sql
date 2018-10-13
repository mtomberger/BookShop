ALTER TABLE buch RENAME COLUMN genre TO category;
ALTER TABLE author DROP CONSTRAINT pk_author;
DROP TABLE author
CREATE SEQUENCE author_ID_Seq START WITH 101 INCREMENT BY 1;
CREATE TABLE author (
   id INTEGER,
   firstname VARCHAR(75),
   lastname VARCHAR(75),
   contraction VARCHAR(5),
   birthdate DATE
);

ALTER TABLE author
ADD CONSTRAINT pk_author PRIMARY KEY (id);