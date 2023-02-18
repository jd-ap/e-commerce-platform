package tech.proof.ecommerce.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
            @ApiResponse(responseCode = "200", description = "Found the price", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Item.class),
                            examples = @ExampleObject("{\n" +
                                    "  \"productId\": 35455,\n" +
                                    "  \"brandId\": 1,\n" +
                                    "  \"priceList\": 1,\n" +
                                    "  \"startDate\": \"2020-06-14T00:00:00\",\n" +
                                    "  \"endDate\": \"2020-12-31T23:59:59\",\n" +
                                    "  \"rate\": \"EUR 35,50 €\"\n" +
                                    "}"))
            }),
            @ApiResponse(responseCode = "412", description = "Invalid parameters", content = @Content),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content)})
    ResponseEntity<Item> findPriceByProduct(@Parameter(description = "identifier of brand to be searched", required = true, example = "zara") String brandUrl,
                                            @Parameter(description = "id of product to be searched", required = true, example = "35455") Long productId,
                                            @Parameter(description = "date to be searched", schema = @Schema(type="string", format = "date", example = "202006141000")) LocalDateTime priceDate);

}
