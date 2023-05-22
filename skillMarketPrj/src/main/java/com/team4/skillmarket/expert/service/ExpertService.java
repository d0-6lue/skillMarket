package com.team4.skillmarket.expert.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.expert.dao.ExpertDao;
import com.team4.skillmarket.expert.vo.CareerVo;
import com.team4.skillmarket.expert.vo.EducationVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.vo.MemberVo;

public class ExpertService {
	private final ExpertDao dao = new ExpertDao();

	public List<EstimateCategoryVo> getSelectCategory() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateCategoryVo> categoryList = dao.getSelectCategory(conn);
		
		JDBCTemplate.close(conn);
		
		return categoryList;
		
	}

	public int register(ExpertVo ev, String during, String[] careerList, String[] educationList, MemberVo mv) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.register(conn, ev);
		int result2 = 1;
		if(careerList.length > 0) {
			result2 = dao.insertCareer(conn, during, careerList);
		}
		
		int result3 = 1;
		if(educationList.length > 0) {
			result3 = dao.insertEducation(conn, educationList);
		}
		
		int result4 = 1;
		if(mv.getMemberProfilePhoto() != null ) {
			result4 = dao.changeProfile(conn, mv);
		}
		
		if(result == 1 && result2 > 0 && result3 > 0 && result4 > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return  result;
	}
	
	public int changeProfile(MemberVo mv) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changeProfile(conn, mv);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public List<CareerVo> getCareer(ExpertVo loginExpert) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<CareerVo> careerList = dao.getCareer(conn, loginExpert);
		
		JDBCTemplate.close(conn);
		
		return careerList;
	}

	public List<EducationVo> getEducation(ExpertVo loginExpert) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<EducationVo> educationList = dao.getEducation(conn, loginExpert);
		
		JDBCTemplate.close(conn);
		
		return educationList;
	}

	public int updateExpertInfo(ExpertVo ev, String during, ExpertVo loginExpert, String[] careerList, String[] educationList) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.updateExpertInfo(conn, ev);
		int result2 = 1;
		if(careerList.length > 0) {
			dao.deleteCareer(conn, loginExpert);
			result2 = dao.updateCareer(conn, loginExpert, during, careerList);
		}
		
		int result3 = 1;
		if(educationList.length > 0) {
			dao.deleteEducation(conn, loginExpert);
			result3 = dao.updateEducation(conn, loginExpert, educationList);
		}
		
		if(result == 1 && result2 > 0 && result3 > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return  result;
		
	}
	
	
}
