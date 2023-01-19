@RequiredArgsConstructor
public class OrderNumberController {
    private final OrderNumberService orderNumberService;

    // dev jun23
    @GetMapping("/order/order-number") // 주문번호 생성
    public OrderNumberDto signup(@RequestBody Order orderId) {
        OrderNumber ordernum = orderNumberService.Numbering(id, orderId);
        return new OrderNumberDto(ordernum); //
    }

    // dev jun23
    @GetMapping("/order/waiting-number")
    public OrderNumberDto signup(@RequestBody OrderNumber orderNum) {
        OrderNumber waitingnum = orderNumberService.Waiting(number, orderNum);
        return new OrderNumberDto(waitingnum); //
    }


}