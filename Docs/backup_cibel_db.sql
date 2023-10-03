-- MySQL dump 10.13  Distrib 8.1.0, for macos13 (x86_64)
--
-- Host: localhost    Database: cibel_db
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `activo`
--

DROP TABLE IF EXISTS `activo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `icono` varchar(500) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `fk_categoria` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo7jvg33gldxf460kybfaricgl` (`fk_categoria`),
  CONSTRAINT `FKo7jvg33gldxf460kybfaricgl` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activo`
--

LOCK TABLES `activo` WRITE;
/*!40000 ALTER TABLE `activo` DISABLE KEYS */;
INSERT INTO `activo` VALUES (1,'https://i.imgur.com/O0cnwUV.jpg','iPhone 12',4),(2,'https://i.imgur.com/2Txmbm9.jpg','TP-LINK Archer C50',4),(3,'https://i.imgur.com/wSJQS6n.jpg','Amazon echo dot',4),(4,'https://i.imgur.com/703D8Kf.jpg','HP OfficeJet Pro',4),(5,'https://i.imgur.com/q3hit4x.jpg','Lenovo ThinkPad T440s',4),(6,'https://i.imgur.com/fWhU1zr.jpg','Playstation 3',4),(7,'https://i.imgur.com/S15N7Ke.jpg','Samsung Smart TV X10P',4),(8,'https://i.imgur.com/RJBXOi9.jpg','Pebble Smartwatch',4),(9,'https://i.imgur.com/g5RMyjd.png','Radio Thermostat CT80',3),(10,'https://i.imgur.com/QbGQQRs.jpg','TPLink Tapo L530',2),(11,'https://i.imgur.com/YExRit4.jpg','Samsung Galaxy S5',4),(12,'https://i.imgur.com/O3UGkJZ.jpg','iPhone 13',4);
/*!40000 ALTER TABLE `activo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amenaza`
--

DROP TABLE IF EXISTS `amenaza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amenaza` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(2000) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amenaza`
--

