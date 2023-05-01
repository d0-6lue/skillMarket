<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${root}/static/css/chat/chatRoom.css">
</head>
<body>

    <div class="wrap">

		<%-- <%@ include file="/WEB-INF/views/common/header.jsp" %> --%>
	
        <div class="horizontal-border"></div>

		<main>
		
			<div class="chat-area">

                <div class="chat-util">

                    <div class="chat-util-top">
                        <div class="chat-opponent">
                            <div class="bold opponent-category">판매자or구매자</div>
                            <div class="regular opponent-nick">채팅상대 닉네임</div>
                        </div>
                        <div class="chat-util-horizontal-border"></div>
                        <div class="project-info">
                            <div class="bold project-title">프로젝트 제목</div>
                            <img src="" alt="프로젝트 홍보 이미지" class="project-thumbnail">
                            <div class="regular project-info">
                                아무튼 정말 어마무시한 훌륭한 프로젝트입니다
                            </div>
                        </div>
                    </div>

                    <div class="chat-util-bottom">
                        <button class="regular request-btn">요청하기</button>
                    </div>

                </div>

                <div class="chat-box-wrap">
                    <div class="chat-box">
                        <div class="chat">
                            <div class="chat-info">
                                <span class="regular chat-sender">보낸사람 ㅇㅇㅇ</span>
                                <span class="regular chat-enroll-time">보낸시간 23/05/01 21:24</span>
                            </div>
                            <div class="regular chat-contents">
                                상대방이 보낸 채팅 내용 ~~~
                            </div>
                        </div>
                        <div class="chat my-chat">
                            <div class="chat-info">
                                <span class="regular chat-enroll-time">보낸시간 23/05/01 21:24</span>
                            </div>
                            <div class="regular chat-contents my-chat-contents">
                                내가 보낸 채팅 내용 ~~~
                            </div>
                        </div>
                        <span class="regular readcheck my-chat">읽음 여부</span>
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

</body>
</html>