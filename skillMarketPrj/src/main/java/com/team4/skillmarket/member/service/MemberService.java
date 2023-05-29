package com.team4.skillmarket.member.service;

import java.sql.Connection;
import java.util.List;

import com.team4.skillmarket.cash.vo.CashVo;
import com.team4.skillmarket.cashlog.vo.CashLogVo;
import com.team4.skillmarket.cashlog.vo.CashSearchVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateViewVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.dao.MemberDao;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.order.vo.QuotationSerachVo;
import com.team4.skillmarket.order.vo.QuotationViewVo;

public class MemberService {
	private final MemberDao dao = new MemberDao();

	public MemberVo login(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberVo loginMember = dao.login(conn, vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
		
		
	}

	public int join(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.join(conn, vo);
		int result2 = dao.cashJoin(conn);
		
		if(result == 1 && result2 == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int checkId(String memberId) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.checkId(conn, memberId);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int checkNick(String memberNick) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.checkNick(conn, memberNick);
		
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int checkEmail(String memberEmail) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.checkEmail(conn, memberEmail);
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ExpertVo searchExpertInfo(MemberVo loginMember) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ExpertVo loginExpert = dao.searchExpertInfo(conn, loginMember);
		
		JDBCTemplate.close(conn);
		
		return loginExpert;
		
	}

	public List<EstimateCategoryVo> searchCategoryJoin() throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		List<EstimateCategoryVo> categoryList =  dao.searchCategoryJoin(conn);
		
		JDBCTemplate.close(conn);
		
		return categoryList;
	}

	public int changeInfo(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changeInfo(conn,vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public MemberVo getMyInfo(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		MemberVo loginMember= dao.getMyInfo(conn, vo);
		
		JDBCTemplate.close(conn);
		
		return loginMember;
	}

	public int changePwd(MemberVo vo) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.changePwd(conn,vo);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteMember(String no) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = dao.deleteMember(conn, no);
		
		if(result == 1) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public CashVo getCash(MemberVo loginMember) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		CashVo cashVo = dao.getCash(conn, loginMember);
		
		JDBCTemplate.close(conn);
		
		return cashVo;
		
	}

	public List<CashLogVo> getCashLogList(MemberVo loginMember) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<CashLogVo> cashList = dao.getCashLogList(conn, loginMember);
		
		JDBCTemplate.close(conn);
		
		return cashList;
	}

	public List<CashLogVo> getSearchCashLogList(CashSearchVo csv) throws Exception {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<CashLogVo> cashList = dao.getSearchCashLogList(conn,csv);

		JDBCTemplate.close(conn);
		
		return cashList;
	}

	public List<QuotationViewVo> getOrderList(MemberVo loginMember) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<QuotationViewVo> orderList = dao.getOrderList(conn,loginMember);

		JDBCTemplate.close(conn);
		
		return orderList;
	}

	public List<QuotationViewVo> getSearchOrderList(QuotationSerachVo qvo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<QuotationViewVo> searchList = dao.getSearchOrderList(conn, qvo);

		JDBCTemplate.close(conn);
		
		return searchList;
	}

	public List<QuotationViewVo> getSaleList(ExpertVo loginExpert) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<QuotationViewVo> saleList = dao.getSaleList(conn,loginExpert);

		JDBCTemplate.close(conn);
		
		return saleList;
	}

	public List<QuotationViewVo> getSearchSaleList(QuotationSerachVo qvo) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<QuotationViewVo> searchList = dao.getSearchSaleList(conn, qvo);

		JDBCTemplate.close(conn);
		
		return searchList;
		
	}

	public List<EstimateViewVo> getEstimateList(ExpertVo loginExpert) throws Exception {
		Connection conn = JDBCTemplate.getConnection();
		
		List<EstimateViewVo> estimateList = dao.getEstimateList(conn, loginExpert);

		JDBCTemplate.close(conn);
		
		return estimateList;
		
	}

	

}
