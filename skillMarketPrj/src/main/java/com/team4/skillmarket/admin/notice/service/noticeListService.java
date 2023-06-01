package com.team4.skillmarket.admin.notice.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.notice.dao.AdminNoticeDao;
import com.team4.skillmarket.admin.notice.vo.noticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class noticeListService {
	
	private final AdminNoticeDao dao = new AdminNoticeDao();
	
	public List<noticeListVo> selectNoticeList() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		List<noticeListVo> noticeArrList =  dao.selectNoticeList(conn);
		
		JDBCTemplate.close(conn);
		
		
		return noticeArrList;

	}

	public noticeListVo getNewNotice() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		noticeListVo newNotice =  dao.getNewNotice(conn);
		
		JDBCTemplate.close(conn);
		
		return newNotice;
	}

	public List<noticeListVo> noticeSelectList() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		List<noticeListVo> noticeSelectList = dao.noticeSelectList(conn);
		
		JDBCTemplate.close(conn);
		
		return noticeSelectList;
	}

	public int editNotice(noticeListVo vo) throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		int editNotice =  dao.editNotice(vo,conn);
		
		if (editNotice == 1) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return editNotice;
	}

}
