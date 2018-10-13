CREATE TABLE buch (
   id INTEGER,
   title VARCHAR(75),
   pages INTEGER,
   genre SMALLINT
);

ALTER TABLE buch
ADD CONSTRAINT pk_buch PRIMARY KEY (id);