package miniProject.kiosk.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniProject.kiosk.dto.*;

import miniProject.kiosk.dto.member.UpdatePointDto;
import miniProject.kiosk.entity.*;
import miniProject.kiosk.jwt.JwtUtil;
import miniProject.kiosk.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderNumberRepository orderNumberRepository;
    private final Beveragerepository beveragerepository;
    private final FoodRepository foodRepository;

    public OrderResponseDto totalPayment(TokenAccessDto token, HttpServletResponse response) {

        // Request에서 Token 가져오기
        log.info("requestToken = " + token.getAuthorization());
        String tokens = token.getAuthorization().substring(7);
        log.info("Token = " + tokens);
        Claims claims;

        if (token != null) {
            claims = jwtUtil.getUserInfoFromToken(tokens);

            String time = (String) claims.get("time");
            log.info("시간정보입니다 " + time);

            List<Orders> targetOrders = orderRepository.findAllByCreatedAt(time);
            log.info(targetOrders.toString());

            int sum = 0;

            for (Orders targetOrder : targetOrders) {
                if (foodRepository.existsByFoodName(targetOrder.getMenuName())) {
                    Food targetFood = foodRepository.findByFoodName(targetOrder.getMenuName());
                    int price = targetFood.getPrice() * targetOrder.getAmount();
                    sum += price;
                }

                if (beveragerepository.existsByBeverageName(targetOrder.getMenuName())) {
                    Beverage targetBeverage = beveragerepository.findByBeverageName(targetOrder.getMenuName());
                    int price = targetBeverage.getPrice() * targetOrder.getAmount();
                    sum += price;
                }
            }
            return new OrderResponseDto(sum);
        } else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
        }
    }

    public TokenAccessDto saveOrder(List<Orders> ordersList, HttpServletResponse response) {

        int sum = 0;

        for (Orders orders : ordersList) {

            if (foodRepository.existsByFoodName(orders.getMenuName())) {
                Food orderFood = foodRepository.findByFoodName(orders.getMenuName());
                Beverage beverage = new Beverage();

                Orders orders1 = Orders.builder()
                        .menuName(orders.getMenuName())
                        .amount(orders.getAmount())
                        .imageUrl(orderFood.getImageUrl())
                        .food(orderFood)
                        .beverage(beverage)
                        .build();
                orderRepository.save(orders1);
                sum += orderFood.getPrice();
                log.info("가격확인" + sum);
            }

            if (beveragerepository.existsByBeverageName(orders.getMenuName())) {
                Beverage orderBeverage = beveragerepository.findByBeverageName(orders.getMenuName());
                Food food = new Food();

                Orders orders1 = Orders.builder()
                        .menuName(orders.getMenuName())
                        .amount(orders.getAmount())
                        .imageUrl(orderBeverage.getImageUrl())
                        .food(food)
                        .beverage(orderBeverage)
                        .build();
                orderRepository.save(orders1);
                sum += orderBeverage.getPrice();
                log.info("가격확인" + sum);
            }
        }

        String token = jwtUtil.createToken(MemberRoleEnum.MEMBER);

        OrderNumberRequestDto orderNumberRequestDto = new OrderNumberRequestDto();
        orderNumberRequestDto.setOrderCnt(orderNumberRequestDto.getOrderCnt());
        OrderNumber orderNumber = new OrderNumber(orderNumberRequestDto);

        orderNumberRepository.save(orderNumber);

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");

        return new TokenAccessDto(token);
    }

    @Transactional
    public StackPointResponseDto stackPoints(StackPointDto stackPointDto) {

        String tokens = stackPointDto.getToken().substring(7);
        Claims claims;

        log.info("폰넘버 = " + stackPointDto.getPhoneNumber());
        Member member = memberRepository.findByPhoneNumber(stackPointDto.getPhoneNumber());

        if ((tokens != null) && member != null) {
            if (jwtUtil.validateToken(tokens)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(tokens);
            } else {
                throw new IllegalArgumentException("주문 신청에 오류가 있습니다.");
            }

            String time = (String) claims.get("time");
            log.info("시간정보입니다 " + time);

            List<Orders> targetOrders = orderRepository.findAllByCreatedAt(time);

            int sum = 0;

            for (Orders targetOrder : targetOrders) {
                if (foodRepository.existsByFoodName(targetOrder.getMenuName())) {
                    Food targetFood = foodRepository.findByFoodName(targetOrder.getMenuName());
                    int price = targetFood.getPrice() * targetOrder.getAmount();
                    sum += price;
                }

                if (beveragerepository.existsByBeverageName(targetOrder.getMenuName())) {
                    Beverage targetbeverage = beveragerepository.findByBeverageName(targetOrder.getMenuName());
                    int price = targetbeverage.getPrice() * targetOrder.getAmount();
                    sum += price;
                }
            }
            log.info("sum = " + sum);
            double point = sum * ((double) 5 / 100);
            UpdatePointDto updatePointDto = new UpdatePointDto((int) point);

            log.info("point = " + point);
            member.updatePoint(updatePointDto);
            return new StackPointResponseDto((int) point);

        } else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
        }
    }

    @Transactional
    public int dailySales(DailySalesRequestDto date) {
        List<Orders> orders = orderRepository.findAllByCreatedAtContains(date.getDate());
        log.info("date = " + date);
        log.info("orders = " + orders);

        int sum = 0;

        for (Orders order : orders) {
            if (foodRepository.existsByFoodName(order.getMenuName())) {
                Food targetFood = foodRepository.findByFoodName(order.getMenuName());
                sum += targetFood.getPrice() * (order.getAmount());
            }

            if (beveragerepository.existsByBeverageName(order.getMenuName())) {
                Beverage targetBeverage = beveragerepository.findByBeverageName(order.getMenuName());
                sum += targetBeverage.getPrice() * order.getAmount();
            }

        }

        return sum;
    }

    //의도를 잘 모르겠음 뭔진 모르겠지만 나중엔 수정이 필요해보임
    public OrderNumberRequestDto cntOrder() {
        Long cnt = orderNumberRepository.countBy();
        return new OrderNumberRequestDto(cnt);
    }

    public List<Orders> showDailySalesDetails(DailySalesRequestDto date) {
        return orderRepository.findAllByCreatedAtContains(date.getDate());
    }

    public Food findInFoodList (Orders orders) {
        Food foodName = foodRepository.findByFoodName(orders.getMenuName());
        if (foodName != null) {
            return foodRepository.findByFoodName(orders.getMenuName());
        } else {
            throw new IllegalArgumentException("음식에 등록된 메뉴가 아닙니다");
        }
    }

    public Beverage findInBeverageList (Orders orders) {
        Beverage beverageName = beveragerepository.findByBeverageName(orders.getMenuName());
        if (beverageName != null) {
            return beveragerepository.findByBeverageName(orders.getMenuName());
        } else {
            throw new IllegalArgumentException("음료에 등록된 메뉴가 아닙니다.");
        }
    }
}
