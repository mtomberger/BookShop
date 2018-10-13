ALTER TABLE buch
DROP CONSTRAINT pk_buch;
DROP TABLE buch;
CREATE TABLE buch (
   id SERIAL,
   title VARCHAR(75),
   pages INTEGER,
   category SMALLINT
);
ALTER TABLE buch
ADD CONSTRAINT pk_buch PRIMARY KEY (id);