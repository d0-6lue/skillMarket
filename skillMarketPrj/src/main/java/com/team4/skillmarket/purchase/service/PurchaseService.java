package com.team4.skillmarket.purchase.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.purchase.dao.PurchaseDao;
import com.team4.skillmarket.purchase.vo.InfoVo;
import com.team4.skillmarket.purchase.vo.OptionVo;

public class PurchaseService {

	private final PurchaseDao purchaseDao = new PurchaseDao();
	
	public InfoVo getInfo(String no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		InfoVo infoVo = purchaseDao.getInfo(conn, no);
		
		JDBCTemplate.close(conn);
		
		return infoVo;
	} // getInfo

	public List<OptionVo> getOptionList(String no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<OptionVo> optionList = purchaseDao.getOptionList(conn, no);
		
		JDBCTemplate.close(conn);
		
		return optionList;
	} // getOptionList

}
