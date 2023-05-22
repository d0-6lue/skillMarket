<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티 게시글 상세보기 페이지</title>
    <link rel="stylesheet" href="${root}/static/css/community/post/commudetail.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<script>
    window.onbeforeunload = function() {
  window.location.replace(window.location.href);
};
</script>
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
                        <span>
                            <a class="btn btn-primary" href="${root}/community/post/edit?no=${vo.boardNo}">수정하기</a>
                            <a class="btn btn-primary" href="${root}/community/post/delete?no=${vo.boardNo}">삭제하기</a>
                        </span>
                        
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
                    <form id="comment-form" action="/community/comment/write?no=${vo.boardNo}" method="post">
                        <input type="hidden" name="no" value="${vo.boardNo}">
                        <textarea class="textcontent" name="comment" rows="3" cols="40" required></textarea>
                        <button class="btn btn-primary" type="button" onclick="writeComment();">댓글 등록</button>
                    </form>
                    <div class="community-comment-box" id="comment-box">
                    </div>
                    
                </div>
            </div>
        </main>
        <%@ include file="/WEB-INF/views/common/footer.jsp" %>	
    </div>

        <script>
        function writeComment() {
        const comment = document.querySelector("textarea[name=comment]").value;
        console.log(comment);
        $.ajax({
            url: "${root}/community/comment/write",
            type: "POST",
            data: {
            no: '${vo.boardNo}',
            comment: comment,
            },
            success: (x) => {
            console.log(x);
            if (x == 'ok') {
                alert('댓글 작성 성공');
                document.querySelector("textarea[name=comment]").value = '';
                loadComment();
            } else {
                alert("댓글 작성 실패...");
            }
            },
            error: (x) => {
            console.log(x);
            },
        });
        }
        //댓글불러오기
        function loadComment() {
        $.ajax({
            url: "${root}/community/comment/list",
            type: "GET",
            data: { no: "${vo.boardNo}" },
            dataType: "json",

            success: function(response) {
                var comments = response;
                console.log(comments);

                $('#comment-box').empty();

                for (var i = 0; i < comments.length; i++) {
                var comment = comments[i];
                console.log(comment);
                var memberName = comments[i].memberName;
                var commentContent = comments[i].commentContent;
                var commentDate = comments[i].commentDate;

                var commentHtml = '<div class="card mb-3 g-0">' +
                    '<div class="row">' +
                    '<div class="col-md-8">' +
                    '<div class="card-body">' +
                    '<h5 class="card-title">' + memberName + '</h5>' +
                    '<p class="card-text">' + commentContent + '</p>' +
                    '<p class="card-text"><small class="text-muted">' + commentDate + '</small></p>' +
                    '</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';

                $('#comment-box').append(commentHtml);
            }

            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    }

        loadComment();

        </script>
</body>
</html>


