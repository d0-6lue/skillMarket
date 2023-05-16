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
                                <br>
                                asdfasdfasdfasdfsdsassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss
                                <br>
                                sabsdafasdfsda
                                <br>
                                asdfasdfasdf
                            </div>
                        </div>

                        <div class="chat my-chat">
                            <div class="chat-info my-chat-info">
                                <span class="regular chat-enroll-time">보낸시간 23/05/01 21:24</span>
                            </div>
                            <div class="regular chat-contents">
                                내가 보낸 채팅 내용 ~~~
                            </div>
                        </div>
                        <span class="regular readcheck my-chat">읽음</span>
                        
                        <div class="chat">
                            <div class="chat-info">
                                <span class="regular chat-sender">보낸사람 ㅇㅇㅇ</span>
                                <span class="regular chat-enroll-time">보낸시간 23/05/01 21:24</span>
                            </div>
                            <div class="regular chat-contents">
                                <div class="request_">
                                    <div class="request-title bold">
                                        요청서
                                    </div>
                                    <div class="request-content regular">
                                        ㅇㅇㅇ로 ㅁㅁㅁㅁ해야합니다.
                                    </div>
                                    <div class="request-check-btn regular">
                                        확인하기
                                    </div>
                                </div>
                            </div>
                        </div>

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
            <div class="request-modal-title bold">
                요청서 작성하기
            </div>
            <div class="modal-content">
                <div class="request-modal-category">
                    <span class="regular subhead">카테고리</span>
                    <select name="request-category" id="request-select">
                        <option value="" hidden>--요청하실 카테고리를 골라주세요--</option>
                        <option value="deadline-extension">마감기간 연장</option>
                        <option value="deadline-reduce">마감기간 단축</option>
                        <option value="option-add">옵션 추가</option>
                        <option value="option-remove">옵션 취소</option>
                        <option value="retouch-request">수정 요청</option>
                        <option value="handsel-request">선금 요청</option>
                        <option value="cancle-request">거래 취소</option>
                    </select>
                </div>
                <div class="request-modal-content">
                    <span class="regular subhead">내용</span>
                    <textarea name="request-content-textarea" id="request-content-textarea" cols="100" rows="15"></textarea>
                </div>
                <div class="request-modal-changes">
                    <span class="regular subhead">변경사항</span>
    
                </div>
            </div>
            <div class="modal-btns">
                <button class="request-modal-btn">요청하기</button>
                <button class="request-modal-cancle-btn">취소</button>
            </div>
        </div>
    </div>

</body>
</html>