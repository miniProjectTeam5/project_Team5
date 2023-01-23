package miniProject.kiosk.controller;

import lombok.RequiredArgsConstructor;
import miniProject.kiosk.dto.MenuRequestDto;
import miniProject.kiosk.dto.OrderRequestMsgDto;
import miniProject.kiosk.entity.Beverage;
import miniProject.kiosk.entity.Food;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/store/food")
    public void foodList(@RequestBody MenuRequestDto menuRequestDto) {
        menuService.postFood(menuRequestDto);
    }
    @PostMapping("/store/beverage")
    public void beverageList(@RequestBody MenuRequestDto requestDto) {
        menuService.postBeverage(requestDto);
    }

    @GetMapping("/store/food")
    public List<Food> getFoods(){
        return menuService.getFood();
    }

    @GetMapping("/store/beverage")
    public List<Beverage> getBeverages(){
        return menuService.getBeverages();
    }

    @DeleteMapping("/store/food/{id}")
    public OrderRequestMsgDto deletefoods(@PathVariable Long id) {
        return menuService.deleteFood(id);
    }


    @DeleteMapping("/store/beverage/{id}")
    public OrderRequestMsgDto deleteBeverages(@PathVariable Long id) {
        return menuService.deleteBeverage(id);
    }

}
