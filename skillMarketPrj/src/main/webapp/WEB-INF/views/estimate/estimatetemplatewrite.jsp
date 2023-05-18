<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>견적서 작성 페이지</title>

<!-- 견적서 작성 페이지 css -->
<link rel="stylesheet" href="${root}/static/css/estimate/estimatewrite.css">

<!-- 견적서 작성 페이지 js -->
<script defer src="${root}/static/js/estimate/estimatewrite1.js"></script>
<script defer src="${root}/static/js/estimate/estimatewrite.js"></script>

<!-- 부트스트랩 css js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

    <div id="wrap">

        <!-- header -->
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <form id="estimate-post-btn" method="post" action="${root}/myestimate" enctype="multipart/form-data">
        <main>
                <div class="estimate-main-box">
                    <!-- 왼쪽 사이드바 -->
                    <div class="sidebar">
                        <div class="list-group" id="list-tab" role="tablist">
                            <a class="list-group-item list-group-item-action active" id="list-home-list" data-bs-toggle="list" href="#list-home" role="tab" aria-controls="list-home">기본정보</a>
                            <a class="list-group-item list-group-item-action" id="list-price-list" data-bs-toggle="list" href="#list-price" role="tab" aria-controls="list-price">가격설정</a>
                            <a class="list-group-item list-group-item-action" id="list-service-list" data-bs-toggle="list" href="#list-service" role="tab" aria-controls="list-service">서비스 설명</a>
                            <a class="list-group-item list-group-item-action" id="list-image-list" data-bs-toggle="list" href="#list-image" role="tab" aria-controls="list-image">이미지</a>
                            <a class="list-group-item list-group-item-action" id="list-faq-list" data-bs-toggle="list" href="#list-faq" role="tab" aria-controls="list-faq">FAQ</a>
                        </div>
                    </div>
                
                <!-- 오른쪽 입력 폼 -->
                <div class="col-8 form">
                    <div class="tab-content" id="list-tabContent" data-bs-spy="scroll" data-bs-target="#list-tab">
                        <!-- 기본정보 -->
                        <div class="tab-pane fade show active" id="list-home" role="tabpanel" aria-labelledby="list-home-list">
                            <div class="form-group">
                                <label for="job-title">제목</label>
                                <input type="text" class="form-control" id="job-title" name="job-title" placeholder="서비스를 잘 드러낼 수 있는 제목을 입력하세요 최대 30자까지 가능합니다." maxlength="30">
                            </div>
                           <!-- 카테고리 -->
                           <div class="form-group">
                                <label for="job-category">카테고리</label>
                                <div class="form-group">
                                    <select class="form-control" id="job-category1">
                                        <option value="" selected>대분류</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <select class="form-control" id="job-category2" disabled>
                                        <option value="" selected>중분류</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <select class="form-control" id="job-category3" disabled>
                                        <option value="" selected>소분류</option>
                                    </select>
                                </div>
                                <input type="hidden" id="estiCatevoList" value='${estiCatevoList}'/>
                            </div>
                            <!-- 포트폴리오 최대(5장) -->
                            <div class="form-group form-control-lg portfolio-container">
                                <div id="job-portfolio-container">
                                    <label for="job-portfolio">포트폴리오</label>
                                    <input type="file" id="portfolio-submit" name="portfolio-submit" accept="image/*" multiple>
                                    <div id="preview-container"></div>
                                </div>                     
                            </div>
                            <div>
                                <button class="btn btn-primary" type="button" id="cancel-button">파일선택 초기화</button>
                            </div>
                        </div>
    
                        <!-- 가격설정 -->
                        <div class="tab-pane fade" id="list-price" role="tabpanel" aria-labelledby="list-price-list">
                            <div class="form-group">
                                <label for="job-duration">작업기간</label>
                                <select class="form-control" id="job-duration" name="job-duration">
                                    <option value="" disabled selected>작업기간</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                    <option value="3">3</option>
                                    <option value="4">4</option>
                                    <option value="5">5</option>
                                    <option value="6">6</option>
                                    <option value="7">7</option>
                                    <option value="8">8</option>
                                    <option value="9">9</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                    <option value="24">24</option>
                                    <option value="25">25</option>
                                    <option value="26">26</option>
                                    <option value="27">27</option>
                                    <option value="28">28</option>
                                    <option value="29">29</option>
                                    <option value="30">30</option>
                                </select>
                            </div>
                            <div class="form-group">
                            <label for="job-price">${estiCatevolist}</label>
                                <input type="text" class="form-control" id="job-price" name="job-price" placeholder="5000">
                            </div>
                            <div id="customOptionsContainer"></div>
    
                            <button id="addCustomOptionsButton" type="button">추가옵션 추가</button>
                        </div>
    
                        <!-- 서비스설명 -->
                        <div class="tab-pane fade" id="list-service" role="tabpanel" aria-labelledby="list-service-list">
                            <div class="form-group">
                            <label for="job-summary">한줄소개</label>
                            <input type="text" class="form-control form-control-lg" id="job-summary" name="job-summary" placeholder="한줄소개를 입력하세요">
                            </div>
                            <hr>
                            <h4>서비스설명</h4>
                            <div class="form-group">
                            <textarea class="form-control form-control-lg" id="job-description" name="job-description" rows="10" placeholder="서비스설명을 입력하세요"></textarea>
                            </div>
                        </div>
    
                        <!-- 이미지 -->
                        <div class="tab-pane fade" id="list-image" role="tabpanel" aria-labelledby="list-image-list">

                            <!-- 메인 이미지(필수) 1장 -->
                            <div class="form-group">
                                <label for="job-mainfile-upload">메인이미지등록(필수)</label>
                                <input class="form-control form-control-lg" type="file" name="main-file-upload" id="main-file-upload" onchange="previewImage(this, 'main-image-preview')">
                            </div>
                            <div class="image-preview" id="main-image-preview-container">
                                <img id="main-image-preview" src="" alt="메인이미지 미리보기">
                                <button type="button" class="remove-image" onclick="removeImage('main-image-preview-container')">X</button>
                            </div>
                            <hr>
                            <!-- 상세 이미지 (6장) -->
                            <div class="form-group form-control-lg image-container">
                                <div id="job-subimage-container">
                                    <label for="job-subimage">상세이미지</label>
                                    <input type="file" id="sub-file-upload" name="sub-file-upload" accept="image/*" multiple>
                                    <div id="subimage-preview-container"></div>
                                </div>
                            </div>
                            <button class="btn btn-primary" type="button" id="cancel-subimage-button">상세이미지 초기화</button>

                            
                        </div>
    
    
                        <!-- FAQ -->
                        <div class="tab-pane fade show" id="list-faq" role="tabpanel" aria-labelledby="list-faq-list">
                            <div class="form-group">
                                <label for="custom-qa">자주 묻는 질문</label>
                                <div class="custom-qa-box">
                                    <div class="custom-qa-content">
                                        <div id="customQAGroupContainer"></div>
                                    </div>
                                    <button id="addQuestionButton" type="button">질문 추가</button>
                                </div>
                            </div>
                            <button id="submitButton" type="button">전송</button>
                        </div>
                    </main>
                    <!-- footer -->
                    <div class="estimate-footer-box">
                        <div class="estimate-next-btn">
                            <input class="btn btn-secondary" type="submit" value="다음" id="nextButton">
                        </div>                        
                    </div>
            </form>
                
    </div>

</body>
</html>