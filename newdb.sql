/*
SQLyog Community Edition- MySQL GUI v8.03 
MySQL - 5.5.20-log : Database - ev charging stations
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`ev charging stations` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ev charging stations`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` varchar(50) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`booking_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

insert  into `booking`(`booking_id`,`sta_id`,`user_id`,`date`,`time`,`status`) values (1,2,5,'2022-08-05','5.00 AM','paid'),(2,2,5,'2022-08-05','6.00 AM','paid'),(3,2,5,'2022-08-05','6.00 AM','paid');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `com_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `complaint` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `stid` int(11) DEFAULT NULL,
  PRIMARY KEY (`com_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`com_id`,`user_id`,`complaint`,`date`,`reply`,`stid`) values (1,5,'Good service ','2022-08-04','pending',1);

/*Table structure for table `fecility` */

DROP TABLE IF EXISTS `fecility`;

CREATE TABLE `fecility` (
  `fe_id` int(11) NOT NULL AUTO_INCREMENT,
  `sta_id` int(11) DEFAULT NULL,
  `fecility` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fe_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `fecility` */

insert  into `fecility`(`fe_id`,`sta_id`,`fecility`,`description`,`image`) values (1,2,'washing','car washing ','car_wash.jpg');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `feedback` varchar(100) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `sta_id` int(11) DEFAULT NULL,
  `rating` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`type`) values (1,'admin','admin','admin'),(2,'TATA','Tata@123','station'),(3,'DAYA','Daya@123','station'),(4,'ANOVA','Anova@123','pending'),(5,'sanju@gmail.com','12345','user'),(6,'sanju@gmail.com','12345','user');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(100) DEFAULT NULL,
  `ifsc` varchar(50) DEFAULT NULL,
  `ac_no` int(11) DEFAULT NULL,
  `balance` bigint(20) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `ac_name` varchar(50) DEFAULT NULL,
  `sta-id` int(11) DEFAULT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

/*Table structure for table `rate_info` */

DROP TABLE IF EXISTS `rate_info`;

CREATE TABLE `rate_info` (
  `rate_id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` int(11) DEFAULT NULL,
  `sta_id` int(11) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rate_info` */

/*Table structure for table `resturent` */

DROP TABLE IF EXISTS `resturent`;

CREATE TABLE `resturent` (
  `rest_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `distric` varchar(100) DEFAULT NULL,
  `lattiude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `phone` varchar(25) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `resturent` */

insert  into `resturent`(`rest_id`,`name`,`place`,`distric`,`lattiude`,`longitude`,`phone`,`email`) values (1,'Hayath','Koyilandy','Kozhikode',11.259169136306788,75.78058847483483,'9988776655','haya@gmail.com');

/*Table structure for table `station` */

DROP TABLE IF EXISTS `station`;

CREATE TABLE `station` (
  `sta_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `distric` varchar(100) DEFAULT NULL,
  `lattitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `station` */

insert  into `station`(`sta_id`,`login_id`,`name`,`place`,`phone`,`email`,`distric`,`lattitude`,`longitude`,`image`) values (1,2,'TATA ','KOYILANDY','9988776655','tata@gmail.com','Kozhikode',11.259169136306788,75.78058847483483,'OIP3.jpg'),(2,3,'DAYA','VATAKARA','9977886655','daya@gmail.com','Kozhikode',11.259169136306788,75.78058847483483,'OIP_1.jpg'),(3,4,'ANOVA','VATAKARA','9977665544','anova@gmail.com','Kozhikode',11.259169136306788,75.78058847483483,'OIP_2.jpg');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `house_name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(20) DEFAULT NULL,
  `pin` int(11) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`first_name`,`last_name`,`house_name`,`place`,`post`,`pin`,`district`,`phone`,`email`,`gender`) values (1,5,'sanju','k','krishana kripa','koyilandy ','Melur ',673105,'Kozhikode','9645655113','sanju@gmail.com','Female'),(2,6,'sanju','k','krishana kripa','koyilandy ','Melur ',673105,'Kozhikode','9645655113','sanju@gmail.com','Female');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

