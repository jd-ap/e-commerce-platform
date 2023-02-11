package tech.proof.ecommerce.products;

import jakarta.persistence.*;
import lombok.*;
import tech.proof.ecommerce.brands.Brand;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor(staticName = "newInstance")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "PRODUCTS")
public final class Product {
    @Id
    @GeneratedValue(generator = "productSeq")
    @SequenceGenerator(name = "productSeq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false, length = 64)
    private String name;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

}
