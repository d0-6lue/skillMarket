package com.team4.skillmarket.csc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.inquiry.vo.InquiryCategoryVo;
import com.team4.skillmarket.admin.inquiry.vo.inquiryListVo;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class CSCDao {

	public List<noticeListVo> getNotice(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM NOTICE N JOIN NOTICE_CATEGORY C ON ( N.NOTI_CAT_NO = C.NOTI_CAT_NO) WHERE NOTI_STATUS = 'Y' ORDER BY NOTI_MODIFYDATE DESC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<noticeListVo> noticeList = new ArrayList<>();
		while(rs.next()) {
			String notiNo = rs.getString("NOTI_NO");
			String notiCatNo = rs.getString("NOTI_CAT_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String notiTitle = rs.getString("NOTI_TITLE");
			String notiContent = rs.getString("NOTI_CONTENT");
			String notiEnrolldate = rs.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs.getString("NOTI_MODIFYDATE");
			String notiStatus = rs.getString("NOTI_STATUS");
			String notiHit = rs.getString("NOTI_HIT");
			String notiCatName = rs.getString("NOTI_CAT_NAME");
			
			noticeListVo vo = new noticeListVo();
			
			vo.setNotiNo(notiNo);
			vo.setNotiCatNo(notiCatNo);
			vo.setAdminNo(adminNo);
			vo.setNotiTitle(notiTitle);
			vo.setNotiContent(notiContent);
			vo.setNotiEnrolldate(notiEnrolldate);
			vo.setNotiModifydate(notiModifydate);
			vo.setNotiStatus(notiStatus);
			vo.setNotiHit(notiHit);
			vo.setNotiCatName(notiCatName);
			
			noticeList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeList;
	}
	
	public List<noticeListVo> getRecentNotice(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM( SELECT * FROM NOTICE N JOIN NOTICE_CATEGORY C ON ( N.NOTI_CAT_NO = C.NOTI_CAT_NO) WHERE NOTI_STATUS = 'Y' ORDER BY NOTI_MODIFYDATE DESC) WHERE ROWNUM <= 4";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<noticeListVo> noticeList = new ArrayList<>();
		while(rs.next()) {
			String notiNo = rs.getString("NOTI_NO");
			String notiCatNo = rs.getString("NOTI_CAT_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String notiTitle = rs.getString("NOTI_TITLE");
			String notiContent = rs.getString("NOTI_CONTENT");
			String notiEnrolldate = rs.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs.getString("NOTI_MODIFYDATE");
			String notiStatus = rs.getString("NOTI_STATUS");
			String notiHit = rs.getString("NOTI_HIT");
			String notiCatName = rs.getString("NOTI_CAT_NAME");
			
			noticeListVo vo = new noticeListVo();
			
			vo.setNotiNo(notiNo);
			vo.setNotiCatNo(notiCatNo);
			vo.setAdminNo(adminNo);
			vo.setNotiTitle(notiTitle);
			vo.setNotiContent(notiContent);
			vo.setNotiEnrolldate(notiEnrolldate);
			vo.setNotiModifydate(notiModifydate);
			vo.setNotiStatus(notiStatus);
			vo.setNotiHit(notiHit);
			vo.setNotiCatName(notiCatName);
			
			noticeList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return noticeList;
	}

	public List<InquiryCategoryVo> getInquiryCategory(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM QNA_CATEGORY";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<InquiryCategoryVo> inquiryCategoryList = new ArrayList<>();
		while(rs.next()) {
			String qnaCatNo = rs.getString("QNA_CAT_NO");
			String qnaCatName = rs.getString("QNA_CAT_NAME");
			
			InquiryCategoryVo vo = new InquiryCategoryVo();
			
			vo.setQnaCatNo(qnaCatNo);
			vo.setQnaCatName(qnaCatName);
			
			inquiryCategoryList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return inquiryCategoryList;
	}

	public int inquiry(Connection conn, inquiryListVo ivo) throws Exception {
		
		String sql = "INSERT INTO QNA( QNA_NO , MEMBER_NO , QNA_CAT_NO , QNA_TITLE , QNA_CONTENT ) VALUES( SEQ_QNA.NEXTVAL, ?, ?, ?, ? )";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ivo.getMemberNo());
		pstmt.setString(2, ivo.getQnaCatNo());
		pstmt.setString(3, ivo.getQnaTitle());
		pstmt.setString(4, ivo.getQnaContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public noticeListVo getNoticeByNo(Connection conn, String no) throws Exception {
		
		String sql = "SELECT * FROM NOTICE WHERE NOTI_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		
		noticeListVo vo = null;
		if(rs.next()) {
			
			String notiNo = rs.getString("NOTI_NO");
			String notiCatNo = rs.getString("NOTI_CAT_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String notiTitle = rs.getString("NOTI_TITLE");
			String notiContent = rs.getString("NOTI_CONTENT");
			String notiEnrolldate = rs.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs.getString("NOTI_MODIFYDATE");
			String notiStatus = rs.getString("NOTI_STATUS");
			String notiHit = rs.getString("NOTI_HIT");
			
			vo = new noticeListVo();
			
			vo.setNotiNo(notiNo);
			vo.setNotiCatNo(notiCatNo);
			vo.setAdminNo(adminNo);
			vo.setNotiTitle(notiTitle);
			vo.setNotiContent(notiContent);
			vo.setNotiEnrolldate(notiEnrolldate);
			vo.setNotiModifydate(notiModifydate);
			vo.setNotiStatus(notiStatus);
			vo.setNotiHit(notiHit);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return vo;
		
	}

	public int addHit(Connection conn, String no) throws Exception {
		String sql = "UPDATE NOTICE SET NOTI_HIT = NOTI_HIT + 1 WHERE NOTI_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

}
