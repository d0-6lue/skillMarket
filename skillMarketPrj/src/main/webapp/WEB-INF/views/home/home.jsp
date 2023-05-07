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
					<div class="banner-letter">자유로운 거래 스킬마켓</div>
					<div class="banner-slide">슬라이드란</div>

				</div>				
			</div>

			<div class="main-category">

				<div class="main-category-area">

					<div class="main-category-title bold">원하는 견적서 카테고리 선택하세요.</div>

					<div class="main-category-detail">

						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						


					</div>					

				</div>

			</div>

			<div class="favorite-category">

				<div class="favorite-category-area">

					<div class="favorite-category-title bold">인기 카테고리</div>
					<div class="favorite-category-detail">
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
						<a class="main-category-detail-can" href="">
							<img src="" alt="로고이미지">
							<div>IT/프로그래밍</div>
						</a>
					</div>
					
				</div>

			</div>


		</main>
		
		
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>

    </div>

</body>
</html>