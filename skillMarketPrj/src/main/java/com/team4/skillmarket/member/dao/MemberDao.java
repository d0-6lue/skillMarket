package com.team4.skillmarket.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team4.skillmarket.common.db.JDBCTemplate;
import com.team4.skillmarket.member.vo.MemberVo;

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
			
			loginMember = new MemberVo();
			
			loginMember.setMemberNo(memberNo);
			loginMember.setStatusNo(statusNo);
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
			
		}
		
		JDBCTemplate.close(rs);
		JDBCTemplate.close(pstmt);
		
		
		return loginMember;
		
		
	}

	
	
	
}
