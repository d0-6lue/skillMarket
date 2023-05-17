package com.team4.skillmarket.admin.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class AdminNoticeDao {

	public List<noticeListVo> selectNoticeList(Connection conn) throws Exception {
		
		String sql = "SELECT * FROM NOTICE";
		
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

}
