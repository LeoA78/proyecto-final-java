--CUSTOMERS DETAILS
INSERT INTO `spring_finalproject`.`customer_details` (`id_customer_detail`, `deleted`, `vip`, `credits`) SELECT 1, 0, 1 , 12000
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id_customer_detail = 1);
INSERT INTO `spring_finalproject`.`customer_details` (`id_customer_detail`, `deleted`, `vip`, `credits`) SELECT 2, 0, 0 , 3000
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id_customer_detail = 2);
INSERT INTO `spring_finalproject`.`customer_details` (`id_customer_detail`, `deleted`, `vip`, `credits`) SELECT 3, 0, 1 , 10000
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id_customer_detail = 3);
INSERT INTO `spring_finalproject`.`customer_details` (`id_customer_detail`, `deleted`, `vip`, `credits`) SELECT 4, 0, 1 , 9000
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id_customer_detail = 4);
INSERT INTO `spring_finalproject`.`customer_details` (`id_customer_detail`, `deleted`, `vip`, `credits`) SELECT 5, 0, 0 , 4000
WHERE NOT EXISTS (SELECT * FROM `customer_details` WHERE id_customer_detail = 5);

--CUSTOMERS
INSERT INTO `spring_finalproject`.`customers` (`id_customer`, `created_date`, `date_of_birth`, `deleted`, `dni`, `name`, `last_name`, `customer_detail_id`) SELECT 1, now(), '1990-01-12', 0, '34123456', 'Carlos', 'Ramirez', 4
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id_customer = 1);
INSERT INTO `spring_finalproject`.`customers` (`id_customer`, `created_date`, `date_of_birth`, `deleted`, `dni`, `name`, `last_name`, `customer_detail_id`) SELECT 2, now(), '1992-05-01', 0, '36123456', 'Manuel', 'Fernandez', 2
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id_customer = 2);
INSERT INTO `spring_finalproject`.`customers` (`id_customer`, `created_date`, `date_of_birth`, `deleted`, `dni`, `name`, `last_name`, `customer_detail_id`) SELECT 3, now(), '1989-08-19', 0, '33123456', 'Romina', 'Garay', 3
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id_customer = 3);
INSERT INTO `spring_finalproject`.`customers` (`id_customer`, `created_date`, `date_of_birth`, `deleted`, `dni`, `name`, `last_name`, `customer_detail_id`) SELECT 4, now(), '1991-03-13', 0, '35123456', 'Fernando', 'Peralta', 5
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id_customer = 4);
INSERT INTO `spring_finalproject`.`customers` (`id_customer`, `created_date`, `date_of_birth`, `deleted`, `dni`, `name`, `last_name`, `customer_detail_id`) SELECT 5, now(), '1993-11-26', 0, '36123456', 'Sofia', 'Gutierrez', 1
WHERE NOT EXISTS (SELECT * FROM `customers` WHERE id_customer = 5);

--ADDRESSES
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 1, '2A', 'Rosario', 'Argentina', 0, '2000', 'Santa Fe', 'Urquiza', '2546', 1
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 1);
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 2, '5B', 'Paraná', 'Argentina', 0, '3100', 'Entre Rios', 'Sarmiento', '420', 1
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 2);
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 3, 'Casa', 'Córdoba', 'Argentina', 0, '5000', 'Córdoba', 'San Martin', '100', 2
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 3);
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 4, '1D', 'Santa Fe', 'Argentina', 0, '3000', 'Santa Fe', 'Belgrano', '1234', 3
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 4);
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 5, 'Casa', 'Santa Fe', 'Argentina', 0, '3000', 'Santa Fe', 'Corrientes', '320', 3
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 5);
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 6, 'Casa', 'Paraná', 'Argentina', 0, '3100', 'Entre Rios', 'Colon', '123', 4
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 6);
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 7, '7C', 'Córdoba', 'Argentina', 0, '5000', 'Córdoba', 'Velez', '900', 5
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 7);
INSERT INTO `spring_finalproject`.`addresses` (`id_address`, `apartment`, `city`, `country`, `deleted`, `postcode`, `province`, `street`, `number`, `customer_id`)
SELECT 8, 'Casa', 'Córdoba', 'Argentina', 0, '5000', 'Córdoba', 'Alameda', '456', 5
WHERE NOT EXISTS (SELECT * FROM `addresses` WHERE id_address = 8);

--INVOICES
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 1, now(), 0, 'TV Smart', 'A', '35000', 1
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 1);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 2, now(), 0, 'Notebook', 'B', '45000', 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 2);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 3, now(), 0, 'Cafetera', 'C', '35000', 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 3);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 4, now(), 0, 'Aspiradora', 'C', '35000', 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 4);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 5, now(), 0, 'Monitor', 'B', '35000', 2
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 5);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 6, now(), 0, 'Licuadora', 'A', '35000', 3
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 6);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 7, now(), 0, 'Horno', 'A', '35000', 3
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 7);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 8, now(), 0, 'Estufa', 'B', '35000', 3
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 8);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 9, now(), 0, 'Anafe', 'C', '35000', 4
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 9);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 10, now(), 0, 'Celular', 'C', '35000', 5
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 10);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 11, now(), 0, 'Lámpara', 'B', '35000', 5
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 11);
INSERT INTO `spring_finalproject`.`invoices` (`id_invoice`, `created_date`, `deleted`, `description`, `invoice_type`, `total`, `customer_id`) SELECT 12, now(), 0, 'Escritorio', 'A', '35000', 5
WHERE NOT EXISTS (SELECT * FROM `invoices` WHERE id_invoice = 12);
