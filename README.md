# Fashion Store

Sistema de gestión de tienda de ropa desarrollado en Java utilizando Programación Orientada a Objetos (POO), JDBC y MySQL.

# Repositorio

- https://github.com/sergiodiazmantilla/fashion_store.git

# Clonar

```bash
git clone https://github.com/TU-USUARIO/fashion-store-java.git
```

# Conceptos Aplicados
Este proyecto fue desarrollado como proyecto final del curso **Técnicas de Programación Orientada a Objetos**, aplicando conceptos como:

- Herencia
- Polimorfismo
- Abstracción
- Encapsulamiento
- JDBC
- DAO (Data Access Object)
- Conexión a base de datos MySQL
- CRUD
- Relaciones entre tablas

# Descripción del proyecto

Fashion Store es un sistema de consola que permite gestionar una tienda de ropa mediante diferentes módulos:

- Gestión de productos
- Gestión de clientes
- Gestión de ventas
- Reportes
- Login de administrador

El sistema permite registrar productos, controlar stock, realizar ventas, generar reportes y almacenar toda la información en una base de datos MySQL.

# Tecnologías utilizadas

- Java JDK 21
- MySQL
- JDBC
- VSCode

# Estructura del proyecto

```plaintext
Tecnicas_POO_Proyecto_Final/
│
│── src/
│     └──config/  
│     └──dao/
│          └──interfaces/
│     └──main/
│     └──model/
│     └──service/
│     └──util/
│     └──view/
│
│── lib/
│     └── mysql-connector-j-9.7.0.jar
│
│── database/
│     └── fashion_store_db.sql
│
│── .vscode/
│
│── README.md