<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 게시글 상세보기 페이지</title>

<!-- 상세보기 페이지 css -->
<link rel="stylesheet" href="${root}/static/css/community/post/commudetail.css">

<!-- 상세보기 페이지 js -->

<!-- 부트스트랩 css js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>

    <div id="wrap">

		
			<!-- header -->
			<%@ include file="/WEB-INF/views/common/header.jsp" %>

            <div class="community-title-box">
                <div class="community-header-content">
                    <div class="community-title">게시글 제목</div>
                    <div class="community-desc-content">
                        <span class="community-desc-text">주제 입니다. ~</span>
                    </div>
                </div>					
            </div>

            <main>

                <div class="community-detail-box">

                    <div class="detail-userinfo-box">
                        <div class="card mb-3" style="max-width: 540px;">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="${root}/static/png/카톡 기본프로필.png" class="img-fluid rounded-start img-seller" alt="...">
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
                        <p>게시글 내용입니다</p>
                    </div>

                    <div class="community-detail-reply-content-box">
                        <div class="comment-input-box">
                            <div class="comment-body">
                                <!-- 댓글 내용을 입력해주세요 ~. -->
                                <textarea name="comment-input" placeholder="댓글 내용을 입력해주세요" rows="1" wrap="soft" class="sg-text-body2 sg-font-regular comment-input form-control" maxlength="500" style="height: 22px; overflow: hidden; resize: none;" id="__BVID__1563"></textarea>
                            </div>
                            <div class="btn btn-primary"> 등록 </div>
                        </div>

                        <!-- 댓글상자 -->
                        <div class="community-comment-box">
                            <div class="comment-userinfo-box">
                                <div class="card mb-3" style="max-width: 960px;">
                                    <div class="row g-0">
                                        <div class="col-md-4">
                                            <img src="${root}/static/png/카톡 기본프로필.png" class="img-seller img-fluid rounded-start" alt="...">
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

			<!-- footer  -->
			<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
	
		

	</div>	

</body>
</html>