package com.team4.skillmarket.estimate.faq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateFaqVo;

public class EstimateFaqDao {

	public int insertEstimateFaq(Connection conn, EstimateFaqVo faq) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO ESTIMATE_FAQ (ESTIMATE_FAQ_NO, ESTIMATE_NO, ESTIMATE_FAQ_QCONTENT) VALUES (SEQ_ESTIMATE_FAQ.NEXTVAL, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, faq.getEstimateNo());
            pstmt.setString(2, faq.getEstimateFaqQContent());

            int result = pstmt.executeUpdate();
            
            return result;
        } finally {
            JDBCTemplate.close(pstmt);
        }
    }

}
