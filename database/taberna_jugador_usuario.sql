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
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `contrasena` varchar(9) DEFAULT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `id_grupo` int DEFAULT NULL,
  `id_juego` int DEFAULT NULL,
  `id_usuario` int DEFAULT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_grupo` (`id_grupo`),
  KEY `id_juego` (`id_juego`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`id_grupo`) REFERENCES `grupo` (`id`),
  CONSTRAINT `usuario_ibfk_3` FOREIGN KEY (`id_juego`) REFERENCES `juego` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'Usuario 1','contrasea','string',NULL,NULL,NULL,'http://localhost:8080/imagenes/tuerca.png'),(3,'Usuario 2','contrase','stri',NULL,NULL,NULL,NULL),(4,'Admin','admin','ho',NULL,NULL,NULL,'http://localhost:8080/imagenes/icono.jpg'),(5,'UsuarioEsplotable','con','Uusario para experimentos',NULL,NULL,NULL,'http://localhost:8080/imagenes/xplotable.png'),(7,'Invitado','invitado','Es un usuario de prueba no podrá ni crear ni edita',NULL,NULL,NULL,'http://localhost:8080/imagenes/invitado.png');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-04 18:20:43