LOCK TABLES `amenaza` WRITE;
/*!40000 ALTER TABLE `amenaza` DISABLE KEYS */;
INSERT INTO `amenaza` VALUES (1,'El fraude de identidad debe ser abordado no solo mediante la protección de credenciales, sino también mediante la protección de datos privados que podrían contribuir a la suplantación de identidad. Por lo tanto, esto también está cubierto por medidas de privacidad.','Fraude de identidad','DispositivoIoT'),(2,'Este tema típicamente puede ser abordado a través de campañas de concientización por parte de los proveedores y prestadores de servicios.','Correo electrónico no solicitado e infectado - Broma','DispositivoIoT'),(3,'Mecanismos de seguridad, multiplicidad de interfaces y notificaciones al usuario deben asegurar un nivel mínimo de robustez contra la denegación de servicio. La protección de las comunicaciones también aborda estas preocupaciones.','Denegación de servicio','DispositivoIoT'),(4,'La autodefensa debe abarcar las vulnerabilidades relacionadas con código malicioso. Otra forma de mitigar estas amenazas consiste en proporcionar protección adicional en la Red de Área del Hogar y gestionar relaciones de confianza para aislar dispositivos comprometidos.','Actividad de software/Código malicioso','DispositivoIoT'),(5,'Las funciones de administración de seguridad también deben estar disponibles (gestión de seguridad). En última instancia, el diseño debe llevar a interfaces de usuario comprensibles y utilizables. Los requisitos de privacidad y confidencialidad también requieren transparencia, para que el usuario esté consciente del tipo de datos que podrían estar potencialmente en riesgo.','Abuso de fuga de información','DispositivoIoT'),(6,'Las relaciones de confianza y reputación, asociadas a la protección de la comunicación, deben cubrir los riesgos de manipulaciones de información externa. No debe ser posible manipular el registro de auditoría.','Manipulación de información','DispositivoIoT'),(7,'La identificación, autenticación y autorización son requisitos generales para el acceso a funciones sensibles. Esto es especialmente importante en el caso de capacidades administrativas.','Acceso no autorizado al sistema/red de información - Uso no autorizado de la administración de dispositivos y sistemas - Abuso de autorizaciones','DispositivoIoT'),(8,'La gestión de seguridad también establece que la instalación de firmware debe estar protegida para asegurar su autenticidad e integridad.','Instalación no autorizada de software - Software malicioso','DispositivoIoT'),(9,'Las capacidades remotas de un dispositivo deben estar disponibles solo para usuarios autorizados y deben llevarse a cabo a través de canales seguros.','Actividad remota (ejecución)','DispositivoIoT'),(10,'Los dispositivos y servicios deben implementar comunicaciones seguras. Sin embargo, hay algunos dispositivos que carecen de capacidades para esto y, en el caso de \"war driving\", el atacante apunta a la Red de Área del Hogar. Por esta razón, la protección de red adicional en la Red de Área del Hogar es particularmente crítica con respecto a esta amenaza.','War driving','DispositivoIoT'),(11,'Los mecanismos de seguridad, la diversidad de interfaces y las notificaciones al usuario deben asegurar que los cortes de energía o de red no obstaculicen la seguridad.','Falta de recursos/electricidad - Corte de Internet - Corte de red – Huelga - Pérdida de servicios de soporte','DispositivoIoT'),(12,'Las funciones de administración de seguridad también deben estar disponibles (gestión de seguridad). Eventualmente, el diseño debería conducir a interfaces de usuario comprensibles y utilizables. Los requisitos de privacidad y confidencialidad también requieren transparencia, para que el usuario esté consciente del tipo de datos que podrían estar potencialmente en riesgo.','Fuga o intercambio de información - Uso o administración errónea de dispositivos y sistemas','DispositivoIoT'),(13,'La protección de los datos del usuario incluye medidas para asegurar que estos datos no se filtren accidentalmente. La orientación al usuario contribuye a educar al usuario final en este sentido.','Fuga de información','DispositivoIoT'),(14,'Los mecanismos de seguridad a prueba de fallos, la diversidad de interfaces y las notificaciones al usuario deben asegurar que los cortes de energía o de red no obstaculicen la seguridad.','Fallas o interrupciones de enlaces de comunicación (redes de comunicación) - Fallas o interrupciones del suministro principal - Fallas o interrupciones del suministro eléctrico','DispositivoIoT'),(15,'El diseño debe llevar a interfaces de usuario comprensibles y utilizables. La orientación al usuario también contribuye a ayudar al usuario a tomar decisiones de configuración sensatas.','Errores de configuración','DispositivoIoT');
/*!40000 ALTER TABLE `amenaza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aplicacion`
--

DROP TABLE IF EXISTS `aplicacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `aplicacion` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKtdtlbqiykwo00024cj2w8kav5` FOREIGN KEY (`id`) REFERENCES `activo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aplicacion`
--

LOCK TABLES `aplicacion` WRITE;
/*!40000 ALTER TABLE `aplicacion` DISABLE KEYS */;
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
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Constrained device - Class 0','DispositivoIoT'),(2,'Constrained device - Class 1','DispositivoIoT'),(3,'Constrained device - Class 2','DispositivoIoT'),(4,'High-capacity device','DispositivoIoT');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_x_control`
--

