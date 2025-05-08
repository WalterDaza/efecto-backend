# Proyecto Efecto

## Descripción

**Efecto** es una solución backend desarrollada con microservicios utilizando **Spring Boot**, orientada a optimizar la gestión operativa del bar Efecto. Este sistema proporciona funcionalidades clave para la administración de ventas, inventario y finanzas, con un enfoque escalable y preparado para integrarse con futuras interfaces de usuario (frontend).

## Tecnologías Utilizadas

- **Java** con **Spring Boot**
- **MySQL** (base de datos relacional)
- **Swagger** (documentación de APIs REST)
  http://localhost:8080/swagger-ui.html
- **MapStruct** (conversión eficiente entre entidades y DTOs)
- **Lombok** (reducción de código repetitivo)
- **JavaMailSender** (recuperación de contraseñas vía correo electrónico)
- **Spring Security + JSON Web Token (JWT)**:
    - Autenticación y autorización
    - Protección de rutas según roles de usuario

## Características Principales

- 🔒 Seguridad robusta con control de acceso basado en roles
- 📦 Gestión de ventas e inventario del bar
- 📊 Módulo de finanzas para seguimiento de ingresos y egresos
- ✉️ Recuperación de contraseñas mediante envío de email
- 🔄 Conversión limpia de entidades a DTOs con MapStruct
- 🧾 Documentación automática de endpoints con Swagger

## Requisitos

- Java 17+
- Maven
- MySQL Server
- IDE (IntelliJ IDEA, Eclipse, etc.)



