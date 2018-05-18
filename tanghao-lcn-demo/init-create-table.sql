create database tanghao_learn;

use tanghao_learn;

CREATE TABLE `et_pay_order_base` (
  `id` int(22) unsigned NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(32) DEFAULT NULL,
  `pay_order_no` varchar(32) DEFAULT NULL,
  `gmt_create` datetime,
  `buyer` varchar(32) DEFAULT NULL,
  `seller` varchar(32),
  `amount` decimal(22),
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31;

CREATE TABLE `ep_account` (
	`id` int(22) unsigned NOT NULL AUTO_INCREMENT,
	`account_no` varchar(32),
	`balance` decimal(22),
	`freeze_balance` decimal(22),
	`gmt_create` datetime,
	`gmt_modified` datetime,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=31;

create database tanghao_learn_2;

use tanghao_learn_2;

CREATE TABLE `et_pay_order_base` (
  `id` int(22) unsigned NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(32) DEFAULT NULL,
  `pay_order_no` varchar(32) DEFAULT NULL,
  `gmt_create` datetime,
  `buyer` varchar(32) DEFAULT NULL,
  `seller` varchar(32),
  `amount` decimal(22),
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31;

CREATE TABLE `ep_account` (
	`id` int(22) unsigned NOT NULL AUTO_INCREMENT,
	`account_no` varchar(32),
	`balance` decimal(22),
	`freeze_balance` decimal(22),
	`gmt_create` datetime,
	`gmt_modified` datetime,
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=31;