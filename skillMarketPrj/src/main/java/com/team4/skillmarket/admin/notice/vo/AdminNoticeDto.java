package com.team4.skillmarket.admin.notice.vo;

import lombok.Data;

@Data
public class AdminNoticeDto {
	
	private String title;
	private String content;
	private String category;
	private String[] images;
	
}
