
CREATE USER 'user'@'%%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'user'@'%%';
FLUSH PRIVILEGES;
SET GLOBAL local_infile = true;

CREATE TABLE IF NOT EXISTS  products (
	    epoch bigint,
        orderid varchar(20),
        descripion text,
        PRIMARY KEY( orderId, epoch )
);

CREATE TABLE IF NOT EXISTS products_v2 (
	    epoch bigint,
        orderid varchar(20),
        descripion text,
        PRIMARY KEY( orderId, epoch )
);
