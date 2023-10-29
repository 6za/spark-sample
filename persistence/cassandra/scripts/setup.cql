-- Here, you can execute any CQL commands, e.g.
CREATE KEYSPACE IF NOT EXISTS data WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE data.products (
        epoch bigint,
        orderId text,
        descripion text,        
    PRIMARY KEY (orderId, epoch ));

COPY data.products  FROM '/load-data/products.csv' WITH HEADER = true and PAGETIMEOUT = 1000 and MAXATTEMPTS=30 and INGESTRATE=10000;

CREATE TABLE data.products_v2 (
        epoch bigint,
        orderId text,
        descripion text,        
    PRIMARY KEY (orderId, epoch ));