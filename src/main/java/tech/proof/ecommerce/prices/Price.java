package tech.proof.ecommerce.prices;

import jakarta.persistence.*;
import lombok.*;
import tech.proof.ecommerce.products.Product;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "newInstance")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "PRICE_LIST",
        indexes = @Index(name = "IX_START_DATE_PRODUCT_ID", columnList = "startDate desc, product_id"))
public final class Price {
    @Id
    @GeneratedValue(generator = "priceSeq")
    @SequenceGenerator(name = "priceSeq", sequenceName = "price_seq", allocationSize = 1)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @Column(nullable = false, precision = 16, columnDefinition = "NUMBER (16,2)")
    private double amount = 0.0;
    @Column(nullable = false, length = 3)
    @Enumerated(value = EnumType.STRING)
    private Curr curr;
    @Column(nullable = false)
    private Integer priority = 0;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public String getAmountFormatted() {
        return curr.formatted(amount);
    }

    @RequiredArgsConstructor
    public enum Curr {
        EUR("€", "Eur", "%1$s %2$,.2f %3$s"),
        USD("$", "US Dollar", "%3$s %2$,.2f %1$s"),
        COP("$", "Colombian Peso", "%3$s %2$,.2f %4$s");

        public final String symbol;
        public final String displayName;
        private final String patternFormat;

        public String formatted(double aValue) {
            return this.patternFormat.formatted(this.name(), aValue, this.symbol, this.displayName);
        }
    }

}
