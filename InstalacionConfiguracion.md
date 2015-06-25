# Instalación y Configuración #

Herramientas que van a ser usadas en el desarrollo del proyecto de Micro-curriculos.

## Links de Descarga: ##

### 1) Eclipse Indigo ###

http://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/indigosr2

### 2) GWT Plugin para Eclipse Indigo ###

Desde la opción install new software de Eclipse, crear un nuevo update site. Seleccionar las opciones Google Plugin for Eclipse, GWT Designer for GPE y SDKs (solo Google Web Toolkit SDK).

http://dl.google.com/eclipse/plugin/3.7

### 3) Glassfish 3.1.1 ###

http://glassfish.java.net/downloads/3.1.1-final.html

### 4) MySQL JDBC Connector ###

http://dev.mysql.com/downloads/connector/j/5.1.html

Descomprimir y copiar el archivo mysql-connector-java-5.1.21-bin.jar en la carpeta lib/ext del dominio creado en Glassfish (ejemplo: ~/glassfish-3.1.1/glassfish/domains/domain1/lib/ext/mysql-connector-java-5.1.21-bin.jar)

### 5) Server Adapter para Glassfish ###

Desde la vista Servers de Eclipse, definir un nuevo servidor usando el wizard. Hacer clic sobre el enlace de descargar adaptadores adicionales. Seleccionar e instalar Oracle Glassfish Server Tools.

Desde la vista Servers de Eclipse, definir un nuevo servidor usando el wizard. Seleccionar Glassfish > Glassfish 3.1.1. Seleccionar el JDK instalado y buscar la ruta de instalación de Glassfish.

### 6) JDBC Connection Pool ###

Desde la vista Servers iniciar Glassfish y abrir la consola de administración de este. En el panel de tareas comunes abrir Resources > JDBC > JDBC Connection Pools y crear un nuevo Connection Pool.

  * ![http://200.3.193.23/11.20.01.png](http://200.3.193.23/11.20.01.png)

  * ![http://200.3.193.23/11.25.55.png](http://200.3.193.23/11.25.55.png)

  * ![http://200.3.193.23/11.26.13.png](http://200.3.193.23/11.26.13.png)

  * ![http://200.3.193.23/11.28.01.png](http://200.3.193.23/11.28.01.png)

Para mayor información: http://backus1.uniandes.edu.co/~s2603b06/dokuwiki/doku.php?id=tutorial_aplicacion:mbd

### 7) m2e-wtp ###

Desde la opción install new software de Eclipse utilizar el update site http://download.jboss.org/jbosstools/updates/m2eclipse-wtp. Seleccionar las opciones Maven Integration for Eclipse, y Maven Integration for WTP.

