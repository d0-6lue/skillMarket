package com.team4.skillmarket.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.order.vo.QuotationOptionVo;
import com.team4.skillmarket.order.vo.QuotationVo;

public class OrderDao {

	public QuotationVo getOrderDetailbyNo(Connection conn, String quatationNo) {
		
		QuotationVo quotationVo = null;
		
		// 
		String getQuotationSql = "SELECT QUOTATION_NO, QUOTATION_PREV_VER, QUOTATION_STATUS_NAME, QUOTATION_PRICE, QUOTATION_PERIOD,\r\n"
				+ "TO_CHAR(QUOTATION_ENROLL_DATE, 'YYYY-MM-DD') AS QUOTATION_ENROLL_DATE_, TO_CHAR(QUOTATION_MODIFICATION_DATE, 'YYYY-MM-DD') AS QUOTATION_MODIFICATION_DATE_,\r\n"
				+ "A.MEMBER_NO, B.MEMBER_NICK AS BUYER, C.MEMBER_NICK AS SELLER,\r\n"
				+ "A.ESTIMATE_NO, ESTIMATE_TITLE, ESTIMATE_THUMBNAIL, ESTIMATE_LINE_INTRODUCTION, ESTIMATE_DETAIL, ESTIMATE_PRICE, ESTIMATE_DURATION\r\n"
				+ "FROM QUOTATION A\r\n"
				+ "    JOIN MEMBER B ON A.MEMBER_NO = B.MEMBER_NO\r\n"
				+ "    JOIN (\r\n"
				+ "        SELECT *\r\n"
				+ "        FROM ESTIMATE A\r\n"
				+ "            JOIN (SELECT MEMBER_NICK, FREELANCER_NO\r\n"
				+ "                FROM FREELANCER A\r\n"
				+ "                    JOIN MEMBER B ON A.MEMBER_NO = B.MEMBER_NO\r\n"
				+ "                ) B ON A.FREELANCER_NO = B.FREELANCER_NO\r\n"
				+ "        ) C ON A.ESTIMATE_NO = C.ESTIMATE_NO\r\n"
				+ "    JOIN QUOTATION_STATUS D ON A.QUOTATION_STATUS_NO = D.QUOTATION_STATUS_NO\r\n"
				+ "WHERE A.QUOTATION_NO = ?";
		
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getQuotationSql);
			pstmt.setString(1, quatationNo);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				quotationVo = new QuotationVo();
				
				quotationVo.setQuotationNo(rs.getString("QUOTATION_NO"));
				quotationVo.setQuotationPrevVer(rs.getString("QUOTATION_PREV_VER"));
				quotationVo.setQuotationStatusNo(rs.getString("QUOTATION_STATUS_NAME"));
				quotationVo.setQuotationPrice(rs.getString("QUOTATION_PRICE"));
				quotationVo.setQuotationPeriod(rs.getString("QUOTATION_PERIOD"));
				quotationVo.setQuotationEnrollDate(rs.getString("QUOTATION_ENROLL_DATE_"));
				quotationVo.setQuotationModificationDate(rs.getString("QUOTATION_MODIFICATION_DATE_"));
				quotationVo.setMemberNo(rs.getString("SELLER"));
				
				quotationVo.setEstimateNo(rs.getString("ESTIMATE_NO"));
				quotationVo.setEstimateTitle(rs.getString("ESTIMATE_TITLE"));
				quotationVo.setEstimateThumbnail(rs.getString("ESTIMATE_THUMBNAIL"));
				quotationVo.setEstimateLineIntroduction(rs.getString("ESTIMATE_LINE_INTRODUCTION"));
				quotationVo.setEstimateDetail(rs.getString("ESTIMATE_DETAIL"));
				quotationVo.setEstimatePrice(rs.getString("ESTIMATE_PRICE"));
				quotationVo.setEstimatePeriod(rs.getString("ESTIMATE_DURATION"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
		}
		
		return quotationVo;
	}
	
public List<QuotationOptionVo> getOrderOptionbyNo(Connection conn, String quatationNo) {
		
		List<QuotationOptionVo> optionVoList = new ArrayList<>();
		
		String getQuoattionOptionSql = "SELECT QUOTATION_OPTION_NO, QUOTATION_OPTION_QUANTITY,\r\n"
				+ "ESTIMATE_NO, ESTIMATE_OPTION_NAME, ESTIMATE_OPTION_PRICE, ESTIMATE_OPTION_QUANTITY\r\n"
				+ "FROM QUOTATION_OPTION A\r\n"
				+ "    JOIN ESTIMATE_OPTION B ON A.ESTIMATE_OPTION_NO = B.ESTIMATE_OPTION_NO\r\n"
				+ "WHERE QUOTATION_NO = ?";
		
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getQuoattionOptionSql);
			pstmt.setString(1, quatationNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				QuotationOptionVo optionVo = new QuotationOptionVo();
				
				optionVo.setQuotationOptionNo(rs.getString("QUOTATION_OPTION_NO"));
				optionVo.setQuotationOptionQuantity(rs.getString("QUOTATION_OPTION_QUANTITY"));
				
				
				optionVo.setEstimateOptionName(rs.getString("ESTIMATE_OPTION_NAME"));
				optionVo.setEstimateOptionPrice(rs.getString("ESTIMATE_OPTION_PRICE"));
				optionVo.setEstimateOptionPeriod(rs.getString("ESTIMATE_OPTION_QUANTITY"));
				
				optionVoList.add(optionVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rs);
			
		}
		
		return optionVoList;
	}

}
