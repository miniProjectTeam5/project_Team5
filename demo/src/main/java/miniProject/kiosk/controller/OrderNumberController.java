package miniProject.kiosk.controller;

//import miniProject.kiosk.dto.OrderNumberDto;
import miniProject.kiosk.dto.OrderNumberRequestDto;
import miniProject.kiosk.dto.OrderRequestMsgDto;
import miniProject.kiosk.dto.TokenAccessDto;
import miniProject.kiosk.entity.OrderNumber;
import miniProject.kiosk.entity.Orders;
//import miniProject.kiosk.service.OrderNumberService;
import lombok.RequiredArgsConstructor;
import miniProject.kiosk.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderNumberController {
//    private final OrderNumberService orderNumberService;
    private final OrderService orderService;

    // dev jun23
    @GetMapping("/order/orderNumber") // 주문번호 생성
    public OrderNumberRequestDto cntOrder() {
        return orderService.cntOrder();
    }

    // dev jun23
//    @GetMapping("/order/waiting-number")
//    public OrderNumberDto waitingnumber(@RequestBody OrderNumber orderNum) {
//        OrderNumber waitingnum = orderNumberService.Waiting(number, orderNum);
//        return new OrderNumberDto(waitingnum); //
//    }


}