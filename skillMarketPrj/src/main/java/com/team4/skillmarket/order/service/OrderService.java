package com.team4.skillmarket.order.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.order.dao.OrderDao;
import com.team4.skillmarket.order.vo.QuotationOptionVo;
import com.team4.skillmarket.order.vo.QuotationVo;

public class OrderService {

	public Map<String, Object> getOrderDetailbyNo(String quatationNo) {
		
		Map<String, Object> voMap = new HashMap();
		
		// getConnection
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		OrderDao orderDao = new OrderDao();
		QuotationVo quotationVo = orderDao.getOrderDetailbyNo(conn, quatationNo);
		List<QuotationOptionVo> optionVoList = orderDao.getOrderOptionbyNo(conn, quatationNo); 
		
		voMap.put("quotation", quotationVo);
		voMap.put("optionList", optionVoList);
		
		// close
		JDBCTemplate.close(conn);
		
		return voMap;
	}
	
	

}
