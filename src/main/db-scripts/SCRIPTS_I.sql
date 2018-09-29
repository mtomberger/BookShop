CREATE TABLE book (
   id INTEGER,
   title VARCHAR(75),
   pages INTEGER
);

ALTER TABLE book
ADD CONSTRAINT pk_book PRIMARY KEY (id);