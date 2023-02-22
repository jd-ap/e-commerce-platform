package tech.proof.ecommerce.web;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.proof.ecommerce.prices.PriceService;
import tech.proof.ecommerce.web.model.Item;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog")
public class CatalogController implements CatalogApi {

    private final PriceService priceService;

    @GetMapping("/{brand-url}/products/{product-id}")
    public ResponseEntity<Item> findPriceByProduct(@PathVariable(name = "brand-url") String brandUrl,
                                                   @PathVariable(name = "product-id") Long productId,
                                                   @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyyMMddHHmm") LocalDateTime priceDate) {

        return priceService.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(brandUrl, productId, priceDate)
                .map(Item::of)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

}
