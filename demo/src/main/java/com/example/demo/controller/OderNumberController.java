package com.example.demo.controller;

import com.example.demo.service.OrderNumberService;
import com.example.demo.dto.OrderNumberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.dto.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OderNumberController {

    private final OrderNumberService orderNumberService;

    @GetMapping("/order-number/{id}")
    public OrderNumberDto NumberingOrder(@PathVariable Long id, @RequestBody OderRequestDto requestDto) {
        Long orderNumber = requestDto.getOrderId();
        return new orderNumberService.NumberingOrder(id);
    }

}
