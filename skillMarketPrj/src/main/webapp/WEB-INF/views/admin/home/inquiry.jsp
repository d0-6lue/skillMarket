<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의</title>
</head>
<body>

    <div id="wrap">

        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>
        
        <header>
            
            <h1>문의</h1>
        
        </header>
        
        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle">
                                <h3>문의처리 요약</h3>
                            </div>
                            <div class="statistics statistics_100">
                                <table id="statistics_1">
                                    <thead>
                                        <tr>
                                            <th><div>99</div></th>
                                            <th><span>99</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <span>99</span>
                                            </td>
                                            <td>
                                                <span>999</span>
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
                                <h3>문의 내역</h3>
                                <div>
                                    <div>필터</div>
                                    <div>미처리</div>
                                </div>
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