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
                                    <div>99</div>
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
                                                <span>99</span>
                                            </td>
                                            <td>
                                                <span>999</span>
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
                            <div class="subtitle">
                                <h3>회원 목록</h3>
                            </div>
                            <div class="statistics statistics_list">
                                <table>
                                    <thead>
                                        <tr>
                                            <th>
                                                <span>회원번호</span>
                                            </th>
                                            <th>
                                                <span>제목</span>
                                            </th>
                                            <th>
                                                <span>일자</span>
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