<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 게시글 상세보기 페이지</title>
    <link rel="stylesheet" href="${root}/static/css/community/post/commudetail.css">
</head>
<body>
    <div id="wrap">
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <main>
            <div class="community-title-box">
                <div class="community-header-content">
                    <div class="community-title">${vo.freeBoardTitle}</div>
                    <c:if test="${loginMember.memberNo == vo.memberNo}">
                    </div>
                        <a class="btn btn-primary" href="${root}/community/post/edit?no=${vo.boardNo}">수정하기</a>
                        <a class="btn btn-primary" href="${root}/community/post/delete?no=${vo.boardNo}">삭제하기</a>
                    <div>
                        
                    </c:if>
                </div>					
            </div>
            <div class="community-detail-box">
                <div class="detail-userinfo-box">
                    <div class="card mb-3 g-0">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="${root}/static/png/카톡 기본프로필.png" class="img-seller" alt="...">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title">홍길동</h5>
                                    <p class="card-text">엄준식</p>
                                    <p class="card-text"><small class="text-muted">3분전</small></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="community-detail-board-info-box">
                    <div id="content">
                        <c:out value="${vo.freeBoardContent}" escapeXml="false" />
                    </div>
                </div>
                <div class="community-detail-reply-content-box">
                    <!-- 댓글상자 -->
                    <form id="comment-form" action="/postComment" method="post">
                        <textarea name="comment" rows="3" cols="40"></textarea>
                        <button type="submit">댓글 작성</button>
                    </form>
                    <div class="community-comment-box">
                        <div class="comment-userinfo-box">
                            <div class="card mb-3 g-0">
                                <div class="row">
                                    <div class="col-md-4">
                                        <img src="${root}/static/png/카톡 기본프로필.png" class="img-seller" alt="...">
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body">
                                            <h5 class="card-title">홍길동</h5>
                                            <p class="card-text">댓글 내용입니다.</p>
                                            <p class="card-text"><small class="text-muted">5분전</small></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>	
    </div>
</body>
</html>
