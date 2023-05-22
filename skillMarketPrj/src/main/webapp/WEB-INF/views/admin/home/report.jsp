<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고</title>
</head>
<body>

    <div id="wrap">

        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>
        
        <header>
            
            <h1>신고</h1>
        
        </header>
        
        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle">
                                <h3>신고처리 요약</h3>
                            </div>
                            <div class="statistics statistics_100">
                                <table id="statistics_1">
                                    <thead>
                                        <tr>
                                            <th><div>미처리 신고</div></th>
                                            <th><span>처리한 신고</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <span>${ msVo.statusNCnt }</span>
                                            </td>
                                            <td>
                                                <span>${ msVo.statusYCnt }</span>
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
                            <div class="subtitle" id="members_cnt">
                                <h3>신고 내역</h3>
                                <div>
                                    <div>필터</div>
                                    <div>미처리</div>
                                </div>
                            </div>
                            <div class="statistics statistics_list_2">
                                <table>
                                    <thead class="scroll_tbody">
                                        <tr>
                                            <th>
                                                <span>신고 번호</span>
                                            </th>
                                            <th>
                                                <span>제목</span>
                                            </th>
                                            <th>
                                                <span>처리 상태</span>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody class="scroll_tbody">

                                        <c:forEach items="${ reprotArrList }" var="list">
                                            <tr>
                                                <td>
                                                    <span>${list.rptNo}</span>
                                                </td>
                                                <td>
                                                    <span>[${list.rptcatNo}]${list.rptTitle}</span>
                                                </td>
                                                <td>
                                                    <span>${
                                                        list.rptStatus}</span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
    
            </div>
    
        </article>

    </div>
	

</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">