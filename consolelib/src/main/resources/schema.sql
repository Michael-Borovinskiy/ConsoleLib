
DROP TABLE IF EXISTS AUTHOR;
CREATE TABLE AUTHOR(ID BIGINT PRIMARY KEY AUTO_INCREMENT, FULLNAME VARCHAR(255) );

DROP TABLE IF EXISTS GENRE;
CREATE TABLE GENRE(ID BIGINT PRIMARY KEY AUTO_INCREMENT, GENRENAME VARCHAR(255));

DROP TABLE IF EXISTS BOOK;
CREATE TABLE BOOK(ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                  TITLE VARCHAR(255),
                  AUTHORID BIGINT  REFERENCES AUTHOR(ID),
                  GENREID BIGINT REFERENCES GENRE(ID));

DROP TABLE IF EXISTS COMMENT;
CREATE TABLE COMMENT(   ID BIGINT PRIMARY KEY AUTO_INCREMENT,
                        COMMENTTEXT VARCHAR(255),
                        BOOKID BIGINT REFERENCES BOOK(ID) ON DELETE CASCADE);