package tech.proof.ecommerce.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import tech.proof.ecommerce.ECommercePlatformApplication;
import tech.proof.ecommerce.web.model.Item;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT, classes = ECommercePlatformApplication.class)
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@DisplayName("CatalogController Integration Tests")
class CatalogControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void givenFindPriceByProduct_whenTest1_thenReturns200() {
        //given
        String brand = "zara";
        Long productId = 35455L;
        String aDate = "202006141000";

        var expected = new Item(35455L, 1L, 1L,
                LocalDateTime.of(2020, 06, 14, 00, 00, 00),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                "EUR 35,50 €");

        //when
        ResponseEntity<Item> responseEntity =
                restTemplate.getForEntity("/catalog/%s/products/%d?date=%s".formatted(brand, productId, aDate), Item.class);

        //then
        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expected, responseEntity.getBody());

    }

    @Test
    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void givenFindPriceByProduct_whenTest2_thenReturns200() {
        //given
        String brand = "zara";
        Long productId = 35455L;
        String aDate = "202006141600";

        var expected = new Item(35455L, 1L, 4L,
                LocalDateTime.of(2020, 06, 14, 16, 00, 00),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                "EUR 38,95 €");

        //when
        ResponseEntity<Item> responseEntity =
                restTemplate.getForEntity("/catalog/%s/products/%d?date=%s".formatted(brand, productId, aDate), Item.class);

        //then
        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expected, responseEntity.getBody());

    }

    @Test
    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void givenFindPriceByProduct_whenTest3_thenReturns200() {
        //given
        String brand = "zara";
        Long productId = 35455L;
        String aDate = "202006142100";

        var expected = new Item(35455L, 1L, 4L,
                LocalDateTime.of(2020, 06, 14, 16, 00, 00),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                "EUR 38,95 €");

        //when
        ResponseEntity<Item> responseEntity =
                restTemplate.getForEntity("/catalog/%s/products/%d?date=%s".formatted(brand, productId, aDate), Item.class);

        //then
        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expected, responseEntity.getBody());

    }

    @Test
    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
    void givenFindPriceByProduct_whenTest4_thenReturns200() {
        //given
        String brand = "zara";
        Long productId = 35455L;
        String aDate = "202006151000";

        var expected = new Item(35455L, 1L, 3L,
                LocalDateTime.of(2020, 06, 15, 00, 00, 00),
                LocalDateTime.of(2020, 06, 15, 11, 00, 00),
                "EUR 30,50 €");

        //when
        ResponseEntity<Item> responseEntity =
                restTemplate.getForEntity("/catalog/%s/products/%d?date=%s".formatted(brand, productId, aDate), Item.class);

        //then
        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expected, responseEntity.getBody());

    }

    @Test
    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
    void givenFindPriceByProduct_whenTest5_thenReturns200() {
        //given
        String brand = "zara";
        Long productId = 35455L;
        String aDate = "202006162100";

        var expected = new Item(35455L, 1L, 4L,
                LocalDateTime.of(2020, 06, 14, 16, 00, 00),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                "EUR 38,95 €");

        //when
        ResponseEntity<Item> responseEntity =
                restTemplate.getForEntity("/catalog/%s/products/%d?date=%s".formatted(brand, productId, aDate), Item.class);

        //then
        assertEquals(OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expected, responseEntity.getBody());

    }

}