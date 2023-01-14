package com.example.demo.controller;

import com.example.demo.service.OrderNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.dto.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OderNumberController {

    private final OrderNumberService orderNumberService;

    @GetMapping("/order-number/{id}")
    public String NumberingOrder(@PathVariable Long id/*, @RequestBody OrderRequestDto requestDto*/) {
        OrderRequestDto requestDto = new OrderRequestDto();
        Long orderNumber = requestDto.getOrderId();

         orderNumberService.Numbering(orderNumber);
         return "redirect:/articles";
    }

}
