package com.example.demo.repository;

import com.example.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderNumberRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderByModifiedAtDesc();
}