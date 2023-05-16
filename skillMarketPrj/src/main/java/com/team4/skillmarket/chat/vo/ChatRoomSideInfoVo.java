package com.team4.skillmarket.chat.vo;

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
public class ChatRoomSideInfoVo {
	
	private String seller;
	private String buyer;
	private String title;
	private String thumbnail;
	private String lineIntroduce;

}
