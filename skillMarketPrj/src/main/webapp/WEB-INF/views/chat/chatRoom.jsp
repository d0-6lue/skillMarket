<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/chat/chatRoom.css">
<script defer src="${root}/static/js/chat/chatRoom.js"></script>
</head>
<body>

    <div class="wrap">

		<%@ include file="/WEB-INF/views/common/header.jsp" %>
        <script>
            const loginMemberNo = '${loginMember.memberNo}';
        </script>
        <div class="horizontal-border"></div>

		<main>
		
			<div class="chat-area">

                <div class="chat-util">

                    <div class="chat-util-top">
                        <div class="chat-opponent">
                            <div class="bold opponent-category">판매자</div>
                            <div class="regular opponent-nick">${sideInfo.seller}</div>
                            <script>
                                const sellerNick = '${sideInfo.seller}';
                            </script>
                        </div>
                        <div class="chat-util-horizontal-border"></div>
                        <div class="project-info">
                            <div class="bold project-title">${sideInfo.title}</div>
                            <img src="" alt="프로젝트 홍보 이미지" class="project-thumbnail">
                            <div class="chat-util-horizontal-border_"></div>
                            <div class="regular project-info">
                                ${sideInfo.lineIntroduce}
                            </div>
                        </div>
                    </div>

                    <div class="chat-util-bottom">
                        <button class="regular request-btn">요청하기</button>
                    </div>

                </div>

                <div class="chat-box-wrap">
                    <div class="chat-box">



                    </div>
                    <div class="chat-input-btn">
                        <input type="text" class="regular chat-msg">
                        <button class="chat-send-btn"></button>
                    </div>
                </div>

            </div>
		
		</main>
	
	    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </div>

    <div class="request-modal">
        <div class="request-modal-wrap">
            
            <div class="modal-box">

                <div class="request-modal-title bold">
                    요청서 작성하기
                </div>
                <div class="modal-content">

                    <div class="request-modal-category">
                        <span class="regular subhead">카테고리</span>
                        <select name="request-category" id="request-select">
                            <option value="" hidden>--요청하실 카테고리를 골라주세요--</option>
                            <c:forEach var="categoryVo" items="${categoryList }">
                            	<option value="${categoryVo.categoryNo }">${categoryVo.categoryName }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="request-modal-content">
                        <span class="regular subhead">내용</span>
                        <textarea name="request-content-textarea" id="request-content-textarea" cols="100" rows="13"></textarea>
                    </div>

                    <div class="request-modal-changes">
                        <span class="regular subhead change-title">변경사항</span>
                        <div class="change-area regular">

                        </div>
                    </div>

                </div>
                <div class="modal-btns">
                    <button class="request-modal-btn">요청하기</button>
                    <button class="request-modal-cancle-btn">취소</button>
                </div>

            </div>
        </div>
    </div>

</body>
</html>