package miniProject.kiosk.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderNumberDto {

    Long orderId;
    OrderNumber orderNumber;

    Long waitingNumber; // 대기번호 추가

//    public OrderNumberDto(OrderNumber orderId){
//        return this.orderNumber;
//    }

    public OrderNumberDto(OrderNumber orderNumber){
        return this.waitingNumber;
    }


}
