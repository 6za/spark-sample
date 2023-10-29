
CREATE TABLE IF NOT EXISTS  public.products (
	    epoch bigint,
        orderid text,
        descripion text,
        PRIMARY KEY( orderId, epoch )
);

\copy public.products FROM '/load-data/products.csv'  WITH CSV DELIMITER ',' HEADER;

CREATE TABLE IF NOT EXISTS  public.products_v2 (
	    epoch bigint,
        orderid text,
        descripion text,
        PRIMARY KEY( orderId, epoch )
);