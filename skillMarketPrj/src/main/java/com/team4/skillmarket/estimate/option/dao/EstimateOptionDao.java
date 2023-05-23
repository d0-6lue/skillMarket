package com.team4.skillmarket.estimate.option.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateOptionVo;

public class EstimateOptionDao {

	 public int insertEstimateOption(Connection conn, EstimateOptionVo option) throws SQLException {
	        PreparedStatement pstmt = null;
	        try {
	            String sql = "INSERT INTO ESTIMATE_OPTION (ESTIMATE_OPTION_NO, ESTIMATE_NO, ESTIMATE_OPTION_NAME) VALUES (SEQ_ESTIMATE_OPTION.NEXTVAL, ?, ?)";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setInt(1, option.getEstimateNo());
	            pstmt.setString(2, option.getEstimateOptionName());

	            return pstmt.executeUpdate();
	        } finally {
	            JDBCTemplate.close(pstmt);
	        }
	    }

}
