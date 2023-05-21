
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤 홈</title>
    <link rel="stylesheet" href="${root}/static/css/community/commuHome.css">
    <script defer src="${root}/static/js/community/commuHome.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <!-- 전체화면 뒷배경~ -->
    <div class="wrap">
        <!-- header -->
        <%@ include file="/WEB-INF/views/common/header.jsp" %>

        <div class="community-title-box">
            <div class="community-header-content">
                <div class="community-title">커뮤니티</div>
                <div class="community-desc-content">
                    <span class="community-desc-text">게시글을 올려 소통해보세요~</span>
                    <span>
                        <a class="btn btn-primary" onclick="writePostWithTopic()">글쓰기</a>
                    </span>
                </div>
            </div>
        </div>

        <main>
            <div class="community-box">
                <div></div>
                <div class="community-left-side">
                    <!-- 주제바(전체/궁금해요/함께해요) -->
                    <div class="topic-group">
                        <ul>
                            <li><a href="${root}/community/all">전체</a></li>
                            <li><a href="${root}/community/together">함께해요</a></li>
                            <li><a href="${root}/community/qna">궁금해요</a></li>
                            <li><a href="${root}/community/how-much">얼마에요</a></li>
                        </ul>
                    </div>
                </div>

                <div class="community-body-content">
                    <!-- ajax로 바꾸려고 ~ -->
					<div id="main-content-container">
						<!-- 검색 창 -->
						<div class="search-container">
                            <div class="search-content">
                                <div class="search-input-box">
                                    <form method="post" name="search" action="searchbbs.jsp">
                                        <table class="pull-right">
                                            <tr>
                                                <td>
                                                    <select class="form-control" name="searchField">
                                                        <option value="0">선택</option>
                                                        <option value="bbsTitle">제목</option>
                                                        <option value="userID">작성자</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <input type="text" class="form-control" placeholder="궁금한 내용을 입력해보세요." name="searchText" maxlength="100">
                                                </td>
                                                <td>
                                                    <button type="submit" class="btn btn-success search-btn">검색 </button>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                        
                        <div class="posts-container-menu-bar">
                            <ul class="order-pc">
                                <li class="e-order active" data-order="recent">
                                    <button class="ac-button is-md is-text tab-button ">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
                                            <path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path>
                                        </svg> 최신순
                                    </button>
                                </li>
                                <li class="e-order " data-order="score">
                                    <button class="ac-button is-md is-text tab-button ">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
                                            <path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path>
                                        </svg> 정확도순
                                    </button>
                                </li>
                                <li class="e-order " data-order="comment">
                                    <button class="ac-button is-md is-text tab-button ">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
                                            <path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path>
                                        </svg> 답변많은순
                                    </button>
                                </li>
                                <li class="e-order " data-order="recommend">
                                    <button class="ac-button is-md is-text tab-button ">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16">
                                            <path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path>
                                        </svg> 좋아요순
                                    </button>
                                </li>
                            </ul>
                        </div>

                        <!-- 게시글입니다. -->
                        <br>
                        <div class="list-group">
							<c:forEach items="${volist}" var="vo" varStatus="loop">
								<div class="list-group-item list-group-item-action" data-boardNo="${vo.boardNo}">
									<!-- 게시글 제목 -->
									<h5 class="mb-1">${vo.freeBoardTitle}</h5>
									<!-- 게시글내용짧게보여주기 -->
									<p class="mb-1">${vo.freeBoardContentShort}</p>
						
									<!-- 메타데이터 -->
									<div>
										<small class="text-muted">
											<!-- 하트 -->
											❤️<i class="fas fa-heart"></i> ${vo.recommendCount}
											
											<!-- 댓글 -->
											<i class="fas fa-comment"></i> ${vo.commentCount}
						
											<!-- 시간~ -->
											${vo.freeBoardEnrollDate}
										</small>
									</div>
								</div>
							</c:forEach>
						</div>

						<div class="page-area">
							<c:if test="${pv.currentPage > 1}">
							<a class="btn btn-primary" href="${root}/community?page=${pv.currentPage - 1}">이전</a>
							</c:if>
								<c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
									<c:if test="${pv.currentPage != i}">
										<a class="btn btn-primary btn-sm" href="${root}/community?page=${i}">${i}</a>
									</c:if>
									<c:if test="${pv.currentPage == i}">
										<a class="btn btn-primary btn-sm">${i}</a>
									</c:if>
								</c:forEach>
							<c:if test="${pv.currentPage < pv.maxPage}">
								<a class="btn btn-primary btn-sm" href="${root}/community?page=${pv.currentPage + 1}">다음</a>
							</c:if>
						</div>
					</div>   
					</div>
                    <!-- 메인 오른쪽 끝~ -->
                </div>
            </div>
        </main>
		
    </div>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>
</body>
</html>

<script>
    window.addEventListener('load', function() {
        let posts = document.querySelectorAll('.list-group-item');

        posts.forEach((post, index) => {
            post.addEventListener('click', function() {
                let boardNo = this.getAttribute('data-boardNo');
                location.href = "${root}/community/post/detail?bno=" + boardNo;
            });
        });
    });
</script>

