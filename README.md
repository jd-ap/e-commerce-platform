# eCommerce platform

## Building Spring Web Service container
    docker build -f containerfile -t proof/e-commerce .
    docker run -p 9000:9000 proof/e-commerce --server.port=9000

## URLS
| name                  | path                                                                        |
|-----------------------|-----------------------------------------------------------------------------|
| index                 | localhost:80/ecommerce                                                      |
| h2 console            | /                                                                           |
| swagger               | /                                                                           |
| findPriceByProduct    | /catalog/${brandUrl:'ZARA'}/products/${productId}?priceDate=#{yyyyMMddHHmm} |
| ---                   | ---                                                                         |
| getProductListByBrand | /catalog/${brandUrl:'ZARA'}/products                                        |
| getBrandList          | /catalog                                                                    |