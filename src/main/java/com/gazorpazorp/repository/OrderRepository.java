package com.gazorpazorp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gazorpazorp.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
