package miniProject.kiosk.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class OrderResponseDto {
    private Integer totalAmount;

    public OrderResponseDto(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
}
