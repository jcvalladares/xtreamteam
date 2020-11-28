DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id INT PRIMARY KEY,
  FIRST_NAME VARCHAR(250) NOT NULL,
  LAST_NAME VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL,
  TYPE VARCHAR(20) NOT NULL DEFAULT 'User',
  BIRTH_DATE DATE,
  VALIDITY_IND VARCHAR(1) NOT NULL DEFAULT 'N',
  QRCODE_IND VARCHAR(1) NOT NULL DEFAULT 'N'
);

INSERT INTO users (ID, FIRST_NAME, LAST_NAME, EMAIL, TYPE) VALUES
  (1, 'Juan', 'Valladares', 'Juan@Valladares.com', 'NGO'),
  (2, 'Prashant', 'More', 'Prashant@More.com', 'NGO'),
  (3, 'Alex', 'Giannini', 'Alex@Giannini.com', 'Donor'),
  (4, 'Manav', 'Agarwal', 'Manav@Agarwal.com', 'Donor'),
  (5, 'Anup', 'Shetye', 'Anup@Shetye.com', 'Donor');