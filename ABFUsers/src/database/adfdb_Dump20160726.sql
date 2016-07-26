-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: adfdb
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `am_contract`
--

DROP TABLE IF EXISTS `am_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `am_contract` (
  `am_contract_id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_id` int(11) DEFAULT NULL,
  `onshore_price_id` int(11) DEFAULT NULL,
  `offshore_price_id` int(11) DEFAULT NULL,
  `details_xml` text,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`am_contract_id`),
  KEY `FK_am_contract_id_idx` (`contract_id`),
  KEY `FK_am_onshoreprice_id_idx` (`onshore_price_id`),
  KEY `FK_am_offshoreprice_id_idx` (`offshore_price_id`),
  CONSTRAINT `FK_am_contract_id` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_am_offshoreprice_id` FOREIGN KEY (`offshore_price_id`) REFERENCES `offshore_price` (`offshoreprice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_am_onshoreprice_id` FOREIGN KEY (`onshore_price_id`) REFERENCES `onshore_price` (`onshoreprice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `am_contract`
--

LOCK TABLES `am_contract` WRITE;
/*!40000 ALTER TABLE `am_contract` DISABLE KEYS */;
INSERT INTO `am_contract` VALUES (5,14,NULL,4,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>5</id><months><name>May 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Jun 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Jul 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Aug 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Sep 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Oct 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Nov 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Dec 16</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Jan 17</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Feb 17</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Mar 17</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months><months><name>Apr 17</name><total>19800.0</total><weeks><w1>20.0</w1><w2>20.0</w2><w3>30.0</w3><w4>40.0</w4></weeks></months></Resource>',1),(8,17,NULL,4,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>6</id><months><month><name>May 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Jun 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Jul 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Aug 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Sep 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Oct 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Nov 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Dec 16</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Jan 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Feb 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Mar 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Apr 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>May 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Jun 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Jul 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Aug 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Sep 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Oct 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Nov 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Dec 17</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Jan 18</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Feb 18</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Mar 18</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>Apr 18</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month><month><name>May 18</name><total>28800.0</total><weeks><w1>40.0</w1><w2>40.0</w2><w3>40.0</w3><w4>40.0</w4></weeks></month></months></Resource>',1),(10,18,NULL,4,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>9</id><months><month><name>May 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jun 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jul 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Aug 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Sep 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Oct 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Nov 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Dec 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jan 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Feb 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Mar 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Apr 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>May 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jun 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jul 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Aug 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Sep 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Oct 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Nov 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month></months></Resource>',1),(11,18,NULL,4,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>11</id><months><month><name>May 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jun 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jul 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Aug 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Sep 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Oct 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Nov 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Dec 16</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jan 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Feb 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Mar 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Apr 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>May 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jun 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Jul 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Aug 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Sep 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Oct 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month><month><name>Nov 17</name><total>22860.0</total><weeks><w1>10.0</w1><w2>20.0</w2><w3>30.0</w3><w4>67.0</w4></weeks></month></months></Resource>',1),(12,18,NULL,4,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>12</id><months><month><name>May 16</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Jun 16</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Jul 16</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Aug 16</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Sep 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Oct 16</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Nov 16</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Dec 16</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Jan 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Feb 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Mar 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Apr 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>May 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Jun 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Jul 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Aug 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Sep 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Oct 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month><month><name>Nov 17</name><total>0.0</total><weeks><w1>0.0</w1><w2>0.0</w2><w3>0.0</w3><w4>0.0</w4></weeks></month></months></Resource>',1),(13,16,NULL,4,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>13</id><months><month><name>May 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Jun 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Jul 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Aug 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Sep 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Oct 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Nov 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Dec 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month></months></Resource>',1),(14,16,NULL,4,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>14</id><months><month><name>May 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Jun 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Jul 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Aug 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Sep 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Oct 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Nov 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month><month><name>Dec 16</name><total>9000.0</total><weeks><w1>5.0</w1><w2>10.0</w2><w3>15.0</w3><w4>20.0</w4></weeks></month></months></Resource>',1);
/*!40000 ALTER TABLE `am_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approval_flow`
--

DROP TABLE IF EXISTS `approval_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `approval_flow` (
  `approval_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `contract_id` int(11) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`approval_id`),
  KEY `FK_ap_login_id_idx` (`login_id`),
  KEY `FK_ap_contract_id_idx` (`contract_id`),
  CONSTRAINT `FK_ap_contract_id` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ap_login_id` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval_flow`
--

LOCK TABLES `approval_flow` WRITE;
/*!40000 ALTER TABLE `approval_flow` DISABLE KEYS */;
/*!40000 ALTER TABLE `approval_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `band`
--

DROP TABLE IF EXISTS `band`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `band` (
  `band_id` int(11) NOT NULL AUTO_INCREMENT,
  `band_name` varchar(45) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`band_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `band`
--

LOCK TABLES `band` WRITE;
/*!40000 ALTER TABLE `band` DISABLE KEYS */;
INSERT INTO `band` VALUES (1,'P1',1),(2,'P2',1),(3,'P3',1),(4,'P4',1),(5,'P5',1),(6,'P6',1),(7,'Band2',1);
/*!40000 ALTER TABLE `band` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_line`
--

DROP TABLE IF EXISTS `business_line`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `business_line` (
  `businessline_id` int(11) NOT NULL AUTO_INCREMENT,
  `businessline_name` varchar(100) DEFAULT NULL,
  `resourcetype_id` int(11) DEFAULT NULL,
  `skill_id` int(11) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`businessline_id`),
  KEY `FK_resource_id_idx` (`resourcetype_id`),
  KEY `FK_skill_id_idx` (`skill_id`),
  CONSTRAINT `FK_resource_id` FOREIGN KEY (`resourcetype_id`) REFERENCES `resource_type` (`resourcetype_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_skill_id` FOREIGN KEY (`skill_id`) REFERENCES `skill` (`skill_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_line`
--

LOCK TABLES `business_line` WRITE;
/*!40000 ALTER TABLE `business_line` DISABLE KEYS */;
INSERT INTO `business_line` VALUES (1,'Security',1,NULL,1),(2,'Test Automation',1,NULL,1),(3,'Testing Services',1,NULL,1),(4,'Test & Quality Consulting Services',1,NULL,1),(5,'Oracle',1,NULL,1),(6,'BI & Analytics',1,NULL,1),(7,'Java',1,NULL,1),(8,'Microsoft',1,NULL,1),(9,'Mobile',1,NULL,1),(10,'SAP',1,NULL,1),(11,'Business Technology',1,NULL,1),(12,'Enterprise Engineering',1,NULL,1),(13,'Functioneel Beheer',1,NULL,1),(14,'Project- en Service Management',1,NULL,1),(15,'Infrastructure Services',1,NULL,1),(16,'High Tech',1,NULL,1),(17,'BT - Global Sourcing',2,1,1),(18,'PSM - Global Sourcing',2,1,1),(19,'Functioneel Beheer - Global Sourcing',2,1,1),(20,'Enterprise Engineering - Global Sourcing',2,1,1),(21,'Oracle - Global Sourcing',2,1,1),(22,'SAP - Global Sourcing',2,1,1),(23,'Microsoft - Global Sourcing',2,1,1),(24,'Java - Global Sourcing',2,1,1),(25,'Testing Services - Global Sourcing',2,1,1),(26,'Test Automation - Global Sourcing',2,1,1),(27,'TQCS - Global Sourcing',2,1,1),(28,'Security - Global Sourcing',2,2,1),(29,'ITS - Global Sourcing',2,2,1),(30,'BIM - Global Sourcing',2,3,1),(31,'Mobile - Global Sourcing',2,3,1),(32,'HT - Global Sourcing',2,3,1),(35,'Strategy',2,2,1),(36,'Happy Hours',2,1,1),(37,'Test Business line',6,1,1);
/*!40000 ALTER TABLE `business_line` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `contract_id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(100) DEFAULT NULL,
  `contract_name` varchar(100) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `login_id` int(11) DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  `contract_start_date` date DEFAULT NULL,
  `contract_end_date` date DEFAULT NULL,
  `contract_created_datetime` datetime DEFAULT NULL,
  `contract_modified_date_time` datetime DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`contract_id`),
  KEY `FK_c_login_id_idx` (`login_id`),
  KEY `FK_c_status_id_idx` (`status_id`),
  CONSTRAINT `FK_c_login_id` FOREIGN KEY (`login_id`) REFERENCES `login` (`login_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_c_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contract`
--

LOCK TABLES `contract` WRITE;
/*!40000 ALTER TABLE `contract` DISABLE KEYS */;
INSERT INTO `contract` VALUES (1,'Sogeti','SOgeti_KPN','KPN',1,1,'this is test contract','2016-04-28','2016-12-31',NULL,NULL,1),(2,'KPN','Sogeti_KPN','KPN',1,1,'rgasgsdfgsdf','2016-04-28','2016-12-31',NULL,NULL,1),(3,'Kishore','SogKishore','Capgemini',1,1,'This is my contract.','2016-05-24','2017-01-10',NULL,NULL,1),(4,'Philips','Sogeti_Philips','kishore',1,1,'my comments','2016-05-02','2016-11-30',NULL,NULL,1),(5,'Kingston','Sogeti-KPN','',1,1,'a Test contract','2016-05-03','2016-12-30',NULL,NULL,1),(6,'Seagate','Cap_Seagate','',1,1,'this is test','2016-05-03','2016-12-31',NULL,NULL,1),(7,'test','test1','',1,1,'test test','2016-05-04','2016-12-31',NULL,NULL,1),(8,'test1','teste2','',1,1,'test contract','2016-05-03','2016-12-31',NULL,NULL,1),(9,'test2','test3','',1,1,'one of the commented contract','2016-05-15','2016-12-31',NULL,NULL,1),(10,'Philips','Sog_Philips','',1,1,'This is test.','2016-05-10','2016-12-30',NULL,NULL,1),(11,'test','test2','',1,1,'this is stest','2016-05-08','2016-12-31',NULL,NULL,1),(13,'temp1','temp2','',1,1,'tersatata','2016-05-09','2016-05-26',NULL,NULL,1),(14,'Test10','Test100','',1,1,'sargad gfsadf','2016-05-09','2017-05-08',NULL,NULL,1),(15,'Test4','Test4','',1,1,'A full year contract','2016-05-30','2016-10-31',NULL,NULL,1),(16,'Kalyan','Sogeti_kalyan','',1,1,'A simple test','2016-05-31','2017-01-31',NULL,NULL,1),(17,'Kishore','Contract_kishore','',1,1,'kjugkjg','2016-05-09','2018-05-16',NULL,NULL,1),(18,'ASML','Kalyan_FAN','',1,1,'Testing Demo editted','2016-05-24','2017-12-31',NULL,NULL,1),(19,'','','',NULL,1,'',NULL,NULL,NULL,NULL,1),(20,'blabla','Snel Contract','',NULL,1,'a special comments','2016-07-04','2018-07-10',NULL,NULL,1);
/*!40000 ALTER TABLE `contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_contract`
--

DROP TABLE IF EXISTS `fixed_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fixed_contract` (
  `fixedcontract_id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_id` int(11) DEFAULT NULL,
  `fixed_cost_id` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`fixedcontract_id`),
  KEY `FK_f_contract_id_idx` (`contract_id`),
  KEY `FK_f_fixedcost_id_idx` (`fixed_cost_id`),
  CONSTRAINT `FK_f_contract_id` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_f_fixedcost_id` FOREIGN KEY (`fixed_cost_id`) REFERENCES `fixed_cost` (`fixedcost_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_contract`
--

LOCK TABLES `fixed_contract` WRITE;
/*!40000 ALTER TABLE `fixed_contract` DISABLE KEYS */;
INSERT INTO `fixed_contract` VALUES (3,2,2,100.00,'To support application with issues and new request need this set up.',1),(4,2,3,600.00,'This setup is required for support application.',1),(5,5,4,900.00,'This is test setup tool for the application support and maintenance.',1),(6,18,2,10.00,'To support application with issues and new request need this set up. editted',1),(7,18,3,124.00,'This setup is required for support application. Editted',1),(15,16,2,232.00,'To support application with issues and new request need this set up.',1),(16,20,3,90.00,'This setup is required for support application.bla bla',1);
/*!40000 ALTER TABLE `fixed_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fixed_cost`
--

DROP TABLE IF EXISTS `fixed_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fixed_cost` (
  `fixedcost_id` int(11) NOT NULL AUTO_INCREMENT,
  `fixedcost_name` varchar(100) DEFAULT NULL,
  `fixedcost_description` varchar(500) DEFAULT NULL,
  `active` int(1) GENERATED ALWAYS AS (1) VIRTUAL,
  PRIMARY KEY (`fixedcost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fixed_cost`
--

LOCK TABLES `fixed_cost` WRITE;
/*!40000 ALTER TABLE `fixed_cost` DISABLE KEYS */;
INSERT INTO `fixed_cost` (`fixedcost_id`, `fixedcost_name`, `fixedcost_description`) VALUES (2,'Setup Service Desk','To support application with issues and new request need this set up.'),(3,'Setup ITMS Tooling','This setup is required for support application.'),(4,'Test2','This is test setup tool for the application support and maintenance.');
/*!40000 ALTER TABLE `fixed_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_type` varchar(45) DEFAULT NULL,
  `active` int(1) DEFAULT '1',
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'1',1),(2,'2',1),(3,'3',1),(4,'4',1),(5,'5',1),(6,'6',1),(7,'7',1),(8,'8',1),(9,'9',1),(10,'10',1),(11,'grade2',1);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kt_contract`
--

DROP TABLE IF EXISTS `kt_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kt_contract` (
  `kt_contract_id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_id` int(11) DEFAULT NULL,
  `onshore_price_id` int(11) DEFAULT NULL,
  `offshore_price_id` int(11) DEFAULT NULL,
  `details_xml` text,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`kt_contract_id`),
  KEY `FK_kt_contract_id_idx` (`contract_id`),
  KEY `FK_kt_onshore_price_id_idx` (`onshore_price_id`),
  KEY `FK_kt_offshore_price_id_idx` (`offshore_price_id`),
  CONSTRAINT `FK_kt_contract_id` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_kt_offshore_price_id` FOREIGN KEY (`offshore_price_id`) REFERENCES `offshore_price` (`offshoreprice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_kt_onshore_price_id` FOREIGN KEY (`onshore_price_id`) REFERENCES `onshore_price` (`onshoreprice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kt_contract`
--

LOCK TABLES `kt_contract` WRITE;
/*!40000 ALTER TABLE `kt_contract` DISABLE KEYS */;
INSERT INTO `kt_contract` VALUES (1,20,NULL,1,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>2</id><months><month><name>Jul 16</name><total>139500.0</total><weeks><w1>400.0</w1><w2>400.0</w2><w3>450.0</w3><w4>300.0</w4></weeks></month><month><name>Aug 16</name><total>139500.0</total><weeks><w1>400.0</w1><w2>400.0</w2><w3>450.0</w3><w4>300.0</w4></weeks></month><month><name>Sep 16</name><total>144000.0</total><weeks><w1>400.0</w1><w2>450.0</w2><w3>450.0</w3><w4>300.0</w4></weeks></month><month><name>Oct 16</name><total>139500.0</total><weeks><w1>400.0</w1><w2>400.0</w2><w3>450.0</w3><w4>300.0</w4></weeks></month><month><name>Nov 16</name><total>139500.0</total><weeks><w1>400.0</w1><w2>400.0</w2><w3>450.0</w3><w4>300.0</w4></weeks></month><month><name>Dec 16</name><total>139500.0</total><weeks><w1>400.0</w1><w2>400.0</w2><w3>450.0</w3><w4>300.0</w4></weeks></month></months></Resource>',1),(2,20,3,NULL,'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><Resource><id>2</id><months><month><name>Jul 16</name><total>5429600.0</total><weeks><w1>1000.0</w1><w2>1300.0</w2><w3>1200.0</w3><w4>900.0</w4></weeks></month><month><name>Aug 16</name><total>5429600.0</total><weeks><w1>1000.0</w1><w2>1300.0</w2><w3>1200.0</w3><w4>900.0</w4></weeks></month><month><name>Sep 16</name><total>5528320.0</total><weeks><w1>1000.0</w1><w2>1300.0</w2><w3>1280.0</w3><w4>900.0</w4></weeks></month><month><name>Oct 16</name><total>5429600.0</total><weeks><w1>1000.0</w1><w2>1300.0</w2><w3>1200.0</w3><w4>900.0</w4></weeks></month><month><name>Nov 16</name><total>5429600.0</total><weeks><w1>1000.0</w1><w2>1300.0</w2><w3>1200.0</w3><w4>900.0</w4></weeks></month><month><name>Dec 16</name><total>5429600.0</total><weeks><w1>1000.0</w1><w2>1300.0</w2><w3>1200.0</w3><w4>900.0</w4></weeks></month></months></Resource>',1);
/*!40000 ALTER TABLE `kt_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `user_role_id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL,
  `last_login_datetime` datetime DEFAULT NULL,
  `user_type_id` varchar(255) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`login_id`),
  KEY `FK_user_role_idx` (`user_role_id`),
  CONSTRAINT `FK_user_role` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`user_role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'Sachin','Jaibhay','sachin@gmail.com','sachin',1,0,NULL,NULL,1),(2,'Ram','Kishore','ramkishore@gmail.com','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae',1,0,'2016-07-26 12:23:55',NULL,1),(3,'Pattabhi','Mohan','mohan@gmail.com','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae',1,0,NULL,NULL,1),(4,'Raju','Kalyan','kalyanraju@gmail.com','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae',1,0,NULL,NULL,1),(5,'kittu','lovely','kittu@gmail.com','ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae',2,NULL,'2016-07-26 13:30:30',NULL,1);
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offshore_price`
--

DROP TABLE IF EXISTS `offshore_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `offshore_price` (
  `offshoreprice_id` int(11) NOT NULL AUTO_INCREMENT,
  `businessline_id` int(11) DEFAULT NULL,
  `band_id` int(11) DEFAULT NULL,
  `stay_type_id` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated_datetime` datetime DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`offshoreprice_id`),
  KEY `FK_businessline_idx` (`businessline_id`),
  KEY `FK_band_id_idx` (`band_id`),
  KEY `FK_stay_type_id_idx` (`stay_type_id`),
  CONSTRAINT `FK_band_id` FOREIGN KEY (`band_id`) REFERENCES `band` (`band_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_businessline_id` FOREIGN KEY (`businessline_id`) REFERENCES `business_line` (`businessline_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_stay_type_id` FOREIGN KEY (`stay_type_id`) REFERENCES `stay_type` (`stay_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offshore_price`
--

LOCK TABLES `offshore_price` WRITE;
/*!40000 ALTER TABLE `offshore_price` DISABLE KEYS */;
INSERT INTO `offshore_price` VALUES (1,17,1,1,90.00,NULL,NULL,NULL,1),(2,19,3,2,120.00,NULL,NULL,NULL,1),(3,10,6,1,505.00,'A sample price tag editted',2,'2016-05-17 17:24:22',1),(4,1,6,3,180.00,'A sample price tag for offshore resource',2,'2016-05-18 12:53:04',1);
/*!40000 ALTER TABLE `offshore_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `onshore_price`
--

DROP TABLE IF EXISTS `onshore_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `onshore_price` (
  `onshoreprice_id` int(11) NOT NULL AUTO_INCREMENT,
  `businessline_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `last_updated_by` int(11) DEFAULT NULL,
  `last_updated_datetime` datetime DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`onshoreprice_id`),
  KEY `FK_businessline_is_idx` (`businessline_id`),
  KEY `FK_on_role_id_idx` (`role_id`),
  KEY `FK_on_grade_id_idx` (`grade_id`),
  CONSTRAINT `FK_on_businessline_id` FOREIGN KEY (`businessline_id`) REFERENCES `business_line` (`businessline_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_on_grade_id` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`grade_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_on_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `onshore_price`
--

LOCK TABLES `onshore_price` WRITE;
/*!40000 ALTER TABLE `onshore_price` DISABLE KEYS */;
INSERT INTO `onshore_price` VALUES (1,10,1,1,78.00,NULL,NULL,NULL,1),(2,12,2,3,50.00,NULL,NULL,NULL,1),(3,1,4,5,1234.00,'sdt hsgs g',1,'2016-05-09 13:24:35',1),(4,7,2,2,200.00,'test',2,'2016-05-20 13:21:05',1),(5,16,10,11,1500.00,'Test2q',2,'2016-05-20 13:21:50',1);
/*!40000 ALTER TABLE `onshore_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_type`
--

DROP TABLE IF EXISTS `resource_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource_type` (
  `resourcetype_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_type` varchar(45) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`resourcetype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_type`
--

LOCK TABLES `resource_type` WRITE;
/*!40000 ALTER TABLE `resource_type` DISABLE KEYS */;
INSERT INTO `resource_type` VALUES (1,'Onshore',1),(2,'Offshore',1),(6,'New Resource',1);
/*!40000 ALTER TABLE `resource_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `risk_comments`
--

DROP TABLE IF EXISTS `risk_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `risk_comments` (
  `risk_id` int(11) NOT NULL AUTO_INCREMENT,
  `contract_id` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`risk_id`),
  KEY `FK_contract_id_idx` (`contract_id`),
  CONSTRAINT `FK_contract_id` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`contract_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `risk_comments`
--

LOCK TABLES `risk_comments` WRITE;
/*!40000 ALTER TABLE `risk_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `risk_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_type` varchar(45) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'1',1),(2,'2',1),(3,'3',1),(4,'4',1),(5,'5',1),(6,'6',1),(7,'7',1),(8,'8',1),(9,'9',1),(10,'Role',1),(11,'Developer',1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill`
--

DROP TABLE IF EXISTS `skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skill` (
  `skill_id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(45) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`skill_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill`
--

LOCK TABLES `skill` WRITE;
/*!40000 ALTER TABLE `skill` DISABLE KEYS */;
INSERT INTO `skill` VALUES (1,'AM',1),(2,'IS',1),(3,'Primitive Skills',1),(4,'Secondary skills',1),(5,'Traditional Editted',1);
/*!40000 ALTER TABLE `skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(45) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'In Progress','Awaiting approval',1),(2,'Approved','Compltely Approved',1);
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stay_type`
--

DROP TABLE IF EXISTS `stay_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stay_type` (
  `stay_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `stay_type` varchar(45) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`stay_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stay_type`
--

LOCK TABLES `stay_type` WRITE;
/*!40000 ALTER TABLE `stay_type` DISABLE KEYS */;
INSERT INTO `stay_type` VALUES (1,'Long',1),(2,'Short',1),(3,'Standard',1),(4,'Quater1',1);
/*!40000 ALTER TABLE `stay_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(50) DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'Admin',1),(2,'User',1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'adfdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-26 14:41:13
