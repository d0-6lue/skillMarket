package com.team4.skillmarket.admin.notice.service;

import java.sql.Connection;

import com.team4.skillmarket.admin.notice.dao.AdminNoticeDao;
import com.team4.skillmarket.admin.notice.vo.noticeVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class noticeService {

	public int noticeWriteService(noticeVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		AdminNoticeDao dao = new AdminNoticeDao();
		int result =  dao.noticeWriteService(vo,conn);
		
		if (result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
