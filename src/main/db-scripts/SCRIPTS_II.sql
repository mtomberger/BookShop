ALTER TABLE buch RENAME COLUMN genre TO category;

CREATE TABLE author (
   id INTEGER,
   firstname VARCHAR(75),
   lastname VARCHAR(75),
   contraction VARCHAR(5),
   birthdate DATE
);

ALTER TABLE author
ADD CONSTRAINT pk_author PRIMARY KEY (id);