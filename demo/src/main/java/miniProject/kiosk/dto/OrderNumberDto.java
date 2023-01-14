package miniProject.kiosk.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderNumberDto {

    Long orderId;
    Long oderNumber;

    Long Order(Long orderId){
        return this.oderNumber;
    }

}
