# Caso Práctico: Eventos, Navegación y Paso de Datos en JavaFX

Aplicación de escritorio desarrollada en **Java** y **JavaFX** que demuestra el manejo integral de eventos de usuario, navegación entre múltiples ventanas y 
el paso seguro de datos entre controladores. El proyecto implementa el patrón de diseño **MVC (Modelo-Vista-Controlador)** y utiliza un **Singleton** para garantizar la integridad y compartición de datos en tiempo real.


## Características Principales

- **Login con Validación**: Pantalla de inicio de sesión con validación de credenciales y redirección al menú principal.
- **Navegación Fluida**: Transición entre ventanas (Login → Principal → Registro/Consulta) gestionando correctamente el ciclo de vida de las `Stage`.
- **Paso de Datos entre Controladores**: Implementación de inyección de datos usando `FXMLLoader.getController()` para pasar objetos completos (ej. un `Cliente`) de una vista a otra.
- **Patrón Singleton en DAO**: La clase `ClienteDAO` está implementada como Singleton, asegurando que las vistas de *Registro* y *Consulta* compartan la misma instancia y lista de datos en memoria.
- **Manejo de Eventos de Teclado (`KeyEvent`)**: Intercepción de teclas (ej. tecla `ESC` para cerrar ventanas de detalle) y validación de entrada en campos de texto.
- **Uso de Diálogos (`Dialog`)**: Visualización de detalles de un cliente en una ventana modal emergente en lugar de una `Stage` independiente, mejorando la experiencia de usuario (UX).
- **Validaciones en Tiempo Real**: Alertas (`Alert`) y retroalimentación visual ante errores de ingreso de datos o acciones del usuario.



## Estructura del Proyecto

```text
src/main/
├── java/ni/uam/edu/proyectonavegacion/
│   ├── DAO/
│   │   └── ClienteDAO.java          # Acceso a datos implementado como Singleton
│   ├── Interfaces/
│   │   └── CRUD.java                # Contrato para operaciones de base de datos/memoria
│   ├── controllers/
│   │   ├── LoginController.java     # Lógica de autenticación
│   │   ├── PrincipalController.java # Menú principal de navegación
│   │   ├── RegistroController.java  # Formulario de alta de clientes
│   │   ├── ConsultaController.java  # Tabla de clientes y apertura de detalles
│   │   └── DetalleController.java   # Vista modal con la información de un cliente
│   ├── modelos/
│   │   └── Cliente.java             # Clase de dominio (POJO)
│   ├── util/                        # Clases de utilidad (si aplica)
│   ├── Launcher.java                # Punto de entrada de la aplicación
│   └── RegistroApplication.java     # Clase principal que extiende Application
└── resources/ni/uam/edu/proyectonavegacion/
    ├── login-view.fxml
    ├── principal-view.fxml
    ├── registro-view.fxml
    ├── Consulta-view.fxml
    ├── detalle-view.fxml
    └── uam.png                      # Recursos gráficos
