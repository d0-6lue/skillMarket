<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/common/header.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <script defer src="${root}/static/js/common/header.js"></script>
    <c:if test="${ not empty alertMsg }">
	<script>
	    alert('${alertMsg}');
	</script>
	<c:remove var="alertMsg" scope="session"/>
    </c:if>
</head>
<body>

	<div class="header-yellowarea"></div>
    
    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="${root}/static/svg/메인로고.svg" alt="로고이미지">
                    </a>
                </div>
                <div class="header-search">
                        <input type="text" placeholder="서비스를 입력해주세요">
                        <button>
                            <span class="material-symbols-outlined">
                                search
                                </span>
                        </button>
                </div>
                <div class="header-menu">
                <c:if test="${empty loginMember}">
                    <ul>
                        <li><a href="/skillmarket/expert/register">전문가 등록</a></li>
                        <li><a class="login-btn" id="login-modal-add-btn">로그인</a></li>
                        <li><a href="${root}/join">회원가입</a></li>
    
                    </ul>
                
                </c:if>
                
                <c:if test="${not empty loginMember && empty loginExpert}">
                    <ul>
                        <li><a href="/skillmarket/expert/register">전문가 등록</a></li>
                        <li><a class="login-btn" href="${root}/logout">로그아웃</a></li>
                        <li class="header-profile-li"><div class="header-profile-area">
                        	<c:if test="${empty loginMember.memberProfilePhoto}">
                            	<img src="${root}/static/svg/기본프로필.svg" alt="프로필">
                        	
                        	</c:if>
                        	
                        	<c:if test="${not empty loginMember.memberProfilePhoto}">
                        		<img alt="프로필" src="${root}/static/img/profile/${loginMember.memberProfilePhoto}">
                        	</c:if>
                        </div>
                        <div class="login-nav-area">
                            <div><a href="${root}/customer/order-list">프로젝트 관리</a></div>
                            <div><a href="${root}/my-info">프로필 정보</a></div>
                            <div><a href="${root}/expert-info">전문가 정보</a></div>
                            <div><a href="${root}/customer/favorite">찜목록</a></div>
                        </div>    
                        </li>
    
                    </ul>
                
                </c:if>

				<c:if test="${not empty loginMember && not empty loginExpert}">
				
                    <ul>
                        <li><a href="${root}/myestimate">견적서 작성</a></li>
                        <li><a class="login-btn" href="${root}/logout">로그아웃</a></li>
                        <li class="header-profile-li"><div class="header-profile-area">
                            <c:if test="${empty loginMember.memberProfilePhoto}">
                            	<img src="${root}/static/svg/기본프로필.svg" alt="프로필">
                        	
                        	</c:if>
                        	
                        	<c:if test="${not empty loginMember.memberProfilePhoto}">
                        		<img alt="프로필" src="${root}/static/img/profile/${loginMember.memberProfilePhoto}">
                        	</c:if>
                        </div>
                        <div class="login-nav-area">
                                <div><a href="${root}/customer/order-list">프로젝트 관리</a></div>
                                <div><a href="${root}/my-info">프로필 정보</a></div>
                                <div><a href="${root}/expert-info">전문가 정보</a></div>
                                <div><a href="${root}/customer/favorite">찜목록</a></div>
                        </div>
                    </li>
                        
    
                    </ul>
				</c:if>


                    

                </div>
    
            </div>

            <nav>
                <ul class="nav-list">
                    <li class="first-nav-elem">
                        <span class="material-symbols-outlined">menu</span>
                        <span class=" bold">전체 카테고리</span>
                        <div class="category-bar">
                            <div class="simple-category">
                                
                            </div>
                            <div class="detail-category-area">
                                <div class="detail-category">
                                    
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="nav-elem">
                        <a class="nav-alink regular" class="bold" href="">인기견적서</a>
                    </li>
                    <li class="nav-elem">
                        <a class="nav-alink regular" class="bold" href="${root}/community/all">커뮤니티</a>
                    </li>
                </ul>
            </nav>

        </div>


    </div>

    <div class="modal" id="modal">
        <div class="modal-body">
            <div class="m-head">
                <div class="m-img">
                    <img src="${root}/static/svg/로그인사진.svg" alt="로그인사진">
                </div>
                <form action="${root}/login" method="post">
                <div class="m-main">
                    <div class="close-btn" id="close-btn">
                        <span class="material-symbols-outlined">close</span>
                    </div>
                    <div class="login-title bold">로그인</div>
                    
                        <input type="text" name="memberId" placeholder="아이디를 입력해주세요">
                        <input type="password" name="memberPwd" placeholder="비밀번호를 입력해주세요">
                        <br>
                        <input type="hidden" name="currentUrl" value="">
                        <input type="submit" value="로그인" class="login-submit-btn">
                        <div class="idpwd-search bold">
                            <a href="${root}/forgot-id">아이디/비밀번호 찾기</a>
                        </div>
                        <button class="join-move-btn" type="button" onclick="location.href='${root}/join'">회원가입</button>
                    </div>
                </form>
                    
            </div>
            
            
        </div>
    </div>



