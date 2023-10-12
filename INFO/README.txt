
Стек: Spring Data, PostgreSql, Lombok.


CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    quota DOUBLE PRECISION NOT NULL,
    price DOUBLE PRECISION NOT NULL
);


INSERT INTO products (name, quota, price) VALUES
    ('Milk', 10.5, 25.99),
    ('Beer', 15.0, 34.75),
    ('Coca-Cola', 8.25, 19.99);