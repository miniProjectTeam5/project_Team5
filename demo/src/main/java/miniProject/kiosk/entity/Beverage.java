package miniProject.kiosk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.core.SpringVersion;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beverage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String beverageName;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String imageUrl;
}
