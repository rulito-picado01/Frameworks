# Frameworks

- Técnica de reuso de código. Es una técnica implementada utilizando las bondades del paradigma de objetos donde el
  reuso se da utilizando herencia, composición, polimorfismo, overriding, etc.
- Definición: A framework is a set of cooperating classes that make up a reusable design for a specific class of
  software (GOF book).
- Imcompleto por naturaleza.
    - Aplicación basada en un framework = clases del framework + configuración + clases de la aplicación.
    - Puntos de Extensión (métodos hooks): Aquellas "partes" a completar para formar la aplicación llevan el nombre de
      métodos hooks.
- Frameworks vs Librerías.
    - Las librerías también son una forma de reutilizar código.
    - Sin embargo, las librerías son mas específicas, para problemas concretos.
    - Los frameworks brindan un mayor poder de reuso a nivel aplicación dentro de un dominio específico.
    - Dominios ténicos: Aplicaciones Web, Mobile, Persistencia, Sistemas de Ventanas, etc.
    - Dominios funcionales: Bancario, Ecommerce, etc.
    - En las librerías es el desarrollador de la aplicación el que instancia las clases de las librerías e invoca sus
      servicios.
    - En los frameworks, son las clases del framework quienes invocan a las clases implementadas por el desarrollador,
      usuario del framework. Además, son las clases del framework las que inician la aplicacion.
- Los frameworks al ser mas complejos que las librerias requieren de muy buena documentación.

## Ejemplos de Frameworks Web: Spring y Javalin

- Observemos que en ambos son las clases del framework las que inician la aplicación.
- Transforman una petición `http` en una invocación a un método del usuario del framework.
    - `http://{HOST}:{PORT}/action?param1=valor1`
- Spring:
    - Tiene parte de su configuracion en un archivo de propiedades
    - Sus hooks/extension points son basados fuertemente en annotations.
    - Debo anotar con `@RestController` y otras anotaciones para crear controllers.
    - Spring descubre as anotaciones, instancia las clases y las pone a disposición de las clases del framework.
- Javalin:
    - Por el contrario, Javalin requiere que las clases que son controllers implementen la interfaz `Handler` y luego
      dichas instancias se las damos al framework a travez de la clase `Javalin`.

## Implementación: De Caja Blanca vs Caja Negra

- Caja Blanca: Basados en la herencia y en el patrón de diseño Template Method.
- Caja Negra: Basados en la composición o descubrimiento (anotaciones, reflection).

### Mostrar Texto en Pantalla

- Este framework le permite mostrar texto en la pantalla.
- Implementado como Caja Blanca: para usarlo, Ud. debe:
    - Extender de la case: `Screen`, e implementar su método abstracto.
    - Luego crea una instancia de `Start` pasando por constructor la implementacion de la subclase de `Screen`, asi:
      ```java
      Start s = new Start(new MyApplication());
      s.init();
      ```
- Implementado como Caja Negra
- Versión 1:
    - Para usarlo, Usted debe:
        - Implementar la interfaz `Text`.
        - Crear una instancia de `Start` y pase su implementación de `Text` como parámetro del constructor, así:
          `Start frw = new Start(new MiImplementacionDeText());`
        - Finalmente, invoque al metodo init():
          `frw.init();`
- Versión 2: usando un archivo de configuración:
    - Para usarlo, Usted debe:
        - Implementar la interfaz `Text`.
        - Genere un archivo `.properties` con la estructura:
          `clase = paquete.clase`
        - Instancie la clase `Screen` y como parámetro de constructor pase el path al archivo properties, así:
          `Start frw = new Start("path/al/archivo");`
        - Finalmente, invoque al metodo init():
          `frw.init();`

### JUnit: Una pequeña implementación ilustrativa

- Implementado como **Caja Blanca** (versiones viejas de JUnit)
- Dos versiones, v1: un método test por clase. v2: varios métodos tests por clase.
    - **v1**: Para crear tests unitarios, Ud. debe:
        - Extender de la clase `TestCase` y sobreescribir el método `runTest()`.
        - Ademas puede sobreescribir los métodos `before()` y `after()` para realizar tareas de inicialización y clean
          up.
        - Luego, utilizar `TestRunner.executeAll` pasando por parémetro una lista de subclases de `TestCase`, así:
          ```java
            var tests = new TestCase[]{
                new TestUno(),
                new TestDos()
            };
            TestRunner.executeAll(tests);
          ```
    - **v2**: Para crear tests unitarios, Ud. debe:
        - Extender de la clase `TestCase` y escribir cada método test con las siguientes características:
            - `public`.
            - retorna `void`.
            - no recibe parámetros.
            - su nombre comienza con `test`.
        - Ademas puede sobreescribir los métodos `before()` y `after()` para realizar tareas de inicialización y
          clean up.
            - Luego, utilizar `TestRunner.executeAll` pasando por parémetro una lista de subclases de `TestCase`,
              así:
              ```java
              var tests = new TestCase[]{
                new TestUnoYDos()
              };
              TestRunner.executeAll(tests);
              ```
- Implementado como **Caja Negra** (version 5 de JUnit)
    - Para crear tests unitarios, Ud. debe:
        - En una clase especifica, Ud debe anotar con `@TestUnitario` cada uno de los métodos de test.
        - Ademas puede anotar con `@Before()` y `@After()` para realizar tareas de inicialización y
          clean up.
        - Luego, utilizar `new TestRunner().runTest(tests)` pasando por parámetro una lista de clases que contienen los
          tests así:
          ```java
            var tests = new Class[]{
               TestUnoYDos.class
            };
            new TestRunner().runTests(tests);
          ```

## n8n: Un Application Framework

- n8n es un application framework que permite crear workflows.
- ¿Por qué es un application framework?
    - Porque no esta incompleto, viene con una serie de nodos predefinidos.
    - Porque permite crear mis propios nodos (Nodo Code) para extender su funcionalidad.
- Para iniciarlo:
    - `cd n8ndocker`
    - `docker compose up -d`
    - Navegar a: `http://localhost:5678/`
    - Click en "demo_frameworks"
    - Modificar las credenciales del nodo Email Sender por las tuyas de Mailtrap.

## Implementación de un Mini n8n

- Este framework (de caja negra) le permite realizar una tarea compleja combinando múltiples tareas simples.
- Para usarlo, Ud. debe:
    - Crear una instancia de `Workflow` pasando por constructor una lista de nodos:
      ```java
      // Crea una instancia de Workflow inicializada.
      var workflow = Workflow.createFrom(/* Lista de Nodos */);
      // Ejecuta cada nodo en el orden en que fueron agregados a la lista.
      workflow.run(Params.empty());
      ```
    - Existen dos nodos provistos por el framework `HttpRequest` y `EmailSender`.
    - Si se desea crear un nodo propio, se debe implementar la interfaz `CodeNode`.
  
 

