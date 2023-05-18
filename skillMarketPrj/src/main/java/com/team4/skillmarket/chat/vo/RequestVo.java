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
public class RequestVo {

	private String requestNo;
	private String reuqestCatNo;
	private String quotationNo;
	private String requestContent;
	private String requestEnrollDate;
	private String requestStatusNo;
	private String optionNo;
	private String inputNo;
}
