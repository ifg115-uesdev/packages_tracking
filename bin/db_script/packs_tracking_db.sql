CREATE DATABASE `packs_tracking_db` ;

USE `packs_tracking_db`;

-- packs_tracking_db.department definition

CREATE TABLE `department` (
  `department_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


-- packs_tracking_db.agency_type definition

CREATE TABLE `agency_type` (
  `agency_type_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`agency_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


-- packs_tracking_db.rol definition

CREATE TABLE `rol` (
  `rol_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


-- packs_tracking_db.state_package definition

CREATE TABLE `state_package` (
  `state_package_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`state_package_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


-- packs_tracking_db.agency definition

CREATE TABLE `agency` (
  `agency_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `department_id` int NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  `agency_type_id` int NOT NULL,
  PRIMARY KEY (`agency_id`),
  KEY `agency_FK` (`department_id`),
  KEY `agency_FK_1` (`agency_type_id`),
  CONSTRAINT `agency_FK` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `agency_FK_1` FOREIGN KEY (`agency_type_id`) REFERENCES `agency_type` (`agency_type_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


-- packs_tracking_db.`user` definition

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 NOT NULL,
  `lastname` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `department_id` int NOT NULL,
  `agency_id` int NOT NULL,
  `gender` char(1) NOT NULL,
  `date_birth` date NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_FK` (`department_id`),
  KEY `user_FK_1` (`agency_id`),
  CONSTRAINT `user_FK` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `user_FK_1` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`agency_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;


-- packs_tracking_db.package definition

CREATE TABLE `package` (
  `package_id` varchar(50) NOT NULL,
  `description` varchar(250) NOT NULL,
  `user_id` int NOT NULL,
  `sender_customer_name` varchar(100) NOT NULL,
  `recipient_customer_name` varchar(100) NOT NULL,
  `shipping_agency_id` int NOT NULL,
  `destination_agency_id` int NOT NULL,
  `package_weight` double NOT NULL,
  `start_date` timestamp NOT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `price` float(10,2) NOT NULL,
  PRIMARY KEY (`package_id`),
  KEY `package_FK` (`shipping_agency_id`),
  KEY `package_FK_1` (`destination_agency_id`),
  KEY `package_FK_3` (`user_id`),
  CONSTRAINT `package_FK` FOREIGN KEY (`shipping_agency_id`) REFERENCES `agency` (`agency_id`) ON DELETE RESTRICT,
  CONSTRAINT `package_FK_1` FOREIGN KEY (`destination_agency_id`) REFERENCES `agency` (`agency_id`) ON DELETE RESTRICT,
  CONSTRAINT `package_FK_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- packs_tracking_db.transportation definition

CREATE TABLE `transportation` (
  `transportation_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`transportation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- packs_tracking_db.tracking_hist definition

CREATE TABLE `tracking_hist` (
  `hist_id` int NOT NULL AUTO_INCREMENT,
  `package_id` varchar(50) NOT NULL,
  `state_package_id` int NOT NULL,
  `modify_date` timestamp NOT NULL,
  `agency_id` int NOT NULL,
  `observation` varchar(500) DEFAULT NULL,
  `transportation_id` int NOT NULL,
  `transportation_report` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`hist_id`),
  KEY `tracking_hist_FK` (`agency_id`),
  KEY `tracking_hist_FK_1` (`package_id`),
  KEY `tracking_hist_FK_2` (`state_package_id`),
  KEY `tracking_hist_FK_3` (`transportation_id`),
  CONSTRAINT `tracking_hist_FK` FOREIGN KEY (`agency_id`) REFERENCES `agency` (`agency_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tracking_hist_FK_1` FOREIGN KEY (`package_id`) REFERENCES `package` (`package_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tracking_hist_FK_2` FOREIGN KEY (`state_package_id`) REFERENCES `state_package` (`state_package_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `tracking_hist_FK_3` FOREIGN KEY (`transportation_id`) REFERENCES `transportation` (`transportation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

-- packs_tracking_db.rol_user definition

CREATE TABLE `rol_user` (
  `rol_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`rol_id`,`user_id`),
  KEY `rol_user_FK` (`user_id`),
  CONSTRAINT `rol_user_FK` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `rol_user_FK_1` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`rol_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;