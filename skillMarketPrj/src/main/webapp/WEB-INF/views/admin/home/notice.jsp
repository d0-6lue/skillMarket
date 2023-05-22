<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지관리</title>
</head>
<body>

    <div id="wrap">

        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>
        
        <header>
            
            <h1>공지관리</h1>
        
        </header>
        
        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle">
                                <h3>최근 공지</h3>
                            </div>
                            <div class="statistics statistics_list_2">
                                <table >
                                    <thead class="scroll_tbody thead_50 ">
                                        <tr>
                                            <th>
                                                <span>번호</span>
                                            </th>
                                            <th>
                                                <span>제목</span>
                                            </th>
                                            <th>
                                                <span>조회수</span>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody class="scroll_tbody" id="tdody_Hn">
                                        <tr>
                                            <td>
                                                ${ newNotice.notiNo }
                                            </td>
                                            <td>
                                                ${ newNotice.notiTitle }
                                            </td>
                                            <td>
                                                ${ newNotice.notiHit }
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
                                <h3>공지 목록</h3>
                                <div >
                                    <button id="openModalBtn">공지작성</button>
                                </div>
                            </div>
                            <div class="statistics statistics_list_2">
                                <table>
                                    <thead class="scroll_tbody">
                                        <tr>
                                            <th>
                                                <span>번호</span>
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
                                        <c:forEach items="${ noticeArrList }" var="list">
                                            <tr>
                                                <td>
                                                    ${ list.notiNo }
                                                </td>
                                                <td>
                                                    [${ list.notiCatNo }]${ list.notiTitle }
                                                </td>
                                                <td>
                                                    ${ list.notiHit }
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

    <!-- 모달 -->
    <c:forEach items="${ noticeArrList }" var="modal">

        <div id="noticeDetail_${ modal.notiNo }" class="noticeDetail modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h1>공지 상세</h1>
                <div id="modal_div">
                    <div class="nDC">[${ modal.notiCatNo }]</div>
                    <input type="text" placeholder="제목" value="${ modal.notiTitle }" readonly>
                </div>
                <div id="noticeDetailContent_${ modal.notiNo }"><div>${ modal.notiContent }</div></div>
                <button id="submitBtn1" onclick="editForm();">수정하기</button>
            </div>
        </div>

    </c:forEach>
    <!----------------------------->
    

    <!-- 모달 -->
	<div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h1>공지작성</h1>
            <div id="modal_div">
                <select name="" id="cat_select">
                    <option value="default" selected>카테고리</option>
                    <option value="1">1</option>
                    <option value="1">2</option>
                    <option value="1">3</option>
                </select>
                <input id="titleInput" type="text" name="title" placeholder="제목">
            </div>
            <div id="summernote"></div>
            <button id="submitBtn">등록하기</button>
        </div>
      </div>


</body>
</html>



<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/notice.css">
<script src="${root}/static/js/admin/common/adminSet.js"></script>
<script src="${root}/static/js/admin/notice.js"></script>
