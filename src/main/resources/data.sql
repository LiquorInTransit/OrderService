insert into LCBO_ORDER (id, account_id, delivery_location, store_location, total, status) values (1, 1, '43.3443935,-80.3309458', '43.3524497,-80.3291695', 1000000, 'complete');

insert into LINE_ITEM (id, order_id, product_id, qty) values (1, 1, 1, 2), (2, 1, 2, 1), (3, 1, 3, 5);