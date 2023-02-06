### Documentation
En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

Campos:

__*BRAND_ID*__: foreign key de la cadena del grupo (1 = ZARA).  
__*START_DATE , END_DATE*__: rango de fechas en el que aplica el precio tarifa indicado.  
__*PRICE_LIST*__: Identificador de la tarifa de precios aplicable.  
__*PRODUCT_ID*__: Identificador código de producto.  
__*PRIORITY*__: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rango de fechas se aplica la de mayor prioridad (mayor valor numérico).  
__*PRICE*__: precio final de venta.  
__*CURR*__: iso de la moneda.  

Se pide:

Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:

__*Acepte como parámetros de entrada*__: fecha de aplicación, identificador de producto, identificador de cadena.  
__*Devuelva como datos de salida*__: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.

Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

-     Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-     Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-     Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-     Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-     Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)

Se valorará:

* Diseño y construcción del servicio.
* Calidad de Código.
* Resultados correctos en los test.

### Guides
Pensando en los próximos creemos que sería muy interesante para ti el poder acceder a un proyecto de Inditex. En concreto hemos pensado en el equipo actual de Sisu, es la parte de empaquetado de Inditex-

Para ello, desde Inditex nos solicitan prueba técnica y luego entrevista con ellos.

La prueba está adjunta y como indicaciones generales te diría que les gusta un enfoque API First, arquitectura hexagonal (máximo desacoplamiento), cubrir la mayor parte de la pirámide de tests, seguir buenas prácticas (Clean Code, SOLID...), así como contenerizar las soluciones.

Es indispensable incluir un README (en inglés) en el que se indiquen decisiones de diseño, comandos de compilación, arranque, etc., así como las principales URLs de la aplicación (swagger, openapi, consola H2, postman con peticiones de prueba, etc...). Al final es para que vean tu potencial así que cuanto mejor lo despliegues más lo apreciarán.

Cuando lo tengas listo, compártemelo, por favor. Lo mejor sería un zip dentro de google drive, si además lo tienes en un repo, mejor aún porque pueden ver commits y demás.

Por supuesto, si tienes alguna duda me comentas y lo vemos. Normalmente se suele tardar un par de días si es dedicación en pleno o una semana si es parcial.

