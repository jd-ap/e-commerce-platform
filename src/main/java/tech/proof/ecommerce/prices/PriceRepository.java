package tech.proof.ecommerce.prices;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query("from Price p where :aDate between p.startDate and p.endDate and p.product.id = :productId and p.product.brand.keyword = :keyword order by p.priority desc, p.startDate desc limit 1")
    Optional<Price> findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(String keyword, Long productId, LocalDateTime aDate);

}
