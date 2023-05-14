package com.team4.skillmarket.admin.report.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.report.dao.roportListDao;
import com.team4.skillmarket.admin.report.vo.reportListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class roportListService {

	public List<reportListVo> selectReportList() throws Exception {

		Connection conn = JDBCTemplate.getConnection();
		
		roportListDao dao = new roportListDao();
		List<reportListVo> reprotArrList =  dao.selectReportList(conn);
		
		JDBCTemplate.close(conn);
		
		return reprotArrList;
	}

}
