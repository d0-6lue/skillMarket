package com.team4.skillmarket.estimate.option.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateOptionVo;
import com.team4.skillmarket.estimate.vo.EstimateVo;

public class EstimateOptionDao {

	 public int insertEstimateOption(Connection conn, EstimateOptionVo option) throws SQLException {
	        PreparedStatement pstmt = null;
	        try {
	            String sql = "INSERT INTO ESTIMATE_OPTION (ESTIMATE_OPTION_NO, ESTIMATE_NO, ESTIMATE_OPTION_NAME,ESTIMATE_OPTION_PRICE,ESTIMATE_OPTION_QUANTITY) VALUES (SEQ_ESTIMATE_OPTION.NEXTVAL, ?, ?,?,?)";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, option.getEstimateNo());
	            pstmt.setString(2, option.getEstimateOptionName());
	            pstmt.setString(3, option.getEstimateOptionPrice());
	            pstmt.setString(4, option.getEstimateOptionQuantity());
	         int result = pstmt.executeUpdate();
	         System.out.println(result);
	         return result;
	        } finally {
	            JDBCTemplate.close(pstmt);
	        }
	    }

	public EstimateVo getEstimateVoByNo(Connection conn, String estimateNo) {

		EstimateVo estimateVo = null;
		
		String getEstimateVoSql = "SELECT ESTIMATE_NO, ESTIMATE_TITLE, ESTIMATE_THUMBNAIL, ESTIMATE_DURATION, ESTIMATE_LINE_INTRODUCTION, ESTIMATE_PRICE, ESTIMATE_DETAIL, ESTIMATE_DETAIL_FILE,\r\n"
				+ "TO_CHAR(ESTIMATE_ENROLL_DATE, 'YYYY-MM-DD') AS ENROLL_DATE, FREELANCER_CONTACT_TIME, MEMBER_NICK, MEMBER_PROFILE_PHOTO\r\n"
				+ "FROM ESTIMATE A\r\n"
				+ "    JOIN (SELECT * FROM FREELANCER A JOIN MEMBER B ON A.MEMBER_NO = B.MEMBER_NO) B \r\n"
				+ "    ON A.FREELANCER_NO = B.FREELANCER_NO\r\n"
				+ "WHERE ESTIMATE_NO = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			pstmt = conn.prepareStatement(getEstimateVoSql);
			pstmt.setString(1, estimateNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				String estimateNo_ = rs.getString("ESTIMATE_NO");
				String estimateThumbnail = rs.getString("ESTIMATE_THUMBNAIL");
				String estimateDuration = rs.getString("ESTIMATE_DURATION");
				String estimateLineIntroduction = rs.getString("ESTIMATE_LINE_INTRODUCTION");
				String estimatePrice = rs.getString("ESTIMATE_PRICE");
				String estimateDetail = rs.getString("ESTIMATE_DETAIL");
				String estimateDetailFile = rs.getString("ESTIMATE_DETAIL_FILE");
				String estimateEnrollDate = rs.getString("ENROLL_DATE");
				String freelancerContactTime = rs.getString("FREELANCER_CONTACT_TIME");
				String freelancerNick = rs.getString("MEMBER_NICK");
				String freelancerProfile = rs.getString("MEMBER_PROFILE_PHOTO");
				
				estimateVo = new EstimateVo();
				
				estimateVo.setEstimateNo(estimateNo_);
				estimateVo.setMainImage(estimateThumbnail);
				estimateVo.setEstimateDuration(estimateDuration);
				estimateVo.setEstimateLineIntroduction(estimateLineIntroduction);
				estimateVo.setEstimatePrice(estimatePrice);
				estimateVo.setEstimateDetail(estimateDetail);
				estimateVo.setSubImage(estimateDetailFile);
				estimateVo.setEstimateEnrollDate(estimateEnrollDate);
				estimateVo.setFreelancerContactTime(freelancerContactTime);
				estimateVo.setMemberNick(freelancerNick);
				estimateVo.setMemberProfile(freelancerProfile);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return estimateVo;
	}

}
