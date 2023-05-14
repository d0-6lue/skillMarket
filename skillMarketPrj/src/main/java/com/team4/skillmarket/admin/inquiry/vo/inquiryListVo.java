package com.team4.skillmarket.admin.inquiry.vo;

public class inquiryListVo {

	private String qnaNo;
	private String memberNo;
	private String qnaCatNo;
	private String qnaTitle;
	private String qnaContent;
	private String qnaEnrolldate;
	private String qnaStatus;
	
	public inquiryListVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public inquiryListVo(String qnaNo, String memberNo, String qnaCatNo, String qnaTitle, String qnaContent,
			String qnaEnrolldate, String qnaStatus) {
		super();
		this.qnaNo = qnaNo;
		this.memberNo = memberNo;
		this.qnaCatNo = qnaCatNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.qnaEnrolldate = qnaEnrolldate;
		this.qnaStatus = qnaStatus;
	}
	@Override
	public String toString() {
		return "inquiryListVo [qnaNo=" + qnaNo + ", memberNo=" + memberNo + ", qnaCatNo=" + qnaCatNo + ", qnaTitle="
				+ qnaTitle + ", qnaContent=" + qnaContent + ", qnaEnrolldate=" + qnaEnrolldate + ", qnaStatus="
				+ qnaStatus + "]";
	}
	public String getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(String qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getQnaCatNo() {
		return qnaCatNo;
	}
	public void setQnaCatNo(String qnaCatNo) {
		this.qnaCatNo = qnaCatNo;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public String getQnaEnrolldate() {
		return qnaEnrolldate;
	}
	public void setQnaEnrolldate(String qnaEnrolldate) {
		this.qnaEnrolldate = qnaEnrolldate;
	}
	public String getQnaStatus() {
		return qnaStatus;
	}
	public void setQnaStatus(String qnaStatus) {
		this.qnaStatus = qnaStatus;
	}
	
	
	
	
	
}
