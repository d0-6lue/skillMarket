package com.team4.skillmarket.admin.FAQ.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.admin.FAQ.dao.AdminFAQDao;
import com.team4.skillmarket.admin.FAQ.vo.AdminFAQVo;
import com.team4.skillmarket.admin.category.dao.AdminCatagoryDao;
import com.team4.skillmarket.admin.category.vo.AdminCategoryVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
	
	
	public class AdminFAQService {
	
		private final AdminFAQDao dao = new AdminFAQDao();
		
		public List<AdminFAQVo> selectFAQList() throws Exception {
	
			Connection conn = JDBCTemplate.getConnection();
			
			List<AdminFAQVo> FAQArrList =  dao.selectFAQList(conn);
			
			JDBCTemplate.close(conn);
			
			return FAQArrList;
			
		}
	
		public AdminFAQVo gerHitFAQ() throws Exception {
	
			Connection conn = JDBCTemplate.getConnection();
			
			
			AdminFAQVo FAQHit =  dao.gerHitFAQ(conn);
			
			JDBCTemplate.close(conn);
			
			return FAQHit;
		}
	
		
		
		public int editFAQ(AdminFAQVo vo) throws Exception {
			
			Connection conn = JDBCTemplate.getConnection();
			
			int result = dao.editFAQ(conn,vo);
			
			if (result == 1) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}
			
			JDBCTemplate.close(conn);
			
			return result;
		}

		public AdminFAQVo updateFAQList(AdminFAQVo vo) throws Exception {

			Connection conn = JDBCTemplate.getConnection();
			
			AdminFAQVo updateFAQList = dao.updateFAQList(conn,vo);
			
			JDBCTemplate.close(conn);
			
			System.out.println("updateFAQList : " + updateFAQList);
			
			return updateFAQList;
		}

}
