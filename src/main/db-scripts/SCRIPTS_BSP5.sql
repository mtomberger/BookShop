set schema 'bsp5';

CREATE TABLE buch (
   id SERIAL,
   title VARCHAR(75),
   pages INTEGER,
   category SMALLINT,
   fk_Buch_Store INTEGER
);

ALTER TABLE buch
ADD CONSTRAINT pk_buch PRIMARY KEY (id);

ALTER TABLE buch
ADD CONSTRAINT fk_buch_identifier
FOREIGN KEY (fk_Buch_Store) REFERENCES store(id)

