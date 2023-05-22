package com.team4.skillmarket.community.vo;

import lombok.Data;

@Data
public class CommunityCommentVo {
	
	private String commentNo;
	private String memberNo;
	private String boardNo;
	private String replyNo;
	private String commentContent;
	private String commentEnrollDate;
	private String commentEditDate;
	private String commentStatus;

}
