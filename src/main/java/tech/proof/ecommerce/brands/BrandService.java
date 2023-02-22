package tech.proof.ecommerce.brands;

import java.util.Optional;

public sealed interface BrandService permits BrandServiceImpl {

    Optional<Brand> findByKeyword(String keyword);

}
