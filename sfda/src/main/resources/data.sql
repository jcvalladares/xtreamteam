DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY,
  FIRST_NAME VARCHAR(250) NOT NULL,
  MIDDLE_NAME VARCHAR(250),
  LAST_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL,
  PHONE VARCHAR(15),
  TYPE VARCHAR(20) NOT NULL DEFAULT 'User',
  BIRTH_DATE DATE,
  VALIDITY_IND VARCHAR(1) NOT NULL DEFAULT 'N',
  QRCODE_IND VARCHAR(1) NOT NULL DEFAULT 'N',
  PASSWORD VARCHAR(50) NOT NULL
);
ALTER TABLE users ADD CONSTRAINT EMAIL_UNIQUE UNIQUE(EMAIL);


INSERT INTO users (ID, FIRST_NAME, LAST_NAME, EMAIL, TYPE, PASSWORD) VALUES
  (1, 'Juan', 'Valladares', 'Juan@Valladares.com', 'NGO', 'xtreamteam!5'),
  (2, 'Prashant', 'More', 'Prashant@More.com', 'NGO', 'xtreamteam!5'),
  (3, 'Alex', 'Giannini', 'Alex@Giannini.com', 'Donor', 'xtreamteam!5'),
  (4, 'Manav', 'Agarwal', 'Manav@Agarwal.com', 'Donor', 'xtreamteam!5'),
  (5, 'Anup', 'Shetye', 'Anup@Shetye.com', 'Donor', 'xtreamteam!5'),
  (6, 'Richard', 'Kasperowski', 'test@test.com', 'Donor', 'test');