//package miniProject.kiosk.service;
//
//import miniProject.kiosk.entity.Orders;
//import miniProject.kiosk.repository.OrderRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class OrdersServiceTest {
//OrderRepository orderRepository;
//
//    @Transactional
//    @Test
//    void saveOrder() {
//
//        int count = 3;
//        String menuName = "테스트";
//        Integer amount = 2;
//
//        List<Orders> ordersList = new ArrayList<>();
//
//        while (count-- > 0) {
//            Orders orders = Orders.builder()
//                    .menuName(menuName + count)
//                    .amount(amount)
//                    .build();
//
//            ordersList.add(orders);
//        }
//
//        orderRepository.saveAll(ordersList);
//
//
//    }
//}