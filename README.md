# eCommerce platform

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [FAQs](#faqs)

## General Info
Service to consult price variations over time of the products offered by the company's ecommerce.

### Screenshot
![test results](https://github.com/jd-ap/e-commerce-platform/tree/main/doc/tests-picture.png)

### URLS
| Name               | Path                                                             |
|--------------------|------------------------------------------------------------------|
| index              | localhost:8080/ecommerce                                         |
| h2 console         | /h2-console `user = guest` `password = changeIt`                 |
| swagger ui         | /swagger-ui.html                                                 |
| findPriceByProduct | /catalog/{brand}/products/{product-id}?priceDate=#{yyyyMMddHHmm} |

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

* [java](https://example.com): Version 17
* [Spring boot](https://example.com): Version 3.0.2

## Installation

### Building Spring Web Service container
    $ docker build -f containerfile -t proof/e-commerce .
    $ docker run -p 9000:9000 proof/e-commerce --server.port=9000

## FAQs
1. **Why does the url of `findPriceByProduct` for price have this format?**
   - I defined a `catalog` collection that although it is not said in the statement, for context I considered more comfortable that in a catalog the products are collected by brand.
   - That `{brand}` is a keyword helps to make the url more descriptive.
   - `{product-id}` is the product identifier to query, at first I was thinking of separating this value, being the first two digits in a family (`{family}{product-seq}`).
   - `priceDate` is an optional parameter, by default the current date and time is taken.
2. **How is the structure of the packages defined in the project?**
   The project packaging follows a component segmentation, where `brands`, `products` and `prices` are domain components; and `web` is a http exposure component.
3. **Why does `CatalogController` call directly to `PriceRepository`?**
   To maintain a sober architecture and to the minimum of the solution. At the time of growth you can define the corresponding `<ENTITY>Service` classes, like this:   ````java
   ````java
   package tech.proof.ecommerce.prices;

   import java.time.LocalDateTime;
   import java.util.Optional;
   
   public sealed interface PriceService permits PriceServiceImpl {
   
       Optional<Price> findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(String keyword, Long productId, LocalDateTime aDate);
   
   }
   ````
   ````java
   package tech.proof.ecommerce.prices;

   import lombok.RequiredArgsConstructor;
   import org.springframework.stereotype.Service;
   
   import java.time.LocalDateTime;
   import java.util.Optional;
   
   @RequiredArgsConstructor
   @Service
   final class PriceServiceImpl implements PriceService {
   
       private final PriceRepository priceRepository;
   
       @Override
       public Optional<Price> findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(String keyword, Long productId, LocalDateTime aDate) {
           LocalDateTime priceDate = null == aDate ? LocalDateTime.now() : aDate;
   
           return priceRepository.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(keyword, productId, priceDate);
       }
   
   }
   ````
   And change the reference from `<ENTITY>Repository` to `<ENTITY>Service`.
   > see: branch `feature/architecture-extended` 
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
   
   ** The order of the statements in the where was modified by the definition of an index, for that reason the name of the method does not match the query.

> Translated with www.DeepL.com/Translator (free version)