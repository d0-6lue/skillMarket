package com.team4.skillmarket.community.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode

//게시글 리스트보기용 vo
public class CommunityPostVo {
    private String boardNo;
    private String freeBoardTitle;
    private String freeBoardContentShort;
    private String freeBoardEnrollDate;
    private String memberName;
    private int recommendCount;
    private int commentCount;
    private int viewCount;
    private int freeBoardStatus;
    private int FreeBoardHit;
    private String CategoryName;
}