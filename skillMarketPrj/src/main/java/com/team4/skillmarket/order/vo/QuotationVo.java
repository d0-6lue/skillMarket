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
public class QuotationVo {
	
	private String quotationNo;
	private String estimateNo;
	private String quotationPrevVer;
	private String memberNo;
	private String quotationStatusNo;
	private String requestNo;
	private String quotationPrice;
	private String quotationPeriod;
	private String quotationEnrollDate;
	private String quotationModificationDate;
	
	
	private String estimateTitle;
	private String estimateThumbnail;
	private String estimateLineIntroduction;
	private String estimateDetail;
	private String estimatePrice;
	private String estimatePeriod;
	
	

}
