
--- it doesn't work due to security issues

LOAD DATA LOCAL INFILE '/load-data/products.csv' 
        INTO TABLE products 
        FIELDS TERMINATED BY ',' 
        ENCLOSED BY '"'
        LINES TERMINATED BY '\n'
        IGNORE 1 ROWS;