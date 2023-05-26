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
                                            <td>
                                                <span>${ adminMemberVo.freeLancerCnt }</span>
                                            </td>
                                            <td>
                                                <span>${ adminMemberVo.newBeCnt }</span>
                                            </td>
                                            <td>
                                                <span>${ adminMemberVo.statusCnt }</span>
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
                                            <th id="status_Sort" oncli>
                                                상태
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody class="scroll_tbody">
                                        <div class="list_box">

                                            <c:forEach items="${ memberArrList }" var="list">
                                                <tr class="tr_backColor_${ list.statusNo } tr_R10">
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
<script>

    const rootAdmin = '${root}'+'/admin/';
    console.log(rootAdmin);

</script>

</body>
</html>



<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/members.css">
<script src="${root}/static/js/admin/members.js"></script>