package tech.proof.ecommerce.prices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @InjectMocks
    PriceServiceImpl priceService;

    @Spy
    PriceRepository priceRepository;

    @Test
    void givenFindOne_thenReturnsOne() {
        //given
        String brand = "zara";
        Long productId = 0L;
        LocalDateTime aDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Price expected = Price.builder().id(0L).amount(0d).curr(Price.Curr.EUR).build();

        when(priceRepository.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(any(), any(), any()))
                .thenReturn(Optional.of(expected));

        //when
        Optional<Price> result = priceService.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(brand, productId, aDate);

        //then
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void givenFindOne_whenProductNotExist_thenReturnsEmpty() {
        //given
        String brand = "zara";
        Long productId = 0L;
        LocalDateTime aDate = LocalDateTime.of(2020, 6, 14, 10, 0);

        when(priceRepository.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(any(), any(), any()))
                .thenReturn(Optional.empty());

        //when
        Optional<Price> result = priceService.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(brand, productId, aDate);

        //then
        assertTrue(result.isEmpty());
    }

    @Test
    void givenFindOne_whenKeywordParamIsNull_thenThrowAssertionError() {
        var error = assertThrows(AssertionError.class, () -> priceService.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority(null, 0L, null));

        assertNotNull(error);
        assertTrue(error.getMessage().contains("keyword"));
    }

    @Test
    void givenFindOne_whenProductIdParamIsNull_thenThrowAssertionError() {
        var error = assertThrows(AssertionError.class, () -> priceService.findOneByBrandKeywordAndProductIdAndDateBetweenOrderByPriority("", null, null));

        assertNotNull(error);
        assertTrue(error.getMessage().contains("productId"));
    }
}