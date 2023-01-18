package miniProject.kiosk.service;

import io.jsonwebtoken.Claims;
import jakarta.persistence.criteria.Order;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import miniProject.kiosk.dto.*;

import miniProject.kiosk.dto.member.UpdatePointDto;
import miniProject.kiosk.entity.Member;
import miniProject.kiosk.entity.MemberRoleEnum;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.entity.Orders;
import miniProject.kiosk.jwt.JwtUtil;
import miniProject.kiosk.repository.MemberRepository;
import miniProject.kiosk.repository.MenuRepository;
import miniProject.kiosk.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;

    public OrderResponseDto totalPayment(HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("주문 신청에 오류가 있습니다.");
            }

            String time = (String) claims.get("time");
            log.info("시간정보입니다 " + time);

            List<Orders> targetOrders = orderRepository.findAllByCreatedAt(time);
            log.info(targetOrders.toString());

            Integer sum = 0;

            for (Orders targetOrder : targetOrders) {
                Menu targetMenu = targetOrder.getMenu();
                Integer price = targetMenu.getPrice() * targetOrder.getAmount();
                sum += price;
            }
            return new OrderResponseDto(sum);
        } else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
        }
    }

    public OrderRequestMsgDto saveOrder(List<Orders> ordersList, String phoneNumber, HttpServletResponse response) {

        int sum = 0;

        for (Orders orders : ordersList) {
            Menu menu = menuRepository.findByMenuName(orders.getMenuName());

            if (menu == null) {
                throw new IllegalArgumentException("메뉴 정보를 찾을 수 없습니다");

            } else {
                Orders orders1 = Orders.builder()
                        .menuName(orders.getMenuName())
                        .amount(orders.getAmount())
                        .menu(menu)
                        .build();
                orderRepository.save(orders1);
                sum += menu.getPrice();
                log.info("가격확인" + sum);
            }
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(MemberRoleEnum.MEMBER));

            return new OrderRequestMsgDto("주문하였습니다.", HttpStatus.OK.value());

    }

    @Transactional
    public Integer stackPoints(PhoneNumRequestDto phoneNumber, HttpServletRequest request) {

        // Request에서 Token 가져오기
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        log.info("폰넘버 = " + phoneNumber.getPhoneNumber());
        Member member = memberRepository.findByPhoneNumber(phoneNumber.getPhoneNumber());

        if ((token != null) && member != null)  {
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 정보 가져오기
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("주문 신청에 오류가 있습니다.");
            }

            String time = (String) claims.get("time");
            log.info("시간정보입니다 " + time);

            List<Orders> targetOrders = orderRepository.findAllByCreatedAt(time);

            Integer sum = 0;

            for (Orders targetOrder : targetOrders) {
                Menu targetMenu = targetOrder.getMenu();
                Integer price = targetMenu.getPrice() * targetOrder.getAmount();
                sum += price;
            }
            log.info("sum = " + sum);
            double point = sum * ((double)5/100);
            UpdatePointDto updatePointDto = new UpdatePointDto((int)point);

            log.info("point = " + point);
            member.updatePoint(updatePointDto);
            return (int)point;

        } else {
            throw new IllegalArgumentException("토큰이 확인되지 않음");
        }
    }

    @Transactional
    public Long dailySales(DailySalesRequestDto date) {
        List<Orders> orders = orderRepository.findAllByCreatedAtContains(date.getDate());
        log.info("date = " + date);
        log.info("orders = "+ orders );

        long sum = 0;

        for (Orders order : orders) {
            sum += order.getMenu().getPrice() * (order.getAmount());
        }

        return sum;
    }
}
