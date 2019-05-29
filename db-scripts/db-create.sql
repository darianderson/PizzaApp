USE pizzadb;

DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS pizza;
DROP TABLE IF EXISTS drink;

SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE users (
	username varchar(50) NOT NULL,
    firstName varchar(50) NOT NULL,
    secondName varchar(50) NOT NULL,
    phoneNumber varchar(50) NOT NULL,
    address varchar(50) NOT NULL,
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
	users (username) ON DELETE CASCADE ON update cascade
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

CREATE TABLE orders(
	id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
	status INTEGER default 0,
	productType VARCHAR(16) NOT NULL,
    idProduct INTEGER NOT NULL,
    idClient varchar(50) NOT NULL,
    INDEX FK_orders_idClient (idClient),
    CONSTRAINT FK_orders_idClient FOREIGN KEY (idClient) REFERENCES users (username) ON DELETE CASCADE ON update cascade
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO users VALUES
		('test', 'Dmitry','Blackwell','+1256','Solaris','test@mail.com' ,'{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1), -- fun123
		('admin', 'Daria','Tsyhanok','+1234','Solaris','admin@pizza.com' ,'{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1);

INSERT INTO authorities VALUES
			('test','ROLE_USER'),
			('admin','ROLE_USER'),
			('admin','ROLE_ADMIN');

INSERT INTO pizza VALUES (1,'meat', 35, 85), (2,'paper', 35, 70), (3,'chiken', 35, 75), (4,'marshmallow', 35, 65);
INSERT INTO drink VALUES (1,12 ,'pepsi' ), (2,14,'coca-cola'), (3,10,'fanta' ), (4, 10,'sprite');
INSERT INTO orders VALUES (default,0,"pizza",1,'test'), (default ,0,"pizza",3,'test');
INSERT INTO orders VALUES (default,0,"pizza",4,'admin'), (default ,0,"pizza",3,'test'), (default ,0,'drink',1,'test');
