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
-- Table structure for table `criaturas`
--

DROP TABLE IF EXISTS `criaturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `criaturas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `nivel` decimal(5,0) DEFAULT NULL,
  `rango` decimal(1,0) DEFAULT NULL,
  `historia` text,
  `id_estadistica` int DEFAULT NULL,
  `id_juego` int DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_estadistica` (`id_estadistica`),
  KEY `id_juego` (`id_juego`),
  CONSTRAINT `criaturas_ibfk_1` FOREIGN KEY (`id_estadistica`) REFERENCES `estaditicas` (`id`),
  CONSTRAINT `criaturas_ibfk_2` FOREIGN KEY (`id_juego`) REFERENCES `juego` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `criaturas`
--

LOCK TABLES `criaturas` WRITE;
/*!40000 ALTER TABLE `criaturas` DISABLE KEYS */;
INSERT INTO `criaturas` VALUES (3,'mqMag_01','Expecimen 01 para comprobar la asimilacion de hechizos en maquinas',26,1,'string',NULL,1,'http://localhost:8080/imagenes/mqMag_01.jpeg'),(8,'mqFis_02','Expecimen 03 para comprobar un booss maquina',50,1,'string',NULL,1,'http://localhost:8080/imagenes/mqMag_02.jpeg');
/*!40000 ALTER TABLE `criaturas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-04 18:20:44
