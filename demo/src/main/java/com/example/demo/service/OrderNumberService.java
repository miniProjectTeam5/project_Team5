package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.OrderNumberRepository;
import com.example.demo.dto.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderNumberService {

    private final OrderNumberRepository orderNumberRepository;

    /*@Transactional */
    public OrderNumberDto Numbering(Long ordered) {
        //OderNumber FK

        OrderNumberDto orderNumber = new OrderNumberDto(); // 주문 번호
        Long id = orderNumber.getOrderId(); // 주문 ID 획득
       // Long id = OrderRequestDto.getOrderId();
        // 주문 ID로 구간있는 주문번호 계산
        Long number = orderNumber.Order(id);
        Order od = orderNumberRepository.findById(number).orElseThrow(); //주문 id
        return orderNumber;
    }

//    public void Numbering(Long orderNumber) {
//    }
}
