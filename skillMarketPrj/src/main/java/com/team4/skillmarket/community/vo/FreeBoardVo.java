package com.team4.skillmarket.community.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
