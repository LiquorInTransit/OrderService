create table LCBO_ORDER (
	id INTEGER PRIMARY KEY,
	customer_id INTEGER,
	delivery_location VARCHAR(60),
	store_location VARCHAR(60),
	status VARCHAR(60),
	total INTEGER,
	order_date DATE
);

create table LINE_ITEM (
	id INTEGER PRIMARY KEY,
	order_id INTEGER,
	product_id INTEGER,
	qty INTEGER
);