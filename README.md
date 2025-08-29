# E-commerce Básico

## Descripción
E-commerce Básico es un proyecto desarrollado en **Java Spring Boot** que permite gestionar un sistema de venta de productos en línea. La aplicación incluye funcionalidades de gestión 
de usuarios, productos, categorías, carritos de compras y pedidos.  

El proyecto está diseñado con una **arquitectura en capas**, siguiendo buenas prácticas de desarrollo y permitiendo fácil escalabilidad.  

---

## Tecnologías utilizadas
- **Lenguaje:** Java 24  
- **Framework:** Spring Boot  
- **Base de datos:** PostgreSQL  
- **Dependencias:** Spring Web, Spring Data JPA, Lombok, Validation   
- **Control de versiones:** Git / GitHub  

---

## Funcionalidades

### Usuarios
- Listar todos los usuarios: `GET /api/usuarios`  
- Obtener usuario por ID: `GET /api/usuarios/{id}`  
- Crear usuario: `POST /api/usuarios/crear`  
- Actualizar usuario por ID: `PUT /api/usuarios/actualizar/{id}`  
- Eliminar usuario: `DELETE /api/usuarios/borrar/{id}`  

### Productos
- Listar todos los productos: `GET /api/productos`  
- Obtener producto por ID: `GET /api/productos/{id}`  
- Crear producto: `POST /api/productos/crear`  
- Actualizar producto por ID: `PUT /api/productos/actualizar/{id}`  
- Eliminar producto: `DELETE /api/productos/borrar/{id}`  
- Buscar producto por nombre: `GET /api/productos/buscar?nombre=ejemplo`  
- Buscar producto por categoría: `GET /api/productos/categoria/{categoriaId}`  
- Filtrar productos por rango de precio: `GET /api/productos/filtrar?min=10&max=50`  
- Disminuir stock: `PUT /api/productos/{productoId}/disminuir-stock?cantidad=5`  
- Aumentar stock: `PUT /api/productos/{productoId}/aumentar-stock?cantidad=5`  
- Verificar disponibilidad de stock: `GET /api/productos/{productoId}/hay-stock?cantidad=5`  

### Categorías
- Listar todas las categorías: `GET /api/categorias`  
- Obtener categoría por ID: `GET /api/categorias/{id}`  
- Crear categoría: `POST /api/categorias/crear`  
- Actualizar categoría: `PUT /api/categorias/actualizar/{id}`  
- Eliminar categoría: `DELETE /api/categorias/eliminar/{id}`  

### Carrito de compras
- Agregar producto al carrito: `GET /api/carritos/{usuarioId}/agregar/{productoId}?cantidad=1`  
- Eliminar producto del carrito: `DELETE /api/carritos/{usuarioId}/eliminar/{productoId}`  
- Obtener carrito por usuario: `GET /api/carritos/{usuarioId}`  
- Vaciar carrito: `DELETE /api/carritos/{usuarioId}/vaciar`  

### Pedidos
- Crear pedido: `POST /api/pedidos/{usuarioId}?direccionEnvio=Dirección`  
- Obtener pedidos por usuario: `GET /api/pedidos/usuario/{usuarioId}`  
- Obtener pedido por ID: `GET /api/pedidos/{pedidoId}`  
- Actualizar estado de pedido: `PUT /api/pedidos/{pedidoId}/estado?nuevoEstado=ESTADO`  
  > Los estados posibles se definen en la entidad `Pedido.EstadoPedido`.  

---

## Instalación y ejecución

1. Clonar el repositorio:

```bash
git clone https://github.com/juanjodev27/ecommerce-basico.git
cd ecommerce-basico

2. Configurar la base de datos en application.properties o application.yml:

spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

3.Ejecutar la aplicación:

mvn spring-boot:run

4. La API estará disponible en http://localhost:8080/api/...

Contacto
Autor: Juan José Garcete

Estado del proyecto
Funcional: ✅
Desarrollo de frontend pendiente (opcional)
Documentación con Swagger pendiente (opcional)

