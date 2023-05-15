package com.team4.skillmarket.admin.report.vo;

public class reportListVo {
	
	private String rptNo;
	private String rptcatNo;
	private String memberNo;
	private String memberNoBad;
	private String rptTitle;
	private String rptContent;
	private String rptStatus;
	
	public reportListVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public reportListVo(String rptNo, String rptcatNo, String memberNo, String memberNoBad, String rptTitle,
			String rptContent, String rptStatus) {
		super();
		this.rptNo = rptNo;
		this.rptcatNo = rptcatNo;
		this.memberNo = memberNo;
		this.memberNoBad = memberNoBad;
		this.rptTitle = rptTitle;
		this.rptContent = rptContent;
		this.rptStatus = rptStatus;
	}

	@Override
	public String toString() {
		return "reportListVo [rptNo=" + rptNo + ", rptcatNo=" + rptcatNo + ", memberNo=" + memberNo + ", memberNoBad="
				+ memberNoBad + ", rptTitle=" + rptTitle + ", rptContent=" + rptContent + ", rptStatus=" + rptStatus
				+ "]";
	}

	public String getRptNo() {
		return rptNo;
	}

	public void setRptNo(String rptNo) {
		this.rptNo = rptNo;
	}

	public String getRptcatNo() {
		return rptcatNo;
	}

	public void setRptcatNo(String rptcatNo) {
		this.rptcatNo = rptcatNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberNoBad() {
		return memberNoBad;
	}

	public void setMemberNoBad(String memberNoBad) {
		this.memberNoBad = memberNoBad;
	}

	public String getRptTitle() {
		return rptTitle;
	}

	public void setRptTitle(String rptTitle) {
		this.rptTitle = rptTitle;
	}

	public String getRptContent() {
		return rptContent;
	}

	public void setRptContent(String rptContent) {
		this.rptContent = rptContent;
	}

	public String getRptStatus() {
		return rptStatus;
	}

	public void setRptStatus(String rptStatus) {
		this.rptStatus = rptStatus;
	}
	
	
	
	
}
