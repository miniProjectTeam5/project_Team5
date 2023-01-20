package miniProject.kiosk.repository;

import miniProject.kiosk.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Menu findByMenuName(String menuName);
    List<Menu> findAllByOrderByIdDesc();
}
