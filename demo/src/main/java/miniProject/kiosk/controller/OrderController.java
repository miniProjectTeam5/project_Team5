package miniProject.kiosk.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import miniProject.kiosk.dto.*;

import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.entity.Orders;
import miniProject.kiosk.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;


    @PostMapping("/order")
    public OrderRequestMsgDto saveOrder(@RequestBody List<Orders> ordersList, String phoneNumber ,HttpServletResponse response) {
        return orderService.saveOrder(ordersList, phoneNumber, response);
    }


    @GetMapping("/order/bill")
    public OrderResponseDto totalPayment (HttpServletRequest request) {
        return orderService.totalPayment(request);
    }

    @PostMapping("/order/points")
    public Integer stackPoints(@RequestBody PhoneNumRequestDto phoneNumber, HttpServletRequest request) {
        return orderService.stackPoints(phoneNumber, request);
    }

    @PostMapping("/order/dailySales")
    public Long dailySales(@RequestBody DailySalesRequestDto date) {
        return orderService.dailySales(date);
    }


}
