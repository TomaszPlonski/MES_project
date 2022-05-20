insert into order_handlers(first_name, last_name) values ('Eric', 'Lang');
insert into order_handlers(first_name, last_name) values ('Reiner', 'Knizia');
insert into order_handlers(first_name, last_name) values ('Richard', 'Garfield');

insert into purchasers(adres, name) values ('ul. Siedmiogrodzka 9; 01-204 Warszawa', 'Budimex');
insert into purchasers(adres, name) values ('ul. Parzniewska 10; 05-800 Pruszków', 'Strabag');
insert into purchasers(adres, name) values ('ul. Grzybowska 80/82; 00-844 Warszawa', 'Gulermak');

insert into orders(name, order_value, order_handler_id, purchaser_id) value ('S1 Warszawa - Kraków',120.00,1,1);
insert into orders(name, order_value, order_handler_id, purchaser_id) value ('S2 Kraków - Poznań',233.12,1,2);
insert into orders(name, order_value, order_handler_id, purchaser_id) value ('S3 Poznań - Białystok',95.00,1,2);
insert into orders(name, order_value, order_handler_id, purchaser_id) value ('S4 Białystok - Szczecin',210.09,2,1);
insert into orders(name, order_value, order_handler_id, purchaser_id) value ('S5 Szczecin - Przemyśl',300.00,3,1);

