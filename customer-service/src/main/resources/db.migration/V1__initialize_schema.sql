


CREATE TABLE IF NOT EXISTS `CUSTOMER` (
	`id` int PRIMARY KEY,
	`user_id` varchar(50) not null,
	`first_name` varchar(100) not null,
	`last_name` varchar(100) not null,
	`email` varchar(100) not null,
	`phone_number` varchar(100) not null, /*Should be change to number with country specific*/
)ENGINE=InnoDB DEFAULT CHARSET=UTF8; 


