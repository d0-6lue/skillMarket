package com.team4.skillmarket.purchase.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WriteQuotationVo {

	private String memberNo;
	private String estimateNo;
	private String totalPeriod;
	private String totalPrice;
	
	private String[] estimateOptions;
	private String[] quantitys;
	
	private String purchaseMethod;
}
