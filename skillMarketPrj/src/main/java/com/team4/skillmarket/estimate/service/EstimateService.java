package com.team4.skillmarket.estimate.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.estimate.dao.EstimateDao;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.utill.file.AttachmentVo;

public class EstimateService {
	private EstimateDao estimateDao;

    public EstimateService() {
        estimateDao = new EstimateDao();
    };

    //견적서작성하기
    public int writeEstimate(EstimateVo estimate) throws Exception{
        try (Connection conn = JDBCTemplate.getConnection()) {

            int estimateResult = estimateDao.writeEstimate(conn, estimate);

            if (estimateResult > 0) {
                List<AttachmentVo> attachments = estimate.getAttachments();

                if (attachments != null && !attachments.isEmpty()) {
                    int attachmentResult = estimateDao.insertAttachments(conn, attachments);

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


    //견적서 번호 얻어오기
    public EstimateVo getEstimate(int estimateNo) throws Exception {
        try (Connection conn = JDBCTemplate.getConnection()) {
            return estimateDao.getEstimate(conn, estimateNo);
        }
    }


    //견적서 수정하기
    public int updateEstimate(EstimateVo estimate) throws Exception {
        try (Connection conn = JDBCTemplate.getConnection()) {
            conn.setAutoCommit(false);

            int result = estimateDao.updateEstimate(conn, estimate);

            if (result > 0) {
                conn.commit();
            } else {
                conn.rollback();
            }

            return result;
        }
    }

	public int getEstimateListCnt(String searchType, String searchValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<EstimateCategoryVo> getCategoryList() throws Exception {
		//conn 얻기
		Connection conn = JDBCTemplate.getConnection();
		
		
		List<EstimateCategoryVo> estimateCategoryList = estimateDao.selectCatList(conn);
		
		JDBCTemplate.close(conn);
		
		return estimateCategoryList;
		
	}
    


  

}



    

    
 

	

