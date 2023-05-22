<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SkillMarket</title>
<link rel="stylesheet" href="${root}/static/css/common/home.css">

</head>
<body>

	<div class="wrap">

		<%@ include file="/WEB-INF/views/common/header.jsp" %>

		<main>

			<div class="banner">
				<div class="banner-area">
					<div class="banner-letter-area">
						<div class="banner-letter bold">자유로운 거래</div>
						<div class="banner-letter2 bold">SKILLMARKET</div>
						<div class="banner-letter3 bold">01. 마켓</div>
						<div class="banner-letter3 bold">02. 자유로운 거래방식</div>
						<div class="banner-letter3 bold">03. 커뮤니티</div>
					</div>
					<div class="banner-slice-area">
						<div class="banner-slice">
							<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
								<div class="carousel-indicators">
									<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
									<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
									<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
								</div>
								<div class="carousel-inner">
								  <div class="carousel-item active" data-bs-interval="4000">
									<img src="${root}/static/svg/기본프로필.svg" class="d-block w-100" alt="...">
								  </div>
								  <div class="carousel-item" data-bs-interval="4000">
									<img src="${root}/static/svg/기본프로필.svg" class="d-block w-100" alt="...">
								  </div>
								  <div class="carousel-item">
									<img src="${root}/static/svg/기본프로필.svg" class="d-block w-100" alt="...">
								  </div>
								</div>
								<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
								  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
								  <span class="visually-hidden">Previous</span>
								</button>
								<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
								  <span class="carousel-control-next-icon" aria-hidden="true"></span>
								  <span class="visually-hidden">Next</span>
								</button>
							</div>
	
						</div>

					</div>

				</div>				
			</div>

			<div class="main-category">

				<div class="main-category-area">

					<div class="main-category-title bold">원하는 견적서 카테고리 선택하세요.</div>

					<div class="main-category-detail">

						<c:forEach items="${bigCategoryList}" var="cList">
							<a class="main-category-detail-can" href="">
								<img src="${root}/static/svg/category/${cList.estimateCatFile}" alt="로고이미지">
								<div class="bold">${cList.estimateCatName}</div>
							</a>
						</c:forEach>						

					</div>					

				</div>

			</div>

			<div class="main-category">

				<div class="main-category-area">

					<div class="main-category-title bold">인기 카테고리</div>

					<div class="main-category-detail">

						<a class="main-category-detail-can" href="">
							<img src="${root}/static/svg/category/Cat_Coding.svg" alt="로고이미지">
							<div class="bold">IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="${root}/static/svg/category/Cat_Design.svg" alt="로고이미지">
							<div class="bold">디자인</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="${root}/static/svg/category/Cat_Markerting.svg" alt="로고이미지">
							<div class="bold">마켓팅</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="${root}/static/svg/category/Cat_Sound.svg" alt="로고이미지">
							<div class="bold">영상/음향</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="${root}/static/svg/category/Cat_Sound.svg" alt="로고이미지">
							<div class="bold">영상/음향</div>
						</a>
						


					</div>					

				</div>

			</div>


		</main>
		
		
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </div>

</body>
</html>