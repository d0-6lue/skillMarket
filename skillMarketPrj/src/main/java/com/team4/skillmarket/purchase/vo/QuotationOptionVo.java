package com.team4.skillmarket.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuotationOptionVo {
	
	private String quotationOptionNo;
	private String quotationNo;
	private String estimateOptionNo;
	private String estimateOptionName;
	private String estimateOptionPrice;
	private String estimateOptionPeriod;
	private String quotationOptionQuantity;
			

}
