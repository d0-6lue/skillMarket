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
                            </div>
                            <div class="statistics statistics_list">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>
                                                <span>1</span>
                                            </th>
                                            <th>
                                                <span>22</span>
                                            </th>
                                            <th>
                                                <span>333</span>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <span>999</span>
                                            </td>
                                            <td>
                                                <span>제목1</span>
                                            </td>
                                            <td>
                                                <span>01/01</span>
                                            </td>
                                        </tr>
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