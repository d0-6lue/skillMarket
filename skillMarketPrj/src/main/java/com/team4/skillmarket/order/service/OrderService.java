package com.team4.skillmarket.order.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team4.skillmarket.chat.vo.ChatVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.order.dao.OrderDao;
import com.team4.skillmarket.order.vo.QuotationOptionVo;
import com.team4.skillmarket.order.vo.QuotationVo;

public class OrderService {

	private final OrderDao orderDao = new OrderDao();
	
	public Map<String, Object> getOrderDetailbyNo(String quotationNo) {
		
		Map<String, Object> voMap = new HashMap();
		
		// getConnection
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		QuotationVo quotationVo = orderDao.getOrderDetailbyNo(conn, quotationNo);
		List<QuotationOptionVo> optionVoList = orderDao.getOrderOptionbyNo(conn, quotationNo); 
		
		voMap.put("quotation", quotationVo);
		voMap.put("optionList", optionVoList);
		
		// close
		JDBCTemplate.close(conn);
		
		return voMap;
	}

	public String getLastChat(String quotationNo, String memberNo) {

		// getConnection
		Connection conn = JDBCTemplate.getConnection();

		String lastChatContent = orderDao.getLastChat(conn, quotationNo, memberNo);
		
		// close
		JDBCTemplate.close(conn);
		
		return lastChatContent;
	}
	
	

}
