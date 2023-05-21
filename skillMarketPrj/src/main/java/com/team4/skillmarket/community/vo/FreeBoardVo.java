package com.team4.skillmarket.community.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class FreeBoardVo {
	
	private String boardNo;
	private String memberNo;
	private String freeBoardTitle;
	private String freeBoardContent;
	private String freeBoardEnrollDate;
	private String freeBoardEditDate;
	private String freeBoardStatus;
	private int freeBoardHit;
	private int freeBoardLike;
	private String freeBoardAttachment;
	private String freeBoardCategoryNo;
}
