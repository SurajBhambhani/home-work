


CREATE TABLE IF NOT EXISTS `LOAN` (
	`id` int PRIMARY KEY,
	`customer_id` varchar(50) not null,
	`amount` DECIMAL not null,
	`duration` varchar(100) not null,
	`status` varchar(100) not null
)ENGINE=InnoDB DEFAULT CHARSET=UTF8; 


