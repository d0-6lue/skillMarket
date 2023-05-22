package com.team4.skillmarket.admin.member.vo;


import java.sql.Timestamp;

import lombok.Data;

@Data
public class memberListVo {
	
	private String memberNo;
	private String statusNo;
	private String memberId;
	private String memberPwd;
	private String memberNick;
	private String memberPhone;
	private String memberEmail;
	private String memberInterst;
	private Timestamp memberSignDate;
	private Timestamp memberModifiDate;
	private String memberBank;
	private String memberAccount;
	private String memberCash;
	private String memberProfilePhoto;
	private String memberNickStatus;
	private String freelancerY;
	private String statusName;
	
}
