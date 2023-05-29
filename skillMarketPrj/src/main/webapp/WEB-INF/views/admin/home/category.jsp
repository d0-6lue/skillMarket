<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 관리</title>
</head>
<body>

    <div id="wrap">

        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>
        
        <header>
            
            <h1>카테고리 관리</h1>
        
        </header>
        
        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle">
                                <h3>인기 카테고리</h3>
                            </div>
                            <div class="statistics statistics_rank">
                                <div>
                                    <span>1위</span>
                                    <span>코딩</span>
                                </div>
                                <div>
                                    <span>2위</span>
                                    <span>음향/영상</span>
                                </div>
                                <div>
                                    <span>3위</span>
                                    <span>마케팅</span>
                                </div>
                            </div>
                            
                        </div>
                        
                    </div>
                </div>
    
                <div class="article_area" id="article_area_2">
                    <div class="container_admin" id="container_area_2">
                        <div>
                            <div class="subtitle">
                                <h3>카테고리 리스트</h3>
                                <div >
                                    <button id="openModalBtn">카테고리 추가</button>
                                </div>
                            </div>
                            <div class="statistics statistics_list_2">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>
                                                <span></span>
                                            </th>
                                            <th>
                                                <span></span>
                                            </th>
                                            <th>
                                                <span></span>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody class="scroll_tbody">
                                        <c:forEach items="${ catArrList }" var="list_big">
                                            
                                            <c:if test="${ list_big.estimateCatScope == '1' }">

                                                <tr id="cat_box_${ list_big.estimateCatNo }" class="cat_box_div">
                                                    <td>
                                                        <span>${ list_big.estimateCatName }</span>
                                                    </td>

                                                    <td>
                                                        <input type="button" value="수정" id="catEditBtn">
                                                    </td>

                                                    <td id="meddle_cat_area_${ list_big.estimateCatNo }"  class="meddle_cat_area"">
                                                        중분류
                                                        <div id="meddle_cat_box_${ list_big.estimateCatNo }" class="meddle_cat_box">
                                                            <c:forEach items="${ catArrList }" var="list_middle">

                                                                <c:if test="${ list_middle.aboveCatNo eq list_big.estimateCatNo }">

                                                                    <div>${list_middle.estimateCatName}</div>

                                                                </c:if>


                                                            </c:forEach>
                                                        </div>
                                                    </td>
                                                   
                                                </tr>

                                            </c:if>
                                            

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
<link rel="stylesheet" href="${root}/static/css/admin/category.css">

