package com.team4.skillmarket.admin.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.admin.member.vo.memberListVo;
import com.team4.skillmarket.common.db.JDBCTemplate;

public class MemberListDao {

	public List<memberListVo> selectMemberList(Connection conn) throws Exception {
		
		String slq = "SELECT Z.* , S.STATUS_NAME FROM ( SELECT M.*, CASE WHEN F.MEMBER_NO IS NOT NULL THEN 'Y' ELSE 'N' END AS FREELANCER_Y FROM MEMBER M LEFT JOIN FREELANCER F ON M.MEMBER_NO = F.MEMBER_NO ORDER BY M.MEMBER_NO DESC ) Z JOIN MEMBER_STATUS S ON Z.STATUS_NO = S.STATUS_NO";
		PreparedStatement pstmt = conn.prepareStatement(slq);
		ResultSet rs = pstmt.executeQuery();
		
		List<memberListVo> memberArrList = new ArrayList<>();
		while(rs.next()) {
			
			String memberNo = rs.getString("MEMBER_NO");
			String statusNo = rs.getString("STATUS_NO");
			String memberId = rs.getString("MEMBER_ID");
			String memberPwd = rs.getString("MEMBER_PWD");
			String memberNick = rs.getString("MEMBER_NICK");
			String memberPhone = rs.getString("MEMBER_PHONE");
			String memberEmail = rs.getString("MEMBER_EMAIL");
			String memberInterst = rs.getString("MEMBER_INTERST");
			Timestamp memberSignDate = rs.getTimestamp("MEMBER_SIGN_DATE");
			Timestamp memberModifiDate = rs.getTimestamp("MEMBER_MODIFI_DATE");
			String memberBank = rs.getString("MEMBER_BANK");
			String memberAccount = rs.getString("MEMBER_ACCOUNT");
			String memberCash = rs.getString("MEMBER_CASH");
			String memberProfilePhoto = rs.getString("MEMBER_PROFILE_PHOTO");
			String memberNickStatus = rs.getString("MEMBER_NICK_STATUS");
			String freelancerY = rs.getString("FREELANCER_Y");
			String statusName = rs.getString("STATUS_NAME");
			
			memberListVo mvo = new memberListVo();
			mvo.setMemberNo(memberNo);
			mvo.setStatusNo(statusNo);
			mvo.setMemberId(memberId);
			mvo.setMemberPwd(memberPwd);
			mvo.setMemberNick(memberNick);
			mvo.setMemberPhone(memberPhone);
			mvo.setMemberEmail(memberEmail);
			mvo.setMemberInterst(memberInterst);
			mvo.setMemberSignDate(memberSignDate);
			mvo.setMemberModifiDate(memberModifiDate);
			mvo.setMemberBank(memberBank);
			mvo.setMemberAccount(memberAccount);
			mvo.setMemberCash(memberCash);
			mvo.setMemberProfilePhoto(memberProfilePhoto);
			mvo.setMemberNickStatus(memberNickStatus);
			mvo.setFreelancerY(freelancerY);
			mvo.setStatusName(statusName);
			
			memberArrList.add(mvo);
			
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return memberArrList;
	}

	public List<memberListVo> searchMemberList(Connection conn, String userId) throws Exception {
		
		
		String slq = "SELECT Z.*, S.STATUS_NAME FROM ( SELECT M.*, CASE WHEN F.MEMBER_NO IS NOT NULL THEN 'Y' ELSE 'N' END AS FREELANCER_Y FROM MEMBER M LEFT JOIN FREELANCER F ON M.MEMBER_NO = F.MEMBER_NO ORDER BY M.MEMBER_NO DESC ) Z JOIN MEMBER_STATUS S ON Z.STATUS_NO = S.STATUS_NO WHERE Z.MEMBER_ID LIKE '%"+ userId +"%'";
		PreparedStatement pstmt = conn.prepareStatement(slq);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<memberListVo> memberArrList = new ArrayList<>();
		while(rs.next()) {
			
			String memberNo = rs.getString("MEMBER_NO");
			String statusNo = rs.getString("STATUS_NO");
			String memberId = rs.getString("MEMBER_ID");
			String memberPwd = rs.getString("MEMBER_PWD");
			String memberNick = rs.getString("MEMBER_NICK");
			String memberPhone = rs.getString("MEMBER_PHONE");
			String memberEmail = rs.getString("MEMBER_EMAIL");
			String memberInterst = rs.getString("MEMBER_INTERST");
			Timestamp memberSignDate = rs.getTimestamp("MEMBER_SIGN_DATE");
			Timestamp memberModifiDate = rs.getTimestamp("MEMBER_MODIFI_DATE");
			String memberBank = rs.getString("MEMBER_BANK");
			String memberAccount = rs.getString("MEMBER_ACCOUNT");
			String memberCash = rs.getString("MEMBER_CASH");
			String memberProfilePhoto = rs.getString("MEMBER_PROFILE_PHOTO");
			String memberNickStatus = rs.getString("MEMBER_NICK_STATUS");
			String freelancerY = rs.getString("FREELANCER_Y");
			String statusName = rs.getString("STATUS_NAME");
			
			memberListVo mvo = new memberListVo();
			mvo.setMemberNo(memberNo);
			mvo.setStatusNo(statusNo);
			mvo.setMemberId(memberId);
			mvo.setMemberPwd(memberPwd);
			mvo.setMemberNick(memberNick);
			mvo.setMemberPhone(memberPhone);
			mvo.setMemberEmail(memberEmail);
			mvo.setMemberInterst(memberInterst);
			mvo.setMemberSignDate(memberSignDate);
			mvo.setMemberModifiDate(memberModifiDate);
			mvo.setMemberBank(memberBank);
			mvo.setMemberAccount(memberAccount);
			mvo.setMemberCash(memberCash);
			mvo.setMemberProfilePhoto(memberProfilePhoto);
			mvo.setMemberNickStatus(memberNickStatus);
			mvo.setFreelancerY(freelancerY);
			mvo.setStatusName(statusName);
			
			memberArrList.add(mvo);
			
		}
		
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rs);
		
		return memberArrList;
		
	}
	
	
	
}
