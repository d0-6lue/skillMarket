package com.team4.skillmarket.estimate.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.common.page.PageVo;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateVo;
import com.team4.skillmarket.utill.file.AttachmentVo;

//견적서 글쓰기
public class EstimateDao {
	
	public int insertEstimate(Connection conn, EstimateVo estimate) throws SQLException {
	    String sql = "INSERT INTO ESTIMATE (ESTIMATE_NO, FREELANCER_NO, ESTIMATE_CAT_NO, ESTIMATE_TITLE, ESTIMATE_DURATION, " +
	            "ESTIMATE_LINE_INTRODUCTION, ESTIMATE_PRICE, ESTIMATE_DETAIL, ESTIMATE_ENROLL_DATE, ESTIMATE_MAIN_IMAGE, ESTIMATE_DETAIL_IMAGES) " +
	            "VALUES (SEQ_ESTIMATE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"ESTIMATE_NO"})) {
	        pstmt.setInt(1, estimate.getFreelancerNo());
	        pstmt.setString(2, estimate.getEstimateCatNo());
	        pstmt.setString(3, estimate.getEstimateTitle());
	        pstmt.setString(4, estimate.getEstimateDuration());
	        pstmt.setString(5, estimate.getEstimateLineIntroduction());
	        pstmt.setString(6, estimate.getEstimatePrice());
	        pstmt.setString(7, estimate.getEstimateDetail());
	        pstmt.setString(8, estimate.getEstimateEnrollDate());

	        if (estimate.getMainImage() != null) {
	            pstmt.setString(9, estimate.getMainImage().getAttachmentServerName());
	        } else {
	            pstmt.setNull(9, Types.VARCHAR);
	        }

	        if (!estimate.getDetailImages().isEmpty()) {
	            pstmt.setString(10, estimate.getDetailImages().get(0).getAttachmentServerName());
	        } else {
	            pstmt.setNull(10, Types.VARCHAR);
	        }

	        int result = pstmt.executeUpdate();

	        if (result > 0) {
	            ResultSet generatedKeys = pstmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                int estimateNo = generatedKeys.getInt(1);
	                return estimateNo;
	            }
	        }

	        return 0; // 실패 시 0 반환
	    }
	}












    
    //견적서 번호조회하기
    public EstimateVo getEstimate(Connection conn, int estimateNo) throws Exception {
        String sql = "SELECT * FROM ESTIMATE WHERE ESTIMATE_NO = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, estimateNo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    EstimateVo estimate = new EstimateVo();
                    estimate.setEstimateNo(rs.getInt("ESTIMATE_NO"));
                    estimate.setFreelancerNo(rs.getInt("FREELANCER_NO"));
//                    estimate.setEstimateCatNo(rs.getInt("ESTIMATE_CAT_NO"));
                    estimate.setEstimateTitle(rs.getString("ESTIMATE_TITLE"));
                    estimate.setEstimateDuration(rs.getString("ESTIMATE_DURATION"));
                    estimate.setEstimateLineIntroduction(rs.getString("ESTIMATE_LINE_INTRODUCTION"));
                    estimate.setEstimatePrice(rs.getString("ESTIMATE_PRICE"));
                    estimate.setEstimateDetail(rs.getString("ESTIMATE_DETAIL"));
                    estimate.setAttachmentPaths(rs.getString("ATTACHMENT_PATHS") != null
                            ? List.of(rs.getString("ATTACHMENT_PATHS").split(","))
                            : new ArrayList<>());

                    return estimate;
                } else {
                    return null;
                }
            }
        }
    }

    //견적서 수정해주기
    public int updateEstimate(Connection conn, EstimateVo estimate) throws Exception {
        String sql = "UPDATE ESTIMATE SET FREELANCER_NO = ?, ESTIMATE_CAT_NO = ?, ESTIMATE_TITLE = ?, "
                + "ESTIMATE_DURATION = ?, ESTIMATE_LINE_INTRODUCTION = ?, ESTIMATE_PRICE = ?, "
                + "ESTIMATE_DETAIL = ?, ATTACHMENT_PATHS = ? WHERE ESTIMATE_NO = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, estimate.getFreelancerNo());
            pstmt.setInt(2, estimate.getEstimateCatNo());
            pstmt.setString(3, estimate.getEstimateTitle());
            pstmt.setString(4, estimate.getEstimateDuration());
            pstmt.setString(5, estimate.getEstimateLineIntroduction());
            pstmt.setString(6, estimate.getEstimatePrice());
            pstmt.setString(7, estimate.getEstimateDetail());
            pstmt.setString(8, String.join(",", estimate.getAttachmentPaths()));
            pstmt.setInt(9, estimate.getEstimateNo());
	        int result = pstmt.executeUpdate();


            return result;
        }
    }
    
    //견적서 목록 조회하기
	public List<EstimateVo> getEstimateList(Connection conn, PageVo pv) throws Exception{
		
		//SQL
		String sql = "SELECT E.ESTIMATE_NO, E.ESTIMATE_TITLE, E.ESTIMATE_THUMBNAIL, E.ESTIMATE_PRICE, COUNT(R.REVIEW_NO) AS REVIEW_COUNT " +
	             "FROM ESTIMATE E " +
	             "JOIN FREELANCER F ON E.FREELANCER_NO = F.FREELANCER_NO " +
	             "LEFT JOIN REVIEW R ON E.ESTIMATE_NO = R.ESTIMATE_NO " +
	             "GROUP BY E.ESTIMATE_NO, E.ESTIMATE_TITLE, E.ESTIMATE_THUMBNAIL, E.ESTIMATE_PRICE";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs= pstmt.executeQuery();
		
		// tx||rs
		List<EstimateVo> estimateList = new ArrayList<>();
		while (rs.next()) {
		    int estimateNo = rs.getInt("ESTIMATE_NO");
		    String estimateTitle = rs.getString("ESTIMATE_TITLE");
		    String estimateThumbnail = rs.getString("ESTIMATE_THUMBNAIL");
		    String estimatePrice = rs.getString("ESTIMATE_PRICE");
		    int reviewCount = rs.getInt("REVIEW_COUNT");

		    // 데뭉
		    EstimateVo estimate = new EstimateVo();
		    estimate.setEstimateNo(estimateNo);
		    estimate.setEstimateTitle(estimateTitle);
		    estimate.setEstimatePrice(estimatePrice);

		    estimateList.add(estimate);
		}

		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		return estimateList;
	}

	//카테고리 가져오기
	public List<EstimateCategoryVo> selectCatList(Connection conn) throws Exception {

		String sql = "SELECT ESTIMATE_CAT_NO,ABOVE_CAT_NO,ESTIMATE_CAT_NAME,ESTIMATE_CAT_SCOPE FROM ESTIMATE_CAT";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<EstimateCategoryVo> catArrList = new ArrayList<>();
		while (rs.next()) {
			
			String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
			String aboveCatNo = rs.getString("ABOVE_CAT_NO");
			String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
			String estimateCatScope = rs.getString("ESTIMATE_CAT_SCOPE");
			
			EstimateCategoryVo vo = new EstimateCategoryVo();
			vo.setEstimateCatNo(estimateCatNo);
			vo.setAboveCatNo(aboveCatNo);
			vo.setEstimateCatName(estimateCatName);
			vo.setEstimateCatScope(estimateCatScope);
			
			catArrList.add(vo);
			
		}
		return catArrList;
	}

	public int insertEstimate(EstimateVo estimate) {
		// TODO Auto-generated method stub
		return 0;
	}
    
}




