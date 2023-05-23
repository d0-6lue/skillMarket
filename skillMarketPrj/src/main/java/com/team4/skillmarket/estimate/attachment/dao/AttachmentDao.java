package com.team4.skillmarket.estimate.attachment.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.utill.file.AttachmentVo;

public class AttachmentDao {
	public int insertAttachment(Connection conn, AttachmentVo attachment) throws SQLException {
	    PreparedStatement pstmt = null;
	    int result = 0;

	    try {
	        String query = "INSERT INTO attachment (attachment_no, estimate_no, attachment_origin_name, attachment_server_name, main_image) "
	                + "VALUES (SEQ_ATTACHMENT.NEXTVAL, ?, ?, ?, ?)";
	        pstmt = conn.prepareStatement(query);
	        pstmt.setInt(1, attachment.getEstimateNo());
	        pstmt.setString(2, attachment.getAttachmentOriginName());
	        pstmt.setString(3, attachment.getAttachmentServerName());
	        pstmt.setBoolean(4, attachment.isMainImage());

	        System.out.println("Estimate No: " + attachment.getEstimateNo()); // Estimate No 출력

	        result = pstmt.executeUpdate();

	        // 로그 추가
	        System.out.println("Attachment inserted: " + attachment.getAttachmentOriginName());
	    } finally {
	        JDBCTemplate.close(pstmt);
	    }

	    return result;
	}




}
