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
							<div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
								<div class="carousel-inner">
									<c:forEach items="${bList}" var="bvo">
										<div class="carousel-item">
										  <img style="background-color: ${bvo.bannerBackgroundcolor};" src="${root}/static/img/banner/${bvo.bannerFile}" class="d-block w-100" alt="...">
										</div>
	  								</c:forEach>
								</div>
								<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
								  <span class="carousel-control-prev-icon" aria-hidden="true"></span>
								  <span class="visually-hidden">Previous</span>
								</button>
								<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
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
							<a class="main-category-detail-can" href="${root}/category?categoryNo=${cList.estimateCatNo}">
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

						<c:forEach items="${pList}" var="pvo">
							<a class="main-category-detail-can" href="${root}/category?categoryNo=${pvo.estimateCatNo}">
								<img src="${root}/static/svg/category/${pvo.estimateCatFile}" alt="로고이미지">
								<div class="bold">${pvo.estimateCatName}</div>
							</a>
						</c:forEach>
						
					</div>					

				</div>

			</div>


		</main>
		
		
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </div>

</body>
</html>

<script>

	const slideImg = document.querySelectorAll(".carousel-item");
	slideImg[0].classList.add('active');

	console.log(slideImg.length);
	for(let i = 0; i < slideImg.length - 1; i++){
		slideImg[i].setAttribute('data-bs-interval','4000')
	}

</script>