INSERT
INTO
  users
  (email, enabled, locked, password, phone, role, username)
VALUES
  ('admin@gmail.com', true, false, '$2a$12$NW3FCTSgwW435PTAhnJw/euwUrRvp5q.k88TJuGwNmUfyD3xmyyse', '+375447931700', 'ROLE_ADMIN', 'admin'),
  ('user@gmail.com', true, false, '$2a$12$NW3FCTSgwW435PTAhnJw/euwUrRvp5q.k88TJuGwNmUfyD3xmyyse', '+375444561701', 'ROLE_USER', 'user');

INSERT
INTO
  products
  (name, price, status, created_at)
VALUES
  ('milk', 1.55, 'IN_STOCK', FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' ))),
  ('bread', 0.99, 'IN_STOCK', FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' ))),
  ('sausage', 3.99, 'IN_STOCK', FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' ))),
  ('meat', 7.99, 'IN_STOCK', FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' )));

INSERT
INTO
  orders
  (status, created_at, user_id)
VALUES
  (ROUND(RAND() * 3), FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' )), 1),
  (ROUND(RAND() * 3), FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' )), 2),
  (ROUND(RAND() * 3), FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' )), 1),
  (ROUND(RAND() * 3), FROM_UNIXTIME(RAND( ) * ( UNIX_TIMESTAMP( '2022-01-31' ) - UNIX_TIMESTAMP( '2022-01-01' )) + UNIX_TIMESTAMP( '2022-01-01' )), 2);

INSERT
INTO
  order_details
  (quantity, order_id, product_id)
VALUES
  (2, 1, 1),
  (1, 1, 2),
  (1, 2, 3),
  (2, 2, 4),
  (1, 3, 2),
  (1, 3, 1),
  (1, 4, 1);
