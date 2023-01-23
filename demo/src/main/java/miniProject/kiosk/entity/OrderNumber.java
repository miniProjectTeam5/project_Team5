package miniProject.kiosk.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import miniProject.kiosk.dto.OrderNumberRequestDto;


import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class OrderNumber extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderCnt = 0L;

    public OrderNumber(OrderNumberRequestDto orderNumberRequestDto) {
        this.orderCnt = orderNumberRequestDto.getOrderCnt();

    }
}
