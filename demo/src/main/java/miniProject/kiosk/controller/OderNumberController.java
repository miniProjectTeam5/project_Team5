//package miniProject.kiosk.controller;
//
//import miniProject.kiosk.dto.OderRequestDto;
//import miniProject.kiosk.service.OrderNumberService;
//import miniProject.kiosk.dto.OrderNumberDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class OderNumberController {
//
//    private final OrderNumberService orderNumberService;

//    @GetMapping("/order-number/{id}")
//    public OrderNumberDto NumberingOrder(@PathVariable Long id, @RequestBody OderRequestDto requestDto) {
//        Long orderNumber = requestDto.getOrderId();
//        return new orderNumberService.NumberingOrder(id);
//    }

//}
