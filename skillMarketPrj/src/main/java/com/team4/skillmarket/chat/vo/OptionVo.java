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
public class OptionVo {
	
	private String estimateOptionNo;
	private String estimateNo;
	private String estimateOptionName;
	private String estimateOptionPrice;
	private String estimateOptionPeriod;
	
	private String quotationOptionNo;
	private String quotationNo;
	private String quotationEstimateOptionNo;
	private String quotationOptionQuantity;

}
