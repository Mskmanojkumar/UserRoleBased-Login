/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "article")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "cost")
	private String cost;
	
	@CreationTimestamp
	@Column(name="creation_date")
	private LocalDate creationDate;
	

	@Column(name="delivered_date")
	private LocalDate deliveredDate;
	

	@ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)//,targetEntity = User.class
	private User sender;
	

	@ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
	private User recevier;

	public Article() {
		
	}

	

	public Article(String title, String cost, LocalDate creationDate, LocalDate deliveredDate, User sender,
			User recevier) {
		super();
		this.title = title;
		this.cost = cost;
		this.creationDate = creationDate;
		this.deliveredDate = deliveredDate;
		this.sender = sender;
		this.recevier = recevier;
	}



	public Article(Long id, String title, String cost, LocalDate creationDate, LocalDate deliveredDate, User sender,
			User recevier) {
		super();
		this.id = id;
		this.title = title;
		this.cost = cost;
		this.creationDate = creationDate;
		this.deliveredDate = deliveredDate;
		this.sender = sender;
		this.recevier = recevier;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(LocalDate deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecevier() {
		return recevier;
	}

	public void setRecevier(User recevier) {
		this.recevier = recevier;
	}

	

	
	

//	@Override
//	public String toString() {
//		return "Article [id=" + id + ", title=" + title + ", cost=" + cost + ", creationDate=" + creationDate
//				+ ", deliveredDate=" + deliveredDate + ", sender=" + sender + ", recevier=" + recevier + "]";
//	}

	
	
	
}
