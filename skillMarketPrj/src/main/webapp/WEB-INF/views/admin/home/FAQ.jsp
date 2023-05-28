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
                                    <thead class="thead_50">
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
                                        <tr id="FAQ_Detail_${ FAQHit.faqNo }" class="FAQ_Detail_TR">
                                            <td>
                                                ${ FAQHit.faqNo }
                                            </td>
                                            <td>
                                                ${ FAQHit.faqQContent }
                                            </td>
                                            <td>
                                                ${ FAQHit.faqHit }
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
                                <div >
                                    <button id="openModalBtn">FAQ 추가</button>
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
                                            <tr id="FAQ_Detail_${list.faqNo}" class="FAQ_Detail_TR">
                                                <td>
                                                    ${ list.faqNo }
                                                </td>
                                                <td>
                                                    ${ list.faqCatName }
                                                </td>
                                                <td>
                                                    ${ list.faqQContent }
                                                </td>
                                                <td>
                                                    ${ list.faqHit }
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

	 

    <c:forEach items="${ FAQArrList }" var="modal">
        <!-- FAQ 모달 -->
        <div id="FAQ_detail_${ modal.faqNo }" class="FAQ_detail modal">
            <div class="modal-content FAQ_modal">
                <span id="close_${modal.faqNo}"  class="close">&times;</span>
                <div id="h1_area">
                    <h1>
                        <select name="" id="cat_select_FAQ" class="cat_select_FAQ_${modal.faqNo}" disabled>
                            <c:forEach var="FAQ" items="${catNameMap['FAQ']}">
                                <c:if test="${modal.faqCatNo eq FAQ.faqCatNo}">
                                    <option value="${FAQ.faqCatNo }" selected >${FAQ.faqCatName }</option>
                                </c:if>
                                <c:if test="${not modal.faqCatNo eq FAQ.faqCatNo}">
                                    <option value="${FAQ.faqCatNo }">${FAQ.faqCatName }</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </h1>
                    <h1>FAQ</h1>

                </div>
                <div id="modal_div">
                    <input type="text" id="modal_Title_${ modal.faqNo }" class="modal_Title" name="title" value="${ modal.faqQContent }" placeholder="제목" readonly></input>
                    <div id="faq_Hit" class="faq_Hit_${modal.faqNo }">
                        조회수 
                        <br>
                        ${ modal.faqHit }
                    </div>
                </div>
                <div class="modal_content_area" id="FAQ_detailContent_${ modal.faqNo }">
                    <div id="FAQ_summer_note_${ modal.faqNo }"> ${ modal.faqAContent }</div>
                </div>
                <button id="submitBtn1" class="FAQ_edit_btn FAQ_edit_btn_${ modal.faqNo }" >수정하기</button>
            </div>
        </div>
    </c:forEach>

</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<script src="${root}/static/js/admin/common/adminSet.js"></script>
<link rel="stylesheet" href="${root}/static/css/admin/FAQ.css">
<script src="${root}/static/js/admin/FAQ.js"></script>