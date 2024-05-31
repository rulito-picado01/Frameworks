# Frameworks

- Técnica de reuso de código. Es una técnica implementada utilizando las bondades del paradigma de objetos donde el
  reuso se da utilizando herencia, composición, polimorfismo, overriding, etc.
- Definición: A framework is a set of cooperating classes that make up a reusable design for a specific class of
  software (GOF book).
- Imcompleto por naturaleza.
    - Aplicación basada en un framework = clases del framework + configuración + clases de la aplicación.
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
      Start s = new Start(m);
      ```
    - Luego invoque al método `init()`.

## Frameworks de Caja Blanca

- Basados en la composición.