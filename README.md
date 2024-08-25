# ApiRestUser
Evaluación: JAVA

# Servicio User  API

Este proyecto proporciona un servicio RESTFul para gestionar usuarios. A continuación se detallan los endpoints disponibles y cómo utilizarlos.

## Diagrama de la solución

- **Link**: https://drive.google.com/file/d/1WTm_BXkT3_--JQ2g3ty23v69hL4G2tAJ/view?usp=sharing

## swagger
- **Link**: http://localhost:8080/api/swagger-ui/index.html#/

## Endpoints -  localhost:8080/api

### 1. Registrar un nuevo usuario

**POST** `/usuario`

- **Descripción**: Registra un nuevo usuario en el sistema.
- **Body**:
  ```json
  {
    "name": "string",
    "email": "string",
    "password": "string",
    "phones": [
      {
        "number": "string",
        "citycode": "string",
        "contrycode": "string"
      }
    ]
  }

Respuestas:
201 CREATED: Retorna el usuario creado con todos sus detalles.
409 CONFLICT: Retorna un mensaje indicando que el correo ya está registrado.
500 INTERNAL SERVER ERROR: Retorna un mensaje de error genérico si ocurre un problema inesperado.

### 2. Obtener todos los usuarios

**GET** `/usuario`

- **Descripción**: Retorna una lista de todos los usuarios registrados.
- **Respuestas**: 200 OK: Retorna una lista de usuarios

### 3. Obtener un usuario por ID

**GET** `/usuario/{id}`

- **Descripción**: Obtiene la información de un usuario específico por su ID.
- **Path Variable**: id (UUID del usuario).
- **Respuestas**: : 
  - **200 OK**: Retorna la información del usuario
  - **404 NOT FOUND**: Retorna un mensaje indicando que el usuario no fue encontrado

### 4. Actualizar un usuario

**PUT** `/usuario/{id}`

- **Descripción**: Actualiza la información de un usuario existente.
- **Path Variable**: id (UUID del usuario).
- **Body**:
  ```json
  {
  "name": "string",
  "email": "string",
  "password": "string",
  "phones": [
    {
      "number": "string",
      "citycode": "string",
      "contrycode": "string"
    }
  ]
  }

- **Respuestas**: :
  - **200 OK**: Retorna el usuario actualizado
  - **404 NOT FOUND**: Retorna un mensaje indicando que el usuario no fue encontrado


### 5. Eliminar un usuario

**DELETE** `/usuario/{id}`

- **Descripción**: Elimina un usuario por su ID.
- **Path Variable**: id (UUID del usuario).
- **Respuestas**: :
  - **200 OK**: Retorna un mensaje indicando que el usuario fue eliminado exitosamente.
  - **404 NOT FOUND**: Retorna un mensaje indicando que el usuario no fue encontrado
