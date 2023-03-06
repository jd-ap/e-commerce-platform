package tech.proof.ecommerce.prices;

import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.Optional;

public sealed interface PriceService permits PriceServiceImpl {

    Optional<Price> findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(@NonNull String keyword, @NonNull Long productId, LocalDateTime aDate);

}
