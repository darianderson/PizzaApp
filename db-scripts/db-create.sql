USE pizzadb;

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS pizza;
DROP TABLE IF EXISTS drink;

CREATE TABLE users (
	username varchar(50) NOT NULL,
	email varchar(50) NOT NULL,
	password varchar(100) NOT NULL,
	enabled tinyint(1) NOT NULL,
	PRIMARY KEY (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE authorities (
	username varchar(50) NOT NULL,
	authority varchar(50) NOT NULL,
	UNIQUE KEY authorities_idx_1 (username,authority),
	CONSTRAINT authorities_ibfk_1 FOREIGN KEY (username) REFERENCES
	users (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE pizza(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    info VARCHAR(45) NOT NULL,
    size INTEGER NOT NULL,
    price INTEGER NOT NULL
);

CREATE TABLE drink(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
    price INTEGER NOT NULL,
    name VARCHAR(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO users VALUES
		('test', 'test@mail.com' ,'{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1), -- fun123
		('admin', 'admin@pizza.com' ,'{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);

INSERT INTO authorities VALUES
			('test','ROLE_USER'),
			('admin','ROLE_USER'),
			('admin','ROLE_ADMIN');

INSERT INTO pizza VALUES (1,'meat', 35, 85), (2,'paper', 35, 70), (3,'chiken', 35, 75), (4,'marshmallow', 35, 65);
INSERT INTO drink VALUES (1,12 ,'pepsi' ), (2,14,'coca-cola'), (3,10,'fanta' ), (4, 10,'sprite');