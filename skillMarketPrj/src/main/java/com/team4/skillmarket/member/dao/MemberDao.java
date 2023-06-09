package com.team4.skillmarket.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.cash.vo.CashVo;
import com.team4.skillmarket.cashlog.vo.CashLogVo;
import com.team4.skillmarket.cashlog.vo.CashSearchVo;
import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.estimate.vo.EstimateCategoryVo;
import com.team4.skillmarket.estimate.vo.EstimateViewVo;
import com.team4.skillmarket.expert.vo.ExpertVo;
import com.team4.skillmarket.member.vo.MemberVo;
import com.team4.skillmarket.order.vo.QuotationSerachVo;
import com.team4.skillmarket.order.vo.QuotationViewVo;

public class MemberDao {

	public MemberVo login(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ? AND STATUS_NO = 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberId());
		pstmt.setString(2, vo.getMemberPwd());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember = null;
		
		if(rs.next()) {
			
			String memberNo = rs.getString("MEMBER_NO");
			String statusNo = rs.getString("STATUS_NO");
			String memberName = rs.getString("MEMBER_NAME");
			String memberId = rs.getString("MEMBER_ID");
			String memberPwd = rs.getString("MEMBER_PWD");
			String memberNick = rs.getString("MEMBER_NICK");
			String memberPhone = rs.getString("MEMBER_PHONE");
			String memberEmail = rs.getString("MEMBER_EMAIL");
			String memberInterst = rs.getString("MEMBER_INTERST");
			String memberSignDate = rs.getString("MEMBER_SIGN_DATE");
			String memberModifiDate = rs.getString("MEMBER_MODIFI_DATE");
			String memberBank = rs.getString("MEMBER_BANK");
			String memberAccount = rs.getString("MEMBER_ACCOUNT");
			String memberCash = rs.getString("MEMBER_CASH");
			String memberProfilePhoto = rs.getString("MEMBER_PROFILE_PHOTO");
			String memberNickStatus = rs.getString("MEMBER_NICK_STATUS");
			String memberAddress = rs.getString("MEMBER_ADDRESS");
			
			loginMember = new MemberVo();
			
			loginMember.setMemberNo(memberNo);
			loginMember.setStatusNo(statusNo);
			loginMember.setMemberName(memberName);
			loginMember.setMemberId(memberId);
			loginMember.setMemberPwd(memberPwd);
			loginMember.setMemberNick(memberNick);
			loginMember.setMemberPhone(memberPhone);
			loginMember.setMemberEmail(memberEmail);
			loginMember.setMemberInterst(memberInterst);
			loginMember.setMemberSignDate(memberSignDate);
			loginMember.setMemberModifiDate(memberModifiDate);
			loginMember.setMemberBank(memberBank);
			loginMember.setMemberAccount(memberAccount);
			loginMember.setMemberCash(memberCash);
			loginMember.setMemberProfilePhoto(memberProfilePhoto);
			loginMember.setMemberNickStatus(memberNickStatus);
			loginMember.setMemberAddress(memberAddress);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return loginMember;
		
		
	}
	
	public int join(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "INSERT INTO MEMBER(MEMBER_NO, MEMBER_NAME, MEMBER_ID, MEMBER_PWD, MEMBER_NICK, MEMBER_PHONE, MEMBER_EMAIL, MEMBER_INTERST, MEMBER_BANK, MEMBER_ACCOUNT, MEMBER_ADDRESS) VALUES(SEQ_MEMBER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberName());
		pstmt.setString(2, vo.getMemberId());
		pstmt.setString(3, vo.getMemberPwd());
		pstmt.setString(4, vo.getMemberNick());
		pstmt.setString(5, vo.getMemberPhone());
		pstmt.setString(6, vo.getMemberEmail());
		pstmt.setString(7, vo.getMemberInterst());
		pstmt.setString(8, vo.getMemberBank());
		pstmt.setString(9, vo.getMemberAccount());
		pstmt.setString(10, vo.getMemberAddress());
		int result = pstmt.executeUpdate();
		
		System.out.println(vo.getMemberInterst());
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int checkId(Connection conn, String memberId) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberId);
		ResultSet rs = pstmt.executeQuery();
		
