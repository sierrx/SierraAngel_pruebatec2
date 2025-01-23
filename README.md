# Sistema_de_turnos
En este código se presenta un sistema para gestionar turnos en una base de datos a traves de un JSP (JavaServer Pages) .

## Funcionamiento
Se muestra un index minimalista donde se registran 5 campos para crear un turno Fecha, Asunto del problema, Descripción del problema, asi como el Nombre, Apellido y Teléfono de quien esta reportando el problema, una vez llenado los campos se presiona el boton "Registrar Turno" el cual registrara en la base de datos el turno.
Se incluyen dos apartados de búsqueda uno por fecha y otro por el Estatus del Turno, se pueden hacer las dos busquedas al mismo tiempo por si se desan comparar datos, los resultados se presentan en una tabla con las siguientes columnas:Fecha, Asunto, Descripción, Ciudadano y	Estatus, ademas de una ultima columna llamada Acciones donde se presentan dos botones si es que el turno esta en el estatus de "En espera" que son Atender para cambiar el estatus a "Ya atendido" y el boton eliminar para realizar un borrado logico que cambia el turno a un estado inactivo que hace que no salga en los resultados pero que los datos se mantengan en la base de datos, en caso de que el estatus del sea "Ya atendido" solo muestra el boton "eliminar"


## Características

- **Alta de turnos**: Agrega nuevos turnos al sistema.
- **Actualización de datos**: Modifica el estatus de los turnos existentes.
- **Búsqueda por Fecha**: Filtra turnos por su fecha.
- **Búsqueda por estatus**: Filtra turnos por el estatus en el que se encuentran.
- **Eliminación de turnos**: Borrado de los turno en la salida de resultados pero se mantienen en la base de datos.


## Supuestos

-**Editar turnos**: La consigna solo menciona que se pueda cambiar el status de un turno se menciona si se desea cambiar algun otro dato por lo cual solo se incluye el cambio del estatus del turno con un boton.
-****:

## Paquetes
-**com.turnos.model**: Aqui encontraremos las clases Ciudadano, Turno y la clase controladora Model.
-**com.turnos.persistencia**: Aqui encontraremos los JpaController de las clases Turno y Ciudadano asi como una clase llamada Controladora Persistencia
-**com.turnos.servlets**: Aqui encontramos los servlets para Actualizar un turno, Eliminar un turno y obtener los datos del turno provenientes JSP

## UML
![Diagrama UML](main/diagramaUml.png)

Este diagrama representa la arquitectura del sistema respetando las capas **JSP → Servlets → Modelo (ControladoraModel) → Persistencia (ControladoraPersistencia) → BD (JpaControllers)**. A continuación, se explica cada componente y sus relaciones:


### **JSP**
- **Descripción**: 
  Las páginas JSP actúan como la capa de presentación. A través del archivo JSP `index`, se obtienen los datos del usuario o instancias relacionadas con el flujo del sistema.
- **Relación**:
  - Los datos capturados en el JSP son enviados a los **Servlets**.


### **Servlets**
- **Descripción**:
  Los **Servlets** actúan como intermediarios entre la capa de presentación (JSP) y la lógica del modelo. Reciben datos del JSP y delegan la lógica al modelo.
- **Clases**:
  - `TurnoSv`: Procesa la creación de nuevos turnos.
  - `ActualizarEstadoSv`: Actualiza el estado de un turno existente.
  - `EliminarSv`: Gestiona la eliminación de turnos.
- **Relación**:
  - Envían solicitudes a la **ControladoraModel**.


### **Controladora Model (Modelo)**
- **Descripción**:
  Representa la capa de lógica. Aquí se procesa la información enviada por los Servlets y se interactúa con la capa de persistencia.
- **Clases**:
  - `ControladoraModel`: Contiene métodos para gestionar las entidades principales (`Turno` y `Ciudadano`) y coordina con la persistencia.
- **Relación**:
  - Comunica las operaciones requeridas a la **ControladoraPersistencia**.



### **Controladora Persistencia**
- **Descripción**:
  Interactúa directamente con la base de datos a través de los **JpaControllers**. Gestiona la persistencia de las entidades.
- **Clases**:
  - `ControladoraPersistencia`: Contiene métodos para interactuar con el modelo y realizar operaciones CRUD en la base de datos.
- **Relación**:
  - Utiliza los **JpaControllers** para ejecutar las operaciones en la base de datos.


### **BD (JpaControllers)**
- **Descripción**:
  Esta capa se encarga de realizar operaciones específicas en la base de datos mediante las entidades definidas.
- **Clases**:
  - `TurnoJpaController`: Maneja la persistencia de la entidad `Turno`.
  - `CiudadanoJpaController`: Maneja la persistencia de la entidad `Ciudadano`.
- **Relación**:
  - Es utilizada por la **ControladoraPersistencia** para realizar consultas y operaciones sobre la base de datos.


### **Relaciones **
- **JSP → Servlets**: Los JSP proporcionan datos a los Servlets.
- **Servlets → Modelo**: Los Servlets delegan la lógica al modelo mediante la `ControladoraModel`.
- **Modelo → Persistencia**: El modelo utiliza la `ControladoraPersistencia` para interactuar con los datos persistentes.
- **Persistencia → BD**: La `ControladoraPersistencia` accede a la base de datos a través de los **JpaControllers**.





## Requisitos
- **JDK**: Versión 17
- **Apache Maven**: Para la gestión de dependencias.
- **Apache Tomcat**:Para la ejecución de JSP y Servlets.
- **IDE**: NetBeans
- **Base de datos relacional**:Mysql
