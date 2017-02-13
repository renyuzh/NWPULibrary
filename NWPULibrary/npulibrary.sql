-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: npulibrary
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aname` varchar(20) DEFAULT NULL,
  `ano` varchar(20) DEFAULT NULL,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(20) DEFAULT NULL,
  `udate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'admin','admin','2017-02-13 07:58:38','admin','0000-00-00 00:00:00',NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) DEFAULT NULL,
  `bname` varchar(20) DEFAULT NULL,
  `bno` varchar(20) DEFAULT NULL,
  `brief` varchar(200) DEFAULT NULL,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `price` decimal(10,2) DEFAULT '0.00',
  `remain` int(11) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `tname` varchar(20) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `udate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'冯诺依曼','计算机','A00','','2017-02-12 16:00:00',100.00,2,1,'计算机',2,'2017-02-13 08:01:11',NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookstudent`
--

DROP TABLE IF EXISTS `bookstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookstudent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `author` varchar(20) DEFAULT NULL,
  `bdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `bid` int(11) DEFAULT NULL,
  `bname` varchar(20) DEFAULT NULL,
  `bno` varchar(20) DEFAULT NULL,
  `cdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `price` decimal(10,2) DEFAULT '0.00',
  `rdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `sid` int(11) DEFAULT NULL,
  `sname` varchar(20) DEFAULT NULL,
  `sno` varchar(20) DEFAULT NULL,
  `udate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookstudent`
--

LOCK TABLES `bookstudent` WRITE;
/*!40000 ALTER TABLE `bookstudent` DISABLE KEYS */;
INSERT INTO `bookstudent` VALUES (1,'冯诺依曼','2017-02-13 08:11:17',1,'计算机','A00','2017-02-13 08:11:17',100.00,'2017-03-15 08:11:17',1,'小冯','2016','2017-02-13 08:11:17',NULL);
/*!40000 ALTER TABLE `bookstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booktype`
--

DROP TABLE IF EXISTS `booktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booktype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tname` varchar(20) DEFAULT NULL,
  `udate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booktype`
--

LOCK TABLES `booktype` WRITE;
/*!40000 ALTER TABLE `booktype` DISABLE KEYS */;
INSERT INTO `booktype` VALUES (1,'2017-02-12 16:00:00','计算机','2017-02-13 08:00:34',NULL);
/*!40000 ALTER TABLE `booktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `setting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(20) DEFAULT NULL,
  `udate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `value` int(11) DEFAULT NULL,
  `yn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,'2017-02-13 08:10:17','最大可借阅书籍数量','0000-00-00 00:00:00',30,NULL),(2,'2017-02-13 08:10:45','最大可借阅天数','0000-00-00 00:00:00',30,NULL);
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `password` varchar(20) DEFAULT NULL,
  `passwordNew` varchar(20) DEFAULT NULL,
  `sname` varchar(20) DEFAULT NULL,
  `sno` varchar(20) DEFAULT NULL,
  `udate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `yn` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'2017-02-12 16:00:00','123456',NULL,'小冯','2016','2017-02-13 08:01:35',NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-02-13 16:27:50
