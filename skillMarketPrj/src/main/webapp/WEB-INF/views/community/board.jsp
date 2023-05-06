<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤 홈</title>
<link rel="stylesheet" href="${root}/static/css/community/commuHome.css">

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
						<a class="btn btn-primary" href="${root}/community/post/write">글쓰기</a>
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
							<li><a href="${root}/community/how-much">리뷰</a></li>
						</ul>
					</div>
				</div>

				<div class="community-body-content">
						<!-- ajax로 바꾸려고 ~ -->
						<div id="board-">
							<!-- 검색 창 -->
							<div class="search-container">
								<div class="search-content">
									<div class="search-input-box">
										<form method="post" name="search" action="searchbbs.jsp">
											<table class="pull-right">
												<tr>
													<td><select class="form-control" name="searchField">
															<option value="0">선택</option>
															<option value="bbsTitle">제목</option>
															<option value="userID">작성자</option>
														</select>
													</td>
													<td><input type="text" class="form-control"
														placeholder="궁금한 내용을 입력해보세요." name="searchText" maxlength="100"></td>
													<td><button type="submit" class="btn btn-success search-btn">검색</button></td>
											</tr>
										</table>
									</form>
								</div>
							</div>
							<!-- 메인 왼쪽 끝~ -->

							<div class="posts-container-menu-bar">
								<ul class="order-pc"> 
									<li class="e-order active" data-order="recent">  
									<button class="ac-button is-md is-text tab-button "><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path></svg> 최신순</button>
									</li>
									<li class="e-order " data-order="score">		  
									<button class="ac-button is-md is-text tab-button "><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path></svg> 정확도순</button>
									</li>
									<li class="e-order " data-order="comment">
									<button class="ac-button is-md is-text tab-button "><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path></svg> 답변많은순</button>
									</li>
									<li class="e-order " data-order="recommend">
									<button class="ac-button is-md is-text tab-button "><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16"><path fill="#212529" d="M8 10c-1.105 0-2-.895-2-2s.895-2 2-2 2 .895 2 2-.895 2-2 2z" clip-rule="evenodd"></path></svg> 좋아요순</button>
									</li>
								</ul>
							</div>
						</div>
						<!--메인 오른쪽 끝~  -->
						
					</div>
				</div>
			</div>			
		</main>
		<!-- footer  -->
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>
    </div>    
</body>
</html>

