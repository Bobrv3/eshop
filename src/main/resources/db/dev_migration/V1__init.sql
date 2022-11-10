CREATE TABLE order_details (
  quantity INT NOT NULL,
   order_id BIGINT NOT NULL,
   product_id BIGINT NOT NULL,
   CONSTRAINT pk_order_details PRIMARY KEY (order_id, product_id)
);

CREATE TABLE users (
  id BIGINT AUTO_INCREMENT NOT NULL,
   username VARCHAR(255) NOT NULL,
   password VARCHAR(255) NOT NULL,
   phone VARCHAR(255) NOT NULL,
   email VARCHAR(255) NOT NULL,
   `role` VARCHAR(255) NOT NULL,
   `locked` BIT(1) NOT NULL,
   enabled BIT(1) NOT NULL,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE orders (
  id BIGINT AUTO_INCREMENT NOT NULL,
   status INT NOT NULL,
   created_at datetime NOT NULL,
   user_id BIGINT NOT NULL,
   CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE products (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name VARCHAR(255) NOT NULL,
   price DECIMAL(4, 2) NOT NULL,
   status VARCHAR(255) NOT NULL,
   created_at datetime NOT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
     token VARCHAR(64) NOT NULL,
      last_used TIMESTAMP NOT NULL,
      PRIMARY KEY (series)
) ENGINE = InnoDB;

ALTER TABLE order_details ADD CONSTRAINT FK_ORDER_DETAILS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);
ALTER TABLE order_details ADD CONSTRAINT FK_ORDER_DETAILS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);
ALTER TABLE orders ADD CONSTRAINT FK_ORDERS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
ALTER TABLE users ADD CONSTRAINT uc_users_username UNIQUE (username);