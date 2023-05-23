package com.team4.skillmarket.cash.vo;

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
public class UserCashVo {
	
	private String memberNO;
	private String userCashMoney;
	private String userCashPoint;
	private String memberBank;
	private String memberAccount;

}
