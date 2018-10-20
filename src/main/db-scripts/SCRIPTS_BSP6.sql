set schema 'bsp6';
CREATE TABLE buch (
   id SERIAL,
   title VARCHAR(75),
   pages INTEGER
);
CREATE TABLE publisher (
   id SERIAL,
   name VARCHAR(75),
   description VARCHAR(255)
);
CREATE TABLE buch_publisher (
   buch_id INTEGER,
   publisher_id INTEGER
);
ALTER TABLE buch
ADD CONSTRAINT pk_buch PRIMARY KEY (id);
ALTER TABLE publisher
ADD CONSTRAINT pk_publisher PRIMARY KEY (id);
ALTER TABLE buch_publisher
ADD CONSTRAINT pk_buch_publisher PRIMARY KEY (buch_id,publisher_id);
ALTER TABLE buch_publisher
ADD CONSTRAINT fk_buch_id
FOREIGN KEY (buch_id) REFERENCES buch(id);
ALTER TABLE buch_publisher
ADD CONSTRAINT fk_publisher_id
FOREIGN KEY (publisher_id) REFERENCES publisher(id);