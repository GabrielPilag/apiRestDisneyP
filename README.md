# Api Rest Disney

Api para explorar el mundo de Disney, permite conocer y modificar los personajes que lo componen y entender en qué películas estos participaron.



## 1. Modelado de la base de datos

**Personajes(Characters)**

```
id
image
name
age
weight
history
```
**Película o Serie (series_and_films)**

```
id
image
title
creation_date
review
id_genre
```

**Género(genres)**

```
id
image
name
```
**Tabla de relación de Personajes y Película o Serie(relations_characters_series_films)**

```
id
id_character
id_series_and_films
```

**AppUser**

```
id
first_name
last_name
email
password
application_user_role
locked
enabled
```
**Confirmation_token**

```
id
token
crated_at
expires_at
confirmed_at
app_user_id
```

## 2. Autenticación de Usuarios

Para la realizar las peticiones a los endpoints los usuarios deberán poseer un token. Para obtener el token, el usuario primero debe registrarse, luego de realizar el registro debe confirmar el email y después debe ingresar con su nombre de usuario y contraseña. Al ingresar el usuario recibe un token que es válido por 15 días.

* /auth/login
* /auth/register

## 3. Listado de Personajes
El listado muestra
* Imagen.
* Nombre.

El endpoint es:
* /characters

## 4. Creación, Edición y Eliminación de Personajes (CRUD)
La api posee los servicios de creación, edición y eliminación de personajes. Usando respectivamente los métodos HTTP POST, PUT y DELETE.

## 5. Detalle de Personaje
Cuando se realizan búsquedas se muestran los detalles del personaje incluyendo las películas o series relacionadas.

## 6. Búsqueda de Personajes
Permite buscar por nombre, y filtrar por edad, peso o películas/series en las que participó.

* GET /characters?name=nombre
* GET /characters?age=edad
* GET /characters?movies=idMovie

## 7. Listado de Películas
Muestra solamente los campos imagen, título y fecha de creación.
El endpoint deberá ser:
* GET /movies

## 8. Detalle de Película / Serie con sus personajes
Devuelve todos los campos de la película o serie junto a los personajes asociados a la misma
## 9. Creación, Edición y Eliminación de Película / Serie
La api posee los servicios de creación, edición y eliminación de series o películas. Usando respectivamente los métodos HTTP POST, PUT y DELETE.
## 10.Búsqueda de Películas o Series
Permite buscar por título, y filtrar por género. Además, permite ordenar los resultados por
fecha de creación de forma ascendiente o descendiente.
El término de búsqueda, filtro u ordenación se especifican como parámetros de query:

* /movies?name=nombre
* /movies?genre=idGenero
* /movies?order=ASC | DESC

## 11. Envío de emails
Al registrarse en el sitio, el usuario recibe un email de confirmación de registro en donde se envía un link para que el usuario valide su dirección de email.