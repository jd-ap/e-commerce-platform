package tech.proof.ecommerce.prices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
final class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Override
    public Optional<Price> findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(String keyword, Long productId, LocalDateTime aDate) {
        LocalDateTime priceDate = null == aDate ? LocalDateTime.now() : aDate;

        return priceRepository.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(keyword, productId, priceDate);
    }

}
