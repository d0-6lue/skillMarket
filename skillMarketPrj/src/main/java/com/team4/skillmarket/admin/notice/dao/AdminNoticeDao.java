package com.team4.skillmarket.admin.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.member.vo.memberListVo;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.admin.notice.vo.noticeVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminNoticeDao {

	public List<noticeListVo> selectNoticeList(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM NOTICE ORDER BY NOTI_NO DESC";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<noticeListVo> noticeArrList = new ArrayList<>();
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

			noticeArrList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return noticeArrList;
	}

	public int noticeWriteService(noticeVo vo, Connection conn) throws Exception {
		
		String sql = "INSERT INTO NOTICE ( NOTI_NO, NOTI_CAT_NO, ADMIN_NO, NOTI_TITLE, NOTI_CONTENT ) VALUES ( SEQ_NOTICE.NEXTVAL, ?, ?, ?, ? )";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNotiCatNo());
		pstmt.setString(2, vo.getAdminNo());
		pstmt.setString(3, vo.getNotiTitle());
		pstmt.setString(4, vo.getNotiContent());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public noticeListVo getNewNotice(Connection conn) throws Exception {

		String slq = "SELECT * FROM ( SELECT * FROM NOTICE WHERE NOTI_STATUS = 'N' ORDER BY NOTI_NO DESC ) WHERE ROWNUM = 1";
		PreparedStatement pstmt = conn.prepareStatement(slq);
		ResultSet rs = pstmt.executeQuery();
		
		noticeListVo newNotice = null;
		
		if (rs.next()) {
			
			String notiNo = rs.getString("NOTI_NO");
			String notiCatNo = rs.getString("NOTI_CAT_NO");
			String adminNo = rs.getString("ADMIN_NO");
			String notiTitle = rs.getString("NOTI_TITLE");
			String notiContent = rs.getString("NOTI_CONTENT");
			String notiEnrolldate = rs.getString("NOTI_ENROLLDATE");
			String notiModifydate = rs.getString("NOTI_MODIFYDATE");
			String notiStatus = rs.getString("NOTI_STATUS");
			String notiHit = rs.getString("NOTI_HIT");
			
			newNotice = new noticeListVo();
			
			newNotice.setNotiNo(notiNo);
			newNotice.setNotiCatNo(notiCatNo);
			newNotice.setAdminNo(adminNo);
			newNotice.setNotiTitle(notiTitle);
			newNotice.setNotiContent(notiContent);
			newNotice.setNotiEnrolldate(notiEnrolldate);
			newNotice.setNotiModifydate(notiModifydate);
			newNotice.setNotiStatus(notiStatus);
			newNotice.setNotiHit(notiHit);
			
		}
		
		
		return newNotice;
	}

}
