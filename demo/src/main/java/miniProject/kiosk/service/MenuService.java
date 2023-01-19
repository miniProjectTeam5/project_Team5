package miniProject.kiosk.service;

import lombok.RequiredArgsConstructor;
import miniProject.kiosk.dto.MenuRequestDto;
import miniProject.kiosk.dto.OrderRequestMsgDto;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.repository.MenuRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public void postMenu(MenuRequestDto menuRequestDto){

        Menu menu_find = menuRepository.findByMenuName(menuRequestDto.getMenuName());
        if(menu_find != null){
            throw new IllegalArgumentException("이미 등록된 메뉴입니다.");
        }
        Menu menu_save = new Menu(menuRequestDto);

        menuRepository.save(menu_save);

    }

    public List<Menu> showMenu(){
        return menuRepository.findAllByOrderByIdDesc();
    }

    public OrderRequestMsgDto deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다.")
        );

        menuRepository.delete(menu);
        return new OrderRequestMsgDto("삭제하였습니다.", HttpStatus.OK.value());
    }
}
