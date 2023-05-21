<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글상세조회시 프로젝트 소개</title>

<!-- 상세보기 페이지 css -->
<link rel="stylesheet" href="${root}/static/css/estimate/detail.css">

<!-- 부트스트랩 css js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

    <div id="wrap">


        <!-- header -->
			<%@ include file="/WEB-INF/views/common/header.jsp" %>

		<main>
			<div class="esti-info-box">
            
                <!-- 메인 서비스 등록한거 및 프로필보기~ -->
                <div class="esti-info-main">
                    <div class="category-filter">IT.프로그래밍>홈페이지</div>
                    <div class="profile-image-box"><img class="profile-img" src="${root}/static/png/사과.png"></div>

                
                <div class="scrollspy-fixed">

                    <!-- 서비스 영역 메뉴바 -->
                    <nav id="navbar-example2" class="navbar bg-body-tertiary px-3 mb-3">
                        <ul class="nav nav-pills">
                            <li class="nav-item">
                                <a class="nav-link" href="#scrollspyHeading1">포트폴리오</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#scrollspyHeading2">서비스 설명</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#scrollspyHeading3">가격 정보</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="#scrollspyHeading4">서비스 평가</a>
                            </li>
                        </ul>
                    </nav>
                    <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" class="scrollspy-example bg-body-tertiary p-3 rounded-2" tabindex="0">
                        <h4 id="scrollspyHeading1">1번째임</h4>
                        <p>테스트~</p>
                        <p>테스트~</p>
                        <p>테스트~</p>

                        <p>테스트~</p>

                        <h4 id="scrollspyHeading2">2번째임</h4>
                        <p>...</p>
                        <p>테스트~</p>
                        <p>테스트~</p>
                        

                        <h4 id="scrollspyHeading3">세번쨰임</h4>
                        <p>3</p>
                        <p>테스트~</p>
                        <p>테스트~</p>

                        <h4 id="scrollspyHeading4">4번째임</h4>
                        <p>4</p>
            
                    </div>
                </div>
                </div>

                <!-- 오른쪽 영역 ~ (가격표) 프로필 등 -->
                <div class="esti-info-aside">
                    <div class="like-box">
                        <span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-heart" viewBox="0 0 16 16">
                                <path d="m8 2.748-.717-.737C5.6.281 2.514.878 1.4 3.053c-.523 1.023-.641 2.5.314 4.385.92 1.815 2.834 3.989 6.286 6.357 3.452-2.368 5.365-4.542 6.286-6.357.955-1.886.838-3.362.314-4.385C13.486.878 10.4.28 8.717 2.01L8 2.748zM8 15C-7.333 4.868 3.279-3.04 7.824 1.143c.06.055.119.112.176.171a3.12 3.12 0 0 1 .176-.17C12.72-3.042 23.333 4.867 8 15z"/>
                            </svg>10000
                        </span>
                    </div>

                    <div class="esti-pricelist-box">
                    <h2>사과 같은 홈페이지</h2>
                    <div pri></div>
                    <div class="price-list">
                        <br>
                        <div class="price text-center">100,000,000원 (VAT 포함가)</div>
                        <br>
                        <div class="service">홈페이지 제작</div>
                        <div class="price-desc">사과 같은 홈페이지 제작해드립니다</div>
                    
                        <div class="row">
                        <div class="col-md-6">
                            <div class="jobdate-box">
                            <span>작업일</span>
                            <span>1일</span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="modify-box text-right">
                            <span>수정횟수</span>
                            <span>5회</span>
                            </div>
                        </div>
                        </div>
                    
                        <div class="text-center mt-4">
                        <button class="btn btn-primary">구매하기</button>
                        </div>
                    </div>
                    </div>

                    <div class="seller-profile-box">
                        <div class="seller-name-box">
                            <p class="seller-name">홍길동</p>
                        </div>
                    
                        <div class="wrapper">
                            <img class="img-seller" src="${root}/static/png/카톡 기본프로필.png">
                        </div>
                    
                        <div class="seller-container">
                            <div class="contact-available-time">
                                <p><strong>연락가능시간 :</strong> 언제나 가능</p>
                            </div>
                            <div class="seller-intro">
                                <p><strong>소개</strong></p>
                                <br>
                                <p>안녕하세요 홍길동입니다.</p>
                                <p>사과 같은 홈페이지 만들어 드립니다.</p>
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