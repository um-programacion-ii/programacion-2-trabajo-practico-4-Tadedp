[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/Vg2EF-QZ)
# 🚀 Trabajo Práctico: Sistema de Gestión de Biblioteca con Spring Framework

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.5-green)
![Java](https://img.shields.io/badge/Java-21-orange)
![Maven](https://img.shields.io/badge/Maven-3.9.0-red)
![JUnit5](https://img.shields.io/badge/JUnit-5.10.1-green)
![Mockito](https://img.shields.io/badge/Mockito-5.8.0-blue)

## ⚠️ Importante: Antes de Comenzar

1. **Lectura Completa**
   - Es **OBLIGATORIO** leer la consigna completa antes de comenzar a trabajar
   - Asegúrate de entender todos los requisitos y etapas
   - Consulta las dudas antes de iniciar el desarrollo

2. **Configuración del Repositorio**
   - La rama `main` debe estar protegida
   - No se permiten pushes directos a `main`
   - Todo el desarrollo debe realizarse en ramas feature
   - Los cambios deben integrarse mediante Pull Requests

## 🔧 Configuración Inicial del Repositorio

### 1. Protección de la Rama Main
1. En "Branch name pattern" escribir `main`
2. Marcar la siguiente opción:
   - ✓ Require a pull request before merging
3. Hacer clic en "Create"

> 💡 **Nota**: La protección de la rama main es obligatoria y asegura que:
> - No se puedan hacer cambios directos en la rama main
> - Todos los cambios deben hacerse a través de Pull Requests
> - Esto ayuda a mantener un historial de cambios ordenado y a seguir buenas prácticas de desarrollo

### 2. Configuración de Issues y Pull Requests
1. Ir a Settings > General
2. En la sección "Features":
   - ✓ Habilitar Issues
   - ✓ Habilitar Pull Requests
3. En la sección "Pull Requests":
   - ✓ Habilitar "Allow merge commits"
   - ✓ Habilitar "Allow squash merging"
   - ✓ Deshabilitar "Allow rebase merging"

### 3. Configuración de Project Board
1. Ir a la pestaña "Projects"
2. Crear nuevo proyecto "Sistema de Gestión de Biblioteca"
3. Configurar las siguientes columnas:
   - To Do
   - In Progress
   - Code Review
   - Done

### 4. Configuración de Milestones
1. Ir a la pestaña "Milestones"
2. Crear los siguientes milestones:
   - Etapa 1: Configuración y Modelos
   - Etapa 2: Repositories y Services
   - Etapa 3: Controllers
   - Etapa 4: Testing y Documentación

### 5. Configuración de Labels
1. Ir a Issues > Labels
2. Crear las siguientes etiquetas:
   - `enhancement` (verde)
   - `bug` (rojo)
   - `documentation` (azul)
   - `testing` (amarillo)
   - `setup` (gris)
   - `model` (morado)
   - `service` (naranja)
   - `controller` (rosa)
   - `repository` (turquesa)

### 6. Configuración de Templates
1. Verificar que los templat  es estén correctamente ubicados:
   ```
   .github/
   ├── ISSUE_TEMPLATE/
   │   └── issue_template.yml
   └── PULL_REQUEST_TEMPLATE/
       └── pull_request_template.yml
   ```

### 7. Configuración de Git Local
```bash
# Configurar el repositorio remoto
git remote add origin <URL_DEL_REPOSITORIO>

# Crear y cambiar a la rama main
git checkout -b main

# Subir la rama main
git push -u origin main

# Crear rama de desarrollo
git checkout -b develop

# Subir la rama develop
git push -u origin develop
```

## 🎯 Objetivo General

Desarrollar un sistema de gestión de biblioteca utilizando Spring Framework, implementando una arquitectura en capas y aplicando los principios SOLID. El sistema deberá manejar diferentes tipos de recursos bibliográficos, préstamos y usuarios, utilizando una base de datos en memoria para la persistencia de datos.

## ⏰ Tiempo Estimado y Entrega

- **Tiempo estimado de realización:** 24-30 horas
- **Fecha de entrega:** 14/05/2025 a las 13:00 hs

### Desglose estimado por etapa:
- Configuración inicial y modelos: 6-7 horas
- Repositories y Services: 7-9 horas
- Controllers y Endpoints: 6-7 horas
- Testing y documentación: 5-7 horas

> 💡 **Nota**: Esta estimación considera la experiencia adquirida en trabajos anteriores y la complejidad de implementar una arquitectura en capas con Spring Framework. El tiempo se ha ajustado considerando que no se requiere implementación de persistencia real.

## 👨‍🎓 Información del Alumno
- **Nombre y Apellido**: Tadeo Drube Perez
- **Legajo**: 62222

## 📋 Requisitos Previos

- Java 21 o superior
- Maven 3.9.0 o superior
- Conocimientos básicos de:
  - Programación orientada a objetos
  - Principios SOLID
  - Spring Framework básico
  - REST APIs

## 🧩 Tecnologías y Herramientas

- Spring Boot 3.4.5
- Spring Web
- Spring Test
- Lombok (opcional)
- JUnit 5.10.1
- Mockito 5.8.0
- Git y GitHub

## 📘 Etapas del Trabajo

### Etapa 1: Configuración del Proyecto y Modelos Base

#### Objetivos
- Configurar un proyecto Spring Boot
- Implementar los modelos base del sistema
- Aplicar el principio SRP (Single Responsibility)

#### Tareas
1. Crear proyecto Spring Boot con las dependencias necesarias
2. Implementar las siguientes clases modelo:
   - `Libro` (id, isbn, titulo, autor, estado)
   - `Usuario` (id, nombre, email, estado)
   - `Prestamo` (id, libro, usuario, fechaPrestamo, fechaDevolucion)

#### Ejemplo de Implementación
```java
// Modelo base
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private EstadoLibro estado;
}

public enum EstadoLibro {
    DISPONIBLE,
    PRESTADO,
    EN_REPARACION
}
```

### Etapa 2: Implementación de Repositories y Services

#### Objetivos
- Implementar la capa de servicios usando interfaces
- Aplicar el principio ISP (Interface Segregation)
- Implementar la capa de repositorios
- Aplicar el principio DIP (Dependency Inversion)

#### Tareas
1. Crear interfaces de repositorio:
   - `LibroRepository`
   - `UsuarioRepository`
   - `PrestamoRepository`

2. Implementar servicios:
   - Crear interfaces de servicio:
     - `LibroService`
     - `UsuarioService`
     - `PrestamoService`
   - Implementar clases concretas:
     - `LibroServiceImpl`
     - `UsuarioServiceImpl`
     - `PrestamoServiceImpl`

#### Ejemplo de Implementación
```java
// Interface del repositorio
public interface LibroRepository {
    Libro save(Libro libro);
    Optional<Libro> findById(Long id);
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}

// Implementación del repositorio en memoria
@Repository
public class LibroRepositoryImpl implements LibroRepository {
    private final Map<Long, Libro> libros = new HashMap<>();
    private Long nextId = 1L;
    
    @Override
    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(nextId++);
        }
        libros.put(libro.getId(), libro);
        return libro;
    }
    
    @Override
    public Optional<Libro> findById(Long id) {
        return Optional.ofNullable(libros.get(id));
    }
    
    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return libros.values().stream()
            .filter(libro -> libro.getIsbn().equals(isbn))
            .findFirst();
    }
    
    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(libros.values());
    }
    
    @Override
    public void deleteById(Long id) {
        libros.remove(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return libros.containsKey(id);
    }
}

// Interface del servicio
public interface LibroService {
    Libro buscarPorIsbn(String isbn);
    List<Libro> obtenerTodos();
    Libro guardar(Libro libro);
    void eliminar(Long id);
    Libro actualizar(Long id, Libro libro);
}

// Implementación del servicio
@Service
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepository;
    
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    
    @Override
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
            .orElseThrow(() -> new LibroNoEncontradoException(isbn));
    }
    
    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }
    
    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }
    
    @Override
    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
    
    @Override
    public Libro actualizar(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException(id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }
}
```

### Etapa 3: Implementación de Controllers

#### Objetivos
- Implementar la capa de controladores REST
- Aplicar el principio DIP (Dependency Inversion)
- Manejar excepciones HTTP

#### Tareas
1. Crear controladores REST:
   - `LibroController`
   - `UsuarioController`
   - `PrestamoController`

2. Implementar endpoints básicos:
   - GET /api/libros
   - GET /api/libros/{id}
   - POST /api/libros
   - PUT /api/libros/{id}
   - DELETE /api/libros/{id}

#### Ejemplo de Implementación
```java
@RestController
@RequestMapping("/api/libros")
public class LibroController {
    private final LibroService libroService;
    
    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }
    
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.obtenerTodos();
    }
    
    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }
    
    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }
    
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizar(id, libro);
    }
    
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}
```

### Etapa 4: Testing y Documentación

#### Objetivos
- Implementar tests unitarios y de integración
- Documentar la API y el código
- Asegurar la calidad del código

#### Tareas
1. Implementar tests:
   - Tests unitarios para servicios
   - Tests unitarios para repositorios
   - Tests de integración para controladores

2. Documentar:
   - Documentar endpoints con comentarios
   - Actualizar README con instrucciones
   - Documentar arquitectura y decisiones de diseño

#### Ejemplo de Test
```java
@ExtendWith(MockitoExtension.class)
class LibroServiceImplTest {
    @Mock
    private LibroRepository libroRepository;
    
    @InjectMocks
    private LibroServiceImpl libroService;
    
    @Test
    void cuandoBuscarPorIsbnExiste_entoncesRetornaLibro() {
        // Arrange
        String isbn = "123-456-789";
        Libro libroEsperado = new Libro(1L, isbn, "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libroEsperado));
        
        // Act
        Libro resultado = libroService.buscarPorIsbn(isbn);
        
        // Assert
        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }
    
    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        // Arrange
        String isbn = "123-456-789";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(LibroNoEncontradoException.class, () -> 
            libroService.buscarPorIsbn(isbn)
        );
    }
}
```

## ✅ Entrega y Flujo de Trabajo con GitHub

1. **Configuración del Repositorio**
   - Proteger la rama `main`
   - Crear template de Issues y Pull Requests

2. **Project Kanban**
   - `To Do`
   - `In Progress`
   - `Code Review`
   - `Done`

3. **Milestones**
   - Etapa 1: Configuración y Modelos
   - Etapa 2: Repositories y Services
   - Etapa 3: Controllers
   - Etapa 4: Testing y Documentación

4. **Issues y Pull Requests**
   - Crear Issues detallados para cada funcionalidad
   - Asociar cada Issue a un Milestone
   - Implementar en ramas feature
   - Revisar código antes de merge

## ✅ Requisitos para la Entrega

- ✅ Implementación completa de todas las etapas
- ✅ Código bien documentado
- ✅ Todos los Issues cerrados
- ✅ Todos los Milestones completados
- ✅ Pull Requests revisados y aprobados
- ✅ Project actualizado
- ✅ README.md completo con:
  - Instrucciones de instalación
  - Requisitos del sistema
  - Ejemplos de uso
  - Documentación de endpoints

## 📚 Recursos Adicionales

- [Documentación de Spring Boot](https://spring.io/projects/spring-boot)
- [REST API Best Practices](https://restfulapi.net/)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [Spring Boot Test Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.testing)
- [Testing Spring Boot Applications](https://www.baeldung.com/spring-boot-testing)

## 📋 Guía de Testing

### 1. Testing de Servicios
- Usar `@ExtendWith(MockitoExtension.class)`
- Mockear dependencias con `@Mock`
- Inyectar mocks con `@InjectMocks`
- Seguir el patrón Arrange-Act-Assert
- Probar casos positivos y negativos
- Verificar interacciones con mocks

### 2. Testing de Controladores
- Usar `@WebMvcTest` para pruebas de integración
- Mockear servicios con `@MockBean`
- Usar `MockMvc` para simular peticiones HTTP
- Verificar respuestas HTTP y contenido JSON
- Probar diferentes escenarios de error

### 3. Testing de Repositorios
- Probar operaciones CRUD básicas
- Verificar manejo de IDs
- Probar búsquedas y filtros
- Validar comportamiento con datos inválidos

### 4. Buenas Prácticas de Testing
- Nombres descriptivos para tests
- Un assert por test cuando sea posible
- Cobertura de código significativa
- Tests independientes y aislados
- Uso de `@BeforeEach` para setup común
- Documentación de casos de prueba

## 📝 Consideraciones Éticas sobre el Uso de IA

El uso de Inteligencia Artificial (IA) en este trabajo práctico debe seguir las siguientes pautas:

1. **Transparencia**
   - Documentar el uso de IA en el desarrollo
   - Explicar las modificaciones realizadas al código generado
   - Mantener un registro de las herramientas utilizadas

2. **Aprendizaje**
   - La IA debe usarse como herramienta de aprendizaje
   - Comprender y ser capaz de explicar el código generado
   - Utilizar la IA para mejorar la comprensión de conceptos

3. **Integridad Académica**
   - El trabajo final debe reflejar tu aprendizaje
   - No se permite la presentación de código sin comprensión
   - Debes poder explicar y defender cualquier parte del código

4. **Responsabilidad**
   - Verificar la corrección del código generado
   - Asegurar que el código cumple con los requisitos
   - Mantener la calidad y estándares de código

5. **Desarrollo Individual**
   - La IA puede usarse para facilitar el aprendizaje
   - Documentar el proceso de desarrollo
   - Mantener un registro del progreso

## 📝 Licencia

Este trabajo es parte del curso de Programación II de Ingeniería en Informática. Uso educativo únicamente.

# Documentación del proyecto

---

# Ejecución

Requisitos previos:
- Java 21
- Maven 3.9.0
- Git

Desde su terminal, siga los pasos a continuación:

1- Clonar el proyecto:

```bash
git clone https://github.com/um-programacion-ii/programacion-2-trabajo-practico-4-Tadedp.git
```

2- Dirigirse al directorio:

```bash
cd programacion-2-trabajo-practico-4-Tadedp
```

3- Ejecutar el proyecto:

```bash
mvn spring-boot:run
```

Adicionalmente, puede ejecutar los tests corriendo:

```bash
mvn test
```

---

# Arquitectura y diseño

El sistema de gestión de biblioteca es una API RESTful que utiliza el framework Spring Boot aplicando persistencia en memoria de la información, principios SOLID (SRP, ISP, DIP, etc) y una arquitectura en capas (modelos, repositorios, servicios y controladores) detallada a continuación.

# Modelos

---
## Clase Libro
- Atributos:
   - `Long id`
   - `String isbn`
   - `String titulo`
   - `String autor`
   - `LibroEstado estado`
- Constructor: `Libro(Long id, String ISBN, String titulo, String autor, LibroEstado estado)`
- Métodos getter: `getId()`, `getIsbn()`, `getTitulo()`, `getAutor()` y `getEstado()`
- Métodos setter: `setId(Long id)`, `setIsbn(String ISBN)`, `setTitulo(String titulo)`, `setAutor(String autor)` y `setEstado(LibroEstado estado)`
---
## Clase Usuario
- Atributos:
   - `Long id`
   - `String nombre`
   - `String email`
   - `EstadoUsuario estado`
- Constructor: `Usuario(Long id, String nombre, String email, EstadoUsuario estado)`
- Métodos getter: `getId()`, `getNombre()`, `getEmail()` y `getEstado()`
- Métodos setter: `setId(Long id)`, `setNombre(String nombre)`, `setEmail(String email)` y `setEstado(EstadoUsuario estado)`
---
## Clase Prestamo
- Atributos:
   - `Long id`
   - `Libro libro`
   - `Usuario usuario`
   - `LocalDate fechaPrestamo`
   - `LocalDate fechaDevolucion`
- Constructor: `Prestamo(Long id, Libro libro, Usuario usuario, LocalDate fechaPrestamo, LocalDate fechaDevolucion)`
- Métodos getter: `getId()`, `getLibro()`, `getUsuario()`, `getFechaPrestamo()` y `getFechaDevolucion()`
- Métodos setter: `setId(Long id)`, `setLibro(Libro libro)`, `setUsuario(Usuario usuario)`, `setFechaPrestamo(LocalDate fechaPrestamo)` y `setFechaDevolucion(LocalDate fechaDevolucion)`
---

# Repositorios

---
## LibroRepository / LibroRepositoryImpl  
Operaciones CRUD sobre libros en memoria y búsquedas específicas.
- Anotaciones:
   - **@Repository**
- Atributos:
   - `Map<Long, Libro> libros = new HashMap<>()`
   - `Long nextId = 1L`
- Búsquedas: `findById(Long Id)` y `findByIsbn(String isbn)` 
- Crear y actualizar: `save(Libro libro)`
- Leer todos: `findAll()`
- Borrar: `deleteById(Long id)`
- Verificar si existe: `existsById(Long id)`
---
## UsuarioRepository / UsuarioRepositoryImpl
Operaciones CRUD sobre usuarios en memoria y búsquedas específicas.
- Anotaciones:
   - **@Repository**
- Atributos:
   - `Map<Long, Usuarios> usuarios = new HashMap<>()`
   - `Long nextId = 1L`
- Búsquedas: `findById(Long Id)` y `findByEmail(String email)`
- Crear y actualizar: `save(Usuario usuario)`
- Leer todos: `findAll()`
- Borrar: `deleteById(Long id)`
- Verificar si existe: `existsById(Long id)`
---
## PrestamoRepository / PrestamoRepositoryImpl
Operaciones CRUD sobre préstamos en memoria y búsquedas específicas.
- Anotaciones:
   - **@Repository**
- Atributos:
   - `Map<Long, Prestamo> prestamos = new HashMap<>()`
   - `Long nextId = 1L`
- Búsquedas: `findById(Long Id)`, `findByLibro(Libro libro)` y `findByUsuario(Usuario usuario)` 
- Crear y actualizar: `save(Prestamo prestamo)`
- Leer todos: `findAll()`
- Borrar: `deleteById(Long id)`
- Verificar si existe: `existsById(Long id)`
---

# Servicios

---
## LibroService / LibroServiceImpl
Lógica de negocio para libros.
- Anotaciones:
   - **@Service**
- Atributos:
   - `LibroRepository libroRepository`
- Constructor: `LibroServiceImpl(LibroRepository libroRepository)` 
- Búsquedas: `buscarPorId(Long Id)` y `buscarPorIsbn(String isbn)`
- Crear: `guardar(Libro libro)`
- Leer todos: `obtenerTodos()`
- Borrar: `eliminar(Long id)`
- Actualizar: `actualizar(Long id, Libro libro)`
---
## UsuarioService / UsuarioServiceImpl
Lógica de negocio para usuarios.
- Anotaciones:
   - **@Service**
- Atributos:
   - `UsuarioRepository usuarioRepository`
- Constructor: `UsuarioServiceImpl(UsuarioRepository usuarioRepository)`
- Búsquedas: `buscarPorId(Long Id)` y `buscarPorEmail(String email)`
- Crear: `guardar(Usuario usuario)`
- Leer todos: `obtenerTodos()`
- Borrar: `eliminar(Long id)`
- Actualizar: `actualizar(Long id, Usuario usuario)`
---
## PrestamoService / PrestamoServiceImpl
Lógica de negocio para préstamos.
- Anotaciones:
   - **@Service**
- Atributos:
   - `PrestamoRepository prestamoRepository`
- Constructor: `PrestamoServiceImpl(PrestamoRepository prestamoRepository)`
- Búsquedas: `buscarPorId(Long Id)`, `buscarPorLibro(Libro libro)` y `buscarPorUsuario(Usuario usuario)` 
- Crear: `guardar(Prestamo prestamo)`
- Leer todos: `obtenerTodos()`
- Borrar: `eliminar(Long id)`
- Actualizar: `actualizar(Long id, Prestamo prestamo)`
---

# Controladores

---
## LibroController
Controlador REST de la API para libros.
- Anotaciones:
  - **@RestController**
  - **@RequestMapping(/api/libros)**
- Atributos:
   - `LibroService libroService`
- Constructor: `LibroController(LibroService libroService)`
- Endpoints:
  - **GET** `/api/libros`: Obtener todos los libros - 200 OK
  - **GET** `/api/libros/{id}`: Buscar libro por id - 200 OK o 404 Not Found
  - **GET** `/api/libros/por-isbn/{isbn}`: Buscar libro por ISBN - 200 OK o 404 Not Found
  - **POST** `/api/libros`: Crear libro - 200 OK
  - **PUT** `/api/libros/{id}`: Modificar libro por id - 200 OK o 404 Not Found
  - **DELETE** `/api/libros/{id}`: Eliminar libro por id - 204 No Content o 404 Not Found
---
## UsuarioController
Controlador REST de la API para usuarios.
- Anotaciones:
   - **@RestController**
   - **@RequestMapping(/api/usuarios)**
- Atributos:
   - `UsuarioService usuarioService`
- Constructor: `UsuarioController(UsuarioService usuarioService)`
- Endpoints:
   - **GET** `/api/usuarios`: Obtener todos los usuarios - 200 OK
   - **GET** `/api/usuarios/{id}`: Buscar usuario por id - 200 OK o 404 Not Found
   - **GET** `/api/usuarios/por-email/{email}`: Buscar usuario por email - 200 OK o 404 Not Found
   - **POST** `/api/usuarios`: Crear usuario - 200 OK
   - **PUT** `/api/usuarios/{id}`: Modificar usuario por id - 200 OK o 404 Not Found
   - **DELETE** `/api/usuarios/{id}`: Eliminar usuario por id - 204 No Content o 404 Not Found
---
## PrestamoController
Controlador REST de la API para préstamos.
- Anotaciones:
   - **@RestController**
   - **@RequestMapping(/api/prestamos)**
- Atributos:
   - `PrestamoService prestamoService`
- Constructor: `PrestamoController(PrestamoService prestamoService)`
- Endpoints:
   - **GET** `/api/prestamos`: Obtener todos los préstamos - 200 OK
   - **GET** `/api/prestamos/{id}`: Buscar préstamo por id - 200 OK o 404 Not Found
   - **POST** `/api/prestamos/por-libro`: Buscar préstamo por libro (body de la solicitud) - 200 OK o 404 Not Found
   - **POST** `/api/prestamos/por-usuario`: Buscar préstamo por usuario (body de la solicitud) - 200 OK o 404 Not Found
   - **POST** `/api/prestamos`: Crear préstamo - 200 OK
   - **PUT** `/api/prestamos/{id}`: Modificar préstamo por id - 200 OK o 404 Not Found
   - **DELETE** `/api/prestamos/{id}`: Eliminar préstamo por id - 204 No Content o 404 Not Found


