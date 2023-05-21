package com.team4.skillmarket.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.community.vo.CommunityPostVo;
import com.team4.skillmarket.community.vo.FreeBoardCategoryVo;
import com.team4.skillmarket.community.vo.FreeBoardVo;


public class FreeBoardDao {
	
	public List<CommunityPostVo> getCommunityPostList(Connection conn, PageVo pv) {
	    String sql = "WITH base_query AS (" +
	            "SELECT " +
	            "FB.BOARD_NO," +
	            "FB.FREE_BOARD_TITLE," +
	            "FB.FREE_BOARD_CONTENT," +
	            "FB.FREE_BOARD_ENROLL_DATE," +
	            "COUNT(DISTINCT FBL.LIKE_NO) OVER (PARTITION BY FB.BOARD_NO) AS LIKE_COUNT," +
	            "COUNT(DISTINCT FBC.COMMENT_NO) OVER (PARTITION BY FB.BOARD_NO) AS COMMENT_COUNT " +
	            "FROM " +
	            "FREE_BOARD FB " +
	            "LEFT JOIN " +
	            "FREE_BOARD_LIKE FBL ON FB.BOARD_NO = FBL.BOARD_NO " +
	            "LEFT JOIN " +
	            "FREE_BOARD_COMMENT FBC ON FB.BOARD_NO = FBC.BOARD_NO " +
	            "ORDER BY " +
	            "FB.FREE_BOARD_ENROLL_DATE DESC) " +
	            "SELECT " +
	            "BOARD_NO," +
	            "SUBSTR(FREE_BOARD_TITLE, 1, 20) AS SHORT_TITLE," +
	            "SUBSTR(REGEXP_REPLACE(FREE_BOARD_CONTENT, '<[^>]+>', ''), 1, 15) AS SHORT_CONTENT," +
	            "LIKE_COUNT," +
	            "COMMENT_COUNT," +
	            "CASE " +
	            "WHEN EXTRACT(HOUR FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) < 1 " +
	            "THEN ROUND(EXTRACT(MINUTE FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || '분전' " +
	            "WHEN EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) < 1 " +
	            "THEN ROUND(EXTRACT(HOUR FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || '분전' " +
	            "ELSE ROUND(EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || '분전' " +
	            "END AS TIME_AGO " +
	            "FROM base_query " +
	            "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	    List<CommunityPostVo> postList = new ArrayList<>();
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, pv.getBeginRow());
	        pstmt.setInt(2, pv.getLastRow());

	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            CommunityPostVo post = new CommunityPostVo();
	            post.setBoardNo(rs.getString("BOARD_NO"));
	            post.setFreeBoardTitle(rs.getString("SHORT_TITLE"));
	            post.setFreeBoardContentShort(rs.getString("SHORT_CONTENT"));
	            post.setFreeBoardEnrollDate(rs.getString("TIME_AGO"));
	            post.setRecommendCount(rs.getInt("LIKE_COUNT"));
	            post.setCommentCount(rs.getInt("COMMENT_COUNT"));

