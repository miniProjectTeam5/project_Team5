package miniProject.kiosk.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniProject.kiosk.dto.*;

import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.entity.Orders;
import miniProject.kiosk.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    public TokenAccessDto saveOrder(@RequestBody List<Orders> ordersList, String phoneNumber, HttpServletResponse response) {
        return orderService.saveOrder(ordersList, phoneNumber, response);
    }

    @PostMapping("/order/bill")
    public OrderResponseDto totalPayment(@RequestBody TokenAccessDto token, HttpServletResponse response) {
        return orderService.totalPayment(token, response);
    }

    @PostMapping("/order/points")
    public Integer stackPoints(@RequestBody PhoneNumRequestDto phoneNumber, TokenAccessDto token) {
        return orderService.stackPoints(phoneNumber, token);
    }

    @PostMapping("/order/dailySales")
    public Long dailySales(@RequestBody DailySalesRequestDto date) {
        return orderService.dailySales(date);
    }

}
