package miniProject.kiosk.repository;

import miniProject.kiosk.entity.OrderNumber;
import miniProject.kiosk.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderNumberRepository extends JpaRepository<OrderNumber, Long> {

    Long countBy();
}