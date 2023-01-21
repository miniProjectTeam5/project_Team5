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
    public TokenAccessDto saveOrder(@RequestBody List<Orders> ordersList, HttpServletResponse response) {
        return orderService.saveOrder(ordersList, response);
    }

    @PostMapping("/order/bill")
    public OrderResponseDto totalPayment(@RequestBody TokenAccessDto token, HttpServletResponse response) {
        return orderService.totalPayment(token, response);
    }

    @PostMapping("/order/points")
    public StackPointResponseDto stackPoints(@RequestBody StackPointDto stackPointDto) {
        return orderService.stackPoints(stackPointDto);
    }

    @PostMapping("/order/dailySales")
    public int dailySales(@RequestBody DailySalesRequestDto date) {
        return orderService.dailySales(date);
    }

    @GetMapping("/order/dailySales")
    public int showDailySales(@RequestBody DailySalesRequestDto date) {
        return orderService.dailySales(date);
    }

    @GetMapping("/order/dailySales/details")
    public List<Orders> showDailySalesDetails(@RequestBody DailySalesRequestDto date) {
        return orderService.showDailySalesDetails(date);
    }

}
