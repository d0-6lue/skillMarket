package com.team4.skillmarket.estimate.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.dao.EstimateDao;
import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.utill.file.AttachmentVo;

public class EstimateService {
    private EstimateDao estimateDao;

    public EstimateService() {
        this.estimateDao = new EstimateDao();
    }

    public int writeEstimate(EstimateVo estimate) {
        try (Connection conn = JDBCTemplate.getConnection()) {
            conn.setAutoCommit(false);

            int estimateResult = estimateDao.writeEstimate(conn, estimate);

            if (estimateResult > 0) {
                int estimateNo = estimate.getEstimateNo();
                List<AttachmentVo> attachments = estimate.getAttachments();

                if (attachments != null && !attachments.isEmpty()) {
                    for (AttachmentVo attachment : attachments) {
                        attachment.setEstimateNo(estimateNo);
                    }

                    int attachmentResult = estimateDao.insertAttachment(conn, attachments);

                    if (attachmentResult < 1) {
                        JDBCTemplate.rollback(conn);
                        return attachmentResult;
                    }
                }

                JDBCTemplate.commit(conn);
                return estimateResult;
            } else {
                JDBCTemplate.rollback(conn);
                return estimateResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}



    

    
 

	

