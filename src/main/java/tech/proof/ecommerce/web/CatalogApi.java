package tech.proof.ecommerce.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import tech.proof.ecommerce.web.model.Item;

import java.time.LocalDateTime;

@Tag(name = "catalog", description = "the catalog API")
public interface CatalogApi {

    @Operation(summary = "Get price by product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "found the price", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class))}),
            @ApiResponse(responseCode = "412", description = "Invalid parameters", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content)})
    ResponseEntity<Item> findPriceByProduct(@Parameter(description = "identifier of brand to be searched", required = true) String brandUrl,
                                            @Parameter(description = "id of product to be searched", required = true) Long productId,
                                            @Parameter(description = "date to be searched") LocalDateTime priceDate);

}