		int result = 0;
		if(rs.next()) {
			result = 0;
		}else {
			result = 1;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int checkNick(Connection conn, String memberNick) throws Exception {

		String sql = "SELECT * FROM MEMBER WHERE MEMBER_NICK = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberNick);
		ResultSet rs = pstmt.executeQuery();
		
		int result = 0;
		if(rs.next()) {
			result = 0;
		}else {
			result = 1;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public int checkEmail(Connection conn, String memberEmail) throws Exception {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_EMAIL = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberEmail);
		ResultSet rs = pstmt.executeQuery();
		
		int result = 0;
		if(rs.next()) {
			result = 0;
		}else {
			result = 1;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public ExpertVo searchExpertInfo(Connection conn, MemberVo loginMember) throws Exception {
		
		String sql = "SELECT * FROM FREELANCER WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getMemberNo());
		
		ResultSet rs = pstmt.executeQuery();
		
		ExpertVo loginExpert = null;
		if(rs.next()) {
			String freelancerNo = rs.getString("FREELANCER_NO");
			String memberNo = rs.getString("MEMBER_NO");
			String freelancerClassNo = rs.getString("FREELANCER_CLASS_NO");
			String freelancerInroduction = rs.getString("FREELANCER_INRODUCTION");
			String fieldOfExpertise = rs.getString("FIELD_OF_EXPERTISE");
			String freelancerContactTime = rs.getString("FREELANCER_CONTACT_TIME");
			String freelancerStatus = rs.getString("FREELANCER_STATUS");
			
			loginExpert = new ExpertVo();
			
			loginExpert.setFreelancerNo(freelancerNo);
			loginExpert.setMemberNo(memberNo);
			loginExpert.setFreelancerClassNo(freelancerClassNo);
			loginExpert.setFreelancerInroduction(freelancerInroduction);
			loginExpert.setFieldOfExpertise(fieldOfExpertise);
			loginExpert.setFreelancerContactTime(freelancerContactTime);
			loginExpert.setFreelancerStatus(freelancerStatus);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return loginExpert;
		
	}

	public List<EstimateCategoryVo> searchCategoryJoin(Connection conn) throws Exception {
		
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

	public int changeInfo(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "UPDATE MEMBER SET MEMBER_NICK = ?, MEMBER_EMAIL = ?, MEMBER_PHONE = ?, MEMBER_INTERST = ?, MEMBER_BANK = ?, MEMBER_ACCOUNT = ?";
		if(vo.getMemberProfilePhoto() != null) {
			sql	+= ", MEMBER_PROFILE_PHOTO = ?";
		}
		sql += " WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNick());
		pstmt.setString(2, vo.getMemberEmail());
		pstmt.setString(3, vo.getMemberPhone());
		pstmt.setString(4, vo.getMemberInterst());
		pstmt.setString(5, vo.getMemberBank());
		pstmt.setString(6, vo.getMemberAccount());
		if(vo.getMemberProfilePhoto() != null) {
			pstmt.setString(7, vo.getMemberProfilePhoto());
			pstmt.setString(8, vo.getMemberNo());
			
		}else {
			pstmt.setString(7, vo.getMemberNo());
		}
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public MemberVo getMyInfo(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_NO = ? AND STATUS_NO = 1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberNo());
		ResultSet rs = pstmt.executeQuery();
		
		MemberVo loginMember = null;
		
		if(rs.next()) {
			
			String memberNo = rs.getString("MEMBER_NO");
			String statusNo = rs.getString("STATUS_NO");
			String memberName = rs.getString("MEMBER_NAME");
			String memberId = rs.getString("MEMBER_ID");
			String memberPwd = rs.getString("MEMBER_PWD");
			String memberNick = rs.getString("MEMBER_NICK");
			String memberPhone = rs.getString("MEMBER_PHONE");
			String memberEmail = rs.getString("MEMBER_EMAIL");
			String memberInterst = rs.getString("MEMBER_INTERST");
			String memberSignDate = rs.getString("MEMBER_SIGN_DATE");
			String memberModifiDate = rs.getString("MEMBER_MODIFI_DATE");
			String memberBank = rs.getString("MEMBER_BANK");
			String memberAccount = rs.getString("MEMBER_ACCOUNT");
			String memberCash = rs.getString("MEMBER_CASH");
			String memberProfilePhoto = rs.getString("MEMBER_PROFILE_PHOTO");
			String memberNickStatus = rs.getString("MEMBER_NICK_STATUS");
			String memberAddress = rs.getString("MEMBER_ADDRESS");
			
			loginMember = new MemberVo();
			
			loginMember.setMemberNo(memberNo);
			loginMember.setStatusNo(statusNo);
			loginMember.setMemberName(memberName);
			loginMember.setMemberId(memberId);
			loginMember.setMemberPwd(memberPwd);
			loginMember.setMemberNick(memberNick);
			loginMember.setMemberPhone(memberPhone);
			loginMember.setMemberEmail(memberEmail);
			loginMember.setMemberInterst(memberInterst);
			loginMember.setMemberSignDate(memberSignDate);
			loginMember.setMemberModifiDate(memberModifiDate);
			loginMember.setMemberBank(memberBank);
			loginMember.setMemberAccount(memberAccount);
			loginMember.setMemberCash(memberCash);
			loginMember.setMemberProfilePhoto(memberProfilePhoto);
			loginMember.setMemberNickStatus(memberNickStatus);
			loginMember.setMemberAddress(memberAddress);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return loginMember;
		
		
	}

	public int changePwd(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "UPDATE MEMBER SET MEMBER_PWD = ? WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getMemberPwd());
		pstmt.setString(2, vo.getMemberNo());
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int deleteMember(Connection conn, String no) throws Exception {
		
		String sql = "UPDATE MEMBER SET STATUS_NO = '4' WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public CashVo getCash(Connection conn, MemberVo loginMember) throws Exception {
		
		String sql = "SELECT * FROM USER_CASH WHERE MEMBER_NO = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getMemberNo());
		ResultSet rs = pstmt.executeQuery();
		
		CashVo cashVo = null;
		if(rs.next()) {
			String memberNo = rs.getString("MEMBER_NO");
			String cashPoint = rs.getString("CASH_POINT");
			String cashMoney = rs.getString("CASH_MONEY");
			
			int temp = Integer.parseInt(cashPoint) + Integer.parseInt(cashMoney);
			String total = Integer.toString(temp);
			
			cashVo = new CashVo();
			
			cashVo.setMemberNo(memberNo);
			cashVo.setCashPoint(cashPoint);
			cashVo.setCashMoney(cashMoney);
			cashVo.setCashTotal(total);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cashVo;
	}

	public int cashJoin(Connection conn) throws Exception {
		
		String sql = "INSERT INTO USER_CASH(MEMBER_NO) VALUES (SEQ_MEMBER.CURRVAL)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		int result = pstmt.executeUpdate();
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public List<CashLogVo> getCashLogList(Connection conn, MemberVo loginMember) throws Exception {
		
		String sql = "SELECT NO, MEMBER_NO, LOG_CATEGORY_NO, AMOUNT, PAYMENT_METHOD_NO, TO_CHAR(ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS ENROLL_DATE, CATEGORY_NO, CATEGORY_NAME, PAYMENT_METHOD_NAME FROM ( SELECT C.NO, C.MEMBER_NO, C.LOG_CATEGORY_NO, C.AMOUNT, C.PAYMENT_METHOD_NO, C.ENROLL_DATE, L.CATEGORY_NO, L.CATEGORY_NAME, P.PAYMENT_METHOD_NAME FROM CASH_LOG C JOIN LOG_CATEGORY L ON C.LOG_CATEGORY_NO = L.CATEGORY_NO JOIN PAYMENT_METHOD P ON C.PAYMENT_METHOD_NO = P.NO WHERE C.MEMBER_NO = ? ORDER BY ENROLL_DATE DESC)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getMemberNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<CashLogVo> cList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String memberNo = rs.getString("MEMBER_NO");
			String amount = rs.getString("AMOUNT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String categoryNo = rs.getString("CATEGORY_NO");
			String categoryName = rs.getString("CATEGORY_NAME");
			String payMentMethodName = rs.getString("PAYMENT_METHOD_NAME");
			
			CashLogVo vo = new CashLogVo();
			
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setAmount(amount);
			vo.setEnrollDate(enrollDate);
			vo.setCategoryName(categoryName);
			vo.setLogCategoryNo(categoryNo);
			vo.setPaymentMethodName(payMentMethodName);
			
			cList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cList;
	}

	public List<CashLogVo> getSearchCashLogList(Connection conn, CashSearchVo csv) throws Exception {
		
		String sql = "SELECT NO, MEMBER_NO, LOG_CATEGORY_NO, AMOUNT, PAYMENT_METHOD_NO, TO_CHAR(ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS ENROLL_DATE, CATEGORY_NO, CATEGORY_NAME, PAYMENT_METHOD_NAME FROM (SELECT C.NO, C.MEMBER_NO, C.LOG_CATEGORY_NO, C.AMOUNT , C.PAYMENT_METHOD_NO, C.ENROLL_DATE, L.CATEGORY_NO, L.CATEGORY_NAME, P.PAYMENT_METHOD_NAME FROM CASH_LOG C JOIN LOG_CATEGORY L ON C.LOG_CATEGORY_NO = L.CATEGORY_NO JOIN PAYMENT_METHOD P ON C.PAYMENT_METHOD_NO = P.NO WHERE C.MEMBER_NO = ? AND CATEGORY_NAME = ? AND ENROLL_DATE BETWEEN '"+ csv.getOrderDate1() +"-01' AND ( SELECT LAST_DAY(TO_DATE('"+ csv.getOrderDate2() + "-01', 'YYYY-MM-DD')) FROM DUAL) ORDER BY ENROLL_DATE DESC)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, csv.getMemberNo());
		pstmt.setString(2, csv.getOrderStatus());
		ResultSet rs = pstmt.executeQuery();
		
		List<CashLogVo> cList = new ArrayList<>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String memberNo = rs.getString("MEMBER_NO");
			String amount = rs.getString("AMOUNT");
			String enrollDate = rs.getString("ENROLL_DATE");
			String categoryNo = rs.getString("CATEGORY_NO");
			String categoryName = rs.getString("CATEGORY_NAME");
			String payMentMethodName = rs.getString("PAYMENT_METHOD_NAME");
			
			CashLogVo vo = new CashLogVo();
			
			vo.setNo(no);
			vo.setMemberNo(memberNo);
			vo.setAmount(amount);
			vo.setEnrollDate(enrollDate);
			vo.setCategoryName(categoryName);
			vo.setLogCategoryNo(categoryNo);
			vo.setPaymentMethodName(payMentMethodName);
			
			cList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return cList;
		
	}

	public List<QuotationViewVo> getOrderList(Connection conn, MemberVo loginMember) throws Exception {
		
		String sql = "SELECT QUOTATION_NO, TO_CHAR(QUOTATION_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS QUOTATION_ENROLL_DATE, ESTIMATE_TITLE, QUOTATION_STATUS_NAME, QUOTATION_PRICE, QUOTATION_STATUS_NO FROM ( SELECT Q.QUOTATION_NO, Q.QUOTATION_ENROLL_DATE, E.ESTIMATE_TITLE, QS.QUOTATION_STATUS_NAME, Q.QUOTATION_PRICE, Q.QUOTATION_STATUS_NO FROM QUOTATION Q JOIN ESTIMATE E ON Q.ESTIMATE_NO = E.ESTIMATE_NO JOIN QUOTATION_STATUS QS ON Q.QUOTATION_STATUS_NO = QS.QUOTATION_STATUS_NO WHERE Q.MEMBER_NO = ? ORDER BY Q.QUOTATION_ENROLL_DATE DESC)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginMember.getMemberNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<QuotationViewVo> orderList = new ArrayList<>(); 
		while(rs.next()) {
			
			String quotationNo = rs.getString("QUOTATION_NO");
			String quotationEnrollDate = rs.getString("QUOTATION_ENROLL_DATE");
			String estimateTitle = rs.getString("ESTIMATE_TITLE");
			String quotationStatusName = rs.getString("QUOTATION_STATUS_NAME");
			String quotationPrice = rs.getString("QUOTATION_PRICE");
			String quotationStatusNo = rs.getString("QUOTATION_STATUS_NO");
			
			
			QuotationViewVo vo = new QuotationViewVo();
			
			vo.setEstimateTitle(estimateTitle);
			vo.setQuotationEnrollDate(quotationEnrollDate);
			vo.setQuotationNo(quotationNo);
			vo.setQuotationPrice(quotationPrice);
			vo.setQuotationStatusName(quotationStatusName);
			vo.setQuotationStatusNo(quotationStatusNo);
			
			orderList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return orderList;
	}

	public List<QuotationViewVo> getSearchOrderList(Connection conn, QuotationSerachVo qvo) throws Exception {
		
		String sql = "SELECT QUOTATION_NO, TO_CHAR(QUOTATION_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS QUOTATION_ENROLL_DATE, ESTIMATE_TITLE, QUOTATION_STATUS_NAME, QUOTATION_PRICE, QUOTATION_STATUS_NO FROM ( SELECT Q.QUOTATION_NO, Q.QUOTATION_ENROLL_DATE, E.ESTIMATE_TITLE, QS.QUOTATION_STATUS_NAME, Q.QUOTATION_PRICE, Q.QUOTATION_STATUS_NO FROM QUOTATION Q JOIN ESTIMATE E ON Q.ESTIMATE_NO = E.ESTIMATE_NO JOIN QUOTATION_STATUS QS ON Q.QUOTATION_STATUS_NO = QS.QUOTATION_STATUS_NO WHERE Q.MEMBER_NO = ? AND QS.QUOTATION_STATUS_NAME = ? AND Q.QUOTATION_ENROLL_DATE BETWEEN '"+ qvo.getOrderDate1() +"-01' AND ( SELECT LAST_DAY(TO_DATE('"+ qvo.getOrderDate2() +"-01', 'YYYY-MM-DD')) FROM DUAL)";
		if(!"".equals(qvo.getOrderSearch())) {
			sql	+= " AND ESTIMATE_TITLE LIKE '%'||?||'%'";
		}
		sql	+= " ORDER BY Q.QUOTATION_ENROLL_DATE DESC)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, qvo.getMemberNo());
		pstmt.setString(2, qvo.getOrderStatus());
		if(!"".equals(qvo.getOrderSearch())) {
			pstmt.setString(3, qvo.getOrderSearch());
		}
		ResultSet rs = pstmt.executeQuery();
		
		List<QuotationViewVo> orderList = new ArrayList<>(); 
		while(rs.next()) {
			
			String quotationNo = rs.getString("QUOTATION_NO");
			String quotationEnrollDate = rs.getString("QUOTATION_ENROLL_DATE");
			String estimateTitle = rs.getString("ESTIMATE_TITLE");
			String quotationStatusName = rs.getString("QUOTATION_STATUS_NAME");
			String quotationPrice = rs.getString("QUOTATION_PRICE");
			String quotationStatusNo = rs.getString("QUOTATION_STATUS_NO");
			
			
			QuotationViewVo vo = new QuotationViewVo();
			
			vo.setEstimateTitle(estimateTitle);
			vo.setQuotationEnrollDate(quotationEnrollDate);
			vo.setQuotationNo(quotationNo);
			vo.setQuotationPrice(quotationPrice);
			vo.setQuotationStatusName(quotationStatusName);
			vo.setQuotationStatusNo(quotationStatusNo);
			
			orderList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return orderList;
		
	}

	public List<QuotationViewVo> getSaleList(Connection conn, ExpertVo loginExpert) throws Exception {
		
		String sql = "SELECT QUOTATION_NO, TO_CHAR(QUOTATION_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS QUOTATION_ENROLL_DATE, ESTIMATE_TITLE, QUOTATION_STATUS_NAME, QUOTATION_PRICE, QUOTATION_STATUS_NO FROM ( SELECT Q.QUOTATION_NO, Q.QUOTATION_ENROLL_DATE, E.ESTIMATE_TITLE, QS.QUOTATION_STATUS_NAME, Q.QUOTATION_PRICE, Q.QUOTATION_STATUS_NO FROM QUOTATION Q JOIN ESTIMATE E ON Q.ESTIMATE_NO = E.ESTIMATE_NO JOIN QUOTATION_STATUS QS ON Q.QUOTATION_STATUS_NO = QS.QUOTATION_STATUS_NO WHERE E.FREELANCER_NO = ? ORDER BY Q.QUOTATION_ENROLL_DATE DESC)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<QuotationViewVo> saleList = new ArrayList<>(); 
		while(rs.next()) {
			
			String quotationNo = rs.getString("QUOTATION_NO");
			String quotationEnrollDate = rs.getString("QUOTATION_ENROLL_DATE");
			String estimateTitle = rs.getString("ESTIMATE_TITLE");
			String quotationStatusName = rs.getString("QUOTATION_STATUS_NAME");
			String quotationPrice = rs.getString("QUOTATION_PRICE");
			String quotationStatusNo = rs.getString("QUOTATION_STATUS_NO");
			
			
			QuotationViewVo vo = new QuotationViewVo();
			
			vo.setEstimateTitle(estimateTitle);
			vo.setQuotationEnrollDate(quotationEnrollDate);
			vo.setQuotationNo(quotationNo);
			vo.setQuotationPrice(quotationPrice);
			vo.setQuotationStatusName(quotationStatusName);
			vo.setQuotationStatusNo(quotationStatusNo);
			
			saleList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return saleList;
	}

	public List<QuotationViewVo> getSearchSaleList(Connection conn, QuotationSerachVo qvo) throws Exception {
		String sql = "SELECT QUOTATION_NO, TO_CHAR(QUOTATION_ENROLL_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS QUOTATION_ENROLL_DATE , ESTIMATE_TITLE, QUOTATION_STATUS_NAME , QUOTATION_PRICE , QUOTATION_STATUS_NO FROM ( SELECT Q.QUOTATION_NO, Q.QUOTATION_ENROLL_DATE, E.ESTIMATE_TITLE, QS.QUOTATION_STATUS_NAME, Q.QUOTATION_PRICE, Q.QUOTATION_STATUS_NO FROM QUOTATION Q JOIN ESTIMATE E ON Q.ESTIMATE_NO = E.ESTIMATE_NO JOIN QUOTATION_STATUS QS ON Q.QUOTATION_STATUS_NO = QS.QUOTATION_STATUS_NO WHERE E.FREELANCER_NO = ? AND QS.QUOTATION_STATUS_NAME = ? AND Q.QUOTATION_ENROLL_DATE BETWEEN '"+ qvo.getOrderDate1() +"-01' AND ( SELECT LAST_DAY(TO_DATE('"+ qvo.getOrderDate2() +"-01', 'YYYY-MM-DD')) FROM DUAL)";
		if(!"".equals(qvo.getOrderSearch())) {
			sql	+= " AND ESTIMATE_TITLE LIKE '%'||?||'%'";
		}
		sql	+= " ORDER BY Q.QUOTATION_ENROLL_DATE DESC)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, qvo.getMemberNo());
		pstmt.setString(2, qvo.getOrderStatus());
		if(!"".equals(qvo.getOrderSearch())) {
			pstmt.setString(3, qvo.getOrderSearch());
		}
		ResultSet rs = pstmt.executeQuery();
		
		List<QuotationViewVo> saleList = new ArrayList<>(); 
		while(rs.next()) {
			
			String quotationNo = rs.getString("QUOTATION_NO");
			String quotationEnrollDate = rs.getString("QUOTATION_ENROLL_DATE");
			String estimateTitle = rs.getString("ESTIMATE_TITLE");
			String quotationStatusName = rs.getString("QUOTATION_STATUS_NAME");
			String quotationPrice = rs.getString("QUOTATION_PRICE");
			String quotationStatusNo = rs.getString("QUOTATION_STATUS_NO");
			
			
			QuotationViewVo vo = new QuotationViewVo();
			
			vo.setEstimateTitle(estimateTitle);
			vo.setQuotationEnrollDate(quotationEnrollDate);
			vo.setQuotationNo(quotationNo);
			vo.setQuotationPrice(quotationPrice);
			vo.setQuotationStatusName(quotationStatusName);
			vo.setQuotationStatusNo(quotationStatusNo);
			
			saleList.add(vo);
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return saleList;
		
	}

	public List<EstimateViewVo> getEstimateList(Connection conn, ExpertVo loginExpert) throws Exception {
		
		String sql = "SELECT E.ESTIMATE_NO, SUBSTR(E.ESTIMATE_TITLE, 1, 12) ESTIMATE_TITLE, E.ESTIMATE_PRICE, SUBSTR(E.ESTIMATE_LINE_INTRODUCTION, 1 , 15) ESTIMATE_LINE_INTRODUCTION, E.ESTIMATE_THUMBNAIL, E.BUSINESS_REGISTRATION_NUMBER, (SELECT COUNT(*) FROM REVIEW R WHERE R.ESTIMATE_NO = E.ESTIMATE_NO AND REVIEW_STATUS = 'Y') ESTIMATE_REVIEW_COUNT FROM ESTIMATE E WHERE E.FREELANCER_NO = ? AND E.ESTIMATE_STATUS = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<EstimateViewVo> estimateList = new ArrayList<>();
		while(rs.next()) {
			String estimateNo = rs.getString("ESTIMATE_NO");
			String estimateTitle = rs.getString("ESTIMATE_TITLE");
			String estimateLineIntroduction = rs.getString("ESTIMATE_LINE_INTRODUCTION");
			String estimatePrice = rs.getString("ESTIMATE_PRICE");
			String estimateThumbnail = rs.getString("ESTIMATE_THUMBNAIL");
			String businessRegistrationNumber = rs.getString("BUSINESS_REGISTRATION_NUMBER");
			String estimateReviewCount = rs.getString("ESTIMATE_REVIEW_COUNT");
			
			EstimateViewVo vo = new EstimateViewVo();
			
			vo.setBusinessRegistrationNumber(businessRegistrationNumber);
			vo.setEstimateLineIntroducation(estimateLineIntroduction);
			vo.setEstimateNo(estimateNo);
			vo.setEstimatePrice(estimatePrice);
			vo.setEstimateReviewCount(estimateReviewCount);
			vo.setEstimateThumbnail(estimateThumbnail);
			vo.setEstimateTitle(estimateTitle);
			
			estimateList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return estimateList;
		
	}

	public int checkLogin(Connection conn, MemberVo mvo) throws Exception {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_PWD = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mvo.getMemberId());
		pstmt.setString(2, mvo.getMemberPwd());
		ResultSet rs = pstmt.executeQuery();
		
		int result = 0;
		if(rs.next()) {
			result = 1;
		}else {
			result = 0;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	public List<EstimateViewVo> getEstimateQNAList(Connection conn, ExpertVo loginExpert) throws Exception {
		String sql = "SELECT E.ESTIMATE_NO, SUBSTR(E.ESTIMATE_TITLE, 1, 12) ESTIMATE_TITLE, E.ESTIMATE_PRICE, SUBSTR(E.ESTIMATE_LINE_INTRODUCTION, 1 , 15) ESTIMATE_LINE_INTRODUCTION, E.ESTIMATE_THUMBNAIL, E.BUSINESS_REGISTRATION_NUMBER, (SELECT COUNT(*) FROM REVIEW R WHERE R.ESTIMATE_NO = E.ESTIMATE_NO AND REVIEW_STATUS = 'Y' AND REVIEW_COMMENT_STATUS = 'N') ESTIMATE_REVIEW_COUNT FROM ESTIMATE E WHERE E.FREELANCER_NO = ? AND E.ESTIMATE_STATUS = 'Y'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, loginExpert.getFreelancerNo());
		ResultSet rs = pstmt.executeQuery();
		
		List<EstimateViewVo> estimateList = new ArrayList<>();
		while(rs.next()) {
			String estimateNo = rs.getString("ESTIMATE_NO");
			String estimateTitle = rs.getString("ESTIMATE_TITLE");
			String estimateLineIntroduction = rs.getString("ESTIMATE_LINE_INTRODUCTION");
			String estimatePrice = rs.getString("ESTIMATE_PRICE");
			String estimateThumbnail = rs.getString("ESTIMATE_THUMBNAIL");
			String businessRegistrationNumber = rs.getString("BUSINESS_REGISTRATION_NUMBER");
			String estimateDisreivew = rs.getString("ESTIMATE_REVIEW_COUNT");
			
			EstimateViewVo vo = new EstimateViewVo();
			
			vo.setBusinessRegistrationNumber(businessRegistrationNumber);
			vo.setEstimateLineIntroducation(estimateLineIntroduction);
			vo.setEstimateNo(estimateNo);
			vo.setEstimatePrice(estimatePrice);
			vo.setEstimateDisReview(estimateDisreivew);
			vo.setEstimateThumbnail(estimateThumbnail);
			vo.setEstimateTitle(estimateTitle);
			
			estimateList.add(vo);
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return estimateList;
	}

	public String getIdByEmail(Connection conn, String memberEmail) throws Exception {
		
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memberEmail);
		ResultSet rs = pstmt.executeQuery();
		
		String id = null;
		if(rs.next()) {
			String memberId = rs.getString("MEMBER_ID");
			
			id = memberId;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return id;
		
	}

	public String getPwdByIdEmail(Connection conn, MemberVo mvo) throws Exception {
		
		String sql = "SELECT MEMBER_PWD FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_EMAIL = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mvo.getMemberId());
		pstmt.setString(2, mvo.getMemberEmail());
		ResultSet rs = pstmt.executeQuery();
		
		String pwd = null;
		if(rs.next()) {
			String memberpwd = rs.getString("MEMBER_PWD");
			
			pwd = memberpwd;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return pwd;
		
	}

	public int searchMemberByIdEmail(Connection conn, MemberVo mvo) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ? AND MEMBER_EMAIL = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, mvo.getMemberId());
		pstmt.setString(2, mvo.getMemberEmail());
		ResultSet rs = pstmt.executeQuery();
		
		int result = 0;
		if(rs.next()) {
			result = 0;
		}else {
			result = 1;
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		return result;
		
	}

	

	
	
	
} // class
