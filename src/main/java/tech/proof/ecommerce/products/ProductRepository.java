package tech.proof.ecommerce.products;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findByIdAndBrandKeyword(Long productId, String brandUrl);

}
