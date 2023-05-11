package com.team4.skillmarket.admin.member.vo;

public class memberListVo {
	
	private String memberNo;
	private String statusNo;
	private String memberId;
	private String memberPwd;
	private String memberNick;
	private String memberPhone;
	private String memberEmail;
	private String memberInterst;
	private String memberSignDate;
	private String memberModifiDate;
	private String memberBank;
	private String memberAccount;
	private String memberCash;
	private String memberProfilePhoto;
	private String memberNickStatus;
	
	
	public memberListVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public memberListVo(String memberNo, String statusNo, String memberId, String memberPwd, String memberNick,
			String memberPhone, String memberEmail, String memberInterst, String memberSignDate,
			String memberModifiDate, String memberBank, String memberAccount, String memberCash,
			String memberProfilePhoto, String memberNickStatus) {
		super();
		this.memberNo = memberNo;
		this.statusNo = statusNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNick = memberNick;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberInterst = memberInterst;
		this.memberSignDate = memberSignDate;
		this.memberModifiDate = memberModifiDate;
		this.memberBank = memberBank;
		this.memberAccount = memberAccount;
		this.memberCash = memberCash;
		this.memberProfilePhoto = memberProfilePhoto;
		this.memberNickStatus = memberNickStatus;
	}

	@Override
	public String toString() {
		return "memberListVo [memberNo=" + memberNo + ", statusNo=" + statusNo + ", memberId=" + memberId
				+ ", memberPwd=" + memberPwd + ", memberNick=" + memberNick + ", memberPhone=" + memberPhone
				+ ", memberEmail=" + memberEmail + ", memberInterst=" + memberInterst + ", memberSignDate="
				+ memberSignDate + ", memberModifiDate=" + memberModifiDate + ", memberBank=" + memberBank
				+ ", memberAccount=" + memberAccount + ", memberCash=" + memberCash + ", memberProfilePhoto="
				+ memberProfilePhoto + ", memberNickStatus=" + memberNickStatus + "]";
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getStatusNo() {
		return statusNo;
	}

	public void setStatusNo(String statusNo) {
		this.statusNo = statusNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberInterst() {
		return memberInterst;
	}

	public void setMemberInterst(String memberInterst) {
		this.memberInterst = memberInterst;
	}

	public String getMemberSignDate() {
		return memberSignDate;
	}

	public void setMemberSignDate(String memberSignDate) {
		this.memberSignDate = memberSignDate;
	}

	public String getMemberModifiDate() {
		return memberModifiDate;
	}

	public void setMemberModifiDate(String memberModifiDate) {
		this.memberModifiDate = memberModifiDate;
	}

	public String getMemberBank() {
		return memberBank;
	}

	public void setMemberBank(String memberBank) {
		this.memberBank = memberBank;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	public String getMemberCash() {
		return memberCash;
	}

	public void setMemberCash(String memberCash) {
		this.memberCash = memberCash;
	}

	public String getMemberProfilePhoto() {
		return memberProfilePhoto;
	}

	public void setMemberProfilePhoto(String memberProfilePhoto) {
		this.memberProfilePhoto = memberProfilePhoto;
	}

	public String getMemberNickStatus() {
		return memberNickStatus;
	}

	public void setMemberNickStatus(String memberNickStatus) {
		this.memberNickStatus = memberNickStatus;
	}
	
	
	
	
}