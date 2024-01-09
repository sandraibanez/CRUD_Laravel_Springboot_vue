# Introduccion

Restaurante Don Kamaron es una pagina web de reserva de mesas de un restaurante, donde hay dos vistas diferentes dependiendo si el usuario que se logea es un cliente o un administrador.

# Funciones

La web tiene un total de 4 secciones si el que se logea es un cliente que se trata de:

* Home
* Reservation
* User
* Log out

Si el usuario logeado es un administrador el total de secciones de 5 que se trata de:

* Home
* Reservation
* Dashborad
* User
* Log out

# Home

En la seccion de home el usuario puede:

* seleccionar una categoria del carusel de categorias y lo redirige al Reservation con las mesas filtradas por esa categoria.
* Puede seleccionar una mesa en concreto del scroll de mesas del Biggest Tables y lo redirige al detais de esa mesa en concreto donde podra hacer una reserva de esa mesa.

# Reservation

En esta seccion es la mas importante de la pagina web ya que el usuario puede ver el **listado de las mesas** **paginadas**, puede pulsar en una mesa,
acceder al **details de la mesa** que ha seleccionado y **hacer una reserva de esa mesa** en particular.
Tambien hay un **search** que busca por el nombre de la mesa.
El usuario tambien podra **filtrar** las mesas por:
* Capacidad.
* Ver el listado de la mesa de forma ascendente o descendente.


# Dashboard

Esta seccion solo puede acceder el usuario que sea administrador.
Las acciones que se pueden hacer en esta seccion son las siguientes:
* Listar las categorias: Donde se listan las categorias, hay paginacion y search, se puede crear una categoria nueva, borrar una categoria y actualizar una categoria.
* Listar las mesas: Donde se listan las mesas, se puede crear una mesa nueva, borrar una mesa y actualizar una mesa.
* Listar los usuarios: Donde se listan los usuarios, hay paginacion y search, se puede crear un usuario nueva, borrar un usuarios y actualizar un usuarios.
* Listar las reservas: Donde se listan las reservas, hay paginacion y search, se puede crear una reservas nueva,
  borrar una reservas, actualizar una reservas y aceptar o no aceptar una reservas.
# User

Donde aparece el nombre del usuario al clicar se accede a las reservas que tiene ese usuario.
El usuario podra acceder a la informacion de esa reserva y podra modificar la informacion de la reserva o borrar la reserva.

# Log out

La aplicación web, consta también, de un módulo de login donde el usuario puede crear una cuenta, e iniciar sesión con ella.

* Login
* Register
* Logout

Además el login tiene un token mediante JWT en el que va verificando durante el uso de la web si hay un usuario conectado.

# Puesta en marcha

Es necesario crear el fichero .env en la carpeta de servidor.

Tener instalado las siguientes herramientas:

* Springboot V3.8.8
* Laravel V9
* Vue VITE v3.2.7
* MySQL

# BACKEND
 **Laravel**
* cd Backend/Laravel
* php artisan key:generate
* php artisan jwt:secret
* php artisan serve
  
**Spring-boot**

* cd Backend/spring-boot
* php artisan key:generate
* php artisan jwt:secret
* mvn spring-boot:run
  
# FRONTEND

* cd Frontend/Vue
* npm install
* npm run dev

# LIBRERÍAS

Lista de librerías utilizadas en este proyecto:

* [Toastr](https://www.npmjs.com/package/vue-toastr)
* [Pagination](https://www.npmjs.com/package/vue3-pagination)
