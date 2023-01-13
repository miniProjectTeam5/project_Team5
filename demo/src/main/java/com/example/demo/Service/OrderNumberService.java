package com.example.demo.Service;

import com.example.demo.Entity.OrderNumber;
import com.example.demo.Repository.OrderNumberRepository;
import com.example.demo.dto.OrderNumberDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderNumberService {

    private final OrderNumberRepository orderNumberRepository;

    @Transactional
    public OrderNumberDto NumberingOrder(Long id, Long ordered) {
        //OderNumber FK
        OrderNumber orderid = orderNumberRepository.findById(id).orElseThrow(); //주문 id
        OrderNumberDto orderNumber = new OrderNumberDto(); // 주문 번호
        return orderNumber;
    }

}
