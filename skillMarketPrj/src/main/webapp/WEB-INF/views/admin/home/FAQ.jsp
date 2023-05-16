<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
</head>
<body>

    <div id="wrap">

        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>
        
        <header>
            
            <h1>FAQ</h1>
        
        </header>
        
        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle">
                                <h3>최고 조회수 FAQ</h3>
                            </div>
                            <div class="statistics statistics_list_2">
                                <table >
                                    <thead>
                                        <tr>
                                            <th>
                                                <span>번호</span>
                                            </th>
                                            <th>
                                                <span>FAQ</span>
                                            </th>
                                            <th>
                                                <span>조회수</span>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <span>999</span>
                                            </td>
                                            <td>
                                                <span>99</span>
                                            </td>
                                            <td>
                                                <span>9</span>
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
                                <h3>FAQ  목록</h3>
                            </div>
                            <div class="statistics statistics_list_2">
                                <table>
                                    <thead class="scroll_tbody">
                                        <tr>
                                            <th>
                                                <span>번호</span>
                                            </th>
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
                                    <tbody class="scroll_tbody">
                                        <c:forEach items="${ FAQArrList }" var="list">
                                            <tr>
                                                <td>
                                                    <span>${ list.faqNo }</span>
                                                </td>
                                                <td>
                                                    <span>${ list.faqCatNo }</span>
                                                </td>
                                                <td>
                                                    <span>${ list.faqTitle }</span>
                                                </td>
                                                <td>
                                                    <span>${ list.faqHit }</span>
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