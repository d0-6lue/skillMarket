package com.team4.skillmarket.community.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.community.dao.FreeBoardDao;
import com.team4.skillmarket.community.vo.CommunityPostVo;
import com.team4.skillmarket.community.vo.FreeBoardCategoryVo;
import com.team4.skillmarket.community.vo.FreeBoardVo;


public class CommunityService {
	
	private final FreeBoardDao dao = new FreeBoardDao();

	//자유게시판 주제가져오기 ~
	public List<FreeBoardCategoryVo> getFreeBoardCategory() throws Exception {
	   
		Connection conn = JDBCTemplate.getConnection();
		
		List<FreeBoardCategoryVo> cvoList = dao.getFreeBoardCategoryList(conn);
		
		JDBCTemplate.close(conn);
		
		return cvoList;
	}

	//자유게시판 글쓰기
	public int write(FreeBoardVo bvo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.write(conn, bvo);
		
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			
		}
		
		JDBCTemplate.close(conn);

		return result;
	}

	public int FreeBoardedit(FreeBoardVo bvo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.FreeBoardedit(conn,bvo);
		
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	//커뮤니티 게시판 글 상세조회
	public FreeBoardVo getFreeBoardByNo(String bno) throws Exception {
		
		FreeBoardVo vo = null;
		//conn
		try (Connection conn = JDBCTemplate.getConnection();){
			
			//조회수 update
			int result = dao.increaseFreeBoardHit(conn, bno);
			
			//조회수 증가확인
			if(result == 1) {
				vo = dao.getFreeBoardByNo(conn, bno);
			}else {
			JDBCTemplate.rollback(conn);
			throw new Exception("조회수 중가 쿼리문 실행 실패");
		}
	}
		
		return vo;
	
	
	}

	//게시글 갯수 가져오기 ~
	public int getBoardListCnt(String searchType, String searchValue) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int cnt = dao.FreeBoardCnt(conn);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	
	}

	public List<CommunityPostVo> getFreeBoardList(PageVo pv) {
	    Connection conn = JDBCTemplate.getConnection();

	    List<CommunityPostVo> volist = dao.getCommunityPostList(conn, pv);

	    JDBCTemplate.close(conn);

	    return volist;
	}

	
	

}
