package es.unican.CIBEL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.unican.CIBEL.domain.Amenaza;
import es.unican.CIBEL.domain.Categoria;
import es.unican.CIBEL.domain.Control;
import es.unican.CIBEL.domain.DispositivoIot;
import es.unican.CIBEL.repository.AmenazaRepository;
import es.unican.CIBEL.repository.CategoriaRepository;
import es.unican.CIBEL.repository.ControlRepository;
import es.unican.CIBEL.repository.DispositivoIoTRepository;

@Configuration
public class LoadDatabase {
	
	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

	  @Bean
	  CommandLineRunner initDatabase(ControlRepository controlRepo, 
			  AmenazaRepository amenazaRepo, 
			  CategoriaRepository categoriaRepo, 
			  DispositivoIoTRepository deviceRepo) {

	    return args -> {
	    	// Controles
	    	Control l1 = new Control("Protección de intercambio de datos: Asegurar los derechos de acceso", "Los usuarios deben verificar las autorizaciones otorgadas a los dispositivos y servicios para acceder e intercambiar datos. Esto es especialmente importante en caso de una actualización, donde los derechos de acceso pueden modificarse sin el consentimiento del usuario. Por ejemplo, los dispositivos y servicios pueden mostrar una vista completa de sus comunicaciones con dispositivos y servicios externos, su necesidad de utilizar datos privados, etc.", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l2 = new Control("Protección de intercambio de datos: Utilizar gateways para reducir la exposición de red de los dispositivos más débiles", "Las redes locales deben estar separadas siempre que sea posible. Cada una de estas redes debe estar protegida por un gateway dedicado (ya sea porque consisten en dos redes diferentes o porque consisten en dos VLAN diferentes gestionadas por un gateway).", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l3 = new Control("Protección de intercambio de datos: Segregar las Redes del Hogar Inteligente y el AMI (infraestructura de medición avanzada)", "Cumplir con los requisitos y recomendaciones del gateway AMI si se instala un Medidor Inteligente. En algunas arquitecturas la pasarela hacia la Infraestructura de Medición Avanzada se utiliza como una interfaz hacia la Red de Área del Hogar. Para asegurar el correcto funcionamiento de la AMI, los proveedores de Hogar Inteligente deberían:\r\n"
	    			+ "- implementar la protección requerida por la pasarela inteligente, si es necesaria; y\r\n"
	    			+ "- evitar la interrupción del servicio, por ejemplo, causando una denegación de servicio al utilizar demasiado ancho de banda.\r\n"
	    			+ "", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l4 = new Control("Seguridad operativa y mantenimiento - Evaluación de vulnerabilidades: Realizar evaluaciones de vulnerabilidad", "Una vez que un dispositivo está en el mercado, el proveedor debe llevar a cabo una evaluación de vulnerabilidades y corregir las fallas de seguridad en consecuencia. La evaluación de vulnerabilidades debe incluir hallazgos del desarrollador, investigaciones en línea, avisos de CERTs, así como aportes de clientes e investigadores de seguridad. Se debe informar al usuario final sobre el período de soporte del dispositivo y sobre el fin del soporte para correcciones de seguridad.\r\n"
	    			+ "Se debe definir una política para el manejo de vulnerabilidades y concientización sobre divulgación. Los programas de recompensa por errores también pueden proporcionar un incentivo para los investigadores externos.\r\n"
	    			+ "Las vulnerabilidades conocidas deben ser corregidas. Un parche puede consistir en una solución temporal si el desarrollador aún no ha proporcionado una solución definitiva. Cuando las actualizaciones por aire no estén disponibles, se debe considerar un plan para retirar el producto, de modo que los proveedores puedan implementar el parche en los dispositivos.\r\n"
	    			+ "Para los servicios en línea que respaldan los dispositivos del Hogar Inteligente, debe ser posible revertir a un estado seguro.\r\n"
	    			+ "En última instancia, las vulnerabilidades que afecten los datos de usuario deben comunicarse de la manera más transparente posible. La Opinión 03/2014 de la Unión Europea sobre la Notificación de Brechas de Datos Personales del Grupo de Trabajo del Artículo 29 proporciona ejemplos de tales situaciones.\r\n"
	    			+ "", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l5 = new Control("Seguridad operativa y mantenimiento - Evaluación de vulnerabilidades: Verificar regularmente las suposiciones de seguridad durante el tiempo de vida", "Los dispositivos y servicios realizaron suposiciones para asegurarse de que los requisitos de seguridad son suficientes. Se alienta a los proveedores y usuarios a verificar regularmente que estas suposiciones sigan siendo válidas. Por ejemplo:\r\n"
	    			+ "- Limitaciones en el uso del dispositivo (por ejemplo, un dispositivo determinado podría necesitar conectividad ZigBee para transmitir alarmas de seguridad, lo que implica que no podrá enviar alarmas cuando esté ubicado detrás de una pared muy gruesa). Los proveedores podrían descubrir que, en el campo, la señal requiere incluso condiciones más estrictas (paredes aún más delgadas, sin interferencias...). Necesitarán enviar a los usuarios información para que puedan verificar si su instalación es segura.\r\n"
	    			+ "- Propiedades asumidas del entorno (por ejemplo, suponer que todas las autoridades de certificación en el almacén de certificados son confiables y no están comprometidas). Los proveedores deben realizar una evaluación para poder eliminar una CA comprometida del almacén de certificados.\r\n"
	    			+ "- Propiedades asumidas de propiedades criptográficas (por ejemplo, asumir que un determinado algoritmo y tamaño de clave son suficientes para una tarea determinada). Los proveedores deberán verificar regularmente esta suposición, por ejemplo, si un nuevo ataque criptográfico pone en riesgo a los usuarios a menos que utilicen claves más largas o cambien sus conjuntos criptográficos.\r\n"
	    			+ "", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l6 = new Control("Seguridad operativa y mantenimiento - Actualizaciones de seguridad: Proteger el mecanismo de actualización de software", "Las actualizaciones de seguridad ofrecen protección contra vulnerabilidades encontradas durante la vida útil de un dispositivo o aplicación. Sin embargo, esto tiene un costo, ya que el soporte de esta funcionalidad también proporciona un punto de entrada para un atacante. En particular, los proveedores deben:\r\n"
	    			+ "- Proporcionar actualizaciones de seguridad automáticas y oportunas.\r\n"
	    			+ "- Proteger las actualizaciones (típicamente a través de encriptación y firma digital). Los archivos de actualización no deben contener datos sensibles. La firma debe ser verificada antes de que se aplique la actualización. Proteger la aplicación de una actualización en el dispositivo. Un atacante no debería poder iniciar una instalación de firmware sin autorización.\r\n"
	    			+ "- Proteger la interfaz de actualización de seguridad contra ataques.\r\n"
	    			+ "", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l7 = new Control("Seguridad operativa y mantenimiento - Protección de interfaces remotas: Proporcionar interfaces amigables para la gestión de seguridad de dispositivos y servicios", "La facilidad de uso de la interfaz de usuario debe contribuir a ayudar a los usuarios a realizar tareas administrativas correctas. Tales interfaces pueden encontrarse, por ejemplo, en el propio dispositivo, en un portal web o a través de una aplicación móvil. La amigabilidad con el usuario ayuda a mantener un estado seguro en el dispositivo al:\r\n"
	    			+ "- Dar más posibilidades de que acciones simples como una actualización de firmware o cambios de contraseña sean realizadas efectivamente por el usuario.\r\n"
	    			+ "- Proporcionar una descripción precisa del estado de seguridad del servicio y explicar qué acciones pueden contribuir a mitigar posibles amenazas.\r\n"
	    			+ "Los proveedores y prestadores de servicios también deberían sensibilizar a sus usuarios sobre correos electrónicos infectados y bulos.\r\n"
	    			+ "", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l8 = new Control("Seguridad operativa y mantenimiento - Protección de interfaces remotas: Proteger las interfaces de monitoreo remoto", "La protección de las interfaces de monitoreo remoto es crucial, ya que a menudo proporcionan un punto de entrada altamente privilegiado en un dispositivo. Esta protección incluye mecanismos de control de acceso y autenticación.", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l9 = new Control("Seguridad operativa y mantenimiento - Sistema de gestión de seguridad para infraestructuras de soporte: Confiar en fuentes existentes de buenas prácticas de seguridad para asegurar las infraestructuras", "En cuanto a los requisitos para infraestructuras remotas relacionadas con el Hogar Inteligente, no hay necesidades específicas en el caso de uso del Hogar Inteligente en comparación con los servicios en la nube habituales o las infraestructuras de soporte de dispositivos habituales.\r\n"
	    			+ "Por esta razón, las principales recomendaciones consistirían en:\r\n"
	    			+ "- Gestión de seguridad: Como regla general, implementar un sistema de gestión de seguridad de la información (SGSI) como se describe en la norma ISO 27001. Se aplican las recomendaciones de ENISA.\r\n"
	    			+ "- Desarrollo seguro: como regla general, seguir las recomendaciones emitidas por OWASP.\r\n"
	    			+ "- Evaluación de seguridad: recomendamos que los operadores de servicios:\r\n"
	    			+ "  > Soliciten auditorías de terceros en sus infraestructuras (estas auditorías pueden ser parte de una certificación ISO 27001).\r\n"
	    			+ "  > Soliciten pruebas de penetración de terceros de sus servicios, incluyendo al menos un escaneo de red y, si es posible, pruebas de penetración manuales.\r\n"
	    			+ "En cuanto a la computación en la nube, los proveedores de servicios deben considerar la certificación siguiendo las recomendaciones de ENISA.\r\n"
	    			+ "", CIBELConstants.DISPOSITIVO_IOT);
	    	Control l10 = new Control("Control de datos de usuario: Proporcionar copias de seguridad seguras y/o eliminación de los datos almacenados/procesados por el dispositivo (y por los servicios en la nube asociados) durante la operación y al final de su vida útil", "El usuario final debe contar con un medio para borrar de manera segura sus datos privados recopilados o almacenados en un dispositivo del Hogar Inteligente. \r\n"
	    			+ "De manera más general, debería estar disponible en el dispositivo una opción para realizar un restablecimiento seguro de fábrica del firmware y la configuración.\r\n"
	    			+ "Para la información del cliente en infraestructuras remotas como servicios en la nube, se debe contar con un proceso de sanitización de datos.\r\n"
	    			+ "En cuanto a los datos del usuario presentes en los dispositivos, la eliminación segura de las claves de encriptación puede proporcionar suficiente protección, siempre y cuando los datos estén encriptados en condiciones que garanticen la confidencialidad a largo plazo.\r\n"
	    			+ "Los metadatos deben ser borrados de la misma manera que otros datos sensibles, ya que aplican las mismas amenazas.\r\n"
	    			+ "", CIBELConstants.DISPOSITIVO_IOT);
	    	
	    	controlRepo.save(l1);
	    	controlRepo.save(l2);
	    	controlRepo.save(l3);
	    	controlRepo.save(l4);
	    	controlRepo.save(l5);
	    	controlRepo.save(l6);
	    	controlRepo.save(l7);
	    	controlRepo.save(l8);
	    	controlRepo.save(l9);
	    	controlRepo.save(l10);
	    	
	    	// Categorias
	    	Categoria c0 = new Categoria("Constrained device - Class 0", CIBELConstants.DISPOSITIVO_IOT);
	    	Categoria c1 = new Categoria("Constrained device - Class 1", CIBELConstants.DISPOSITIVO_IOT);
	    	Categoria c2 = new Categoria("Constrained device - Class 2", CIBELConstants.DISPOSITIVO_IOT);
	    	Categoria c3 = new Categoria("High-capacity device", CIBELConstants.DISPOSITIVO_IOT);
	    	
	    	c0.getControles().add(l1);
	    	c0.getControles().add(l2);
	    	c0.getControles().add(l3);
	    	c0.getControles().add(l4);
	    	c0.getControles().add(l5);
	    	
	    	c1.getControles().add(l1);
	    	c1.getControles().add(l2);
	    	c1.getControles().add(l3);
	    	c1.getControles().add(l4);
	    	c1.getControles().add(l5);
	    	c1.getControles().add(l6);
	    	c1.getControles().add(l7);
	    	c1.getControles().add(l8);
	    	c1.getControles().add(l9);
	    	c1.getControles().add(l10);
	    	
	    	c2.getControles().add(l1);
	    	c2.getControles().add(l2);
	    	c2.getControles().add(l3);
	    	c2.getControles().add(l4);
	    	c2.getControles().add(l5);
	    	c2.getControles().add(l6);
	    	c2.getControles().add(l7);
	    	c2.getControles().add(l8);
	    	c2.getControles().add(l9);
	    	c2.getControles().add(l10);
	    	
	    	c3.getControles().add(l1);
	    	c3.getControles().add(l2);
	    	c3.getControles().add(l3);
	    	c3.getControles().add(l4);
	    	c3.getControles().add(l5);
	    	c3.getControles().add(l6);
	    	c3.getControles().add(l7);
	    	c3.getControles().add(l8);
	    	c3.getControles().add(l9);
	    	c3.getControles().add(l10);
	    	
	    	categoriaRepo.save(c0);
	    	categoriaRepo.save(c1);
	    	categoriaRepo.save(c2);
	    	categoriaRepo.save(c3);
	    	
	    	// Amenazas
	    	Amenaza a1 = new Amenaza("Fraude de identidad", "El fraude de identidad debe ser abordado no solo mediante la protección de credenciales, sino también mediante la protección de datos privados que podrían contribuir a la suplantación de identidad. Por lo tanto, esto también está cubierto por medidas de privacidad.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a2 = new Amenaza("Correo electrónico no solicitado e infectado - Broma", "Este tema típicamente puede ser abordado a través de campañas de concientización por parte de los proveedores y prestadores de servicios.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a3 = new Amenaza("Denegación de servicio", "Mecanismos de seguridad, multiplicidad de interfaces y notificaciones al usuario deben asegurar un nivel mínimo de robustez contra la denegación de servicio. La protección de las comunicaciones también aborda estas preocupaciones.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a4 = new Amenaza("Actividad de software/Código malicioso", "La autodefensa debe abarcar las vulnerabilidades relacionadas con código malicioso. Otra forma de mitigar estas amenazas consiste en proporcionar protección adicional en la Red de Área del Hogar y gestionar relaciones de confianza para aislar dispositivos comprometidos.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a5 = new Amenaza("Abuso de fuga de información", "Las funciones de administración de seguridad también deben estar disponibles (gestión de seguridad). En última instancia, el diseño debe llevar a interfaces de usuario comprensibles y utilizables. Los requisitos de privacidad y confidencialidad también requieren transparencia, para que el usuario esté consciente del tipo de datos que podrían estar potencialmente en riesgo.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a6 = new Amenaza("Manipulación de información", "Las relaciones de confianza y reputación, asociadas a la protección de la comunicación, deben cubrir los riesgos de manipulaciones de información externa. No debe ser posible manipular el registro de auditoría.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a7 = new Amenaza("Acceso no autorizado al sistema/red de información - Uso no autorizado de la administración de dispositivos y sistemas - Abuso de autorizaciones", "La identificación, autenticación y autorización son requisitos generales para el acceso a funciones sensibles. Esto es especialmente importante en el caso de capacidades administrativas.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a8 = new Amenaza("Instalación no autorizada de software - Software malicioso", "La gestión de seguridad también establece que la instalación de firmware debe estar protegida para asegurar su autenticidad e integridad.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a9 = new Amenaza("Actividad remota (ejecución)", "Las capacidades remotas de un dispositivo deben estar disponibles solo para usuarios autorizados y deben llevarse a cabo a través de canales seguros.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a10 = new Amenaza("War driving", "Los dispositivos y servicios deben implementar comunicaciones seguras. Sin embargo, hay algunos dispositivos que carecen de capacidades para esto y, en el caso de \"war driving\", el atacante apunta a la Red de Área del Hogar. Por esta razón, la protección de red adicional en la Red de Área del Hogar es particularmente crítica con respecto a esta amenaza.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a11 = new Amenaza("Falta de recursos/electricidad - Corte de Internet - Corte de red – Huelga - Pérdida de servicios de soporte", "Los mecanismos de seguridad, la diversidad de interfaces y las notificaciones al usuario deben asegurar que los cortes de energía o de red no obstaculicen la seguridad.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a12 = new Amenaza("Fuga o intercambio de información - Uso o administración errónea de dispositivos y sistemas", "Las funciones de administración de seguridad también deben estar disponibles (gestión de seguridad). Eventualmente, el diseño debería conducir a interfaces de usuario comprensibles y utilizables. Los requisitos de privacidad y confidencialidad también requieren transparencia, para que el usuario esté consciente del tipo de datos que podrían estar potencialmente en riesgo.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a13 = new Amenaza("Fuga de información", "La protección de los datos del usuario incluye medidas para asegurar que estos datos no se filtren accidentalmente. La orientación al usuario contribuye a educar al usuario final en este sentido.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a14 = new Amenaza("Fallas o interrupciones de enlaces de comunicación (redes de comunicación) - Fallas o interrupciones del suministro principal - Fallas o interrupciones del suministro eléctrico", "Los mecanismos de seguridad a prueba de fallos, la diversidad de interfaces y las notificaciones al usuario deben asegurar que los cortes de energía o de red no obstaculicen la seguridad.", CIBELConstants.DISPOSITIVO_IOT);
	    	Amenaza a15 = new Amenaza("Errores de configuración", "El diseño debe llevar a interfaces de usuario comprensibles y utilizables. La orientación al usuario también contribuye a ayudar al usuario a tomar decisiones de configuración sensatas.", CIBELConstants.DISPOSITIVO_IOT);	    	
	    	
	    	a1.getControles().add(l4);
	    	a1.getControles().add(l5);
	    	a1.getControles().add(l6);
	    	a1.getControles().add(l7);
	    	a1.getControles().add(l8);
	    	a1.getControles().add(l9);
	    	
	    	a2.getControles().add(l4);
	    	a2.getControles().add(l5);
	    	a2.getControles().add(l6);
	    	a2.getControles().add(l7);
	    	a2.getControles().add(l8);
	    	a2.getControles().add(l9);
	    	
	    	a3.getControles().add(l4);
	    	a3.getControles().add(l5);
	    	a3.getControles().add(l6);
	    	a3.getControles().add(l7);
	    	a3.getControles().add(l8);
	    	a3.getControles().add(l9);
	    	
	    	a4.getControles().add(l4);
	    	a4.getControles().add(l5);
	    	a4.getControles().add(l6);
	    	a4.getControles().add(l7);
	    	a4.getControles().add(l8);
	    	a4.getControles().add(l9);
	    	
	    	a5.getControles().add(l1);
	    	a5.getControles().add(l2);
	    	a5.getControles().add(l3);
	    	a5.getControles().add(l4);
	    	a5.getControles().add(l5);
	    	a5.getControles().add(l6);
	    	a5.getControles().add(l7);
	    	a5.getControles().add(l8);
	    	a5.getControles().add(l9);
	    	a5.getControles().add(l10);
	    	
	    	a6.getControles().add(l4);
	    	a6.getControles().add(l5);
	    	a6.getControles().add(l6);
	    	a6.getControles().add(l7);
	    	a6.getControles().add(l8);
	    	a6.getControles().add(l9);
	    	
	    	a7.getControles().add(l4);
	    	a7.getControles().add(l5);
	    	a7.getControles().add(l6);
	    	a7.getControles().add(l7);
	    	a7.getControles().add(l8);
	    	a7.getControles().add(l9);
	    	
	    	a8.getControles().add(l4);
	    	a8.getControles().add(l5);
	    	a8.getControles().add(l6);
	    	a8.getControles().add(l7);
	    	a8.getControles().add(l8);
	    	a8.getControles().add(l9);
	    	
	    	a9.getControles().add(l4);
	    	a9.getControles().add(l5);
	    	a9.getControles().add(l6);
	    	a9.getControles().add(l7);
	    	a9.getControles().add(l8);
	    	a9.getControles().add(l9);
	    	
	    	a10.getControles().add(l4);
	    	a10.getControles().add(l5);
	    	a10.getControles().add(l6);
	    	a10.getControles().add(l7);
	    	a10.getControles().add(l8);
	    	a10.getControles().add(l9);

	    	a11.getControles().add(l4);
	    	a11.getControles().add(l5);
	    	a11.getControles().add(l6);
	    	a11.getControles().add(l7);
	    	a11.getControles().add(l8);
	    	a11.getControles().add(l9);
	    	
	    	a12.getControles().add(l4);
	    	a12.getControles().add(l5);
	    	a12.getControles().add(l6);
	    	a12.getControles().add(l7);
	    	a12.getControles().add(l8);
	    	a12.getControles().add(l9);
	    	
	    	a13.getControles().add(l4);
	    	a13.getControles().add(l5);
	    	a13.getControles().add(l6);
	    	a13.getControles().add(l7);
	    	a13.getControles().add(l8);
	    	a13.getControles().add(l9);
	    	
	    	a14.getControles().add(l4);
	    	a14.getControles().add(l5);
	    	a14.getControles().add(l6);
	    	a14.getControles().add(l7);
	    	a14.getControles().add(l8);
	    	a14.getControles().add(l9);
	    	
	    	a15.getControles().add(l4);
	    	a15.getControles().add(l5);
	    	a15.getControles().add(l6);
	    	a15.getControles().add(l7);
	    	a15.getControles().add(l8);
	    	a15.getControles().add(l9);
	    	
	    	amenazaRepo.save(a1);
	    	amenazaRepo.save(a2);
	    	amenazaRepo.save(a3);
	    	amenazaRepo.save(a4);
	    	amenazaRepo.save(a5);
	    	amenazaRepo.save(a6);
	    	amenazaRepo.save(a7);
	    	amenazaRepo.save(a8);
	    	amenazaRepo.save(a9);
	    	amenazaRepo.save(a10);
	    	amenazaRepo.save(a11);
	    	amenazaRepo.save(a12);
	    	amenazaRepo.save(a13);
	    	amenazaRepo.save(a14);
	    	amenazaRepo.save(a15);
	    	
	    	// Dispositivos
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("iPhone 12", "https://i.imgur.com/O0cnwUV.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("TP-LINK Archer C50", "https://i.imgur.com/2Txmbm9.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("Amazon echo dot", "https://i.imgur.com/wSJQS6n.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("HP OfficeJet Pro", "https://i.imgur.com/703D8Kf.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("Lenovo ThinkPad T440s", "https://i.imgur.com/q3hit4x.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("Playstation 3", "https://i.imgur.com/fWhU1zr.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("Samsung Smart TV X10P", "https://i.imgur.com/S15N7Ke.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("Pebble Smartwatch", "https://i.imgur.com/RJBXOi9.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("Radio Thermostat CT80", "https://i.imgur.com/g5RMyjd.png", c2)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("TPLink Tapo L530", "https://i.imgur.com/QbGQQRs.jpg", c1)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("Samsung Galaxy S5", "https://i.imgur.com/YExRit4.jpg", c3)));
	    	log.info("Preloading " + deviceRepo.save(new DispositivoIot("iPhone 13", "https://i.imgur.com/O3UGkJZ.jpg", c3)));
	    };
	  }

}
