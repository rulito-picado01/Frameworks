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

## Frameworks de Caja Blanca

- Basados en la herencia y en el patrón de diseño Template Method.
- Este framework (de caja blanca) le permite mostrar texto en la pantalla.
- Para usarlo, Ud. debe:
    - Extender de la case: `Screen`, e implementar su método abstracto.
    - Luego crea una instancia de `Start` pasando por constructor la implementacion de la subclase de `Screen`, asi:
      ```java
      Start s = new Start(new MyApplication());
      s.init();
      ```

## Frameworks de Caja Negra

- Basados en la composición.

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