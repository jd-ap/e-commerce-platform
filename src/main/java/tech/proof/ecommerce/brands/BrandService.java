package tech.proof.ecommerce.brands;

import org.springframework.lang.NonNull;

import java.util.Optional;

public sealed interface BrandService permits BrandServiceImpl {

    Optional<Brand> findByKeyword(@NonNull String keyword);

}
