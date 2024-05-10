/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.5.20-log : Database - ev_charging
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ev_charging` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ev_charging`;

/*Table structure for table `booking` */

DROP TABLE IF EXISTS `booking`;

CREATE TABLE `booking` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL,
  `u_id` int(11) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `time_slot` varchar(20) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

/*Data for the table `booking` */

insert  into `booking`(`b_id`,`s_id`,`u_id`,`date`,`time_slot`,`status`) values 
(4,11,18,'2022-7-15','5.00 AM','rejected'),
(5,2,18,'2022-7-15','5.00 AM','rejected'),
(6,11,1,'2022-7-16','5.00 AM','completed'),
(8,1,1,'2022-7-15','7.00 AM','completed'),
(10,1,1,'2022-7-16','5.00 AM','cancelled'),
(14,1,1,'2022-7-16','9.00 AM','rejected'),
(16,1,1,'2022-7-18','5.00 AM','paid'),
(17,11,1,'2022-7-18','5.00 AM','completed'),
(18,11,1,'','5.00 AM','paid'),
(19,11,1,'2022-7-19','5.00 AM','paid');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `complaint` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(56) DEFAULT NULL,
  `time` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`c_id`,`u_id`,`s_id`,`complaint`,`reply`,`date`,`time`) values 
(1,7,4,'bad','pending','2022-06-15','09:59:01'),
(2,3,6,'very bad','wert','2022-06-25','10:20:10'),
(3,3,6,'worst','okay','2022-06-26','10:10:10'),
(4,5,7,'worst','pending','2022-06-26','12:12:00'),
(5,1,1,'bad','Okk... we will check','2022-07-04','14:50:00'),
(6,1,5,'bad','pending','2022-07-04','14:50:39'),
(7,1,7,'very bad','okay no problom','2022-07-07','11:51:17'),
(8,1,12,'Dangerous','pending','2022-07-15','10:37:32'),
(9,1,11,'very bad service ','pending','2022-07-15','11:05:20');

/*Table structure for table `fecilites` */

DROP TABLE IF EXISTS `fecilites`;

CREATE TABLE `fecilites` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL,
  `fecility` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

/*Data for the table `fecilites` */

insert  into `fecilites`(`f_id`,`s_id`,`fecility`,`description`,`image`) values 
(7,1,'fd','ertq','puthii.png'),
(19,3,'food','maruthur','2022-03-02.png'),
(25,6,'washing','oil washing available','pexels-cottonbro-4488667.jpg'),
(26,11,'Wahing','We providing a quality vehicle washing','11.jpg'),
(27,1,'washing','free wahing','11.jpg'),
(28,11,'Food court','We have a nice food court near the charging station','food.jpg'),
(29,11,'Tire pressure monitor','Here you can check the tire pressure','tyre.png');

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `feedback` varchar(100) DEFAULT NULL,
  `rating` varchar(20) DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

insert  into `feedback`(`f_id`,`u_id`,`s_id`,`feedback`,`rating`,`date`,`time`) values 
(1,3,6,'good','5','2022-06-15','10:59:01'),
(2,5,6,'not bad','4','2022-06-26','10:59:01'),
(3,1,10,'good','4','2022-07-07','15:22:33'),
(4,1,11,'good','3','2022-07-09','10:49:16'),
(5,1,11,'very good ','5','2022-07-09','10:51:44'),
(6,1,11,'bad','2','2022-07-09','10:52:30'),
(7,1,10,'Average experience ','2.5','2022-07-13','11:56:14'),
(8,1,1,'Avg experience ','2.5','2022-07-13','11:57:41'),
(9,18,11,'One of the best','3.0','2022-07-15','11:00:13'),
(10,18,2,'One of the worst','1.0','2022-07-15','11:34:35');

/*Table structure for table `noti` */

DROP TABLE IF EXISTS `noti`;

CREATE TABLE `noti` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_id` int(11) DEFAULT NULL,
  KEY `n_id` (`n_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `noti` */

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `s_id` int(11) DEFAULT NULL,
  `b_name` varchar(100) DEFAULT NULL,
  `ifsc` varchar(20) DEFAULT NULL,
  `ac_no` int(11) DEFAULT NULL,
  `balance` bigint(20) DEFAULT NULL,
  `ac_name` varchar(100) DEFAULT NULL,
  `key` int(11) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`p_id`,`s_id`,`b_name`,`ifsc`,`ac_no`,`balance`,`ac_name`,`key`) values 
(1,1,'SBI','SBIN004',NULL,7490,NULL,NULL),
(2,2,'CANERA','CNAR002',NULL,4500,NULL,NULL),
(3,3,'IDBI','IDBI009',NULL,3900,NULL,NULL),
(4,4,'YES','YES0009',NULL,2300,NULL,NULL),
(5,7,'SBI','SBIN897',NULL,7500,NULL,NULL),
(6,10,'FEDERAL BANK','FBC0006',NULL,8000,NULL,NULL),
(7,11,'SBI','SBIN008',NULL,10435,NULL,NULL),
(8,12,'SBI','',NULL,NULL,NULL,NULL);

/*Table structure for table `payment_details` */

DROP TABLE IF EXISTS `payment_details`;

