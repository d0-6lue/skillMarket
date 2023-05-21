package com.team4.skillmarket.admin.home.vo;

import lombok.Data;

@Data
public class HomeVo {
	
    // 인기 FAQ
	private String faqNo;
	private String adminNoFAQ;
	private String faqCatNo;
	private String faqTitle;
	private String faqQContent;
	private String faqAContent;
	private String faqStatus;
	private String faqHit;
	private String faqEnrolldate;
	private String faqModifydate;
    
    
    // 최근 작성 공지
    private String notiNo;
    private String notiCatNo;
    private String adminNoNotice;
    private String notiTitle;
    private String notiContent;
    private String notiEnrolldate;
    private String notiModifydate;
    private String notiStatus;
    private String notiHit;
    
    
    // 미처리 신고 내역
 	// 미처리 문의 내역
 	// 요약?
 	// 회원 -> 전문가수 회원수
 	private int noticeCount;
 	private int reportCount;
	private int faqCount;
	private int memberCount;
	private int freelancerCount;
  
	
}
