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
                                            <th><div>미처리 문의</div></th>
                                            <th><span>처리한 문의</span></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                <span id="NCunt_${ statusNCnt }" class="NCunt">${ statusNCnt }</span>
                                            </td>
                                            <td>
                                                <span id="YCunt_${ statusYCnt }">${ statusYCnt }</span>
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
                            <div class="statistics statistics_list_2">
                                <table>
                                    <thead class="scroll_tbody">
                                        <tr>
                                            <th>
                                                <span>문의 번호</span>
                                            </th>
                                            <th>
                                                <span>문의 제목</span>
                                            </th>
                                            <th>
                                                <span>처리 상태</span>
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody class="scroll_tbody">
                                        <c:forEach items="${ inquiryArrList }" var="list">
                                            <tr id="list_${list.qnaNo}">
                                                <td>
                                                    <span>${list.qnaNo}</span>
                                                </td>
                                                <td>
                                                    <span>[${list.qnaCatNo}]${list.qnaTitle}</span>
                                                </td>
                                                <td>
                                                    <span>${
                                                        list.qnaStatus}</span>
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
	
    <div>

        <c:forEach items="${ inquiryArrList }" var="modal">
    
            <div id="myModal" class="modal inquiry_modal_${ modal.qnaNo }">
                <div class="modal-content">
                    <span class="close" id="close_${ modal.qnaNo }">&times;</span>
                    <h1>문의 처리</h1>
                    <div id="modal_div">
                        
                        
                    <div>
                        <div class="content_Q">
                            <select name="" id="cat_select_${ modal.qnaNo }" disabled>
    
                                <c:forEach items="${inquiryCatList}" var="select">
        
                                    <c:if test="${ select.qnaCatNo eq modal.qnaCatNo}">
                                        <option value="${select.qnaCatNo}" selected >${select.qnaCatName}</option>
                                    </c:if>
        
                                    <c:if test="${ select.qnaCatNo ne modal.qnaCatNo}">
                                        <option value="${select.qnaCatNo}" >${select.qnaCatName}</option>
                                    </c:if>
                                    
                                </c:forEach>
                                
                            </select>

                            <input id="titleInput_${ modal.qnaNo }" type="text" name="Q_title" placeholder="제목" value="${ modal.qnaTitle }" readonly>
                        </div>
                        
                        <div id="inquiry_Q_${ modal.qnaNo }" class="inquiry_Q">
                            ${ modal.qnaContent }
                        </div>
                    </div>

                    </div>

                    <div class="content_A">

                        <input id="titleInput_${ modal.qnaNo }" type="text" name="A_title" placeholder="제목" value="답변하기" readonly>

                        <c:if test="${empty modal.questionAnswerContent }">

                            <textarea id="inquiry_A_${ modal.qnaNo }" class="inquiry_A"></textarea>

                        </c:if>

                        <c:if test="${not empty modal.questionAnswerContent }">

                            <textarea id="inquiry_A_${ modal.qnaNo }" class="inquiry_A">${ modal.questionAnswerContent }</textarea>
                            
                        </c:if>

                    </div>


                    <button id="submitBtn_${ modal.qnaNo }" class="inquiry_edit_btn inquiry_edit_btn_${ modal.qnaNo }">등록하기</button>
                </div>
            </div>

        </c:forEach>

    </div>
    

</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/inquiry.css">
<script src="${root}/static/js/admin/inquiry.js"></script>