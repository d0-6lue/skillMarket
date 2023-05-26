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
	            "ESTIMATE_LINE_INTRODUCTION, ESTIMATE_PRICE, ESTIMATE_DETAIL, ESTIMATE_ENROLL_DATE, ESTIMATE_THUMBNAIL, ESTIMATE_DETAIL_FILE) " +
	            "VALUES (SEQ_ESTIMATE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, sysdate, ?, ?)";

	    try (PreparedStatement pstmt = conn.prepareStatement(sql, new String[]{"ESTIMATE_NO"})) {
	        pstmt.setString(1, estimate.getFreelancerNo());
	        pstmt.setString(2, estimate.getEstimateCatNo());
	        pstmt.setString(3, estimate.getEstimateTitle());
	        pstmt.setString(4, estimate.getEstimateDuration());
	        pstmt.setString(5, estimate.getEstimateLineIntroduction());
	        pstmt.setString(6, estimate.getEstimatePrice());
	        pstmt.setString(7, estimate.getEstimateDetail());
	        pstmt.setString(8, estimate.getMainImage());
	        pstmt.setString(9, estimate.getSubImage());

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
                    estimate.setEstimateNo(rs.getString("ESTIMATE_NO"));
                    estimate.setFreelancerNo(rs.getString("FREELANCER_NO"));
                    estimate.setEstimateCatNo(rs.getString("ESTIMATE_CAT_NO"));
                    estimate.setEstimateTitle(rs.getString("ESTIMATE_TITLE"));
                    estimate.setEstimateDuration(rs.getString("ESTIMATE_DURATION"));
                    estimate.setEstimateLineIntroduction(rs.getString("ESTIMATE_LINE_INTRODUCTION"));
                    estimate.setEstimatePrice(rs.getString("ESTIMATE_PRICE"));
                    estimate.setEstimateDetail(rs.getString("ESTIMATE_DETAIL"));
             

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
            pstmt.setString(1, estimate.getFreelancerNo());
            pstmt.setString(2, estimate.getEstimateCatNo());
            pstmt.setString(3, estimate.getEstimateTitle());
            pstmt.setString(4, estimate.getEstimateDuration());
            pstmt.setString(5, estimate.getEstimateLineIntroduction());
            pstmt.setString(6, estimate.getEstimatePrice());
            pstmt.setString(7, estimate.getEstimateDetail());
	        int result = pstmt.executeUpdate();


            return result;
        }
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



	public List<EstimateVo> selectEstimateList(Connection conn, PageVo pv, String categoryNo ) {
	    String sql = "SELECT * FROM ( SELECT e.ESTIMATE_NO, e.ESTIMATE_TITLE, e.ESTIMATE_PRICE, e.ESTIMATE_ENROLL_DATE, e.ESTIMATE_THUMBNAIL, e.ESTIMATE_LINE_INTRODUCTION, m.MEMBER_NICK, COUNT(r.REVIEW_NO) AS REVIEW_COUNT, f.FREELANCER_NO, ROW_NUMBER() OVER (ORDER BY e.ESTIMATE_NO DESC) AS rn FROM estimate e JOIN freelancer f ON e.FREELANCER_NO = f.FREELANCER_NO JOIN member m ON f.MEMBER_NO = m.MEMBER_NO LEFT JOIN review r ON e.ESTIMATE_NO = r.ESTIMATE_NO AND r.REVIEW_STATUS = 'Y' WHERE f.MEMBER_NO = m.MEMBER_NO AND e.ESTIMATE_CAT_NO = ? GROUP BY e.ESTIMATE_NO, e.ESTIMATE_TITLE, e.ESTIMATE_PRICE, e.ESTIMATE_ENROLL_DATE, e.ESTIMATE_THUMBNAIL, e.ESTIMATE_LINE_INTRODUCTION, m.MEMBER_NICK, f.FREELANCER_NO ) WHERE rn >= ? AND rn <= ?";
	    List<EstimateVo> estimateList = new ArrayList<>();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, categoryNo);
	        pstmt.setInt(2, pv.getBeginRow());
	        pstmt.setInt(3, pv.getLastRow());
	        
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            String estimateNo = rs.getString("ESTIMATE_NO");
	            String estimateTitle = rs.getString("ESTIMATE_TITLE");
	            String estimatePrice = rs.getString("ESTIMATE_PRICE");
	            String estimateEnrollDate = rs.getString("ESTIMATE_ENROLL_DATE");
	            String estimateMainImage = rs.getString("ESTIMATE_THUMBNAIL");
	            String estimateLineIntroduction = rs.getString("ESTIMATE_LINE_INTRODUCTION");
	            String memberNick = rs.getString("MEMBER_NICK");
	            String reviewCountStr = rs.getString("REVIEW_COUNT");
	            String reviewCount;
	            if (reviewCountStr == null) {
	                reviewCount = "0";
	            } else {
	                reviewCount = reviewCountStr;
	            }            
	            System.out.println(reviewCount);
	            String freelancerNo = rs.getString("FREELANCER_NO");
	            
	            EstimateVo estimateVo = new EstimateVo();
	            estimateVo.setEstimateNo(estimateNo);
	            estimateVo.setEstimateTitle(estimateTitle);
	            estimateVo.setEstimatePrice(estimatePrice);
	            estimateVo.setEstimateEnrollDate(estimateEnrollDate);
	            estimateVo.setMainImage(estimateMainImage);
	            estimateVo.setEstimateLineIntroduction(estimateLineIntroduction);
	            estimateVo.setMemberNick(memberNick);
	            estimateVo.setReviewCount(reviewCount);
	            estimateVo.setFreelancerNo(freelancerNo);
	            
	            estimateList.add(estimateVo);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JDBCTemplate.close(rs);
	        JDBCTemplate.close(pstmt);
	    }
	    
	    return estimateList;
	}



	public int EstimateBoardCnt(Connection conn) throws Exception {
		
			String sql = "SELECT COUNT(*) FROM ESTIMATE WHERE ESTIMATE_STATUS = 'Y'";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
			
			return cnt;
		}
	}



	
    





