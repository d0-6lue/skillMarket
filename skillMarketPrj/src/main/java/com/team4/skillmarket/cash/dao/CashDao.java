package com.team4.skillmarket.cash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.team4.skillmarket.cash.vo.CashChargeVo;
import com.team4.skillmarket.cash.vo.UserCashVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class CashDao {

	public UserCashVo getMemberCash(Connection conn, String memberNo) {
		
		UserCashVo userCash = null;
		
		String sql = "SELECT MEMBER_NO, CASH_MONEY, CASH_POINT\r\n"
				+ "FROM USER_CASH\r\n"
				+ "WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String no = rs.getString("MEMBER_NO");
				String cashMoney = rs.getString("CASH_MONEY");
				String cashPoint = rs.getString("CASH_POINT");
				
				userCash = new UserCashVo();
				
				userCash.setMemberNO(no);
				userCash.setUserCashMoney(cashMoney);
				userCash.setUserCashPoint(cashPoint);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return userCash;
	} // getMemberCash

	
	public int charge(Connection conn, CashChargeVo chargeVo) {
		
		int result_ = 0;
		int result = 0;
		
		String sql = "INSERT INTO CASH_CHARGE_LOG ( NO, MEMBER_NO, PAYMENT_METHOD_NO, CHARGE_AMOUNT, CHARGE_DATE )\r\n"
				+ "VALUES ( SEQ_CASH_CHARGE_NO.NEXTVAL, ?, ?, ?, DEFAULT)";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chargeVo.getMemberNo());
			pstmt.setString(2, chargeVo.getPaymentMethodNo());
			pstmt.setString(3, chargeVo.getChargeAmount());
			
			result = pstmt.executeUpdate();
			
			
			pstmt = null;
			if(result == 1) {
				
				sql = "UPDATE USER_CASH \r\n"
						+ "SET CASH_MONEY = CASH_MONEY + ?r\n"
						+ "WHERE MEMBER_NO = ?";
				
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, chargeVo.getChargeAmount());
				pstmt.setString(2, chargeVo.getMemberNo());
				
				result_ = pstmt.executeUpdate();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result_;
	} // charge

	
	public UserCashVo getMemberCashAndBank(Connection conn, String memberNo) {
		
		UserCashVo userCash = null;
		
		String sql = "SELECT CASH_MONEY, CASH_POINT, MEMBER_BANK, MEMBER_ACCOUNT\r\n"
				+ "FROM USER_CASH A\r\n"
				+ "    JOIN MEMBER B ON A.MEMBER_NO = B.MEMBER_NO\r\n"
				+ "WHERE A.MEMBER_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				userCash = new UserCashVo();
				
				userCash.setUserCashMoney(rs.getString("CASH_MONEY"));
				userCash.setUserCashPoint(rs.getString("CASH_POINT"));
				userCash.setMemberBank(rs.getString("MEMBER_BANK"));
				userCash.setMemberAccount(rs.getString("MEMBER_ACCOUNT"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return userCash;
	} // getMemberCashAndBank

	
	public int refund(Connection conn, CashChargeVo refundVo) {
		
		int result = 0;
		
		String sql = "UPDATE USER_CASH \r\n"
				+ "SET CASH_MONEY = CASH_MONEY - ?\r\n"
				+ "WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, refundVo.getChargeAmount());
			pstmt.setString(2, refundVo.getMemberNo());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	} // refund

}
