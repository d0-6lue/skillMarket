package com.team4.skillmarket.purchase.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.order.vo.QuotationVo;
import com.team4.skillmarket.purchase.dao.PurchaseDao;
import com.team4.skillmarket.purchase.vo.InfoVo;
import com.team4.skillmarket.purchase.vo.OptionVo;
import com.team4.skillmarket.purchase.vo.QuotationOptionVo;
import com.team4.skillmarket.purchase.vo.WriteQuotationVo;

public class PurchaseService {

	private final PurchaseDao purchaseDao = new PurchaseDao();
	
	public InfoVo getInfo(String no, String seller) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		InfoVo infoVo = purchaseDao.getInfo(conn, no, seller);
		
		JDBCTemplate.close(conn);
		
		return infoVo;
	} // getInfo

	public List<OptionVo> getOptionList(String no) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<OptionVo> optionList = purchaseDao.getOptionList(conn, no);
		
		JDBCTemplate.close(conn);
		
		return optionList;
	} // getOptionList

	public String writeQuotation(WriteQuotationVo writeQuotationVo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String quotationNo = purchaseDao.writeQuotation(conn, writeQuotationVo);
		
		JDBCTemplate.close(conn);
		
		return quotationNo;
	} // writeQuotation

	public QuotationVo getQuotationInfo(String quotationNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		QuotationVo quotationVo = purchaseDao.getQuotationInfo(conn, quotationNo);

		JDBCTemplate.close(conn);
		
		return quotationVo;
	}

	public List<QuotationOptionVo> getQuotationOptionList(String quotationNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<QuotationOptionVo> quotationOptionList = purchaseDao.getQuotationOptionList(conn, quotationNo);
		
		JDBCTemplate.close(conn);
		
		return quotationOptionList;
	}

}
