# Gestor Simple De Balance

## Descripción general

Esta es una aplicación de escritorio diseñada para gestionar balances financieros, ventas y gastos. Proporciona una interfaz centralizada para el seguimiento de productos y transacciones financieras, adaptada específicamente para operaciones de pequeña escala. La aplicación utiliza una base de datos local.

La aplicación está construida con las siguientes tecnologías principales:

- **Lenguaje**: Java 21  
- **Framework UI**: JavaFX 21 (Controles, FXML, Gráficos)  
- **Base de datos**: MySQL a través de JDBC (mysql-connector-j 9.6.0)  
- **Sistema de construcción**: Maven

---

## Requisitos

Para ejecutar la aplicación, el sistema debe cumplir con los siguientes requisitos:

- **Servidor MySQL** (o MariaDB) versión equivalente a 12.2.2-MariaDB.  
- **Sistema Operativo Linux**  cualquier distribución.   
- No es necesario tener Java instalado, ni las librerías adicionales, ya que la aplicación se distribuye empaquetada con **jpackage**.

---

## Primeros pasos

Sigue estos pasos para poner en marcha la aplicación:

1. **Crear un servidor MySQL local**  
   Debes instalar y configurar tu propio servidor MySQL (o MariaDB).  
   Si no sabes cómo hacerlo, puedes seguir una guía como [esta](https://dev.mysql.com/doc/refman/8.0/en/installing.html) (ajusta según tu sistema operativo).
   El servidor debe ser **obligatoriamente** configurado con las siguientes características:
   - **Nombre de usuario:** root.
   - **Sin contraseña**.
   
2. **Ejecutar el script SQL para crear la base de datos y las tablas**  
   En la ubicación: `src/main/resources/` se incluye un archivo `schema.sql`  
   Conéctate a tu servidor MySQL y ejecuta ese script para crear la base de datos y todas las tablas necesarias.  
   Por ejemplo, desde la línea de comandos de MySQL:  
   ```bash
   mysql -u tu_usuario -p < ruta/al/archivo/schema.sql
     
3. **Ejecutar la aplicación**  
   Una vez configurada la conexión, ejecuta el archivo ejecutable generado por jpackage.
