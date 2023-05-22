package com.team4.skillmarket.csc.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.notice.vo.NoticeListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class CSCService {

	public List<NoticeListVo> getNotice() {
		
		Connection conn = JDBCTemplate.getConnection();
		
	}

}