	            postList.add(post);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return postList;
	}

	
	
	//자유게시판 주제가져오기 ~
	public List<FreeBoardCategoryVo> getFreeBoardCategoryList(Connection conn) throws Exception {
	    String sql = "SELECT * from FREE_BOARD_CATEGORY";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    ResultSet rs = pstmt.executeQuery();

	    List<FreeBoardCategoryVo> topicList = new ArrayList<>();
	    while (rs.next()) {
	    	String freeBoardCategoryNo = rs.getString("FREE_BOARD_CATEGORY_NO");
	        String freeBoardCategoryName = rs.getString("FREE_BOARD_CATEGORY_NAME");
	        
	        FreeBoardCategoryVo vo = new FreeBoardCategoryVo();
	        
	        vo.setFreeBoardCategoryNo(freeBoardCategoryNo);
	        vo.setFreeBoardCategoryName(freeBoardCategoryName);
	        
	        topicList.add(vo);
	    }
	    
	    JDBCTemplate.close(pstmt);
	    JDBCTemplate.close(rs);

	    return topicList;
	}


	public int write(Connection conn, FreeBoardVo bvo) throws Exception {
	    String sql = "INSERT INTO FREE_BOARD (BOARD_NO, MEMBER_NO, FREE_BOARD_TITLE, FREE_BOARD_CONTENT, FREE_BOARD_ENROLL_DATE, FREE_BOARD_EDIT_DATE, FREE_BOARD_CATEGORY_NO) VALUES (seq_free_board.NEXTVAL, ?, ?, ?, sysdate, sysdate, ?)";

	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, bvo.getMemberNo());
	    pstmt.setString(2, bvo.getFreeBoardTitle());

	    // HTML content를 그대로 DB에 저장
	    pstmt.setString(3, bvo.getFreeBoardContent());

	    pstmt.setString(4, bvo.getFreeBoardCategoryNo());

	    int result = pstmt.executeUpdate();

	    JDBCTemplate.close(pstmt);
	    return result;
	}




	public int FreeBoardedit(Connection conn, FreeBoardVo bvo) throws Exception {
		
		String sql = "UPDATE FREE_BOARD SET FREE_BOARD_TITLE = ?, FREE_BOARD_CONTENT =? , FREE_BOARD_EDIT_DATE= SYSDATE, FREE_BOARD_ATTACHMENT=?, FREE_BOARD_CATEGORY_NO=? WHERE BOARD_NO = ? AND MEMBER_NO=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
	  	pstmt.setString(1, bvo.getBoardNo());
	    pstmt.setString(2, bvo.getFreeBoardTitle());
	    pstmt.setString(3, bvo.getFreeBoardContent());
	    pstmt.setString(4, bvo.getFreeBoardCategoryNo());	
	    pstmt.setString(5, bvo.getFreeBoardAttachment());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		
		return result;
	}

	//조회수증가
	public int increaseFreeBoardHit(Connection conn, String bno) throws Exception {
	    String sql = "UPDATE FREE_BOARD SET FREE_BOARD_HIT = FREE_BOARD_HIT + 1 WHERE BOARD_NO = ? AND FREE_BOARD_STATUS = 1";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, bno);
	        int result = pstmt.executeUpdate();
	        
	        
	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
	}


	public FreeBoardVo getFreeBoardByNo(Connection conn, String bno) throws Exception {
	    String sql = "SELECT BOARD_NO, MEMBER_NO, FREE_BOARD_TITLE, FREE_BOARD_CONTENT, TO_CHAR(FREE_BOARD_ENROLL_DATE, 'YYYY-MM-DD') AS FREE_BOARD_ENROLL_DATE, TO_CHAR(FREE_BOARD_EDIT_DATE, 'YYYY-MM-DD') AS FREE_BOARD_EDIT_DATE, FREE_BOARD_STATUS, FREE_BOARD_HIT, FREE_BOARD_LIKE, FREE_BOARD_CATEGORY_NO FROM FREE_BOARD WHERE BOARD_NO = ? AND FREE_BOARD_STATUS = '1'";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, bno);
	    ResultSet rs = pstmt.executeQuery();
	    
	    FreeBoardVo vo = null;
	    if (rs.next()) {
	        vo = new FreeBoardVo();
	        vo.setBoardNo(rs.getString("BOARD_NO"));
	        vo.setMemberNo(rs.getString("MEMBER_NO"));
	        vo.setFreeBoardTitle(rs.getString("FREE_BOARD_TITLE"));
	        vo.setFreeBoardContent(rs.getString("FREE_BOARD_CONTENT"));
	        vo.setFreeBoardEnrollDate(rs.getString("FREE_BOARD_ENROLL_DATE"));
	        vo.setFreeBoardEditDate(rs.getString("FREE_BOARD_EDIT_DATE"));
	        vo.setFreeBoardStatus(rs.getString("FREE_BOARD_STATUS"));
	        vo.setFreeBoardHit(rs.getInt("FREE_BOARD_HIT"));
	        vo.setFreeBoardLike(rs.getInt("FREE_BOARD_LIKE"));
	        vo.setFreeBoardCategoryNo(rs.getString("FREE_BOARD_CATEGORY_NO"));
	    }
	    
	    return vo;
	}


	// 커뮤니티 게시판 리스트 조회하기
	public List<FreeBoardVo> getFreeBoardList(Connection conn) {
	    String sql = "SELECT BOARD_NO, MEMBER_NO, FREE_BOARD_TITLE, SUBSTR(REGEXP_REPLACE(REGEXP_REPLACE(FREE_BOARD_CONTENT, '<.*?>', ''), '\\s+', ' '), 1, 15) AS FREE_BOARD_CONTENT, FREE_BOARD_ENROLL_DATE, FREE_BOARD_EDIT_DATE, FREE_BOARD_STATUS, FREE_BOARD_HIT, FREE_BOARD_LIKE, FREE_BOARD_CATEGORY_NO FROM FREE_BOARD";
	    List<FreeBoardVo> volist = new ArrayList<>();
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            FreeBoardVo vo = new FreeBoardVo();
	            String bNo = rs.getString("BOARD_NO");
	            String memberNo = rs.getString("MEMBER_NO");
	            String freeBoardTitle = rs.getString("FREE_BOARD_TITLE");
	            String freeBoardContent = rs.getString("FREE_BOARD_CONTENT");
	            String freeBoardEnrollDate = rs.getString("FREE_BOARD_ENROLL_DATE");
	            String freeBoardEditDate = rs.getString("FREE_BOARD_EDIT_DATE");
	            String freeBoardStatus = rs.getString("FREE_BOARD_STATUS");
	            int freeBoardHit = rs.getInt("FREE_BOARD_HIT");
	            int freeBoardLike = rs.getInt("FREE_BOARD_LIKE");
	            String freeBoardCategoryNo = rs.getString("FREE_BOARD_CATEGORY_NO");

	            vo.setBoardNo(bNo);
	            vo.setMemberNo(memberNo);
	            vo.setFreeBoardTitle(freeBoardTitle);
	            vo.setFreeBoardContent(freeBoardContent);
	            vo.setFreeBoardEnrollDate(freeBoardEnrollDate);
	            vo.setFreeBoardEditDate(freeBoardEditDate);
	            vo.setFreeBoardStatus(freeBoardStatus);
	            vo.setFreeBoardHit(freeBoardHit);
	            vo.setFreeBoardLike(freeBoardLike);
	            vo.setFreeBoardCategoryNo(freeBoardCategoryNo);

	            volist.add(vo);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return volist;
	}



	public int FreeBoardCnt(Connection conn) throws Exception {
		
		String sql = "SELECT COUNT(*) FROM FREE_BOARD WHERE FREE_BOARD_STATUS = 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		int cnt = 0;
		if(rs.next()) {
			cnt = rs.getInt(1);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cnt;
	}











	
	

}
