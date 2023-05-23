package com.team4.skillmarket.cash.vo;

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
public class CashChargeVo {
	
	private String no;
	private String memberNo;
	private String paymentMethodNo;
	private String chargeAmount;
	private String chargeDate;

}
