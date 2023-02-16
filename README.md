# eCommerce platform

## Building Spring Web Service container
    docker build -f containerfile -t proof/e-commerce .
    docker run -p 9000:9000 proof/e-commerce --server.port=9000

## URLS
| Name                  | Path                                                                        |
|-----------------------|-----------------------------------------------------------------------------|
| index                 | localhost:80/ecommerce                                                      |
| h2 console            | /h2-console                                                                 |
| swagger               | /swagger-ui.html                                                            |
| findPriceByProduct    | /catalog/${brandUrl:'ZARA'}/products/${productId}?priceDate=#{yyyyMMddHHmm} |
| ---                   | ---                                                                         |
| getProductListByBrand | /catalog/${brandUrl:'ZARA'}/products                                        |
| getBrandList          | /catalog                                                                    |

## Entities
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