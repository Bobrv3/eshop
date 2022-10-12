create table order_items (order_id bigint not null, product_id bigint not null, quantity integer not null, primary key (order_id, product_id)) engine=InnoDB;
create table orders (id bigint not null auto_increment, created_at datetime(6), status varchar(255), user_id bigint, primary key (id)) engine=InnoDB;
create table products (id bigint not null auto_increment, created_at datetime(6), name varchar(255), price decimal(19,2), status varchar(255), primary key (id)) engine=InnoDB;
create table users (id bigint not null auto_increment, email varchar(255), enabled bit not null, locked bit not null, password varchar(255), phone varchar(255), role varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
alter table order_items add constraint FK_orderitems_orders foreign key (order_id) references orders (id);
alter table order_items add constraint FK_orderitems_products foreign key (product_id) references products (id);
alter table orders add constraint FK_orders_users foreign key (user_id) references users (id);