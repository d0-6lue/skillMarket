package com.team4.skillmarket.admin.login.vo;

public class AdminLoginVo {
	
	private String adminNo;
	private String adminGrNo;
	private String adminId;
	private String adminPwd;
	private String adminNick;
	
	public AdminLoginVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminLoginVo(String adminNo, String adminGrNo, String adminId, String adminPwd, String adminNick) {
		super();
		this.adminNo = adminNo;
		this.adminGrNo = adminGrNo;
		this.adminId = adminId;
		this.adminPwd = adminPwd;
		this.adminNick = adminNick;
	}
	@Override
	public String toString() {
		return "AdminLoginVo [adminNo=" + adminNo + ", adminGrNo=" + adminGrNo + ", adminId=" + adminId + ", adminPwd="
				+ adminPwd + ", adminNick=" + adminNick + "]";
	}
	public String getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}
	public String getAdminGrNo() {
		return adminGrNo;
	}
	public void setAdminGrNo(String adminGrNo) {
		this.adminGrNo = adminGrNo;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAdminNick() {
		return adminNick;
	}
	public void setAdminNick(String adminNick) {
		this.adminNick = adminNick;
	}
	
	
	
	
}
