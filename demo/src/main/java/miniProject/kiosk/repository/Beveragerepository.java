package miniProject.kiosk.repository;

import miniProject.kiosk.entity.Beverage;
import miniProject.kiosk.entity.Food;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Beveragerepository extends JpaRepository<Beverage,Long> {
    Beverage findByBeverageName(String beveragename);
    boolean existsByBeverageName(String foodName);

    List<Beverage> findAllByOrderByIdDesc();
}
