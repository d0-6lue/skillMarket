<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 선택시 프로젝트 리스트 ~</title>

<!-- 상세보기 페이지 css -->
<link rel="stylesheet" href="${root}/static/css/estimate/estimateList.css">

<!-- 부트스트랩 css js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>

    <div id="wrap">
        <!-- header -->
        <%@ include file="/WEB-INF/views/common/header.jsp" %>

		<main>
            <div class="esti-list-box">
                <div class="sidebar">
                    <div class="category-container">
                      <div class="category-title">
                        <strong>카테고리</strong>
                      </div>
                      <div id="middleCategories" class="middle-category-container">
                        <!-- 중분류 카테고리가 동적으로 추가될 위치 -->
                        <div id="subCategories" class="sub-category-container">
                            <!-- 소분류 카테고리가 동적으로 추가될 위치 -->
                          </div>
                      </div>
                      
                    </div>
                  </div>
                  
                  
                  
				
                <div class="main-list-box">
                    <div class="category-total-box">
                        <div class="category-select">홈 > 카테고리</div>
                        <div class="category-menu-bar-container">
                            <span class="category-menu-bar">
                                <select class="form-select" id="category-1">
                                    <option value="" disabled selected>카테고리 선택</option>
                                    <option value="category1">카테고리1</option>
                                    <option value="category2">카테고리2</option>
                                </select>
                                <select class="form-select" id="category-2">
                                    <option value="" disabled selected>서비스 옵션</option>
                                    <option value="category3">카테고리3</option>
                                    <option value="category4">카테고리4</option>
                                </select>
                                <select class="form-select" id="category-3">
                                    <option value="" disabled selected>전문가 옵션</option>
                                    <option value="category5">카테고리5</option>
                                    <option value="category6">카테고리6</option>
                                </select>
                                <select class="form-select" id="category-4">
                                    <option value="" disabled selected>예산</option>
                                    <option value="category7">카테고리7</option>
                                    <option value="category8">카테고리8</option>
                                </select>
                            </span>
                        </div>       
                    </div>


                    <div class="seller-service-box">
                        
                        <div class="four-service-list">
                           
                            <c:forEach var="estimate" items="${estimateList}">
                            <div class="service-border">
                                <div class="profile-image-box">
                                <div id="content">
                                    <c:out value="${estimate.mainImage}" escapeXml="false" />
                                </div>
                                </div>
                                <div class="expert-info">
                                <div class="expert-box">
                                    <div class="expert-name">${estimate.memberNick}</div>
                                    <div class="expert-badge">${estimate.estimateNo}</div>
                                </div>
                                <div class="expert-title">${estimate.estimateTitle}</div>
                                <div class="expert-account">${estimate.estimateLineIntroduction}</div>
                                <div class="expert-balance">${estimate.estimatePrice}원</div>
                                <div class="heart-area">
                                    <div class="heart-icon">❤️</div>
                                    <div class="expert-ratings">${estimate.reviewCount}</div>
                                </div>
                                </div>
                            </div>
                            </c:forEach>
                 
                        </div>

                   
                        </div>
                    </div>

                    <div class="page-area">
                        <c:if test="${pv.currentPage > 1}">
                            <a class="btn btn-primary" href="${root}/category?categoryNo=${catvo.estimateCatNo}&page=${pv.currentPage - 1}">이전</a>
                        </c:if>
                        <c:forEach begin="${pv.startPage}" end="${pv.endPage}" step="1" var="i">
                            <c:if test="${pv.currentPage != i}">
                                <a class="btn btn-primary btn-sm" href="${root}/category?categoryNo=${catvo.estimateCatNo}&page=${i}">${i}</a>
                            </c:if>
                            <c:if test="${pv.currentPage == i}">
                                <a class="btn btn-primary btn-sm">${i}</a>
                            </c:if>
                        </c:forEach>
                        <c:if test="${pv.currentPage < pv.maxPage}">
                            <a class="btn btn-primary btn-sm" href="${root}/category?categoryNo=${catvo.estimateCatNo}&page=${pv.currentPage + 1}">다음</a>
                        </c:if>
                    </div>
                    
                        
                </div>
				
			</div>

			
		</main>

        <!-- footer  -->
			<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
		

	</div>

    <script>
    let cateList = $.parseJSON('${estiCatevoList}');

    let middleCategories = []; // 중분류 카테고리 배열
    let subCategories = []; // 소분류 카테고리 배열

    let cateSelect2 = $("#middleCategories");
    let cateSelect3 = $("#subCategories");

    let selectedCategories = $("#selectedCategories");

    /* 중분류 카테고리 초기화 */
    for (let i = 0; i < cateList.length; i++) {
    if (cateList[i].estimateCatScope === "2") { // 중분류 카테고리인 경우
        let middleCategory = {
        name: cateList[i].estimateCatName, // 카테고리 이름
        code: cateList[i].estimateCatNo, // 카테고리 코드
        parent: cateList[i].aboveCatNo, // 상위 카테고리 번호
        subCategories: [] // 소분류 카테고리 배열
        };
        middleCategories.push(middleCategory); // 중분류 카테고리를 배열에 추가
    }
    }

    $(document).ready(function () {
    for (let i = 0; i < middleCategories.length; i++) {
        cateSelect2.append(
        "<div class='middle-category' data-code='" +
        middleCategories[i].code +
        "'>" +
        middleCategories[i].name +
        "</div>"
        );
    }
    });

    /* 중분류 카테고리 선택 이벤트 */
    $(document).on("click", ".middle-category", function () {
    let selectedCateCode = $(this).attr("data-code");
    let selectedMiddleCategory = middleCategories.find(
        (category) => category.code === selectedCateCode
    );

    cateSelect3.empty();
    selectedMiddleCategory.subCategories = [];

    for (let i = 0; i < cateList.length; i++) {
        if (
        cateList[i].estimateCatScope === "3" &&
        cateList[i].aboveCatNo === selectedMiddleCategory.code
        ) {
        let subCategory = {
            name: cateList[i].estimateCatName,
            code: cateList[i].estimateCatNo
        };
        selectedMiddleCategory.subCategories.push(subCategory);
        }
    }

    for (let i = 0; i < selectedMiddleCategory.subCategories.length; i++) {
        cateSelect3.append(
        "<div class='sub-category' data-code='" +
        selectedMiddleCategory.subCategories[i].code +
        "'>" +
        selectedMiddleCategory.subCategories[i].name +
        "</div>"
        );
    }

    console.log("선택된 중분류 카테고리: " + selectedMiddleCategory.name);
    console.log("소분류 카테고리 초기화");

    // 선택된 카테고리 표시
    selectedCategories.text(selectedMiddleCategory.name);

    // 소분류 카테고리 이름 출력
    for (let i = 0; i < selectedMiddleCategory.subCategories.length; i++) {
        console.log("소분류 카테고리: " + selectedMiddleCategory.subCategories[i].name);
    }
    });

    /* 소분류 카테고리 선택 이벤트 */
    $(document).on("click", ".sub-category", function () {
    let selectedCateCode = $(this).attr("data-code");
    let selectedSubCategory = selectedMiddleCategory.subCategories.find(
        (category) => category.code === selectedCateCode
    );

    console.log("선택된 소분류 카테고리: " + selectedSubCategory.name);
    });




        
    
    </script>

</body>
</html>