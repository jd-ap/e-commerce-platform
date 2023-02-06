package tech.proof.ecommerce.web;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @GetMapping("/${brandUrl}/products/${productId}")
    public ResponseEntity<Void> findPriceByProduct (@PathVariable String brandUrl,
                                                    @PathVariable String productId,
                                                    @RequestParam(required = false) @DateTimeFormat(pattern = "yyyyMMddHHmm") LocalDateTime priceDate) {
        return ResponseEntity.internalServerError().build();
    }

}
