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
                    <ul>
                        <li><a href="/skillmarket/expert/register">전문가 등록</a></li>
                        <li><a class="login-btn" id="login-modal-add-btn">로그인</a></li>
                        <li><a href="${root}/join">회원가입</a></li>
    
                    </ul>
                </div>
    
            </div>

            <nav>
                <ul class="nav-list">
                    <li class="first-nav-elem">
                        <span class="material-symbols-outlined">menu</span>
                        <span class=" bold">전체 카테고리</span>
                        <div class="category-bar">
                            <div class="simple-category">
                                <div class="category-menu bc-design">
                                    <div class="big-category bold">디자인</div>
                                    <a class="mid-category regular" href="">디자인1</a>
                                    <a class="mid-category regular" href="">디자인2</a>
                                    <a class="mid-category regular" href="">디자인3</a>
                                    <a class="mid-category regular" href="">디자인4</a>
                                    <a class="mid-category regular" href="">디자인5</a>
                                </div>
                                <div class="category-menu">
                                    <div class="big-category bold">IT/프로그래밍</div>
                                    <a class="mid-category regular" href="">IT/프로그래밍1</a>
                                    <a class="mid-category regular" href="">IT/프로그래밍2</a>
                                    <a class="mid-category regular" href="">IT/프로그래밍3</a>
                                    <a class="mid-category regular" href="">IT/프로그래밍4</a>
                                    <a class="mid-category regular" href="">IT/프로그래밍5</a>
                                </div>
                                <div class="category-menu">
                                    <div class="big-category bold">영상,사진,음향</div>
                                    <a class="mid-category regular" href="">영상,사진,음향1</a>
                                    <a class="mid-category regular" href="">영상,사진,음향2</a>
                                    <a class="mid-category regular" href="">영상,사진,음향3</a>
                                    <a class="mid-category regular" href="">영상,사진,음향4</a>
                                    <a class="mid-category regular" href="">영상,사진,음향5</a>
                                </div>
                                <div class="category-menu">
                                    <div class="big-category bold">마켓팅</div>
                                    <a class="mid-category regular" href="">마켓팅1</a>
                                    <a class="mid-category regular" href="">마켓팅2</a>
                                    <a class="mid-category regular" href="">마켓팅3</a>
                                    <a class="mid-category regular" href="">마켓팅4</a>
                                    <a class="mid-category regular" href="">마켓팅5</a>
                                </div>
                            </div>
                            <div class="detail-category-area">
                                <div class="detail-category">
                                    <div class="detail-category-menu">
                                        <div class="detail-mid-category bold">로고·브랜딩</div>
                                        <div class="detail-sml-category regular">로고 디자인</div>
                                        <div class="detail-sml-category regular">브랜드 디자인·가이드</div>
                                    </div>
    
                                    <div class="detail-category-menu">
                                        <div class="detail-mid-category bold">웹·모바일 디자인</div>
                                        <div class="detail-sml-category regular">웹 디자인</div>
                                        <div class="detail-sml-category regular">웹·모바일 디자인</div>
                                        <div class="detail-sml-category regular">템플릿형 홈페이지</div>
                                        <div class="detail-sml-category regular">아이콘·버튼</div>
                                    </div>
    
                                    <div class="detail-category-menu">
                                        <div class="detail-mid-category bold">캐릭터·일러스트</div>
                                        <div class="detail-sml-category regular">일러스트</div>
                                        <div class="detail-sml-category regular">캐리커처</div>
                                        <div class="detail-sml-category regular">웹툰·콘티</div>
                                        <div class="detail-sml-category regular">캐릭터</div>
                                        <div class="detail-sml-category regular">이모티콘</div>
                                    </div>
    
                                    <div class="detail-category-menu">
                                        <div class="detail-mid-category bold">공간·건축</div>
                                        <div class="detail-sml-category regular">도면제작·수정</div>
                                        <div class="detail-sml-category regular">인테리어 컨설팅</div>
                                        <div class="detail-sml-category regular">전시·무대 디자인</div>
                                        <div class="detail-sml-category regular">간판·시공</div>
                                    </div>
    
                                    <div class="detail-category-menu">
                                        <div class="detail-mid-category bold">인쇄·홍보물</div>
                                        <div class="detail-sml-category regular">명함</div>
                                        <div class="detail-sml-category regular">전단지·포스터·인쇄물</div>
                                        <div class="detail-sml-category regular">현수막·X배너</div>
                                        <div class="detail-sml-category regular">메뉴판</div>
                                        <div class="detail-sml-category regular">홍보물 인쇄·출력</div>
                                        <div class="detail-sml-category regular">스티커·봉투·초대장</div>
                                    </div>
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
                    
                        <input type="text" name="loginId" placeholder="아이디를 입력해주세요">
                        <input type="password" name="loginPwd" placeholder="비밀번호를 입력해주세요">
                        <br>
                        <input type="submit" value="로그인" class="login-submit-btn">
                        <div class="idpwd-search bold">
                            <a href="${root}/forgot-id">아이디/비밀번호 찾기</a>
                        </div>
                        <button class="join-move-btn" type="button">회원가입</button>
                    </div>
                </form>
                    
            </div>
            
            
        </div>
    </div>

        


</body>
</html>