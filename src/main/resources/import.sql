/* Populate tables */
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Rojas","Alberto");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Zavala","Daniel");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Jimenez","Juan");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Guzman","Ximena");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Juarez","Lidia");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Zavala","Sandra");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Rojas","Cintia");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Zavala","Yanet");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Bustos","Roberto");
 INSERT INTO `db_datajpa`.`clients` (`create_at`,`email`,`image`,`last_name`,`name`) VALUES ("2023-05-29","alberto@correol.com",'',"Moreno","Pedro");



/* Populate table products */

 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",123456,"Nintendo Switch");
 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",573456,"NintendoDS");
 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",348235,"Pantalla 4k");
 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",626245,"Monitor 4k");
 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",890800,"Ps5");
 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",794818,"Ps4");
 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",389701,"Xbox series X");
 INSERT INTO `db_datajpa`.`products`(`create_at`,`price`,`name`) VALUES("2023-05-29",283612,"Gameboy Advance");

/* Inserts de facturas */
INSERT INTO `db_datajpa`.`bills`(`client_id`,`observation`,`description`,`create_at`) VALUES (1,null,"Primera factura de equipos de oficina",NOW());
INSERT INTO `db_datajpa`.`bills_items`(`amount`,`bill_id`,`product_id`) VALUES (10,1,1);
INSERT INTO `db_datajpa`.`bills_items`(`amount`,`bill_id`,`product_id`) VALUES (2,1,3);
INSERT INTO `db_datajpa`.`bills_items`(`amount`,`bill_id`,`product_id`) VALUES (3,1,6);


INSERT INTO `db_datajpa`.`bills`(`client_id`,`observation`,`description`,`create_at`) VALUES (1,"Pagar ya!!","Segunda factura de equipos de oficina",NOW());
INSERT INTO `db_datajpa`.`bills_items`(`amount`,`bill_id`,`product_id`) VALUES (5,2,2);
INSERT INTO `db_datajpa`.`bills_items`(`amount`,`bill_id`,`product_id`) VALUES (2,2,4);
INSERT INTO `db_datajpa`.`bills_items`(`amount`,`bill_id`,`product_id`) VALUES (1,2,7);