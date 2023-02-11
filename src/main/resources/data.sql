-------------------
-- Brands
-------------------
insert into BRANDS(ID, KEYWORD, NAME) values (nextval('brand_seq'), 'zara', 'Zara');
-------------------
-- Products
-------------------
alter sequence PRODUCT_SEQ restart with 35455;
insert into PRODUCTS(ID, NAME, BRAND_ID) values (nextval('product_seq'), 'first product', 1);
-------------------
-- Price list
-------------------
insert into PRICE_LIST(ID, START_DATE, END_DATE, AMOUNT, CURR, PRIORITY, PRODUCT_ID)
       values (nextval('price_seq'), '2020-06-14 00:00:00', '2020-12-31 23:59:59', 35.50, 'EUR', 0, 35455);
insert into PRICE_LIST(ID, START_DATE, END_DATE, AMOUNT, CURR, PRIORITY, PRODUCT_ID)
       values (nextval('price_seq'), '2020-06-14 15:00:00', '2020-06-14 18:30:00', 25.45, 'EUR', 1, 35455);
insert into PRICE_LIST(ID, START_DATE, END_DATE, AMOUNT, CURR, PRIORITY, PRODUCT_ID)
       values (nextval('price_seq'), '2020-06-15 00:00:00', '2020-06-15 11:00:00', 30.50, 'EUR', 1, 35455);
insert into PRICE_LIST(ID, START_DATE, END_DATE, AMOUNT, CURR, PRIORITY, PRODUCT_ID)
       values (nextval('price_seq'), '2020-06-14 16:00:00', '2020-12-31 23:59:59', 38.95, 'EUR', 1, 35455);
