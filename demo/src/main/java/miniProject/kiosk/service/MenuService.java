package miniProject.kiosk.service;

import lombok.RequiredArgsConstructor;
import miniProject.kiosk.dto.MenuRequestDto;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public void postMenu(MenuRequestDto menuRequestDto){

        String menuName = menuRequestDto.getMenuName();
        Integer price = menuRequestDto.getPrice();
        String imageUrl = menuRequestDto.getImageUrl();

        Menu menu_find = menuRepository.findByMenuName(menuRequestDto.getMenuName());
        if(menu_find != null){
            throw new IllegalArgumentException("이미 등록된 메뉴입니다.");
        }
        Menu menu_save = new Menu(menuName, price, imageUrl);

        menuRepository.save(menu_save);

    }

    public List<Menu> Look_upLists(){
        return menuRepository.findAllByOrderByIdDesc();
    }

}
