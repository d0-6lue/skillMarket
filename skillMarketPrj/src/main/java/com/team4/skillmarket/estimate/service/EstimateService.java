package com.team4.skillmarket.estimate.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.estimate.dao.EstimateDao;
import com.team4.skillmarket.estimate.faq.dao.EstimateFaqDao;
import com.team4.skillmarket.estimate.option.dao.EstimateOptionDao;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateFaqVo;
import com.team4.skillmarket.estimate.vo.EstimateOptionVo;
import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.utill.file.AttachmentVo;

public class EstimateService {
	private EstimateDao estimateDao;
    private EstimateOptionDao estimateOptionDao;
    private EstimateFaqDao estimateFaqDao;

    public EstimateService() {
        estimateDao = new EstimateDao();
        estimateOptionDao = new EstimateOptionDao();
        estimateFaqDao = new EstimateFaqDao();
    };

    public int writeEstimate(EstimateVo estimateVo, List<EstimateOptionVo> estimateOptions, List<EstimateFaqVo> estimateFaqs) {
        try (Connection conn = JDBCTemplate.getConnection()) {
            conn.setAutoCommit(false);

            try {
                int estimateNo = estimateDao.insertEstimate(conn, estimateVo);

                if (estimateNo > 0) {


                    for (EstimateOptionVo option : estimateOptions) {
                        option.setEstimateNo(estimateNo);
                        estimateOptionDao.insertEstimateOption(conn, option);
                    }

                    for (EstimateFaqVo faq : estimateFaqs) {
                        faq.setEstimateNo(estimateNo);
                        estimateFaqDao.insertEstimateFaq(conn, faq);
                    }

                    conn.commit();
                    return estimateNo;
                } else {
                    throw new IllegalStateException("Failed to write the estimate...");
                }
            } catch (Exception e) {
                conn.rollback();
                throw new IllegalStateException("Failed to write the estimate...", e);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Failed to write the estimate...", e);
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

    //견적서 갯수가져오기
	public int getEstimateListCnt(String searchType, String searchValue ) throws Exception {
Connection conn = JDBCTemplate.getConnection();
		
		int cnt = estimateDao.EstimateBoardCnt(conn);
		
		JDBCTemplate.close(conn);
		
		return cnt;
	}

	
	//견적서 카테고리얻기
	public List<EstimateCategoryVo> getCategoryList() throws Exception {
		//conn 얻기
		Connection conn = JDBCTemplate.getConnection();
		
		
		List<EstimateCategoryVo> estimateCategoryList = estimateDao.selectCatList(conn);
		
		JDBCTemplate.close(conn);
		
		return estimateCategoryList;
		
	}

	public List<EstimateVo> getEstimateList(PageVo pv, String categoryNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateVo> list =  estimateDao.selectEstimateList(conn, pv, categoryNo);
		
		JDBCTemplate.close(conn);
		
		return list;
		
	}
    


  

}



    

    
 

	