DROP TABLE IF EXISTS `categoria_x_control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_x_control` (
  `fk_categoria` bigint NOT NULL,
  `fk_control` bigint NOT NULL,
  KEY `FKs51q05jamgtu1scgbsy5vq4v1` (`fk_control`),
  KEY `FK5dpqvi8if2cr4d1mefs7p0t6q` (`fk_categoria`),
  CONSTRAINT `FK5dpqvi8if2cr4d1mefs7p0t6q` FOREIGN KEY (`fk_categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FKs51q05jamgtu1scgbsy5vq4v1` FOREIGN KEY (`fk_control`) REFERENCES `control` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_x_control`
--

LOCK TABLES `categoria_x_control` WRITE;
/*!40000 ALTER TABLE `categoria_x_control` DISABLE KEYS */;
INSERT INTO `categoria_x_control` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),(3,8),(3,9),(3,10),(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(4,10);
/*!40000 ALTER TABLE `categoria_x_control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control`
--

DROP TABLE IF EXISTS `control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(2000) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control`
--

LOCK TABLES `control` WRITE;
/*!40000 ALTER TABLE `control` DISABLE KEYS */;
INSERT INTO `control` VALUES (1,'Los usuarios deben verificar las autorizaciones otorgadas a los dispositivos y servicios para acceder e intercambiar datos. Esto es especialmente importante en caso de una actualización, donde los derechos de acceso pueden modificarse sin el consentimiento del usuario. Por ejemplo, los dispositivos y servicios pueden mostrar una vista completa de sus comunicaciones con dispositivos y servicios externos, su necesidad de utilizar datos privados, etc.','Protección de intercambio de datos: Asegurar los derechos de acceso','DispositivoIoT'),(2,'Las redes locales deben estar separadas siempre que sea posible. Cada una de estas redes debe estar protegida por un gateway dedicado (ya sea porque consisten en dos redes diferentes o porque consisten en dos VLAN diferentes gestionadas por un gateway).','Protección de intercambio de datos: Utilizar gateways para reducir la exposición de red de los dispositivos más débiles','DispositivoIoT'),(3,'Cumplir con los requisitos y recomendaciones del gateway AMI si se instala un Medidor Inteligente. En algunas arquitecturas la pasarela hacia la Infraestructura de Medición Avanzada se utiliza como una interfaz hacia la Red de Área del Hogar. Para asegurar el correcto funcionamiento de la AMI, los proveedores de Hogar Inteligente deberían:\r\n- implementar la protección requerida por la pasarela inteligente, si es necesaria; y\r\n- evitar la interrupción del servicio, por ejemplo, causando una denegación de servicio al utilizar demasiado ancho de banda.\r\n','Protección de intercambio de datos: Segregar las Redes del Hogar Inteligente y el AMI (infraestructura de medición avanzada)','DispositivoIoT'),(4,'Una vez que un dispositivo está en el mercado, el proveedor debe llevar a cabo una evaluación de vulnerabilidades y corregir las fallas de seguridad en consecuencia. La evaluación de vulnerabilidades debe incluir hallazgos del desarrollador, investigaciones en línea, avisos de CERTs, así como aportes de clientes e investigadores de seguridad. Se debe informar al usuario final sobre el período de soporte del dispositivo y sobre el fin del soporte para correcciones de seguridad.\r\nSe debe definir una política para el manejo de vulnerabilidades y concientización sobre divulgación. Los programas de recompensa por errores también pueden proporcionar un incentivo para los investigadores externos.\r\nLas vulnerabilidades conocidas deben ser corregidas. Un parche puede consistir en una solución temporal si el desarrollador aún no ha proporcionado una solución definitiva. Cuando las actualizaciones por aire no estén disponibles, se debe considerar un plan para retirar el producto, de modo que los proveedores puedan implementar el parche en los dispositivos.\r\nPara los servicios en línea que respaldan los dispositivos del Hogar Inteligente, debe ser posible revertir a un estado seguro.\r\nEn última instancia, las vulnerabilidades que afecten los datos de usuario deben comunicarse de la manera más transparente posible. La Opinión 03/2014 de la Unión Europea sobre la Notificación de Brechas de Datos Personales del Grupo de Trabajo del Artículo 29 proporciona ejemplos de tales situaciones.\r\n','Seguridad operativa y mantenimiento - Evaluación de vulnerabilidades: Realizar evaluaciones de vulnerabilidad','DispositivoIoT'),(5,'Los dispositivos y servicios realizaron suposiciones para asegurarse de que los requisitos de seguridad son suficientes. Se alienta a los proveedores y usuarios a verificar regularmente que estas suposiciones sigan siendo válidas. Por ejemplo:\r\n- Limitaciones en el uso del dispositivo (por ejemplo, un dispositivo determinado podría necesitar conectividad ZigBee para transmitir alarmas de seguridad, lo que implica que no podrá enviar alarmas cuando esté ubicado detrás de una pared muy gruesa). Los proveedores podrían descubrir que, en el campo, la señal requiere incluso condiciones más estrictas (paredes aún más delgadas, sin interferencias...). Necesitarán enviar a los usuarios información para que puedan verificar si su instalación es segura.\r\n- Propiedades asumidas del entorno (por ejemplo, suponer que todas las autoridades de certificación en el almacén de certificados son confiables y no están comprometidas). Los proveedores deben realizar una evaluación para poder eliminar una CA comprometida del almacén de certificados.\r\n- Propiedades asumidas de propiedades criptográficas (por ejemplo, asumir que un determinado algoritmo y tamaño de clave son suficientes para una tarea determinada). Los proveedores deberán verificar regularmente esta suposición, por ejemplo, si un nuevo ataque criptográfico pone en riesgo a los usuarios a menos que utilicen claves más largas o cambien sus conjuntos criptográficos.\r\n','Seguridad operativa y mantenimiento - Evaluación de vulnerabilidades: Verificar regularmente las suposiciones de seguridad durante el tiempo de vida','DispositivoIoT'),(6,'Las actualizaciones de seguridad ofrecen protección contra vulnerabilidades encontradas durante la vida útil de un dispositivo o aplicación. Sin embargo, esto tiene un costo, ya que el soporte de esta funcionalidad también proporciona un punto de entrada para un atacante. En particular, los proveedores deben:\r\n- Proporcionar actualizaciones de seguridad automáticas y oportunas.\r\n- Proteger las actualizaciones (típicamente a través de encriptación y firma digital). Los archivos de actualización no deben contener datos sensibles. La firma debe ser verificada antes de que se aplique la actualización. Proteger la aplicación de una actualización en el dispositivo. Un atacante no debería poder iniciar una instalación de firmware sin autorización.\r\n- Proteger la interfaz de actualización de seguridad contra ataques.\r\n','Seguridad operativa y mantenimiento - Actualizaciones de seguridad: Proteger el mecanismo de actualización de software','DispositivoIoT'),(7,'La facilidad de uso de la interfaz de usuario debe contribuir a ayudar a los usuarios a realizar tareas administrativas correctas. Tales interfaces pueden encontrarse, por ejemplo, en el propio dispositivo, en un portal web o a través de una aplicación móvil. La amigabilidad con el usuario ayuda a mantener un estado seguro en el dispositivo al:\r\n- Dar más posibilidades de que acciones simples como una actualización de firmware o cambios de contraseña sean realizadas efectivamente por el usuario.\r\n- Proporcionar una descripción precisa del estado de seguridad del servicio y explicar qué acciones pueden contribuir a mitigar posibles amenazas.\r\nLos proveedores y prestadores de servicios también deberían sensibilizar a sus usuarios sobre correos electrónicos infectados y bulos.\r\n','Seguridad operativa y mantenimiento - Protección de interfaces remotas: Proporcionar interfaces amigables para la gestión de seguridad de dispositivos y servicios','DispositivoIoT'),(8,'La protección de las interfaces de monitoreo remoto es crucial, ya que a menudo proporcionan un punto de entrada altamente privilegiado en un dispositivo. Esta protección incluye mecanismos de control de acceso y autenticación.','Seguridad operativa y mantenimiento - Protección de interfaces remotas: Proteger las interfaces de monitoreo remoto','DispositivoIoT'),(9,'En cuanto a los requisitos para infraestructuras remotas relacionadas con el Hogar Inteligente, no hay necesidades específicas en el caso de uso del Hogar Inteligente en comparación con los servicios en la nube habituales o las infraestructuras de soporte de dispositivos habituales.\r\nPor esta razón, las principales recomendaciones consistirían en:\r\n- Gestión de seguridad: Como regla general, implementar un sistema de gestión de seguridad de la información (SGSI) como se describe en la norma ISO 27001. Se aplican las recomendaciones de ENISA.\r\n- Desarrollo seguro: como regla general, seguir las recomendaciones emitidas por OWASP.\r\n- Evaluación de seguridad: recomendamos que los operadores de servicios:\r\n  > Soliciten auditorías de terceros en sus infraestructuras (estas auditorías pueden ser parte de una certificación ISO 27001).\r\n  > Soliciten pruebas de penetración de terceros de sus servicios, incluyendo al menos un escaneo de red y, si es posible, pruebas de penetración manuales.\r\nEn cuanto a la computación en la nube, los proveedores de servicios deben considerar la certificación siguiendo las recomendaciones de ENISA.\r\n','Seguridad operativa y mantenimiento - Sistema de gestión de seguridad para infraestructuras de soporte: Confiar en fuentes existentes de buenas prácticas de seguridad para asegurar las infraestructuras','DispositivoIoT'),(10,'El usuario final debe contar con un medio para borrar de manera segura sus datos privados recopilados o almacenados en un dispositivo del Hogar Inteligente. \r\nDe manera más general, debería estar disponible en el dispositivo una opción para realizar un restablecimiento seguro de fábrica del firmware y la configuración.\r\nPara la información del cliente en infraestructuras remotas como servicios en la nube, se debe contar con un proceso de sanitización de datos.\r\nEn cuanto a los datos del usuario presentes en los dispositivos, la eliminación segura de las claves de encriptación puede proporcionar suficiente protección, siempre y cuando los datos estén encriptados en condiciones que garanticen la confidencialidad a largo plazo.\r\nLos metadatos deben ser borrados de la misma manera que otros datos sensibles, ya que aplican las mismas amenazas.\r\n','Control de datos de usuario: Proporcionar copias de seguridad seguras y/o eliminación de los datos almacenados/procesados por el dispositivo (y por los servicios en la nube asociados) durante la operación y al final de su vida útil','DispositivoIoT');
/*!40000 ALTER TABLE `control` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `control_x_amenaza`
--

DROP TABLE IF EXISTS `control_x_amenaza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `control_x_amenaza` (
  `fk_amenaza` bigint NOT NULL,
  `fk_control` bigint NOT NULL,
  KEY `FKmsnayjmcncrvdeo1rkao06wi5` (`fk_control`),
  KEY `FKroi503iqam96kycfxan7sxwmj` (`fk_amenaza`),
  CONSTRAINT `FKmsnayjmcncrvdeo1rkao06wi5` FOREIGN KEY (`fk_control`) REFERENCES `control` (`id`),
  CONSTRAINT `FKroi503iqam96kycfxan7sxwmj` FOREIGN KEY (`fk_amenaza`) REFERENCES `amenaza` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `control_x_amenaza`
--

LOCK TABLES `control_x_amenaza` WRITE;
/*!40000 ALTER TABLE `control_x_amenaza` DISABLE KEYS */;
INSERT INTO `control_x_amenaza` VALUES (1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(3,4),(3,5),(3,6),(3,7),(3,8),(3,9),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),(6,4),(6,5),(6,6),(6,7),(6,8),(6,9),(7,4),(7,5),(7,6),(7,7),(7,8),(7,9),(8,4),(8,5),(8,6),(8,7),(8,8),(8,9),(9,4),(9,5),(9,6),(9,7),(9,8),(9,9),(10,4),(10,5),(10,6),(10,7),(10,8),(10,9),(11,4),(11,5),(11,6),(11,7),(11,8),(11,9),(12,4),(12,5),(12,6),(12,7),(12,8),(12,9),(13,4),(13,5),(13,6),(13,7),(13,8),(13,9),(14,4),(14,5),(14,6),(14,7),(14,8),(14,9),(15,4),(15,5),(15,6),(15,7),(15,8),(15,9);
/*!40000 ALTER TABLE `control_x_amenaza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dispositivo_iot`
--

DROP TABLE IF EXISTS `dispositivo_iot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dispositivo_iot` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKqbi1hjvxty133uvm2s3w3cb59` FOREIGN KEY (`id`) REFERENCES `activo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dispositivo_iot`
--

LOCK TABLES `dispositivo_iot` WRITE;
/*!40000 ALTER TABLE `dispositivo_iot` DISABLE KEYS */;
INSERT INTO `dispositivo_iot` VALUES (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12);
/*!40000 ALTER TABLE `dispositivo_iot` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-03 12:26:45
