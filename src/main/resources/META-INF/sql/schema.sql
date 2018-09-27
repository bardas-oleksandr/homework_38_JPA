CREATE TABLE IF NOT EXISTS TOPICS(
    ID INT NOT NULL AUTO_INCREMENT,
    TOPIC_NAME VARCHAR(50) NOT NULL UNIQUE,
    CONSTRAINT PKEY_TOPICS PRIMARY KEY (ID)
);

CREATE TABLE IF NOT EXISTS QUESTIONS(
    ID INT NOT NULL AUTO_INCREMENT,
    QUESTION VARCHAR(50) NOT NULL,
    TOPIC_ID INT NOT NULL,
    CREATED_BY VARCHAR(20),
    CREATED_DATE TIMESTAMP,
    LAST_MODIFIED_BY VARCHAR(20),
    LAST_MODIFIED_DATE TIMESTAMP,
    CONSTRAINT PKEY_QUESTIONS PRIMARY KEY (ID),
    CONSTRAINT TOPIC_TO_QUESTION FOREIGN KEY (TOPIC_ID) REFERENCES TOPICS (ID)
    ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS ANSWERS(
    ID INT NOT NULL AUTO_INCREMENT,
    ANSWER VARCHAR(50) NOT NULL,
    CORRECT BOOLEAN NOT NULL,
    QUESTION_ID INT NOT NULL,
    CONSTRAINT PKEY_ANSWERS PRIMARY KEY (ID),
    CONSTRAINT QUESTION_TO_ANSWER FOREIGN KEY (QUESTION_ID) REFERENCES QUESTIONS (ID)
    ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS USERS(
    LOGIN VARCHAR(30) NOT NULL,
    PASSWORD VARCHAR(30) NOT NULL,
    NAME VARCHAR(30) NOT NULL,
    ADMIN_STATUS BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT PKEY_USERS PRIMARY KEY (LOGIN)
);

CREATE TABLE IF NOT EXISTS ATTEMPTS(
    ID INT NOT NULL AUTO_INCREMENT,
    LOGIN VARCHAR(30) NOT NULL,
    DATE TIMESTAMP(8) WITHOUT TIME ZONE NOT NULL,
    RESULT DOUBLE PRECISION NOT NULL,
    CONSTRAINT PKEY_ATTEMPTS PRIMARY KEY (ID),
    CONSTRAINT USER_TO_ATTEMPT FOREIGN KEY (LOGIN) REFERENCES USERS (LOGIN)
    ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS GIVEN_ANSWERS(
    ID INT NOT NULL AUTO_INCREMENT,
    QUESTION VARCHAR(50) NOT NULL,
    GIVEN_ANSWER VARCHAR(50) NOT NULL,
    CORRECT BOOLEAN NOT NULL,
    ATTEMPT_ID INT NOT NULL,
    CONSTRAINT PKEY_GIVEN_ANSWERS PRIMARY KEY (ID),
    CONSTRAINT ATTEMPT_TO_GIVEN_ANSWER FOREIGN KEY (ATTEMPT_ID) REFERENCES ATTEMPTS (ID)
    ON UPDATE NO ACTION ON DELETE NO ACTION
);