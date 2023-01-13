package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Integer mileage;

    @Column(nullable = false)
    private boolean responseSMS;
}
