package com.team4.skillmarket.common.page;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PageVo {
	
	private int listCount;
	private int currentPage;
	private int pageLimit;
	private int boardLimit;
	
	private int maxPage;
	private int startPage;
	private int endPage;
	
	private int beginRow;
	private int lastRow;
	
	public PageVo(int listCount , int currentPage, int pageLimit, int boardLimit) {
		
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		this.maxPage = (int) Math.ceil((double)listCount / boardLimit);
		this.startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		this.endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		this.beginRow = boardLimit * (currentPage-1) + 1;
		this.lastRow = beginRow + boardLimit - 1;
	}

}