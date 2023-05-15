package com.team4.skillmarket.order.vo;

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
public class QuotationOptionVo {
	
	 private String quotationOptionNo;
	 private String quotationNo;
	 private String estimateOptionNo;
	 private String quotationOptionQuantity;

	 
	 private String estimateNo;
	 private String estimateOptionName;
	 private String estimateOptionPrice;
	 private String estimateOptionPeriod;
	 
}
