set schema 'bsp4';

CREATE TABLE identifier(
   id INTEGER,
   isbn VARCHAR(17),
   qrCode VARCHAR(255),
);

ALTER TABLE Identifier
ADD CONSTRAINT pk_Identifier PRIMARY KEY (id);

CREATE TABLE buch (
   id SERIAL,
   title VARCHAR(75),
   pages INTEGER,
   category SMALLINT
   fk_Identifier INTEGER
);

ALTER TABLE buch
ADD CONSTRAINT pk_buch PRIMARY KEY (id);

ALTER TABLE buch
ADD CONSTRAINT fk_buch_identifier
FOREIGN KEY (fk_Identifier) REFERENCES identifier(id)