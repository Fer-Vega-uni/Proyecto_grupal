# Proyecto Grupal - Sistema Colaborativo de Apuntes (SCA)

Proyecto en Desarrollo - Prototipo Funcional correspondiente a la asignatura Programación Orientada a Objetos.

1. Descripción del Proyecto

El Sistema Colaborativo de Apuntes (SCA) es un proyecto universitario diseñado para abordar la necesidad de material de estudio complementario en el contexto de la educación superior. La plataforma permite a los estudiantes centralizar y validar material académico (apuntes, guías, resúmenes) compartido por y para la propia comunidad estudiantil.

El sistema aborda la problemática de la falta de un repositorio fiable, buscando optimizar el proceso de estudio y reducir la fricción asociada a la búsqueda de material no verificado.

2. Integrantes

Fernanda Vega - [@Fer-Vega-uni]

Pablo Ramos - [@PabloRamos02]

Marcelo Orellana - [@morellana09-dotcom]

3. Características Principales

* Registro e Inicio de Sesión de usuarios (estudiantes) con encriptado de contraseñas.
* Gestión de perfiles de usuario.
* Carga (upload) de archivos en formato PDF.
* Visualización y descarga de archivos PDF compartidos.
* Análisis de IA para comprobar la integridad y relevancia de los archivos.
* Carpetas personales por usuario. 
 

4. Stack Tecnológico

Lenguaje: Java (JDK 17).
Interfaz Gráfica (GUI): Java Swing.
Persistencia de Datos: Archivos JSON.
Gestión de Proyecto: Git & GitHub.
Pruebas unitarias: JUnit 5.

5. Instalación y Ejecución Local

El proyecto está configurado para ser ejecutado preferentemente desde un IDE de Java que soporte la compilación de proyectos de tipo Maven.

Requisitos Previos:
Java Development Kit (JDK) 17 o superior.
IDE de Java (Se recomienda Apache NetBeans o IntelliJ IDEA).
Git instalado.

Pasos para la Ejecución

Clonar el Repositorio:
Abra una terminal y ejecute el siguiente comando:

git clone [https://github.com/Fer-Vega-uni/Proyecto_grupal.git](https://github.com/Fer-Vega-uni/Proyecto_grupal.git)


Abrir en su IDE:

Abra su IDE preferido (NetBeans, IntelliJ, Eclipse).
Seleccione File > Open Project... (Archivo > Abrir Proyecto...).
Navegue hasta la carpeta Proyecto_grupal que acaba de clonar y ábrala.

Ejecutar el Proyecto:

El IDE detectará la estructura del proyecto y sus dependencias.
Localice el archivo Launcher.java dentro del paquete launcher.
Haga clic derecho sobre Launcher.java y seleccione "Run File" (Ejecutar Archivo).
Esto compilará el proyecto e iniciará la aplicación, mostrando la ventana de Login.


