package tech.proof.ecommerce.prices;

import java.time.LocalDateTime;
import java.util.Optional;

public sealed interface PriceService permits PriceServiceImpl {

    Optional<Price> findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(String keyword, Long productId, LocalDateTime aDate);

}
