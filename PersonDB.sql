DROP DATABASE IF EXISTS PersonDB;
CREATE DATABASE IF NOT EXISTS PersonDB;
USE PersonDB;

CREATE TABLE IF NOT EXISTS PersonList (
	id INT PRIMARY KEY AUTO_INCREMENT,
	lastname VARCHAR(24) NOT NULL,
	firstname VARCHAR(24) NOT NULL,
	gender CHAR(1) NOT NULL,
	age INT NOT NULL CHECK (age > 0),
	h INT NOT NULL CHECK (h > 60)
);

INSERT INTO PersonList(lastname, firstname, gender, age, h) VALUES
('Biagio Rosario', 'Greco', 'm', 40, 171);