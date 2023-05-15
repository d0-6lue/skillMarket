package com.team4.skillmarket.order.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.order.vo.QuotationVo;

public class OrderDao {

	public Map<String, Object> getDetail(Connection conn, String quatationNo) {
		
		Map<String, Object> voMap = new HashMap<>();
		
		QuotationVo quatationVo = new QuotationVo();
		EstimateVo estimateVo= new EstimateVo();
		
		// 
		String getQuatationSql = "";
		String getEstimateSql = "";
		
		voMap.put("quatation", quatationVo);
		voMap.put("estimate", estimateVo);
		
		return voMap;
	}

}
