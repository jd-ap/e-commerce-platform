package tech.proof.ecommerce.web.model;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.proof.ecommerce.prices.Price;

import java.time.LocalDateTime;

@Schema
public record Item(Long productId,
                   Long brandId,
                   Long priceList,
                   LocalDateTime startDate,
                   LocalDateTime endDate,
                   String rate) {

    public static Item of(Price price) {
        return new Item(price.getProduct().getId(),
                price.getProduct().getBrand().getId(),
                price.getId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getAmountFormatted());
    }

}
