package com.team4.skillmarket.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.lookup.ProblemPackageBinding;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.community.vo.CommunityCommentVo;
import com.team4.skillmarket.community.vo.CommunityPostVo;
import com.team4.skillmarket.community.vo.FreeBoardCategoryVo;
import com.team4.skillmarket.community.vo.FreeBoardVo;


public class FreeBoardDao {
	
	//게시글 한방조회용쿼리 ~ sql(WITH base_query AS 
	//( SELECT FB.BOARD_NO, SUBSTR(FB.FREE_BOARD_TITLE, 1, 20) AS SHORT_TITLE, SUBSTR(REGEXP_REPLACE(FB.FREE_BOARD_CONTENT, '<[^>]+>', ''), 1, 15) AS SHORT_CONTENT, FB.FREE_BOARD_ENROLL_DATE, COUNT(DISTINCT FBL.LIKE_NO) OVER (PARTITION BY FB.BOARD_NO) AS LIKE_COUNT, COUNT(DISTINCT FBC.COMMENT_NO) OVER (PARTITION BY FB.BOARD_NO) AS COMMENT_COUNT, FB.FREE_BOARD_STATUS, FB.FREE_BOARD_HIT FROM FREE_BOARD FB LEFT JOIN FREE_BOARD_LIKE FBL ON FB.BOARD_NO = FBL.BOARD_NO LEFT JOIN FREE_BOARD_COMMENT FBC ON FB.BOARD_NO = FBC.BOARD_NO WHERE FB.FREE_BOARD_STATUS = 1 ORDER BY FB.FREE_BOARD_ENROLL_DATE DESC ) SELECT BOARD_NO, SHORT_TITLE, SHORT_CONTENT, LIKE_COUNT, COMMENT_COUNT, CASE WHEN EXTRACT(HOUR FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) < 1 THEN ROUND(EXTRACT(MINUTE FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || ' 분전' WHEN EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) < 1 THEN ROUND(EXTRACT(HOUR FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || ' 시간전' ELSE ROUND(EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || ' 일전' END AS TIME_AGO FROM base_query ORDER BY FREE_BOARD_STATUS ASC, FREE_BOARD_HIT DESC OFFSET 1 ROWS FETCH NEXT 20 ROWS ONLY;)
	public List<CommunityPostVo> getCommunityPostList(Connection conn, PageVo pv) {
	    String sql = "WITH base_query AS (\r\n"
	    		+ "SELECT\r\n"
	    		+ "FB.BOARD_NO,\r\n"
	    		+ "SUBSTR(FB.FREE_BOARD_TITLE, 1, 20) AS SHORT_TITLE,\r\n"
	    		+ "SUBSTR(REGEXP_REPLACE(FB.FREE_BOARD_CONTENT, '<[^>]+>', ''), 1, 15) AS SHORT_CONTENT,\r\n"
	    		+ "FB.FREE_BOARD_ENROLL_DATE,\r\n"
	    		+ "COUNT(DISTINCT FBL.LIKE_NO) OVER (PARTITION BY FB.BOARD_NO) AS LIKE_COUNT,\r\n"
	    		+ "COUNT(DISTINCT FBC.COMMENT_NO) OVER (PARTITION BY FB.BOARD_NO) AS COMMENT_COUNT,\r\n"
	    		+ "FB.FREE_BOARD_STATUS,\r\n"
	    		+ "FB.FREE_BOARD_HIT\r\n"
	    		+ "FROM\r\n"
	    		+ "FREE_BOARD FB\r\n"
	    		+ "LEFT JOIN\r\n"
	    		+ "FREE_BOARD_LIKE FBL ON FB.BOARD_NO = FBL.BOARD_NO\r\n"
	    		+ "LEFT JOIN\r\n"
	    		+ "FREE_BOARD_COMMENT FBC ON FB.BOARD_NO = FBC.BOARD_NO\r\n"
	    		+ "WHERE\r\n"
	    		+ "FB.FREE_BOARD_STATUS = 1\r\n"
	    		+ "ORDER BY\r\n"
	    		+ "FB.FREE_BOARD_ENROLL_DATE DESC\r\n"
	    		+ ")\r\n"
	    		+ "SELECT\r\n"
	    		+ "BOARD_NO,\r\n"
	    		+ "SHORT_TITLE,\r\n"
	    		+ "SHORT_CONTENT,\r\n"
	    		+ "LIKE_COUNT,\r\n"
	    		+ "COMMENT_COUNT,\r\n"
	    		+ "FREE_BOARD_HIT,\r\n"
	    		+ "CASE\r\n"
	    		+ "WHEN EXTRACT(HOUR FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) < 1\r\n"
	    		+ "THEN ROUND(EXTRACT(MINUTE FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || '분전'\r\n"
	    		+ "WHEN EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) < 1\r\n"
	    		+ "THEN ROUND(EXTRACT(HOUR FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || '시간전'\r\n"
	    		+ "WHEN EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) < 7\r\n"
	    		+ "THEN ROUND(EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND)) || '일전'\r\n"
	    		+ "ELSE EXTRACT(DAY FROM (SYSTIMESTAMP - FREE_BOARD_ENROLL_DATE) DAY TO SECOND) || '일전'\r\n"
	    		+ "END AS TIME_AGO\r\n"
	    		+ "FROM base_query\r\n"
	    		+ "ORDER BY\r\n"
	    		+ "FREE_BOARD_ENROLL_DATE DESC\r\n"
	    		+ "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

	    List<CommunityPostVo> postList = new ArrayList<>();
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, pv.getBeginRow());
	        pstmt.setInt(2, pv.getLastRow());

