package tech.proof.ecommerce.products;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
final class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Optional<Product> findByIdAndBrandKeyword(Long productId, String brandUrl) {
        return productRepository.findByIdAndBrandKeyword(productId, brandUrl);
    }

}
