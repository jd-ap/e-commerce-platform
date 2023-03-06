package tech.proof.ecommerce.products;

import org.springframework.lang.NonNull;

import java.util.Optional;

public sealed interface ProductService permits ProductServiceImpl {

    Optional<Product> findByIdAndBrandKeyword(@NonNull Long productId, @NonNull String brandUrl);

}
