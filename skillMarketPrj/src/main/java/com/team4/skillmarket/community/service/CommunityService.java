package com.team4.skillmarket.community.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.community.dao.FreeBoardDao;
import com.team4.skillmarket.community.vo.CommunityCommentVo;
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

	//게시판 수정
	public int FreeBoardedit(FreeBoardVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.FreeBoardedit(conn,vo);
		
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

	//게시판 조회하기
	public List<CommunityPostVo> getFreeBoardList(PageVo pv) {
	    Connection conn = JDBCTemplate.getConnection();
	    
	    long startTime = System.currentTimeMillis();

	    List<CommunityPostVo> volist = dao.getCommunityPostList(conn, pv);
	    
	    long endTime = System.currentTimeMillis();
	    long executionTime = endTime - startTime;
	    System.out.println("메서드 실행 시간: " + executionTime + "ms");

	    JDBCTemplate.close(conn);

	    return volist;
	}

	//게시판삭제
	public int delte(FreeBoardVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.delete(conn, vo);
		
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int commentWrite(CommunityCommentVo vo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.commentWrite(conn, vo);
		
		if(result ==1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		
		JDBCTemplate.close(conn);
		return result ;
	}

	public List<CommunityCommentVo> getCommentList(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<CommunityCommentVo> list = dao.getCommentList(conn, no);
		
		JDBCTemplate.close(conn);
		
		return list;
		
		
	}

	
	

}
