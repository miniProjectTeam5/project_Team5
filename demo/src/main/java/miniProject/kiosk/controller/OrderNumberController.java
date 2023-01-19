package miniProject.kiosk.controller;

import miniProject.kiosk.service.OrderNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderNumberController {
    private final OrderNumberService orderNumberService;

    // dev jun23
    @GetMapping("/order/order-number") // 주문번호 생성
    public OrderNumberDto ordernumber(@RequestBody Order orderId) {
        OrderNumber ordernum = orderNumberService.Numbering(id, orderId);
        return new OrderNumberDto(ordernum); //
    }

    // dev jun23
    @GetMapping("/order/waiting-number")
    public OrderNumberDto waitingnumber(@RequestBody OrderNumber orderNum) {
        OrderNumber waitingnum = orderNumberService.Waiting(number, orderNum);
        return new OrderNumberDto(waitingnum); //
    }


}