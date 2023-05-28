<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>

</head>
<body>

    <div id="wrap">

        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>
        
        <header>
            
            <h1>회원 관리</h1>
        
        </header>
        
        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle" id="members_cnt">
                                <h3>회원 상태 요약</h3>
                                <div>
                                    <div>총 회원수</div>
                                    <div>${ fn:length(memberArrList) }</div>
                                </div>
                            </div>
                            <div class="statistics statistics_100">
                                <table id="statistics_1">
                                    <thead>
                                        <tr>
                                            <th class="table_type_1"><div>전문가 회원</div></th>
                                            <th class="table_type_1"><span>신규 회원</span></th>
                                            <th class="table_type_1"><span>탈퇴/제제 회원</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="table_type_2">
                                            <td id="freeLancerModal_open">
                                                ${ adminMemberVo.freeLancerCnt }
                                            </td>
                                            <td id="newMemberModal_open">
                                                ${ adminMemberVo.newBeCnt }
                                            </td>
                                            <td id="reportMemberModal_open">
                                                ${ adminMemberVo.statusCnt }
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                        
                    </div>
                </div>
    
                <div class="article_area" id="article_area_2">
                    <div class="container_admin" id="container_area_2">
                        <div>
                            <div class="subtitle">
                                <h3>회원 목록</h3>
                                <div><input type="text" name="search" placeholder="아이디 검색" id="search_input"></div>
                            </div>
                            <div class="statistics statistics_list_2">
                                <table>
                                    <thead class="scroll_tbody">
                                        <tr>
                                            <th>
                                                <span>회원번호</span>
                                            </th>
                                            <th>
                                                <span>아이디</span>
                                            </th>
                                            <th>
                                                <span>가입일</span>
                                            </th>
                                            <th id="status_Sort">
                                                상태
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody class="scroll_tbody">
                                        <div class="list_box">

                                            <c:forEach items="${ memberArrList }" var="list">
                                                <tr id="member_TR_${list.memberNo}" class="tr_backColor_${ list.statusNo } tr_R10 member_TR">
                                                    <td>
                                                        ${ list.memberNo }
                                                    </td>
                                                    <td class="list_memberId_td">
                                                        ${ list.memberId }
                                                    </td>
                                                    <td>
                                                        ${ list.memberSignDate }
                                                    </td>
                                                    <td>
                                                        ${ list.statusName }
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr id="msgTbody">
                                                <td>일치하는 회원이 없습니다. 🤔</td>
                                            </tr>
                                        </div>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
    
            </div>
    
        </article>

    </div>

    
    <c:forEach items="${ memberArrList }" var="modal">

        <div id="modal_memberDetail_${ modal.memberNo }" class="modal_memberDetail modal">
            <div class="modal-content" id="modal-content_${ modal.memberNo }">
                <span class="close">&times;</span>
                <c:if test="${modal.statusNo == '1'}">
                    <h1>회원 상세</h1>
                </c:if>
                <c:if test="${modal.statusNo == '3'}">
                    <h1>🛑 회원 상세 🛑</h1>
                </c:if>
                <c:if test="${modal.statusNo == '4'}">
                    <h1>🚨 회원 상세 🚨</h1>
                </c:if>

                <div class="memberProfilePhoto_area">
                    <img class="memberProfilePhoto" src="${root}/static/img/profile/${modal.memberProfilePhoto}" alt="">
                </div>
                <div id="modal_div">
                    <div class="mNO">No.${ modal.memberNo }</div>
                    <div class="memberId_area">${ modal.memberId }</div>
                </div>
                <div id="modal_memberDetail_Content_${ modal.memberNo }" class="modal_subMain_box">
                    
                    <div class="modal_subMain">
                        <div>
                            닉네임 
                            <input type="text" value="${modal.memberNick}" readonly>
                        </div>
                        <div>닉네임 변경 
                            <input type="text" value="${modal.memberNickStatus}" readonly>
                        </div>
                        <div>전화번호
                            <input type="tel" value="${modal.memberPhone}" readonly>
                        </div>
                        <div>이메일 
                            <input type="email" value="${modal.memberEmail}" readonly>
                        </div>
                        <div>은행
                            <input type="text" value="${modal.memberBank}" readonly>
                        </div>
                        <div>계좌
                            <input type="text" value="${modal.memberAccount}" readonly>
                        </div>
                        <div>캐시
                            <input type="text" value="${modal.memberCash}" readonly>
                        </div>
                        <div>관심분야 
                            <input type="text" value="${modal.memberInterst}" readonly>
                        </div>
                        <div>프래랜서
                            <input type="text" value="${modal.freelancerY}" readonly>
                        </div>
                        <div>상태
                            <input type="text" value="${modal.statusName}" readonly>
                        </div>
                        <div>신고내역
                            <input type="text" value="" readonly>
                        </div>
                        <div>문의내역
                            <input type="text" value="" readonly>
                        </div>
                        <div>가입일
                            <input type="text" value="${modal.memberSignDate}" readonly>
                        </div>
                        <div>마지막 수정일
                            <input type="text" value="${modal.memberModifiDate}" readonly>
                        </div>
                    </div>
                </div>
                <button id="submitBtn">계정정지</button>
            </div>
        </div>

    </c:forEach>

<script>

    const rootAdmin = '${root}'+'/admin/';

</script>

</body>
</html>



<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/members.css">
<script src="${root}/static/js/admin/members.js"></script>