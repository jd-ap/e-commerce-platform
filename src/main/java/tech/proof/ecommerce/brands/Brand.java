package tech.proof.ecommerce.brands;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "newInstance")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "BRANDS")
public final class Brand {
    @Id
    @GeneratedValue(generator = "brandSeq")
    @SequenceGenerator(name = "brandSeq", sequenceName = "brand_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, length = 64)
    private String name;
    @Column(nullable = false, length = 32, unique = true)
    private String keyword;

}
