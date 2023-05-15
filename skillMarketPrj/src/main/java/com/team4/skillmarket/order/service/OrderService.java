package com.team4.skillmarket.order.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.order.dao.OrderDao;

public class OrderService {

	public Map<String, Object> getDetail(String quatationNo) {
		
		Map<String, Object> voMap = null;
		
		// getConnection
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO
		OrderDao orderDao = new OrderDao();
		voMap = orderDao.getDetail(conn, quatationNo);
		
		if(voMap != null) {
			
		}
		
		return voMap;
	}
	
	

}
