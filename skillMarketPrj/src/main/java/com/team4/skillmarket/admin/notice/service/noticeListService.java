package com.team4.skillmarket.admin.notice.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.notice.dao.AdminNoticeDao;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class noticeListService {

	public List<noticeListVo> selectNoticeList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		AdminNoticeDao dao = new AdminNoticeDao();
		List<noticeListVo> noticeArrList =  dao.selectNoticeList(conn);
		
		JDBCTemplate.close(conn);
		
		
		return noticeArrList;

	}

}
