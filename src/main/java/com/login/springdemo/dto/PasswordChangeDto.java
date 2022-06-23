/**
 * @author manoj.kumar
 * Title: UserRole Based Login
 * Description : Admin and Guest two role base login
 *
 */
package com.login.springdemo.dto;

public class PasswordChangeDto {
	private Long id;
	private String newPwd;
	private String confirmPwd;
	
	public PasswordChangeDto(Long id, String newPwd, String confirmPwd) {
		super();
		this.id = id;
		this.newPwd = newPwd;
		this.confirmPwd = confirmPwd;
	}

	public PasswordChangeDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}
	

}
