package com.team4.skillmarket.expert.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.expert.vo.CareerVo;
import com.team4.skillmarket.expert.vo.EducationVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.vo.MemberVo;

public class ExpertDao {

	public List<EstimateCategoryVo> getSelectCategory(Connection conn) throws Exception {
		
		String sql = "SELECT ESTIMATE_CAT_NO, ESTIMATE_CAT_NAME FROM ESTIMATE_CAT WHERE ESTIMATE_CAT_SCOPE = '1'";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<EstimateCategoryVo> categoryList = new ArrayList<>();
		while(rs.next()) {
			String estimateCatNo = rs.getString("ESTIMATE_CAT_NO");
			String estimateCatName = rs.getString("ESTIMATE_CAT_NAME");
			
			EstimateCategoryVo vo = new EstimateCategoryVo();
			
			vo.setEstimateCatNo(estimateCatNo);
			vo.setEstimateCatName(estimateCatName);
			
			categoryList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return categoryList;
		
	}

	public int register(Connection conn, ExpertVo ev) throws Exception {
		
		String sql = "INSERT INTO FREELANCER( FREELANCER_NO , MEMBER_NO , FREELANCER_CLASS_NO , FREELANCER_INRODUCTION , FIELD_OF_EXPERTISE , FREELANCER_CONTACT_TIME) VALUES( SEQ_FREELANCER.NEXTVAL , ? , 1 , ? , ? , ? )";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ev.getMemberNo());
		pstmt.setString(2, ev.getFreelancerInroduction());
		pstmt.setString(3, ev.getFieldOfExpertise());
		pstmt.setString(4, ev.getFreelancerContactTime());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int insertCareer(Connection conn, String during, String[] careerList) throws Exception {
		
		String sql = "INSERT ALL";
		for(String career : careerList) {
			String[] cList = career.split(" ");
			sql += " INTO CAREER( CAREER_NO , FREELANCER_NO , CAREER_DATE , CAREER_COMPANY , CAREER_DEPT , CAREER_RESP , CAREER_LOCATION , CAREER_EMP_DATE ) VALUES( (SELECT GET_CAREER_SEQ FROM DUAL) , SEQ_FREELANCER.CURRVAL , '" + during + "' , '" + cList[0] + "' , '" + cList[1] + "' , '" + cList[2] + "' , '" + cList[3] + "' , '" + cList[4] + "' )";
		}
		sql += "SELECT 1 FROM DUAL";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int result2 = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result2;
	}
	
	public int updateCareer(Connection conn, ExpertVo loginExpert, String during, String[] careerList) throws Exception {
		
		String sql = "INSERT ALL";
		for(String career : careerList) {
			String[] cList = career.split(" ");
			sql += " INTO CAREER( CAREER_NO , FREELANCER_NO , CAREER_DATE , CAREER_COMPANY , CAREER_DEPT , CAREER_RESP , CAREER_LOCATION , CAREER_EMP_DATE ) VALUES( (SELECT GET_CAREER_SEQ FROM DUAL) , '" + loginExpert.getFreelancerNo() + "' , '" + during + "' , '" + cList[0] + "' , '" + cList[1] + "' , '" + cList[2] + "' , '" + cList[3] + "' , '" + cList[4] + "' )";
		}
		sql += "SELECT 1 FROM DUAL";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int result2 = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result2;
	}

	public int insertEducation(Connection conn, String[] educationList) throws Exception {
		String sql = "INSERT ALL";
		for(String education : educationList) {
			String[] eList = education.split(" ");
			sql += " INTO EDUCATION( EDU_NO , FREELANCER_NO , EDU_SCH , EDU_DEP , EDU_STATUS ) VALUES( (SELECT GET_EDUCATION_SEQ FROM DUAL) , SEQ_FREELANCER.CURRVAL , '" + eList[0] + "' , '" + eList[1] + "' , '" + eList[2] + "' )";
		}
		sql += "SELECT 1 FROM DUAL";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int result3 = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result3;
	}
	
	public int updateEducation(Connection conn, ExpertVo loginExpert, String[] educationList) throws Exception {
		String sql = "INSERT ALL";
		for(String education : educationList) {
			String[] eList = education.split(" ");
			sql += " INTO EDUCATION( EDU_NO , FREELANCER_NO , EDU_SCH , EDU_DEP , EDU_STATUS ) VALUES( (SELECT GET_EDUCATION_SEQ FROM DUAL) , '" + loginExpert.getFreelancerNo() + "' , '" + eList[0] + "' , '" + eList[1] + "' , '" + eList[2] + "' )";
		}
		sql += "SELECT 1 FROM DUAL";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int result3 = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result3;
	}
	
	public int changeProfile(Connection conn, MemberVo mv) throws Exception {
		
		String sql = "UPDATE MEMBER SET MEMBER_PROFILE_PHOTO = ? WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mv.getMemberProfilePhoto());
		pstmt.setString(2, mv.getMemberNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public List<CareerVo> getCareer(Connection conn, ExpertVo loginExpert) throws Exception {
		
		String sql = "SELECT * FROM CAREER WHERE FREELANCER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<CareerVo> careerList = new ArrayList<>();
		while(rs.next()) {
			String careerNo = rs.getString("CAREER_NO");
			String freelancerNo = rs.getString("FREELANCER_NO");
			String careerDate = rs.getString("CAREER_DATE");
			String careerCompany = rs.getString("CAREER_COMPANY");
			String careerDept = rs.getString("CAREER_DEPT");
			String careerResp = rs.getString("CAREER_RESP");
			String careerLocation = rs.getString("CAREER_LOCATION");
			String careerEmpDate = rs.getString("CAREER_EMP_DATE");
			
			CareerVo vo = new CareerVo();
			
			vo.setCareerNo(careerNo);
			vo.setFreelancerNo(freelancerNo);
			vo.setCareerDate(careerDate);
			vo.setCareerCompany(careerCompany);
			vo.setCareerDept(careerDept);
			vo.setCareerResp(careerResp);
			vo.setCareerLocation(careerLocation);
			vo.setCareerEmpDate(careerEmpDate);
			
			careerList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return careerList;
	}

	public List<EducationVo> getEducation(Connection conn, ExpertVo loginExpert) throws Exception {
		
		String sql = "SELECT * FROM EDUCATION WHERE FREELANCER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<EducationVo> educationList = new ArrayList<>();
		while(rs.next()) {
			String eduNo = rs.getString("EDU_NO");
			String freelancerNo = rs.getString("FREELANCER_NO");
			String eduSch = rs.getString("EDU_SCH");
			String eduDep = rs.getString("EDU_DEP");
			String eduStatus = rs.getString("EDU_STATUS");
			
			EducationVo vo = new EducationVo();
			
			vo.setEduNo(eduNo);
			vo.setFreelancerNo(freelancerNo);
			vo.setEduSch(eduSch);
			vo.setEduDep(eduDep);
			vo.setEduStatus(eduStatus);

			educationList.add(vo);
		}
	
		return educationList;
	}

	public int updateExpertInfo(Connection conn, ExpertVo ev) throws Exception {
		
		String sql = "UPDATE FREELANCER SET FREELANCER_INRODUCTION = ?, FIELD_OF_EXPERTISE = ?, FREELANCER_CONTACT_TIME = ? WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ev.getFreelancerInroduction());
		pstmt.setString(2, ev.getFieldOfExpertise());
		pstmt.setString(3, ev.getFreelancerContactTime());
		pstmt.setString(4, ev.getMemberNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}


	public int deleteCareer(Connection conn, ExpertVo loginExpert) throws Exception {
		
		String sql = "DELETE FROM CAREER WHERE FREELANCER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
		
		
	}

	public int deleteEducation(Connection conn, ExpertVo loginExpert) throws Exception {
		
		String sql = "DELETE FROM EDUCATION WHERE FREELANCER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;		
	}

	public ExpertVo getGrade(Connection conn, ExpertVo loginExpert) throws Exception {
		
		String sql = "SELECT FREELANCER_CLASS_NAME FROM FREELANCER F JOIN FREELANCER_CLASS C ON F.FREELANCER_CLASS_NO = C.FREELANCER_CLASS_NO WHERE FREELANCER_NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		
		ResultSet rs = pstmt.executeQuery();
		
		ExpertVo evo = null;
		if(rs.next()) {
			String freelancerClassName = rs.getString("FREELANCER_CLASS_NAME");
			
			evo = new ExpertVo();
			
			evo.setFreelancerClassName(freelancerClassName);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return evo;
	
	}
	
	

}
