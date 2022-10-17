insert into users (email, enabled, locked, password, phone, role, username)
values ('admin@gmail.com', true, false, '$2a$12$NW3FCTSgwW435PTAhnJw/euwUrRvp5q.k88TJuGwNmUfyD3xmyyse', '+375447931700', 'ROLE_ADMIN', 'admin'),
       ('user@gmail.com', true, false, '$2a$12$NW3FCTSgwW435PTAhnJw/euwUrRvp5q.k88TJuGwNmUfyD3xmyyse', '+375444561701', 'ROLE_USER', 'user');

INSERT
INTO
  orders
  (status, created_at, user_id)
VALUES
  ('IN_PROCESSING', NOW(), 1);

INSERT
INTO
  products
  (name, price, status, created_at)
VALUES
  ('milk', 1.55, 'IN_STOCK', NOW()),
  ('bread', 0.99, 'IN_STOCK', NOW());

INSERT
INTO
  order_details
  (quantity, order_id, product_id)
VALUES
  (2, 1, 1),
  (1, 1, 2);
