package com.example.demo.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderNumberDto {

    Long orderId;
    Long oderNumber;

    public Long Order(Long orderId){
        return this.oderNumber;
    }

}
