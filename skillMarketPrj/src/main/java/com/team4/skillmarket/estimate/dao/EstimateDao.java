package com.team4.skillmarket.estimate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.List;

import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.utill.file.AttachmentVo;

public class EstimateDao {
    public int writeEstimate(Connection conn, EstimateVo estimate) throws Exception {
        String sql = "INSERT INTO ESTIMATE (ESTIMATE_NO, FREELANCER_NO, ESTIMATE_CAT_NO, ESTIMATE_TITLE, ESTIMATE_DURATION, ESTIMATE_THUMBNAIL, ESTIMATE_LINE_INTRODUCTION, ESTIMATE_PRICE, ESTIMATE_DETAIL, ESTIMATE_PORTFOLIO, BUSINESS_REGISTRATION_NUMBER, ESTIMATE_ENROLL_DATE, ESTIMATE_MODIFICATION_DATE, ESTIMATE_STATUS, ESTIMATE_VIEWS) "
                + "VALUES (SEQ_ESTIMATE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, ?)";

        try (
        	PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"ESTIMATE_NO"})) {
            pstmt.setInt(1, estimate.getFreelancerNo());
            pstmt.setInt(2, estimate.getEstimateCatNo());
            pstmt.setString(3, estimate.getEstimateTitle());
            pstmt.setString(4, estimate.getEstimateDuration());
            pstmt.setString(5, estimate.getEstimateThumbnail());
            pstmt.setString(6, estimate.getEstimateLineIntroduction());
            pstmt.setString(7, estimate.getEstimatePrice());
            pstmt.setString(8, estimate.getEstimateDetail());
            pstmt.setString(9, estimate.getEstimatePortfolio());
            pstmt.setString(10, estimate.getBusinessRegistrationNumber());
            pstmt.setString(11, estimate.getEstimateStatus());
            pstmt.setString(12, estimate.getEstimateViews());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int estimateNo = rs.getInt(1);
                    estimate.setEstimateNo(estimateNo);
                }
                rs.close();
            }

            return result;
        }
    }

    public int insertAttachment(Connection conn, List<AttachmentVo> attachmentList) throws Exception {
        String sql = "INSERT INTO ATTACHMENT (ATTACHMENT_NO, ESTIMATE_NO, ATTACHMENT_INSERT_TIME, ATTACHMENT_ORIGIN_NAME, ATTACHMENT_SERVER_NAME, ATTACHMENT_TYPE) VALUES (SEQ_ATTACHMENT.NEXTVAL, ?, SYSDATE, ?, ?, ?)";

        try (
        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (AttachmentVo attachment : attachmentList) {
                pstmt.setInt(1, attachment.getEstimateNo());
                pstmt.setString(2, attachment.getAttachmentOriginName());
                pstmt.setString(3, attachment.getAttachmentServerName());
                pstmt.setString(4, attachment.getAttachmentType());
                pstmt.addBatch();
            }

            int[] batchResult = pstmt.executeBatch();
            return Arrays.stream(batchResult).sum();
        }
    }
}



