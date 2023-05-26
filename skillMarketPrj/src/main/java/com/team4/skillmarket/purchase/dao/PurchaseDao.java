package com.team4.skillmarket.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.purchase.vo.InfoVo;
import com.team4.skillmarket.purchase.vo.OptionVo;

public class PurchaseDao {

	public InfoVo getInfo(Connection conn, String no) {

		InfoVo infoVo = null;
		
		String sql = "SELECT ESTIMATE_NO, A.FREELANCER_NO, MEMBER_NICK, A.ESTIMATE_CAT_NO, ESTIMATE_CAT_NAME, ESTIMATE_TITLE, ESTIMATE_THUMBNAIL, ESTIMATE_LINE_INTRODUCTION,\r\n"
				+ "ESTIMATE_PRICE, ESTIMATE_PERIOD, ESTIMATE_DETAIL, ESTIMATE_DETAIL_FILE, ESTIMATE_PORTFOLIO, BUSINESS_REGISTRATION_NUMBER, \r\n"
				+ "TO_CHAR(ESTIMATE_ENROLL_DATE, 'YYYY-MM-DD') AS ESTIMATE_ENROLL_DATE, ESTIMATE_MODIFICATION_DATE, ESTIMATE_STATUS, ESTIMATE_VIEWS\r\n"
				+ "FROM ESTIMATE A\r\n"
				+ "    JOIN ( SELECT FREELANCER_NO, MEMBER_NICK\r\n"
				+ "            FROM FREELANCER A\r\n"
				+ "                JOIN MEMBER B ON A.MEMBER_NO = B.MEMBER_NO\r\n"
				+ "            WHERE FREELANCER_NO = 601\r\n"
				+ "        ) B ON A.FREELANCER_NO = B.FREELANCER_NO\r\n"
				+ "    JOIN ESTIMATE_CAT C ON A.ESTIMATE_CAT_NO = C.ESTIMATE_CAT_NO\r\n"
				+ "WHERE ESTIMATE_NO = ?";

		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
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
				String estimatePeriod = rs.getString("ESTIMATE_PERIOD");
				String estimateDetail = rs.getString("ESTIMATE_DETAIL");
				String estimateDetailFile = rs.getString("ESTIMATE_DETAIL_FILE");
				String estimatePortfolio = rs.getString("ESTIMATE_PORTFOLIO");
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
				infoVo.setEstimatePortfolio(estimatePortfolio);
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

		String sql = "SELECT ESTIMATE_OPTION_NO, ESTIMATE_NO, ESTIMATE_OPTION_NAME, ESTIMATE_OPTION_PRICE, ESTIMATE_OPTION_PERIOD\r\n"
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
				String EstimateOptionPeriod = rs.getString("ESTIMATE_OPTION_PERIOD");
				
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

}
