# Sistema de Administracion de Citas

Aplicacion de consola en Java para gestionar citas medicas de un consultorio clinico.

## Acerca de

Sistema CRUD de linea de comandos para administrar doctores, pacientes y citas.
Los datos persisten en archivos CSV locales en la carpeta `db/`.
Requiere autenticacion de administrador al iniciar (usuario: `admin`, contrasena: `admin123`).

## Proyecto

### Estructura del proyecto

```
src/
  Main.java          Punto de entrada
  Menu.java          Interfaz de usuario (CLI)
  Sistema.java       Logica de negocio
  CSVManager.java    Utilidad lectura/escritura CSV
  Persistible.java   Interfaz de serializacion
  Persona.java       Clase abstracta base
  Admin.java         Entidad Administrador
  Doctor.java        Entidad Doctor
  Paciente.java      Entidad Paciente
  Cita.java          Entidad Cita
db/
  admins.csv  doctores.csv  pacientes.csv  citas.csv
```

### Tecnologias

- Java 21+
- Sin dependencias externas
- Persistencia en archivos CSV

### Diagrama de clases (resumen)

```
Persona (abstract)
  ├── Doctor    implements Persistible
  ├── Paciente  implements Persistible
  └── Admin

Cita  implements Persistible  (asocia Doctor + Paciente)
CSVManager  (carga/guarda listas usando Persistible)
Sistema     (logica de negocio, usa CSVManager)
Menu        (interfaz CLI, usa Sistema)
Main        (punto de entrada)
```

## Guias

### Instalacion y configuracion

1. Clonar el repositorio:
   ```
   git clone https://github.com/JDMilenio/consultorio-clinico
   ```
2. Abrir el proyecto en IntelliJ IDEA
3. Verificar JDK en `File > Project Structure > SDK` (requiere Java 21+)
4. Ejecutar `Main.java`

### Uso del programa

1. Ingresar credenciales: ID = `admin`, contrasena = `admin123`
2. Seleccionar modulo desde el menu principal:
   - `1` Doctores
   - `2` Pacientes
   - `3` Citas
   - `4` Salir
3. En cada modulo: `1` Listar  `2` Dar de alta  `3` Eliminar  `0` Regresar

### Creditos

Juan de Dios Gonzalez — Matricula 03013506  
Computacion en Java — Universidad Tecmilenio  
Profesor: Jesus Cazares Martinez

### Licencia

Uso educativo. Todos los derechos reservados — Universidad Tecmilenio.