</body>
</html>

<script>
        const currentUrl = document.querySelector('input[name=currentUrl]');
        url = window.location.pathname;
        currentUrl.value = url;

        printCategoryHover();
        function printCategoryHover(){

            const simpleCategory = document.querySelector(".simple-category");

            getCategory('big');

            const bigList = JSON.parse(sessionStorage.getItem('bigList'));

            bigList.forEach(bigCategory => {
                const categoryMenu = document.createElement('div');
                categoryMenu.classList.add('category-menu');
                categoryMenu.id = bigCategory.estimateCatNo;

                const bigCategoryDiv = document.createElement('div');
                bigCategoryDiv.classList.add('big-category');
                bigCategoryDiv.classList.add('bold');
                bigCategoryDiv.innerText = bigCategory.estimateCatName;
                categoryMenu.append(bigCategoryDiv);

                getSubCategory('mid', bigCategory.estimateCatNo);
                const midCategoryList = JSON.parse(sessionStorage.getItem('midList' + bigCategory.estimateCatNo));
                midCategoryList.forEach(midCategory => {
                    const midCategoryDiv = document.createElement('div');
                    midCategoryDiv.classList.add('mid-category');
                    midCategoryDiv.classList.add('regular');
                    midCategoryDiv.innerText = midCategory.estimateCatName;
                    categoryMenu.append(midCategoryDiv);
                })

                simpleCategory.append(categoryMenu);

            });

        }

        function getCategory(type){
            $.ajax({
                url : "${root}/home/get-category",
                type : "get",
                data : {
                    "type" : type
                },
                dataType : "json",
                success : function(list){
                    const key = type + "List";
                    sessionStorage.setItem(key, JSON.stringify(list));
                },
                error : function(er) {
                    console.log(er);
                }
            })
        }

        function getSubCategory(type, aboveNo){
            $.ajax({
                url : "${root}/home/get-category",
                type : "get",
                data : {
                    "type" : type,
                    "aboveNo" : aboveNo
                },
                dataType : "json",
                success : function(list){
                    const key = type + "List" + aboveNo;
                    sessionStorage.setItem(key, JSON.stringify(list));
                },
                error : function(er) {
                    console.log(er);
                }
            })
        }

        //----------------------------------------------------------------
        // 디테일 카테고리 호버
        categoryDetailHover();
        function categoryDetailHover() {
            const detailCategoryArea = document.querySelector(".detail-category-area");

            const detailCategory = document.querySelector(".detail-category");

            const categoryMenuList = document.querySelectorAll(".category-menu");
            categoryMenuList.forEach(categoryMenu => {

                categoryMenu.addEventListener("mouseover", function() {
                    detailCategoryArea.classList.add('detail-category-area-active');

                    detailCategory.replaceChildren('');
                    detailCategory.id = categoryMenu.id;

                    getSubCategory('mid', categoryMenu.id);
                    const midCategoryList = JSON.parse(sessionStorage.getItem('midList' + categoryMenu.id));

                    midCategoryList.forEach(midCategory => {
                        const detailCategoryMenu = document.createElement('div');
                        detailCategoryMenu.classList.add('detail-category-menu');

                        const detailMidCategory = document.createElement('div');
                        detailMidCategory.classList.add('detail-mid-category');
                        detailMidCategory.classList.add('bold');
                        detailMidCategory.innerText = midCategory.estimateCatName;
                        detailCategoryMenu.append(detailMidCategory);

                        getSubCategory('small', midCategory.estimateCatNo);
                        const smallCategoryList = JSON.parse(sessionStorage.getItem('smallList' + midCategory.estimateCatNo));

                        smallCategoryList.forEach(smallCategory => {

                            const detailSmlCategory = document.createElement('div');
                            detailSmlCategory.classList.add('detail-sml-category');
                            detailSmlCategory.classList.add('regular');
                            detailSmlCategory.innerText = smallCategory.estimateCatName;

                            detailCategoryMenu.append(detailSmlCategory);
                        })
                        detailCategory.append(detailCategoryMenu);
                    })
                })

                categoryMenu.addEventListener('mouseout', ()=>{
                    detailCategoryArea.classList.remove('detail-category-area-active');
                })
                detailCategoryArea.addEventListener('mouseover', ()=>{
                    detailCategoryArea.classList.add('detail-category-area-active');
                    
                    const categoryMenuList = document.querySelectorAll(".category-menu");
                    categoryMenuList.forEach(categoryMenu => {
                        const detailCategory = document.querySelector(".detail-category");

                        if(categoryMenu.id == detailCategory.id){
                            categoryMenu.classList.add('category-menu-hover');
                        }
                    })
                })
                detailCategoryArea.addEventListener('mouseout', ()=>{
                    detailCategoryArea.classList.remove('detail-category-area-active');
                })
            })
        }
</script>