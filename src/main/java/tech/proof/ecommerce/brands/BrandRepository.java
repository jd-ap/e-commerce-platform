package tech.proof.ecommerce.brands;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface BrandRepository extends CrudRepository<Brand, Long> {

    Optional<Brand> findByKeyword(String keyword);

}
