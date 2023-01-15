package miniProject.kiosk.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import miniProject.kiosk.dto.OrderRequestDto;
import miniProject.kiosk.dto.OrderRequestMsgDto;
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

    //사용자가 고른 메뉴를 order 테이블에 넣어야 함.
    //사용자가 여러개를 골랐을텐데 이걸 어떻게 넣지?
    @PostMapping("/order")
    public OrderRequestMsgDto saveOrder(@RequestBody List<Orders> ordersList, String phoneNumber ,HttpServletResponse response) {
        return orderService.saveOrder(ordersList, phoneNumber, response);
    }

    //주문 총 합을 전달해야 함.
    //order에 들어간 메뉴들의 가격을 찾아서 합산하면 될 것 같은데 어떻게 할까?
    //어디까지가 하나의 주문인지 어떻게 알지?
    @GetMapping("/order/bill")
    public OrderRequestMsgDto totalPayment (HttpServletRequest request) {
        return orderService.totalPayment(request);
    }


    @PostMapping("/order/temp")
    public Menu addMenu(@RequestBody Menu menu) {
        return orderService.addMenu(menu);
    }


}
