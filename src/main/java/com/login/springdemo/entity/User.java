/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	private Role role;

	@OneToMany(fetch = FetchType.LAZY)
	private List<Article> article;
	
	public User() {
	}

	public User(Long id, String userName, String password, String firstName, String lastName, String email,
			String address, Role role, List<Article> article) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.role = role;
		this.article = article;
	}

	public User(String userName, String password, String firstName, String lastName, String email, String address,
			Role role, List<Article> article) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.role = role;
		this.article = article;
	}
	

	public User(String userName, String password, String firstName, String lastName, String email, String address,
			List<Article> article) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.address = address;
		this.article = article;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Article> getArticle() {
		return article;
	}

	public void setArticle(List<Article> article) {
		this.article = article;
	}

//	@Override
//	public String toString() {
//		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
//				+ ", lastName=" + lastName + ", email=" + email + ", address=" + address + ", role=" + role
//				+ ", article=" + article + "]";
//	}

	
	

	
}
