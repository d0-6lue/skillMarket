package com.team4.skillmarket.chat.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatVo {
	
	private String chatNo;
	private String quotationNo;
	private String chatSenderNo;
	private String chatSender;
	private String chatContent;
	private String chatRequest;
	private String chatAttachment;
	private String chatRead;
	private String chatStatus;
	private String chatEnrollDate;

	private String requestNo;
	private String requestCatNo;
	private String requestCatName;
	private String requestContent;
	private String requestStatusNo;
	private String optionNo;
	private String inputNo;
	
	private String chatSenderProfile;
}
