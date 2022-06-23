/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.dto;

public class ArticleDto {
	
	private Long id;
	private Long sender;
	private Long recevier;
	private String title;
	private String cost;
	private String deliveredDate;
	
	public ArticleDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArticleDto(Long id, Long sender, Long recevier, String title, String cost, String deliveredDate) {
		super();
		this.id = id;
		this.sender = sender;
		this.recevier = recevier;
		this.title = title;
		this.cost = cost;
		this.deliveredDate = deliveredDate;
	}
  //getter and setter 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getRecevier() {
		return recevier;
	}

	public void setRecevier(Long recevier) {
		this.recevier = recevier;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(String deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	
	

	

	
	
}
