package com.gazorpazorp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gazorpazorp.model.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> findByAccountId(@Param("accountId") Long accountId);
	
	@Query("select o from Order o where o.accountId = ?1 and status != 'complete'")
	public List<Order> getCurrentOrderForAccount(Long accountId);
}
