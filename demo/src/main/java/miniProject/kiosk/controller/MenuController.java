package miniProject.kiosk.controller;

import lombok.RequiredArgsConstructor;
import miniProject.kiosk.dto.MenuRequestDto;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.service.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/store/menus")
    public void menusList(@RequestBody MenuRequestDto requestDto) {
        menuService.postMenu(requestDto);
    }

    @GetMapping("/store/menus")
    public List<Menu> lookingMenus(){
        return menuService.showMenu();
    }


}
