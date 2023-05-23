package com.team4.skillmarket.cash.service;

import java.sql.Connection;

import com.team4.skillmarket.cash.dao.CashDao;
import com.team4.skillmarket.cash.vo.CashChargeVo;
import com.team4.skillmarket.cash.vo.UserCashVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class CashService {

	private final CashDao cashDao = new CashDao();
	
	public UserCashVo getMemberCash(String memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		UserCashVo userCash = cashDao.getMemberCash(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return userCash;
	} // getMemberCash
	
	
	public int charge(CashChargeVo chargeVo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = cashDao.charge(conn, chargeVo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public UserCashVo getMemberCashAndBank(String memberNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		UserCashVo userCash = cashDao.getMemberCashAndBank(conn, memberNo);
		
		JDBCTemplate.close(conn);
		
		return userCash;
	}


	public int refund(CashChargeVo refundVo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = cashDao.refund(conn, refundVo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	

}
