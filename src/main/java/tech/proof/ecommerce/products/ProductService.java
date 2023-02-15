package tech.proof.ecommerce.products;

import java.util.Optional;

public sealed interface ProductService permits ProductServiceImpl {

    Optional<Product> findByIdAndBrandKeyword(Long productId, String brandUrl);

}
