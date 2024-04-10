# eCommerce platform

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [FAQs](#faqs)

## General Info
Service to consult price variations over time of the products offered by the company's ecommerce.

### Run tests in command line
`mvn clean test`  
![test results](https://github.com/jd-ap/e-commerce-platform/blob/main/doc/images/tests-picture.png?raw=true)

### Consume api in command line
````
curl -X 'GET' \
'http://localhost:9000/ecommerce/catalog/zara/products/35455?date=202006141000' \
-H 'accept: application/json'
````  
![test results](https://github.com/jd-ap/e-commerce-platform/blob/main/doc/images/findProduct-response.png?raw=true)

### Try the api from postman
![postman collection](https://github.com/jd-ap/e-commerce-platform/blob/main/doc/images/postman-collection-tree.png?raw=true)  
[go to the collection file](https://raw.githubusercontent.com/jd-ap/e-commerce-platform/main/doc/e-commerce%20platform.postman_collection.json)
### URLS
| Name               | Path                                                                                                                                                                               |
|--------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| index              | [localhost:9000/ecommerce](http://localhost:9000/ecommerce)                                                                                                                        |
| h2 console         | [/h2-console](http://localhost:9000/ecommerce/h2-console) <br>`jdbc = jdbc:h2:mem:ec_db` <br>`user = guest` <br>`password = changeIt`                                              |
| openapi swagger ui | [/swagger-ui.html](http://localhost:9000/ecommerce/swagger-ui.html) <br> [`openapi definition`](https://raw.githubusercontent.com/jd-ap/e-commerce-platform/main/doc/openapi.yaml) |
| findPriceByProduct | [/catalog/{brand}/products/{product-id}?priceDate=#{yyyyMMddHHmm}](http://localhost:9000/ecommerce/catalog/zara/products/35455?date=202006141000)                                  |

### Output
````json
"Item": {
  "product-id": long,
  "brand-id": long,
  "price-list": long,
  "start-date": DateTime,
  "end-date": DateTime,
  "rate": String
}
````

### Entities
````json
"Brand": {
  "id": long,       
  "name": String,
  "keyword": String
}

"Product": {
  "id": long,
  "name": String,
  "brand": Brand
}

"Price": {
  "id": long,
  "start-date": date,
  "end-date": date,
  "amount": double,
  "curr": String,
  "priority": long,
  "product": Product
}
````

## Technologies

* [java](https://www.java.com/es/): Version 17
* [Spring framework](https://spring.io/): Version 6.0.4
* [Spring boot](https://spring.io/projects/spring-boot): Version 3.2.4
* [OpenApi](https://www.openapis.org/): Version 1.8.0
* [Postman](https://www.postman.com/)

## Installation

### Building Spring Web Service container
    $ docker build -f containerfile -t proof/e-commerce .
    $ docker run -d --rm -p 9000:9000 --name ecommerce proof/e-commerce --server.port=9000
    $ docker system prune 

## FAQs
1. **Why does the url of `findPriceByProduct` for price have this format?**  
   - I defined a `catalog` collection that although it is not said in the statement, for context I considered more comfortable that in a catalog the products are collected by brand.
   - That `{brand}` is a keyword helps to make the url more descriptive.
   - `{product-id}` is the product identifier to query, at first I was thinking of separating this value, being the first two digits in a family (`{family}{product-seq}`).
   - `priceDate` is an optional parameter, by default the current date and time is taken.
2. **How is the structure of the packages defined in the project?**  
   The project packaging follows a component segmentation, where `brands`, `products` and `prices` are domain components; and `web` is a http exposure component.
   > _*_ The packages `products` and `brands` only their entities are required to cover the scope of the solution; their respective service and repository have been implemented as their natural evolution in the development.  
3. **Why do you use `@NonNull` annotations and `assert` statements for safenull validations in `PriceServiceImpl`?**  
   The assert statements are used for scenarios where their chance of occurrence is close to zero, for this case, 
   I found it appropriate since the `CatalogController` performs the null filters and the `@NonNull` annotation gives 
   the visual and syntactic reinforcement at compile and parse time.
4. **How did you arrive at the query construction for `PriceRepository.findOneByByBrandKeywordAndProductIdAndDateBetweenOrderByPriority`?**  
   ````jpqlcommunity
   from Price p 
   where :aDate between p.startDate and p.endDate 
     and p.product.id = :productId 
     and p.product.brand.keyword = :keyword 
   order by p.priority desc, p.startDate desc 
   limit 1
   ````
   A search is performed on the `Price` table filtering by the values collected from the endpoint.
   Matching tuples are organized by highest priority and most recent price entry date.
   By limiting the number of result, we only retrieve the best matching price.
   > _*_ The order of the statements in the where was modified by the definition of an index, for that reason the name of the method does not match the query.
5. **Why not a dockerfile?**  
   Following the `open container` proposal which seeks to decouple the `docker` brand from the containerization technology,
   the content is not affected from which I have taken as a basis the [spring guide](https://spring.io/guides/topicals/spring-boot-docker/).