CREATE TABLE `payment_details` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_id` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `payment_details` */

insert  into `payment_details`(`p_id`,`b_id`,`amount`,`date_time`) values 
(1,10,100,'2022-07-15 15:43:56'),
(2,11,100,'2022-07-15 15:49:10'),
(3,12,100,'2022-07-15 15:49:22'),
(4,13,145,'2022-07-15 16:05:11'),
(5,14,145,'2022-07-15 16:12:25'),
(6,15,145,'2022-07-15 16:14:49'),
(7,16,145,'2022-07-18 14:51:34'),
(8,17,145,'2022-07-18 14:52:39'),
(9,18,145,'2022-07-18 16:18:37'),
(10,19,145,'2022-07-18 16:20:01');

/*Table structure for table `rate_info` */

DROP TABLE IF EXISTS `rate_info`;

CREATE TABLE `rate_info` (
  `rate_id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` int(11) DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  `details` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rate_info` */

/*Table structure for table `register` */

DROP TABLE IF EXISTS `register`;

CREATE TABLE `register` (
  `l_id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(100) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `register` */

insert  into `register`(`l_id`,`uname`,`password`,`type`) values 
(1,'admin',NULL,'admin'),
(2,'puthiyottil',NULL,NULL),
(3,'puthiyotil',NULL,NULL),
(4,'kk',NULL,NULL),
(5,'aaa',NULL,NULL),
(6,'aru',NULL,NULL),
(7,'pp','1234','pending');

/*Table structure for table `restaurant` */

DROP TABLE IF EXISTS `restaurant`;

CREATE TABLE `restaurant` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `restaurant` */

insert  into `restaurant`(`r_id`,`name`,`place`,`district`,`latitude`,`longitude`,`phone`,`email`,`image`) values 
(1,'puthiyottil','vadakara','Kozhikode','11.2578131','75.7844665',7902675782,'anu@gmail.com','2022-03-15.png'),
(4,'bhagyasree','kozhikode','Kozhikode','76.999','11.444',7902847782,'paswa@gmail.com','vipina.jpg'),
(5,'Arundhathi','Meppayur','Kozhikode','75.3876543','11.2345678',8590498743,'arundhathi@gmail.com','aru.jfif');

/*Table structure for table `station` */

DROP TABLE IF EXISTS `station`;

CREATE TABLE `station` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `station` */

insert  into `station`(`s_id`,`name`,`place`,`phone`,`email`,`district`,`latitude`,`longitude`,`image`,`password`,`status`) values 
(1,'KSEB','kainatty',7902847782,'puthi@gmail.com','Kozhikode',11.77633917582457,75.48191578539782,'puthii.png','1234','cc'),
(2,'TATA Power','kainatty',7902847782,'kk@gmail.com','Kozhikode',11.77633917582457,75.48191578539782,'puthii.png',NULL,NULL),
(10,'Anova','Vadakara',9876543211,'anova@gmail.com','Kozhikode',11.77633917582457,75.48191578539782,'2022-02-27_1.png','Anova@123','cc'),
(11,'Zeon ev station','Vadakara',8765432109,'zeon@gmail.com','Kozhikode',11.77633917582457,75.48191578539782,'1.jpg','Zeon@123','cc'),
(12,'Change Mode','Vadakara',7654321098,'chmo@gmail.com','Kozhikode',11.77633917582457,75.48191578539782,'4.jpg','Chmo@123','pending');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_name` varchar(50) DEFAULT NULL,
  `l_name` varchar(50) DEFAULT NULL,
  `house` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `post` varchar(100) DEFAULT NULL,
  `pin` int(11) DEFAULT NULL,
  `district` varchar(100) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` bigint(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `type` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`u_id`,`f_name`,`l_name`,`house`,`place`,`post`,`pin`,`district`,`gender`,`email`,`phone`,`password`,`type`) values 
(1,'Arathi','P','parambil','thiruvangoor','koylandi',673108,'kozhikode','female','arathi@gmail.com',9876543321,'1234','user'),
(2,'admin','','kozhikode','kozhikode','kozhikode',673106,'kozhikode','male','admin@gmail.com',7902847782,'admin','admin'),
(3,'Aswanth','P','kainatty','kozhikode','chorode',673106,'kozhikode','male','aswanth@gmail.com',9876543210,'1234','cc'),
(4,'vipin','p','puthiyottil','aroor','aroor',345667,'Kozhikode','male','paswa@gmail.com',7902847782,'1234','cc'),
(5,'Anjana','Ck','cheriya kuttiyil','Iringal','iringal',675423,'Kozhikode','female','anju@gmail.com',9876543210,'1234','user'),
(7,'Bhagya','PR','Maruthur madam ','koylandy ','7558',673106,'Kozhikode','Male','bhagya@gmail.com',9874563214,'bhagy@123','user'),
(8,'Aparna ','k','Parambil','Thiruvagur','Thiruvangur',673304,'Kozhikode','Female','aparna@gmail.com',8976541230,'1234','user'),
(9,'Anju','P','cheriya kuttiyil','irigal ','iringal',654123,'Kozhikode','Female','anju@gmail.com',9847563212,'1234','user'),
(14,'Vipin','Raj','Kuniyil ','Aroor','Aroor',654712,'Kozhikode','Male','vipi@gmail.com',9847563210,'Vipi@123','user'),
(18,'Anjana ','Anil Kumar ','Cheriya kuttiyil','Iringal','Iringal',674521,'Kozhikode','Female','anjana@gmail.com',9874563210,'Anju@123','user');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
