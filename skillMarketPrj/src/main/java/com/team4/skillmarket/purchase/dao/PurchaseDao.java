package com.team4.skillmarket.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.order.vo.QuotationVo;
import com.team4.skillmarket.purchase.vo.InfoVo;
import com.team4.skillmarket.purchase.vo.OptionVo;
import com.team4.skillmarket.purchase.vo.QuotationOptionVo;
import com.team4.skillmarket.purchase.vo.WriteQuotationVo;

public class PurchaseDao {

	public InfoVo getInfo(Connection conn, String no, String seller) {

		InfoVo infoVo = null;
		
		String sql = "SELECT ESTIMATE_NO, A.FREELANCER_NO, MEMBER_NICK, A.ESTIMATE_CAT_NO, ESTIMATE_CAT_NAME, ESTIMATE_TITLE, ESTIMATE_THUMBNAIL, ESTIMATE_LINE_INTRODUCTION,\r\n"
				+ "ESTIMATE_PRICE, ESTIMATE_DURATION, ESTIMATE_DETAIL, ESTIMATE_DETAIL_FILE, BUSINESS_REGISTRATION_NUMBER, \r\n"
				+ "TO_CHAR(ESTIMATE_ENROLL_DATE, 'YYYY-MM-DD') AS ESTIMATE_ENROLL_DATE, ESTIMATE_MODIFICATION_DATE, ESTIMATE_STATUS, ESTIMATE_VIEWS\r\n"
				+ "FROM ESTIMATE A\r\n"
				+ "    JOIN ( SELECT FREELANCER_NO, MEMBER_NICK\r\n"
				+ "            FROM FREELANCER A\r\n"
				+ "                JOIN MEMBER B ON A.MEMBER_NO = B.MEMBER_NO\r\n"
				+ "            WHERE FREELANCER_NO = ?\r\n"
				+ "        ) B ON A.FREELANCER_NO = B.FREELANCER_NO\r\n"
				+ "    JOIN ESTIMATE_CAT C ON A.ESTIMATE_CAT_NO = C.ESTIMATE_CAT_NO\r\n"
				+ "WHERE ESTIMATE_NO = ?";

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, seller);
			pstmt.setString(2, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String estimateNo = rs.getString("ESTIMATE_NO");
				String freelnacerNo = rs.getString("FREELANCER_NO");
				String memberNick = rs.getString("MEMBER_NICK");
				String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
				String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
				String estimateTitle = rs.getString("ESTIMATE_TITLE");
				String estimateThumbnail = rs.getString("ESTIMATE_THUMBNAIL");
				String estimateLineIntroduction = rs.getString("ESTIMATE_LINE_INTRODUCTION");
				String estimatePrice = rs.getString("ESTIMATE_PRICE");
				String estimatePeriod = rs.getString("ESTIMATE_DURATION");
				String estimateDetail = rs.getString("ESTIMATE_DETAIL");
				String estimateDetailFile = rs.getString("ESTIMATE_DETAIL_FILE");
				String businessRegistrationNumber = rs.getString("BUSINESS_REGISTRATION_NUMBER");
				String estimateEnrollDate = rs.getString("ESTIMATE_ENROLL_DATE");
				String estimateModificationDate = rs.getString("ESTIMATE_MODIFICATION_DATE");
				String estimateStatus = rs.getString("ESTIMATE_STATUS");
				String estimateViews = rs.getString("ESTIMATE_VIEWS");
				
				infoVo = new InfoVo();
				
				infoVo.setEstimateNo(estimateNo);
				infoVo.setFreelancerNo(freelnacerNo);
				infoVo.setMemberNick(memberNick);
				infoVo.setEstimateCatNo(estimateCatNo);
				infoVo.setEstimateCatName(estimateCatName);
				infoVo.setEstimateTitle(estimateTitle);
				infoVo.setEstimateThumbnail(estimateThumbnail);
				infoVo.setEstimateLineIntroduction(estimateLineIntroduction);
				infoVo.setEstimatePrice(estimatePrice);
				infoVo.setEstimatePeriod(estimatePeriod);
				infoVo.setEstimateDetail(estimateDetail);
				infoVo.setEstimateDetailFile(estimateDetailFile);
				infoVo.setBusinessRegistrationNumber(businessRegistrationNumber);
				infoVo.setEstimateEnrollDate(estimateEnrollDate);
				infoVo.setEstimateModificationDate(estimateModificationDate);
				infoVo.setEstimateStatus(estimateStatus);
				infoVo.setEstimateViews(estimateViews);
				
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return infoVo;
	} // getInfo

