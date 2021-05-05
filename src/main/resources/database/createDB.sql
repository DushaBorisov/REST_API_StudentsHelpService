CREATE TABLE IF NOT EXISTS users(
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL ,
    surname VARCHAR(50) NOT NULL ,
    age INTEGER,
    login VARCHAR(50) UNIQUE ,
    password VARCHAR(100) NOT NULL ,
    money INTEGER DEFAULT(0),
    role VARCHAR,
    email VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders(
    order_id SERIAL PRIMARY KEY,
    customer_id INTEGER REFERENCES users(user_id) NOT NULL ,
    performer_id INTEGER REFERENCES users(user_id) NOT NULL ,
    description VARCHAR(500) NOT NULL ,
    cost INTEGER NOT NULL ,
    date_order date NOT NULL ,
    date_execution date,
    status INTEGER
);

CREATE TABLE IF NOT EXISTS picture_order(
    id SERIAL PRIMARY KEY,
    order_id INTEGER REFERENCES orders(order_id) NOT NULL,
    picture VARCHAR(500)
);

