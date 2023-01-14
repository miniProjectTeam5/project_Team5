package miniProject.kiosk.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class OrderNumberDto {

    Long orderId;
    Long oderNumber;

    Long Order(Long orderId){
        return this.oderNumber;
    }

}
