DROP SCHEMA IF EXISTS library;

CREATE TABLE IF NOT EXISTS USERLIST(
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    membership_category VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS BOOK(
    book_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    book_name VARCHAR(255) NOT NULL,
    book_category VARCHAR(255),
    book_genre_category VARCHAR(255),
    book_quantity VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS ORDERLIST(
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    order_on TIMESTAMP NOT NULL,
    return_on TIMESTAMP,
    CONSTRAINT fk_order_user FOREIGN KEY (user_id) REFERENCES userlist(user_id),
    CONSTRAINT fk_order_book FOREIGN KEY (book_id) REFERENCES book(book_id)
);


-- INSERT INTO USERLIST (user_name, membership_category, user_age)
-- VALUES ('Bob Brown', 'GOLD', 35);
--
-- INSERT INTO USERLIST (user_name, membership_category, user_age)
-- VALUES ('Charlie Davis', 'PLATINUM', 28);
--
-- INSERT INTO USERLIST (user_name, membership_category, user_age)
-- VALUES ('David Evans', 'SILVER', 15);

INSERT INTO USERLIST (user_name, date_of_birth, membership_category)
VALUES ('Jane Smith', '1995-06-15', 'SILVER');

INSERT INTO USERLIST (user_name, date_of_birth, membership_category)
VALUES ('Bob Johnson', '1980-03-22', 'GOLD');

INSERT INTO USERLIST (user_name, date_of_birth, membership_category)
VALUES ('Alice Brown', '1990-11-01', 'PLATINUM');

INSERT INTO USERLIST (user_name, date_of_birth, membership_category)
VALUES ('Mike Davis', '2011-08-12', 'SILVER');

INSERT INTO BOOK (book_name, book_category, book_genre_category, book_quantity)
VALUES
    ('Book One','BOOK', 'FICTION',1),
    ('History of Science',  'BOOK', 'SCIENCE',1),
    ('Crime and Punishment', 'BOOK', 'CRIMEGENRE',1),
    ('Magazine One', 'MAGAZINE', 'HISTORY',1),
    ('Friction Theory', 'BOOK', 'SCIENCE',1);




