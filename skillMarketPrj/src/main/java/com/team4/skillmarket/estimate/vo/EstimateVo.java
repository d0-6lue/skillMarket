package com.team4.skillmarket.estimate.vo;

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
public class EstimateVo {

	private String estimate_no;
	private String freelancer_no;
	private String estimate_cat_no;
	private String estimate_title;
	private String estimate_thumbnail;
	private String estimate_line_introduction;
	private String estimate_price;
	private String estimate_period;
	private String estimate_detail;
	private String estimate_portfolio;
	private String business_registration_number;
	private String estimate_enroll_date;
	private String estimate_modification_date;
	private String estimate_status;
	private String estimate_views;
	
}
