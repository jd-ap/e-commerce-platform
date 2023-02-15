package tech.proof.ecommerce.brands;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
final class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Optional<Brand> findByKeyword(String keyword) {
        return brandRepository.findByKeyword(keyword);
    }

}
