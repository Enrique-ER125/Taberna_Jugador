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
-- Table structure for table `ficha_jugador`
--

DROP TABLE IF EXISTS `ficha_jugador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ficha_jugador` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `descripcion` varchar(1000) DEFAULT NULL,
  `historia` text,
  `id_estadistica` int DEFAULT NULL,
  `id_juego` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  `nivel` decimal(5,0) DEFAULT NULL,
  `experiencia` decimal(7,0) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_estadistica` (`id_estadistica`),
  KEY `id_juego` (`id_juego`),
  CONSTRAINT `ficha_jugador_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `ficha_jugador_ibfk_2` FOREIGN KEY (`id_estadistica`) REFERENCES `estaditicas` (`id`),
  CONSTRAINT `ficha_jugador_ibfk_3` FOREIGN KEY (`id_juego`) REFERENCES `juego` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ficha_jugador`
--

LOCK TABLES `ficha_jugador` WRITE;
/*!40000 ALTER TABLE `ficha_jugador` DISABLE KEYS */;
INSERT INTO `ficha_jugador` VALUES (5,'Prueba1','string','string',NULL,1,2,NULL,180,0),(9,'Prueba 2',NULL,'string',NULL,1,3,NULL,102,0);
/*!40000 ALTER TABLE `ficha_jugador` ENABLE KEYS */;
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