	public List<OptionVo> getOptionList(Connection conn, String no) {
		
		List<OptionVo> optionList = new ArrayList<>();

		String sql = "SELECT ESTIMATE_OPTION_NO, ESTIMATE_NO, ESTIMATE_OPTION_NAME, ESTIMATE_OPTION_PRICE, ESTIMATE_OPTION_QUANTITY\r\n"
				+ "FROM ESTIMATE_OPTION\r\n"
				+ "WHERE ESTIMATE_NO = ? ORDER BY ESTIMATE_OPTION_NO";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String estimateOptionNo = rs.getString("ESTIMATE_OPTION_NO");
				String estimateNo = rs.getString("ESTIMATE_NO");
				String estimateOptionName = rs.getString("ESTIMATE_OPTION_NAME");
				String estimateOptionPrice = rs.getString("ESTIMATE_OPTION_PRICE");
				String EstimateOptionPeriod = rs.getString("ESTIMATE_OPTION_QUANTITY");
				
				OptionVo vo = new OptionVo();
				
				vo.setEstimateOptionNo(estimateOptionNo);
				vo.setEstimateNo(estimateNo);
				vo.setEstimateOptionName(estimateOptionName);
				vo.setEstimateOptionPrice(estimateOptionPrice);
				vo.setEstimateOptionPeriod(EstimateOptionPeriod);
				
				optionList.add(vo);
			}
			
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return optionList;
	} // getOptionList

	public String writeQuotation(Connection conn, WriteQuotationVo writeQuotationVo) {
		
		String quotationNo = null;
		
		String insertQuotationSql = "INSERT INTO QUOTATION\r\n"
				+ "( QUOTATION_NO, ESTIMATE_NO, QUOTATION_PREV_VER, MEMBER_NO, QUOTATION_STATUS_NO, QUOTATION_PRICE, QUOTATION_PERIOD, PAYMENT_METHOD_NO,\r\n"
				+ "QUOTATION_ENROLL_DATE, QUOTATION_MODIFICATION_DATE)\r\n"
				+ "VALUES ( SEQ_QUOTATION_NO.NEXTVAL, ?, NULL, ?, 1, ?, ?, ?, SYSDATE, NULL)";
		
		PreparedStatement pstmt = null;
		try {
			
			pstmt = conn.prepareStatement(insertQuotationSql);
			pstmt.setString(1, writeQuotationVo.getEstimateNo());
			pstmt.setString(2, writeQuotationVo.getMemberNo());
			pstmt.setString(3, writeQuotationVo.getTotalPrice());
			pstmt.setString(4, writeQuotationVo.getTotalPeriod());
			pstmt.setString(5, writeQuotationVo.getPurchaseMethod());
			
			int result = pstmt.executeUpdate();
			
			pstmt = null;
			if(result == 1) {
				
				quotationNo = getQuotationNo(conn, writeQuotationVo);
				
				String insertQuotationOptionSql = "INSERT INTO QUOTATION_OPTION\r\n"
						+ "( QUOTATION_OPTION_NO, QUOTATION_NO, ESTIMATE_OPTION_NO, QUOTATION_OPTION_QUANTITY )\r\n"
						+ "VALUES ( SEQ_QUOTATION_OPTION_NO.NEXTVAl, ?, ?, ?)";
				
				pstmt = conn.prepareStatement(insertQuotationOptionSql);
				
				for(int i = 0; i < writeQuotationVo.getEstimateOptions().length; i++) {
					
					pstmt.setString(1, quotationNo);
					pstmt.setString(2, writeQuotationVo.getEstimateOptions()[i]);
					pstmt.setString(3, writeQuotationVo.getQuantitys()[i]);
					
					int result_ = pstmt.executeUpdate();
					
					if(result_ != 1) {
						throw new Exception();
					}
				}
				
				pstmt = null;
				
				String decreaseBuyerCashSql = "UPDATE USER_CASH SET CASH_MONEY = CASH_MONEY - ?\r\n"
						+ "WHERE MEMBER_NO = ?";
				
				pstmt = conn.prepareStatement(decreaseBuyerCashSql);
				pstmt.setString(1, writeQuotationVo.getTotalPrice());
				pstmt.setString(2, writeQuotationVo.getMemberNo());
				
				int result__ = pstmt.executeUpdate();
				
				pstmt = null;
				
				String insertCashLogSql = "INSERT INTO CASH_LOG (NO, MEMBER_NO, LOG_CATEGORY_NO, AMOUNT, PAYMENT_METHOD_NO, ENROLL_DATE)\r\n"
						+ "VALUES (SEQ_CASH_LOG_NO.NEXTVAL, ?, 4, ?, ?, SYSDATE)";
				
				pstmt = conn.prepareStatement(insertCashLogSql);
				pstmt.setString(1, writeQuotationVo.getMemberNo());
				pstmt.setString(2, writeQuotationVo.getTotalPrice());
				pstmt.setString(3, writeQuotationVo.getPurchaseMethod());
				
				result__ = pstmt.executeUpdate();
				
				pstmt = null;
				
				String insertSalesLogSql = "INSERT INTO SALES_LOG \r\n"
						+ "( SALES_LOG_NO, CATEGORY_NO, SALES, ENROLL_DATE, QUOTATION_NO )\r\n"
						+ "VALUES (SEQ_SALES_LOG_NO.NEXTVAL, 1, ?, DEFAULT, ?)";
				
				pstmt = conn.prepareStatement(insertSalesLogSql);
				pstmt.setString(1, Integer.toString((int)(Integer.parseInt(writeQuotationVo.getTotalPrice()) * 0.03)) );
				pstmt.setString(2, quotationNo);
				
				result__ = pstmt.executeUpdate();
				
				if(result__ != 1) {
					throw new Exception();
				}
				
			}
			else {
				throw new Exception("Quotation Insert 실패");
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return quotationNo;
	} // writeQuotation
	
	
	private String getQuotationNo(Connection conn, WriteQuotationVo writeQuotationVo) {
		
		String quotationNo = null;
		
		String getQuotationSql = "SELECT QUOTATION_NO FROM QUOTATION WHERE ESTIMATE_NO = ? AND MEMBER_NO = ? AND QUOTATION_STATUS_NO = 1";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getQuotationSql);
			pstmt.setString(1, writeQuotationVo.getEstimateNo());
			pstmt.setString(2, writeQuotationVo.getMemberNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				quotationNo = rs.getString("QUOTATION_NO");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		
		return quotationNo;
	}

	public QuotationVo getQuotationInfo(Connection conn, String quotationNo) {
		
		QuotationVo quotationVo = null;
		
		String getQuotationInfoSql = "SELECT QUOTATION_NO, A.ESTIMATE_NO, ESTIMATE_TITLE, ESTIMATE_PRICE, ESTIMATE_DURATION, \r\n"
				+ "QUOTATION_PREV_VER, MEMBER_NO, QUOTATION_STATUS_NO, QUOTATION_PRICE, QUOTATION_PERIOD, PAYMENT_METHOD_NO, \r\n"
				+ "QUOTATION_ENROLL_DATE, QUOTATION_MODIFICATION_DATE\r\n"
				+ "FROM QUOTATION A\r\n"
				+ "    JOIN ESTIMATE B ON A.ESTIMATE_NO = B.ESTIMATE_NO\r\n"
				+ "WHERE QUOTATION_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			pstmt = conn.prepareStatement(getQuotationInfoSql);
			pstmt.setString(1, quotationNo);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				quotationVo = new QuotationVo();
				
				quotationVo.setQuotationNo(rs.getString("QUOTATION_NO"));
				quotationVo.setEstimateNo(rs.getString("ESTIMATE_NO"));
				quotationVo.setEstimateTitle(rs.getString("ESTIMATE_TITLE"));
				quotationVo.setEstimatePrice(rs.getString("ESTIMATE_PRICE"));
				quotationVo.setEstimatePeriod(rs.getString("ESTIMATE_DURATION"));
				quotationVo.setMemberNo(rs.getString("MEMBER_NO"));
				quotationVo.setQuotationStatusNo(rs.getString("QUOTATION_STATUS_NO"));
				quotationVo.setQuotationPrice(rs.getString("QUOTATION_PRICE"));
				quotationVo.setQuotationPeriod(rs.getString("QUOTATION_PERIOD"));
				quotationVo.setPaymetnMethodNo(rs.getString("PAYMENT_METHOD_NO"));
				quotationVo.setQuotationEnrollDate(rs.getString("QUOTATION_ENROLL_DATE"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quotationVo;
	}

	public List<QuotationOptionVo> getQuotationOptionList(Connection conn, String quotationNo) {
		
		List<QuotationOptionVo> quotationOptionList = new ArrayList<>();
		
		String getQuotationOptionsSql = "SELECT QUOTATION_OPTION_NO, QUOTATION_NO, \r\n"
				+ "A.ESTIMATE_OPTION_NO, ESTIMATE_OPTION_NAME, ESTIMATE_OPTION_PRICE, ESTIMATE_OPTION_QUANTITY, QUOTATION_OPTION_QUANTITY\r\n"
				+ "FROM QUOTATION_OPTION A\r\n"
				+ "    JOIN ESTIMATE_OPTION B ON A.ESTIMATE_OPTION_NO = B.ESTIMATE_OPTION_NO\r\n"
				+ "WHERE QUOTATION_NO = ?\r\n"
				+ "ORDER BY QUOTATION_OPTION_NO";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getQuotationOptionsSql);
			pstmt.setString(1, quotationNo);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				
				String quotationOptionNo = rs.getString("QUOTATION_OPTION_NO");
				String estimateOptionNo = rs.getString("ESTIMATE_OPTION_NO");
				String estimateOptionName = rs.getString("ESTIMATE_OPTION_NAME");
				String estimateOptionPrice = rs.getString("ESTIMATE_OPTION_PRICE");
				String estimateOptionPeriod = rs.getString("ESTIMATE_OPTION_QUANTITY");
				String quotationOptionQuantity = rs.getString("QUOTATION_OPTION_QUANTITY");
				
				QuotationOptionVo quotationOptionVo = new QuotationOptionVo();
				
				quotationOptionVo.setQuotationOptionNo(quotationOptionNo);
				quotationOptionVo.setEstimateOptionNo(estimateOptionNo);
				quotationOptionVo.setEstimateOptionName(estimateOptionName);
				quotationOptionVo.setEstimateOptionPrice(estimateOptionPrice);
				quotationOptionVo.setEstimateOptionPeriod(estimateOptionPeriod);
				quotationOptionVo.setQuotationOptionQuantity(quotationOptionQuantity);
				
				quotationOptionList.add(quotationOptionVo);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return quotationOptionList;
	}

}
