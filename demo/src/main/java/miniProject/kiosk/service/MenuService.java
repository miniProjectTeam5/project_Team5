package miniProject.kiosk.service;

import lombok.RequiredArgsConstructor;
import miniProject.kiosk.dto.MenuRequestDto;
import miniProject.kiosk.dto.OrderRequestMsgDto;
import miniProject.kiosk.entity.Beverage;
import miniProject.kiosk.entity.Food;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.entity.Orders;
import miniProject.kiosk.repository.Beveragerepository;
import miniProject.kiosk.repository.FoodRepository;
import miniProject.kiosk.repository.MenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final FoodRepository foodRepository;
    private final Beveragerepository beveragerepository;

    public void postFood(MenuRequestDto menuRequestDto) {
        Food food = foodRepository.findByFoodName(menuRequestDto.getMenuName());
        if (food != null) {
            throw new IllegalArgumentException("이미 등록된 음식입니다.");
        }
        Food food1 = Food.builder()
                .foodName(menuRequestDto.getMenuName())
                .price(menuRequestDto.getPrice())
                .imageUrl(menuRequestDto.getImageUrl())
                .build();
        foodRepository.save(food1);
    }

    public void postBeverage(MenuRequestDto menuRequestDto) {
        Beverage beverage = beveragerepository.findByBeverageName(menuRequestDto.getMenuName());
        if (beverage != null) {
            throw new IllegalArgumentException("이미 등록된 음료입니다.");
        }

        Beverage beverage1 = Beverage.builder()
                .beverageName(menuRequestDto.getMenuName())
                .price(menuRequestDto.getPrice())
                .imageUrl(menuRequestDto.getImageUrl())
                .build();

        beveragerepository.save(beverage1);
    }

    public List<Food> getFood() {
        return foodRepository.findAllByOrderByIdDesc();
    }

    public List<Beverage> getBeverages() {
        return beveragerepository.findAllByOrderByIdDesc();
    }

    public OrderRequestMsgDto deleteFood(Long id) {
        Food food = foodRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다.")
        );

        foodRepository.delete(food);
        return new OrderRequestMsgDto("삭제하였습니다.", HttpStatus.OK.value());
    }

    public OrderRequestMsgDto deleteBeverage(Long id) {
        Beverage beverage = beveragerepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다.")
        );

        beveragerepository.delete(beverage);
        return new OrderRequestMsgDto("삭제하였습니다.", HttpStatus.OK.value());
    }
}
