-- MySQL dump 10.13  Distrib 8.0.42, for Linux (x86_64)
--
-- Host: localhost    Database: inmobiliaria
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'tati@tati.com','Tatiana'),(2,'sergio@sergio.com','Sergio'),(4,'nayara@nayara.com','nayara');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `id` int NOT NULL AUTO_INCREMENT,
  `property_id` int DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6oqm8c6mg2dgedoppcbu8sm5q` (`property_id`),
  CONSTRAINT `FK6oqm8c6mg2dgedoppcbu8sm5q` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,1,'rent01.png'),(2,2,'rent02.png'),(4,4,'rent04.png'),(7,7,'sale03.png'),(8,8,'sale04.png'),(9,9,'sale05.png'),(10,9,'sale05-2.png'),(11,10,'sale06.png'),(12,10,'sale06-2.png'),(13,10,'sale06-3.png'),(14,10,'sale06-4.png'),(18,12,'sale07.png'),(19,12,'sale07-2.png'),(20,12,'sale07-3.png'),(29,6,'sale02.png'),(30,6,'sale02-2.png'),(31,5,'sale01.png'),(33,3,'rent03.png');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Santa Ponsa'),(2,'El Toro'),(3,'Soller'),(4,'Palma'),(5,'Other');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `property` (
  `bathrooms` int NOT NULL,
  `bedrooms` int NOT NULL,
  `elevator` bit(1) NOT NULL,
  `employee_id` int DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `location_id` int DEFAULT NULL,
  `parking` bit(1) NOT NULL,
  `pool` bit(1) NOT NULL,
  `price` double NOT NULL,
  `publish_date` date DEFAULT NULL,
  `sqmt` double NOT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` enum('RENT','SALE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtpykyhlyhp3dri3686s1257c8` (`employee_id`),
  KEY `FK2ites4e9383wf41m3xlyyr3sb` (`location_id`),
  CONSTRAINT `FK2ites4e9383wf41m3xlyyr3sb` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `FKtpykyhlyhp3dri3686s1257c8` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (3,3,_binary '\0',1,1,1,_binary '',_binary '',10000,'2025-06-05',357,'0','House in Santa Ponsa','RENT'),(7,5,_binary '\0',1,2,1,_binary '',_binary '',15500,'2025-06-05',568,'0','House in Santa Ponsa','RENT'),(4,5,_binary '\0',1,3,2,_binary '',_binary '',18000,'2025-06-05',600,'0','House in Portals Nous','RENT'),(13,16,_binary '\0',1,4,3,_binary '',_binary '',19500,'2025-06-05',1576,'0','House in Soller','RENT'),(4,1,_binary '\0',2,5,1,_binary '\0',_binary '\0',250000,'2025-06-06',34,'5','Apartment in Santa Ponsa','SALE'),(4,4,_binary '\0',2,6,1,_binary '',_binary '',3700000,'2025-06-06',373,'0','House in Santa Ponsa','SALE'),(6,4,_binary '\0',2,7,1,_binary '',_binary '',9500000,'2025-06-06',750,'0','House in Santa Ponsa','SALE'),(4,4,_binary '\0',2,8,1,_binary '',_binary '\0',2500000,'2025-06-06',500,'0','House in Santa Ponsa','SALE'),(4,4,_binary '\0',2,9,1,_binary '',_binary '',9890000,'2025-06-06',512,'0','House in Santa Ponsa','SALE'),(3,3,_binary '\0',1,10,1,_binary '',_binary '',785000,'2025-06-06',121,'0','House in Santa Ponsa','SALE'),(1,2,_binary '',1,12,2,_binary '',_binary '\0',520000,'2025-06-11',70,'4','Apartment in El Toro','SALE');
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UKr1usl9qoplqsbrhha5e0niqng` (`employee_id`),
  CONSTRAINT `FK211dk0pe7l3aibwce8yy61ota` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'tati@tati.com','$2a$10$FP8wDRJyvWj8.eUao4RrHeJbywZjW0oXO5QGtT3IqmeTJ2Vi9W5E6',1),(2,'sergio@sergio.com','$2a$10$RWlCVSGxSJ5sZeCOtn9KYuFPo5nD/CgFcyRy4DHcJvr0TpTLEsxxa',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_seq`
--

DROP TABLE IF EXISTS `user_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_seq`
--

LOCK TABLES `user_seq` WRITE;
/*!40000 ALTER TABLE `user_seq` DISABLE KEYS */;
INSERT INTO `user_seq` VALUES (101);
/*!40000 ALTER TABLE `user_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-12 22:42:51
