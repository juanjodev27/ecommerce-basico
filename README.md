# 🛒 E-commerce Básico

Este es un proyecto de e-commerce backend desarrollado con **Java Spring Boot**, que permite gestionar **usuarios, productos, categorías, carritos de compras y pedidos**.

El objetivo del proyecto es aprender y aplicar buenas prácticas de desarrollo backend, arquitectura en capas, documentación de APIs y despliegue con Docker.

---

## 🚀 Características principales

- CRUD de **usuarios, productos y categorías**
- Gestión de **carrito de compras por usuario**
- Gestión de **pedidos y estados**
- Documentación automática con **Swagger**
- Dockerización completa con `Dockerfile` y `docker-compose.yml`
- Arquitectura en capas (`Controller`, `Service`, `Repository`, `DTO`, `Mapper`)
- Base de datos **PostgreSQL**

---

## 🧰 Tecnologías utilizadas

| Herramienta      | Descripción                     |
|------------------|---------------------------------|
| Java             | Versión 17 o superior           |
| Spring Boot      | Backend API REST                |
| PostgreSQL       | Base de datos relacional        |
| Docker           | Contenedores                    |
| Docker Compose   | Orquestación de servicios       |
| Swagger/OpenAPI  | Documentación interactiva       |
| Lombok           | Reducción de boilerplate code   |
| Maven            | Gestión de dependencias         |

---

## ⚙️ Instalación y ejecución

### 🔧 1. Clonar el repositorio

```bash
git clone https://github.com/juanjodev27/ecommerce-basico.git
cd ecommerce-basico

### 2. Ejecutar con Docker (recomendado)

Esto construye el backend + base de datos y los levanta automáticamente
docker compose up --build

Una vez levantado, la API estará disponible en: http://localhost:8080
Y la documentación Swagger en: http://localhost:8080/swagger-ui/index.html

### ⚙️  3. Configuración manual (sin Docker)

Si querés correrlo sin Docker:

1) Crea una base de datos PostgreSQL llamada mi_ecommerze_db

2) Configurá src/main/resources/application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/mi_ecommerze_db
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
spring.jpa.hibernate.ddl-auto=update

Ejecutá: mvn spring-boot:run

📦 Endpoints principales

✅ Usuarios

-GET /api/usuarios

-POST /api/usuarios/crear

-PUT /api/usuarios/actualizar/{id}

-DELETE /api/usuarios/borrar/{id}

✅ Productos

-GET /api/productos

-GET /api/productos/{id}

-POST /api/productos/crear

-PUT /api/productos/actualizar/{id}

-DELETE /api/productos/borrar/{id}

-Búsqueda y filtrado: GET /api/productos/buscar, filtrar, categoria/{id}, etc.

✅ Categorías

-GET /api/categorias

-POST /api/categorias/crear

-PUT /api/categorias/actualizar/{id}

-DELETE /api/categorias/eliminar/{id}

🛒 Carrito de compras

-GET /api/carritos/{usuarioId}

-GET /api/carritos/{usuarioId}/agregar/{productoId}?cantidad=1

-DELETE /api/carritos/{usuarioId}/eliminar/{productoId}

-DELETE /api/carritos/{usuarioId}/vaciar

📦 Pedidos

-POST /api/pedidos/{usuarioId}?direccionEnvio=...

-GET /api/pedidos/usuario/{usuarioId}

-PUT /api/pedidos/{pedidoId}/estado?nuevoEstado=ENVIADO

📁 Estructura del proyecto

ecommerce-basico/
├── src/
│   ├── controller/
│   ├── service/
│   ├── entity/
│   ├── repository/
│   ├── dto/
│   └── mapper/
├── Dockerfile
├── docker-compose.yml
├── .env
├── pom.xml
└── README.md

📌 Estado del proyecto

Componente	Estado
Backend API	✅ Completado
Swagger Docs	✅ Integrado
Docker Support	✅ Listo
Frontend	🔜 Pendiente (opcional)


👨‍💻 Autor

Juan José Garcete

📝 Licencia

Este proyecto es de uso educativo y libre. ¡Usalo como base para tus propios desarrollos!
