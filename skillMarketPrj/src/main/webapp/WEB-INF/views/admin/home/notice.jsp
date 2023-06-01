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
                                            <tr id="notice_List_${ list.notiNo }" class="notice_List">
                                                <td>
                                                    ${ list.notiNo }
                                                </td>
                                                <td>
                                                    [${ list.notiCatName }]${ list.notiTitle }
                                                </td>
                                                <td id="hit_${ list.notiNo }">
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
                <span class="close" id="close_${ modal.notiNo }"">&times;</span>
                <h1>공지 상세</h1>
                <div id="modal_div">

                    <select name="" id="cat_select_detail_${ modal.notiNo }" class="cat_select_detail" disabled>
                        <c:forEach items="${ noticeSelectList }" var="select">
                       

                            <c:if test="${ modal.notiCatNo eq select.notiCatNo }">
                                <option id="cat_select_detail_option_${ modal.notiNo }" value="${ select.notiCatNo }"  class="nDC" selected>${ select.notiCatName }</option>
                            </c:if>
                            <c:if test="${ modal.notiCatNo ne select.notiCatNo }">
                                <option id="cat_select_detail_option_${ modal.notiNo }" value="${ select.notiCatNo }"  class="nDC">${ select.notiCatName }</option>
                            </c:if>
                            
                        </c:forEach>
                    </select>

                    <input type="text" id="cat_input_detail_${ modal.notiNo }" class="cat_input_detail" placeholder="제목" value="${ modal.notiTitle }" readonly>
                </div>
                <div id="noticeDetailContent_${ modal.notiNo }">${ modal.notiContent }</div>

                <div id="submit_btn_area_"+${ modal.notiNo }    class="submit_btn_area">
                    <button id="submit_btn_${ modal.notiNo }"class=" modal_btn_notice  submit_btn submit_btn_${ modal.notiNo }">수정하기</button>

                    <button id="edit_btn_${ modal.notiNo }"class="modal_btn_notice  edit_btn" >등록하기</button>
                </div>
                
            </div>
        </div>

    </c:forEach>
    <!----------------------------->
    

    <!-- 모달 -->
	<div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" id="close_my">&times;</span>
            <h1>공지작성</h1>
            <div id="modal_div">
                <select name="" id="cat_select">
                    <option value="default" selected>카테고리</option>
                    <option value="1">공지</option>
                    <option value="2">이벤트</option>
                    <option value="3">안내</option>
                </select>
                <input id="titleInput" type="text" name="title" placeholder="제목">
            </div>
            <div id="summernote"></div>
            <button id="submitBtn">등록하기</button>
        </div>
      </div>

<script>
   
    const newNoticeNo = parseInt("${ newNotice.notiNo }")+1;
 

</script>
    

</body>
</html>



<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/notice.css">
<script src="${root}/static/js/admin/common/adminSet.js"></script>
<script src="${root}/static/js/admin/notice.js"></script>
