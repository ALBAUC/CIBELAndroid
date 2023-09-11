-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: appwise_db
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `aplicacion`
--

DROP TABLE IF EXISTS `aplicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aplicacion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `icono` varchar(500) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `fk_categoria` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgnmhaqdvbdvv139ad1nhsx6fi` (`fk_categoria`),
  CONSTRAINT `FKgnmhaqdvbdvv139ad1nhsx6fi` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aplicacion`
--

LOCK TABLES `aplicacion` WRITE;
/*!40000 ALTER TABLE `aplicacion` DISABLE KEYS */;
INSERT INTO `aplicacion` VALUES (1,'https://i.imgur.com/bbtqAl0.jpg','Phone Cleaner: Virus Cleaner',1),(2,'https://i.imgur.com/rf9TmMh.jpg','CCleaner - Phone Cleaner',1),(3,'https://i.imgur.com/sPxnIsb.jpg','Avast Cleanup - Phone Cleaner',1),(4,'https://i.imgur.com/LJsU1Yp.jpg','Root Booster',1),(5,'https://i.imgur.com/5hnbLVW.jpg','SD Maid - System Cleaning Tool',1),(6,'https://i.imgur.com/zsn1F6B.jpg','WhatsApp Messenger',2),(7,'https://i.imgur.com/qXtYAwK.jpg','Skype',11),(8,'https://i.imgur.com/PHbvsnd.jpg','Signal Private Messenger',2),(9,'https://i.imgur.com/LcTY7gr.jpg','Telegram',2),(10,'https://i.imgur.com/5Z8uDNj.jpg','Viber - Safe Chats And Calls',2),(11,'https://i.imgur.com/2jJE0Km.jpg','Facebook',3),(12,'https://i.imgur.com/MNaIwAy.jpg','Instagram',3),(13,'https://i.imgur.com/VkvFROC.jpg','Twitter',3),(14,'https://i.imgur.com/nVaiL7E.jpg','Linkedin: Jobs & Business News',3),(15,'https://i.imgur.com/xXQLgov.jpg','Snapchat',3),(16,'https://i.imgur.com/OfqcmZ8.jpg','Netflix',4),(17,'https://i.imgur.com/O5HTVvE.jpg','Spotify: Music and Podcasts',4),(18,'https://i.imgur.com/0OVhDtG.jpg','YouTube',4),(19,'https://i.imgur.com/ad7rJ7z.jpg','Twitch: Live Game Streaming',4),(20,'https://i.imgur.com/ILHC6Pp.jpg','TikTok: Retos, Videos & Música',4),(21,'https://i.imgur.com/8XZ2ZqA.jpg','Candy Crush Saga',5),(22,'https://i.imgur.com/OxRlNOd.jpg','Clash of Clans',5),(23,'https://i.imgur.com/3ZERQr4.jpg','Fruit Ninja',5),(24,'https://i.imgur.com/WhGkrjK.jpg','Chinchón Blyts',5),(25,'https://i.imgur.com/PPMXyDr.jpg','Hay Day',5),(26,'https://i.imgur.com/hAcY8oM.jpg','Norton360 Antivirus & Security',9),(27,'https://i.imgur.com/JP74Q5N.jpg','Avast Antivirus & Security',9),(28,'https://i.imgur.com/OnXnLTf.jpg','Lookout Security and Antivirus',9),(29,'https://i.imgur.com/861YHPq.jpg','McAfee Security: Virus Scanner',9),(30,'https://i.imgur.com/wRbovRC.jpg','Kaspersky Antivirus & VPN',9),(31,'https://i.imgur.com/vvRiUuV.jpg','Speed test - Speed Test Master',10),(32,'https://i.imgur.com/xVP17T4.jpg','WiFi Analyzer',10),(33,'https://i.imgur.com/tvstpMT.jpg','Fing - Network Tools',10),(34,'https://i.imgur.com/U5bi3y0.jpg','NETGEAR Nightawk - WiFi Route',10),(35,'https://i.imgur.com/5qN1JZ4.jpg','Opensignal - 5G, 4G Speed Test',10),(36,'https://i.imgur.com/10xzHz6.jpg','KakaoTalk: Messenger',2),(37,'https://i.imgur.com/af90BuH.jpg','Zoom - One Platform to Connect',11),(38,'https://i.imgur.com/tLgko7v.jpg','Google Meet',11),(39,'https://i.imgur.com/4YRrKru.jpg','Discord: Talk, Chat & Hang Out',11),(40,'https://i.imgur.com/8DJwfzp.jpg','TeamSpeak 3 - Voice Chat Softw',11),(41,'https://i.imgur.com/bUsv2c4.jpg','Microsoft Word: Edit Documents',12),(42,'https://i.imgur.com/AT5Z3LB.jpg','Todoist: to-do list & planner',12),(43,'https://i.imgur.com/jXwefaC.jpg','Trello: Manage Team Projects',12),(44,'https://i.imgur.com/Vve6unO.jpg','Evernote - Note Organizer',12),(45,'https://i.imgur.com/VTLq0o9.jpg','Adobe Acrobat Reader: Edit PDF',12),(46,'https://i.imgur.com/4nUH6va.jpg','Calm - Sleep, Meditate, Relax',13),(47,'https://i.imgur.com/yW15fDG.jpg','MyFitnessPal: Calorie Counter',13),(48,'https://i.imgur.com/ih8QJMT.jpg','Headspace: Mindful Meditation',13),(49,'https://i.imgur.com/D9AcYps.jpg','FitToFit',13),(50,'https://i.imgur.com/8m4NSls.jpg','Lose Weight App for Men',13);
/*!40000 ALTER TABLE `aplicacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Herramientas de Sistema'),(2,'Comunicación y Mensajería'),(3,'Redes Sociales'),(4,'Entretenimiento'),(5,'Juegos'),(9,'Herramientas de Seguridad'),(10,'Redes y Conexiones'),(11,'Comunicación por voz'),(12,'Productividad'),(13,'Salud y Bienestar');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_x_riesgo`
--

