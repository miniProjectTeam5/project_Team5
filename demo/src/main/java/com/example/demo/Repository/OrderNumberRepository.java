package com.example.demo.Repository;

import com.example.demo.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderNumberRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByModifiedAtDesc();
}