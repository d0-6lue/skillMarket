<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자</title>
</head>
<body>

    <div id="wrap">

        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>

        <header>
            
            <h1>메인</h1>
        
        </header>

        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle">
                                <h3>오늘 할일</h3>
                            </div>
                            <div class="statistics statistics_100">
                                <table id="statistics_1">
                                    <thead >
                                        <tr>
                                            <th><div>신고</div></th>
                                            <th><span>문의</span></th>
                                        </tr>
                                    </thead>
                                    <tbody >
                                        <tr>
                                            <td>
                                                <span>${ homeVo.reportCount }</span>
                                            </td>
                                            <td>
                                                <span>${ homeVo.noAnswerCount }</span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            
                        </div>
                        
                    </div>
                </div>
                
                <div class="article_area" id="article_area_3">
                    <div class="container_admin" id="container_area_3">
                        <div class="admin_box">
                            <div class="subtitle">
                                <h3>전체 요약</h3>
                            </div>
                            <div class="statistics statistics_list2">
                                <table>
                                    <thead class="scroll_tbody home_div">
                                        <tr>
                                            <th>
                                                <span>일자</span>
                                            </th>
                                            <th>
                                                <span>매출액</span>
                                            </th>
                                            <th>
                                                <span>가입수</span>
                                            </th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody class="scroll_tbody home_div">
                                        <c:forEach items="${monthStatsList}" var="list">
                                            <tr>
                                                <td>
                                                    ${list.month}
                                                </td>
                                                <td>
                                                    ${list.totalSales}
                                                </td>
                                                <td>
                                                    ${list.signupCount}
                                                </td>
                                            </tr>
                                        
                                        </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="container_admin" id="container_area_3">
                        <div class="admin_box">
                            <div class="subtitle">
                                <h3>회원 요약</h3>
                                <div id="memebr_color">
                                    <div>
                                        <span>⦁</span>
                                        <span>회원</span>
                                    </div>
                                    <div>
                                        <span>⦁</span>
                                        <span>전문가</span>
                                    </div>
                                </div>
                                
                            </div>
                            <div class="statistics statistics_chart">
                                <div id="chart_div">
                                    <svg viewBox='-1.5 -1.5 3 3' style='transform: rotate(-90deg)'></svg>
                                    <div class="center-circle"></div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="container_admin" id="container_area_3">
                        <div class="admin_box">
                            <div class="subtitle">
                                <h3>최근 공지</h3>
                            </div>
                            <div class="statistics statistics_list2">
                                <table>
                                    <thead class="home_div">
                                        <tr>
                                            <th>
                                                <span>번호</span>
                                            </th>
                                            <th>
                                                <span>제목</span>
                                            </th>
                                            <th>
                                                <span>작성일</span>
                                            </th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody class="home_div">
                                        <c:if test="${!empty homeVo.notiNo }">
                                            <tr>
                                                <td>
                                                    <span>${ homeVo.notiNo }</span>
                                                </td>
                                                <td>
                                                    <span>${ homeVo.notiTitle }</span>
                                                </td>
                                                <td>
                                                    <span>${ homeVo.notiEnrolldate }</span>
                                                </td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${empty homeVo.notiNo }">
	                                        <tr>
	                                            <td colspan='3'>
	                                                <span>공지가 하나도 없어요 😥</span>
	                                            </td>
	                                        </tr>
	                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    
                    <div class="container_admin" id="container_area_3">
                        <div class="admin_box">
                            <div class="subtitle">
                                <h3>인기 FAQ</h3>
                            </div>
                            <div class="statistics statistics_list2">
                                <table>
                                    <thead class="home_div">
                                        <tr>
                                            <th>
                                                <span>카테고리</span>
                                            </th>
                                            <th>
                                                <span>제목</span>
                                            </th>
                                            <th>
                                                <span>조회수</span>
                                            </th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody class="home_div">
	                                    <c:if test="${!empty homeVo.faqCatNo }">
	                                    	<tr>
	                                            <td>
	                                                <span>${ homeVo.faqCatNo }</span>
	                                            </td>
	                                            <td>
	                                                <span>${ homeVo.faqTitle }</span>
	                                            </td>
	                                            <td>
	                                                <span>${ homeVo.faqHit }</span>
	                                            </td>
	                                        </tr>
	                                    </c:if>
	                                    <c:if test="${empty homeVo.faqCatNo }">
	                                        <tr>
	                                            <td colspan='3'>
	                                                <span>FAQ가 하나도 없어요 😥</span>
	                                            </td>
	                                        </tr>
	                                    </c:if>
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
        const memberCnt = parseInt('${ homeVo.memberCount }');
        const freeCnt = parseInt('${ homeVo.freelancerCount }');
    </script>

</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/members.css">
<link rel="stylesheet" href="${root}/static/css/admin/common/aside.css">
<link rel="stylesheet" href="${root}/static/css/admin/home.css">
<script src="${root}/static/js/admin/home.js"></script>
