package com.gazorpazorp.model.dto;

import java.sql.Timestamp;
import java.util.Date;

public class OrderMinimalDto {
	private Long id;
	private double total;
	private Timestamp createdAt;
	private String status;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "OrderMinimalDto [id=" + id + ", status=" + status + "]";
	}
}
