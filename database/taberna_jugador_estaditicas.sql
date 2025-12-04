-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: taberna_jugador
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `estaditicas`
--

DROP TABLE IF EXISTS `estaditicas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estaditicas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ataque_fisico` decimal(9,0) DEFAULT NULL,
  `salud` decimal(9,0) DEFAULT NULL,
  `ataque_magico` decimal(9,0) DEFAULT NULL,
  `mana` decimal(9,0) DEFAULT NULL,
  `defensa_fisica` decimal(9,0) DEFAULT NULL,
  `defensa_magica` decimal(9,0) DEFAULT NULL,
  `id_juego` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_juego` (`id_juego`),
  CONSTRAINT `estaditicas_ibfk_1` FOREIGN KEY (`id_juego`) REFERENCES `juego` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estaditicas`
--

LOCK TABLES `estaditicas` WRITE;
/*!40000 ALTER TABLE `estaditicas` DISABLE KEYS */;
INSERT INTO `estaditicas` VALUES (1,0,0,10,10,0,0,NULL),(3,1,5,10,10,0,0,NULL),(4,0,0,80,80,0,0,NULL),(5,21,24,10,100,0,0,NULL),(8,20,29,1,0,0,0,NULL),(9,50,50,1,1,0,0,NULL);
/*!40000 ALTER TABLE `estaditicas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-04 18:20:45
