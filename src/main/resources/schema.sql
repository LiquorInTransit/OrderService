create table LCBO_ORDER (
	id INTEGER PRIMARY KEY,
	customer_id INTEGER,
	delivery_location VARCHAR(60),
	store_location VARCHAR(60),
	status VARCHAR(60),
	total INTEGER,
	order_date DATETIME
);

create table LINE_ITEM (
	id INTEGER PRIMARY KEY,
	order_id INTEGER,
	product_id INTEGER,
	product_name VARCHAR(255),
	price DECIMAL(6,2),
	qty INTEGER
);