DROP TABLE IF EXISTS `categoria_x_riesgo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_x_riesgo` (
  `fk_categoria` bigint NOT NULL,
  `fk_riesgo` bigint NOT NULL,
  KEY `FKtipnr01996h566iw8qejblmbc` (`fk_riesgo`),
  KEY `FKj9eyfre80405p3dixacktvtb0` (`fk_categoria`),
  CONSTRAINT `FKj9eyfre80405p3dixacktvtb0` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FKtipnr01996h566iw8qejblmbc` FOREIGN KEY (`fk_riesgo`) REFERENCES `riesgo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_x_riesgo`
--

LOCK TABLES `categoria_x_riesgo` WRITE;
/*!40000 ALTER TABLE `categoria_x_riesgo` DISABLE KEYS */;
INSERT INTO `categoria_x_riesgo` VALUES (3,4),(3,5),(3,6),(3,7),(3,8),(3,9),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(11,2),(11,3),(11,4),(11,5),(11,7),(11,13),(4,9),(4,10),(4,11),(4,13),(5,9),(5,10),(5,11),(5,13),(12,1),(12,2),(12,3),(12,7),(12,13),(9,1),(9,2),(9,3),(9,7),(9,13),(1,1),(1,2),(1,3),(1,13),(10,2),(10,3),(10,4),(10,5),(10,7),(10,13),(13,1),(13,2),(13,3),(13,4),(13,7),(13,13);
/*!40000 ALTER TABLE `categoria_x_riesgo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control`
--

DROP TABLE IF EXISTS `control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(1000) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control`
--

LOCK TABLES `control` WRITE;
/*!40000 ALTER TABLE `control` DISABLE KEYS */;
INSERT INTO `control` VALUES (1,'Descarga aplicaciones sólo de fuentes confiables, como la App Store de Apple o la Google Play Store.','Fuentes confiables'),(2,'Revisa las reseñas y calificaciones de la aplicación antes de descargarla para asegurarte de que otros usuarios la hayan encontrado segura y confiable.','Revisión previa'),(3,'Lee la política de privacidad y los términos y condiciones de la aplicación para comprender cómo se manejará tu información personal.','Política de privacidad'),(4,'Configura una contraseña segura para el dispositivo móvil y no la compartas con nadie.','Contraseña segura'),(5,'Evita conectarte a redes Wi-Fi públicas no seguras, o utiliza una VPN para proteger tu conexión.','Redes Wi-Fi seguras'),(6,'Configura la función de ubicación de manera adecuada y sólo permite que las aplicaciones accedan a tu ubicación cuando sea necesario.','Función de ubicación'),(7,'Evita hacer clic en enlaces sospechosos o abrir correos electrónicos de remitentes desconocidos.','Enlaces sospechosos'),(8,'No compartas información personal, como contraseñas o números de tarjeta de crédito, con nadie.','Información personal'),(9,'Configura las opciones de privacidad de las redes sociales y otras aplicaciones para limitar la información compartida.','Privacidad'),(10,'Actualiza regularmente las aplicaciones y el sistema operativo del dispositivo para protegerte contra vulnerabilidades conocidas.','Actualizaciones'),(11,'Instala un software antivirus en el dispositivo móvil para protegerte contra virus, malware y otras amenazas de seguridad.','Antivirus'),(12,'Configura filtros para el correo electrónico y mensajes de texto para bloquear mensajes de remitentes desconocidos o sospechosos.','Filtros de correo'),(13,'Configura la autenticación de dos factores para cuentas de aplicaciones que lo permitan, para agregar una capa adicional de seguridad.','Autenticación de dos factores'),(14,'Desactiva la función de Bluetooth cuando no se esté utilizando para evitar la intercepción de datos.','Bluetooth'),(15,'Realiza copias de seguridad periódicas de los datos y aplicaciones importantes en caso de pérdida o robo del dispositivo.','Copias de seguridad');
/*!40000 ALTER TABLE `control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_x_riesgo`
--

DROP TABLE IF EXISTS `control_x_riesgo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_x_riesgo` (
  `fk_riesgo` bigint NOT NULL,
  `fk_control` bigint NOT NULL,
  KEY `FKgchofyh9eoh6ushd3jrbsqogm` (`fk_control`),
  KEY `FK2ge3v90le2s5dgny9ggttihli` (`fk_riesgo`),
  CONSTRAINT `FK2ge3v90le2s5dgny9ggttihli` FOREIGN KEY (`fk_riesgo`) REFERENCES `riesgo` (`id`),
  CONSTRAINT `FKgchofyh9eoh6ushd3jrbsqogm` FOREIGN KEY (`fk_control`) REFERENCES `control` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_x_riesgo`
--

LOCK TABLES `control_x_riesgo` WRITE;
/*!40000 ALTER TABLE `control_x_riesgo` DISABLE KEYS */;
INSERT INTO `control_x_riesgo` VALUES (1,1),(1,3),(1,4),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(2,1),(2,2),(2,3),(2,4),(2,6),(2,7),(2,8),(2,10),(2,11),(2,12),(2,13),(3,1),(3,3),(3,6),(3,7),(3,8),(3,10),(3,11),(3,12),(3,13),(4,1),(4,2),(4,3),(4,4),(4,6),(4,7),(4,8),(4,9),(4,10),(4,11),(4,12),(4,13),(5,4),(5,6),(5,7),(5,8),(5,9),(5,10),(5,11),(5,12),(5,13),(6,1),(6,3),(6,4),(6,6),(6,7),(6,8),(6,9),(6,10),(6,11),(6,12),(6,13),(7,1),(7,3),(7,4),(7,6),(7,7),(7,8),(7,9),(7,10),(7,11),(7,12),(7,13),(8,1),(8,3),(8,4),(8,6),(8,7),(8,8),(8,9),(8,10),(8,11),(8,12),(8,13),(9,1),(9,3),(9,4),(9,6),(9,7),(9,8),(9,9),(9,10),(9,11),(9,12),(9,13),(10,1),(10,2),(10,3),(10,4),(10,6),(10,7),(10,8),(10,10),(10,11),(10,12),(10,13),(11,1),(11,2),(11,3),(11,4),(11,6),(11,7),(11,8),(11,10),(11,11),(11,12),(11,13),(12,1),(12,2),(12,3),(12,4),(12,6),(12,7),(12,8),(12,10),(12,11),(12,12),(12,13),(13,1),(13,2),(13,3),(13,4),(13,6),(13,7),(13,8),(13,10),(13,11),(13,12),(13,13),(5,9),(10,9),(11,9),(12,9);
/*!40000 ALTER TABLE `control_x_riesgo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `riesgo`
--

DROP TABLE IF EXISTS `riesgo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `riesgo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(1000) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `riesgo`
--

LOCK TABLES `riesgo` WRITE;
/*!40000 ALTER TABLE `riesgo` DISABLE KEYS */;
INSERT INTO `riesgo` VALUES (1,'Este riesgo se refiere a la posibilidad de que tus datos personales, como tu nombre, correo electrónico, dirección o número de teléfono, sean compartidos o expuestos a personas no autorizadas. Por ejemplo, si una aplicación solicita tu información personal sin una buena razón, es posible que tus datos se compartan sin tu consentimiento.','Exposición de datos personales'),(2,'Este riesgo se refiere a la posibilidad de que alguien acceda a tus datos personales sin tu permiso. Puede ocurrir si un atacante roba tu información de inicio de sesión o encuentra una vulnerabilidad en la aplicación que le permita acceder a tus datos.','Acceso no autorizado a los datos del usuario'),(3,'Este riesgo se refiere a la posibilidad de que un tercero pueda ver o interceptar los datos que estás compartiendo a través de la aplicación. Esto puede suceder si la aplicación no utiliza medidas de seguridad adecuadas, como el cifrado de datos, para proteger tu información.','Intercepción de datos'),(4,'Este riesgo se refiere a la posibilidad de que tus datos personales o información privada se publiquen sin tu permiso. Esto puede ocurrir si compartes información privada en una plataforma pública o si alguien comparte tu información sin tu consentimiento.','Publicación de información privada'),(5,'Este riesgo se refiere a la posibilidad de que una aplicación recopile y comparta tu ubicación sin tu conocimiento o consentimiento. Esto puede ser utilizado por terceros para rastrear tus movimientos y puede ser una invasión de tu privacidad.','Seguimiento de ubicación'),(6,'Este riesgo se refiere a la posibilidad de que un atacante intente engañarte para que reveles información confidencial, como contraseñas o información bancaria. Puede ocurrir a través de correos electrónicos, mensajes de texto o llamadas telefónicas falsas que parecen legítimas.','Phishing'),(7,'Este riesgo se refiere a la posibilidad de que recibas mensajes no deseados o correo basura en tu aplicación. Esto puede ser molesto y consumir tu tiempo y recursos.','Spam'),(8,'Este riesgo se refiere a la posibilidad de que alguien te acose, intimide o hostigue a través de la aplicación. Esto puede ser especialmente preocupante para los jóvenes o para aquellos que experimentan acoso.','Ciberacoso'),(9,'Este riesgo se refiere a la posibilidad de que una aplicación presente anuncios que puedan ser engañosos o falsos. Por ejemplo, un anuncio que promete una oferta gratuita, pero en realidad requiere que pagues una suscripción.','Publicidad engañosa'),(10,'Este riesgo se refiere a la posibilidad de que una aplicación te engañe o intente estafarte para que pagues dinero o reveles información financiera. Esto puede ocurrir a través de aplicaciones falsas que se hacen pasar por aplicaciones legítimas, o de aplicaciones que intentan engañarte para que reveles información personal o financiera.','Fraude financiero'),(11,'Algunas aplicaciones permiten realizar compras desde dentro de la aplicación, lo que se conoce como compras in-app. Existe un riesgo de que alguien no autorizado pueda realizar compras con tu cuenta de usuario, lo que puede resultar en cargos no deseados en tu tarjeta de crédito u otra forma de pago. Asegúrate de revisar tus estados de cuenta regularmente y denunciar cualquier compra no autorizada.','Compras in-app no autorizadas'),(12,'Las aplicaciones también pueden incluir enlaces a tiendas en línea donde se pueden realizar compras. Existe un riesgo de que alguien no autorizado pueda realizar compras con tu cuenta de usuario, lo que puede resultar en cargos no deseados en tu tarjeta de crédito u otra forma de pago. Asegúrate de revisar tus estados de cuenta regularmente y denunciar cualquier compra no autorizada.','Compras no autorizadas'),(13,'Las aplicaciones pueden tener vulnerabilidades de seguridad que pueden ser explotadas por atacantes para robar información personal o realizar actividades maliciosas en tu dispositivo. Es importante que siempre mantengas tu aplicación actualizada con las últimas versiones de seguridad, y que estés atento a cualquier mensaje o notificación sobre vulnerabilidades de seguridad en la aplicación.','Vulnerabilidades de seguridad en las aplicaciones');
/*!40000 ALTER TABLE `riesgo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-08 11:18:17
