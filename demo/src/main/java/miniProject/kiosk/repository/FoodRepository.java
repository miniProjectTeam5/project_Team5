package miniProject.kiosk.repository;

import miniProject.kiosk.entity.Food;
import miniProject.kiosk.entity.Menu;
import miniProject.kiosk.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    Food findByFoodName(String foodName);

    boolean existsByFoodName(String foodName);

    List<Food> findAllByOrderByIdDesc();
}
