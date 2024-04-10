package tech.proof.ecommerce.web.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tech.proof.ecommerce.prices.Price;
import tech.proof.ecommerce.web.model.Item;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemMapper {

    public static Item fromPrice(Price price) {
        var item = new Item();
        item.setProductId(price.getProduct().getId());
        item.setBrandId(price.getProduct().getBrand().getId());
        item.setPriceList(price.getId());
        item.setStartDate(price.getStartDate());
        item.setEndDate(price.getEndDate());
        item.setRate(price.getAmountFormatted());

        return item;
    }

    public static Item newInstance(Long productId, Long brandId, Long priceId, LocalDateTime priceStartDate, LocalDateTime priceEndDate, String priceAmountFormatted) {
        var item = new Item();
        item.setProductId(productId);
        item.setBrandId(brandId);
        item.setPriceList(priceId);
        item.setStartDate(priceStartDate);
        item.setEndDate(priceEndDate);
        item.setRate(priceAmountFormatted);

        return item;
    }
}