	        ResultSet rs = pstmt.executeQuery();
	        while (rs.next()) {
	            String boardNo = rs.getString("BOARD_NO");
	            String shortTitle = rs.getString("SHORT_TITLE");
	            String shortContent = rs.getString("SHORT_CONTENT");
	            int likeCount = rs.getInt("LIKE_COUNT");
	            int commentCount = rs.getInt("COMMENT_COUNT");
	            String timeAgo = rs.getString("TIME_AGO");
	            int freeBoardHit = rs.getInt("FREE_BOARD_HIT");


	            CommunityPostVo post = new CommunityPostVo();
	            post.setBoardNo(boardNo);
	            post.setFreeBoardTitle(shortTitle);
	            post.setFreeBoardContentShort(shortContent);
	            post.setRecommendCount(likeCount);
	            post.setCommentCount(commentCount);
	            post.setFreeBoardEnrollDate(timeAgo);
	            post.setFreeBoardHit(freeBoardHit);

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




	public int FreeBoardedit(Connection conn, FreeBoardVo vo) throws Exception {
	    String sql = "UPDATE FREE_BOARD SET FREE_BOARD_TITLE = ?, FREE_BOARD_CONTENT =? , FREE_BOARD_EDIT_DATE= SYSDATE, FREE_BOARD_CATEGORY_NO=? WHERE BOARD_NO = ?";
	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, vo.getFreeBoardTitle());
	        pstmt.setString(2, vo.getFreeBoardContent());
	        pstmt.setString(3, vo.getFreeBoardCategoryNo());
	        pstmt.setString(4, vo.getBoardNo());
	        int result = pstmt.executeUpdate();
	        System.out.println(result);
	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw e;
	    }
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



	public int delete(Connection conn, FreeBoardVo vo) throws Exception {
		
		String sql = "UPDATE FREE_BOARD SET FREE_BOARD_STATUS= 0 WHERE BOARD_NO =? AND MEMBER_NO=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getBoardNo());
		pstmt.setString(2, vo.getMemberNo());
		int result = pstmt.executeUpdate();
		
		return result;
		
	}








	public int commentWrite(Connection conn, CommunityCommentVo vo) throws Exception {
		
		String sql = "INSERT INTO FREE_BOARD_COMMENT (COMMENT_NO, MEMBER_NO, BOARD_NO, REPLY_NO, COMMENT_CONTENT, COMMENT_ENROLL_DATE, COMMENT_EDIT_DATE) VALUES (seq_free_board_comment.nextval, ?, ?, 1, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		pstmt.setString(2, vo.getBoardNo());
		pstmt.setString(3, vo.getCommentContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	//댓글 가져오기 ~
	public List<CommunityCommentVo> getCommentList(Connection conn, String no) throws Exception {
	    String sql = "SELECT * FROM free_board_comment WHERE board_no = ?";
	    PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, no);
	    ResultSet rs = pstmt.executeQuery();

	    List<CommunityCommentVo> list = new ArrayList<>();
	    while (rs.next()) {
	        String commentNo = rs.getString("COMMENT_NO");
	        String memberNo = rs.getString("MEMBER_NO");
	        String boardNo = rs.getString("BOARD_NO");
	        String replyNo = rs.getString("REPLY_NO");
	        String commentContent = rs.getString("COMMENT_CONTENT");
	        String commentEnrollDate = rs.getString("COMMENT_ENROLL_DATE");
	        String commentEditDate = rs.getString("COMMENT_EDIT_DATE");
	        String commentStatus = rs.getString("COMMENT_STATUS");

	        // 데뭉해주기
	        CommunityCommentVo commentVo = new CommunityCommentVo();
	        commentVo.setCommentNo(commentNo);
	        commentVo.setMemberNo(memberNo);
	        commentVo.setBoardNo(boardNo);
	        commentVo.setReplyNo(replyNo);
	        commentVo.setCommentContent(commentContent);
	        commentVo.setCommentEnrollDate(commentEnrollDate);
	        commentVo.setCommentEditDate(commentEditDate);
	        commentVo.setCommentStatus(commentStatus);

	        list.add(commentVo);
	    }

	    return list;
	}












	
	

}
