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
                                    <thead>
                                        <tr>
                                            <th><div>신고</div></th>
                                            <th><span>문의</span></th>
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
                
                <div class="article_area" id="article_area_3">
                    <div class="container_admin" id="container_area_3">
                        <div class="admin_box">
                            <div class="subtitle">
                                <h3>전체 요약</h3>
                            </div>
                            <div class="statistics statistics_list">
                                <table>
                                    <thead>
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
                                <div class="chart doughnut1"><span class="center"></span></div>
                            </div>
                        </div>
                    </div>

                    <div class="container_admin" id="container_area_3">
                        <div class="admin_box">
                            <div class="subtitle">
                                <h3>신고 내역</h3>
                            </div>
                            <div class="statistics statistics_list">
                                <table>
                                    <thead>
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
                    
                    <div class="container_admin" id="container_area_3">
                        <div class="admin_box">
                            <div class="subtitle">
                                <h3>문의 내역</h3>
                            </div>
                            <div class="statistics statistics_list">
                                <table>
                                    <thead>
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
	
    <script>
        const chart1 = document.querySelector('.doughnut1');
        

        const makeChart = (percent, classname, color) => {
        let i = 1;
        let chartFn = setInterval(function() {
            if (i <= percent) { 
            colorFn(i, classname, color);
            i++;
            } else {
            clearInterval(chartFn);
            }
        }, 10);
        }

        const colorFn = (i, classname, color) => { 
        classname.style.background = "conic-gradient(" + color + " 0% " + i + "%, #1f77b4 " + i + "% 100%)";
        }

        const replay = () => {
        makeChart(80, chart1, '#FF9435');
        
        }

        makeChart(80, chart1, '#FF9435');
        
    </script>

</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">

