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

<script src="https://cdn.ckeditor.com/ckeditor5/37.1.0/classic/ckeditor.js"></script>



</head>


<body>

    <div id="wrap">

        <!-- header -->
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <form id="estimate-post-btn" method="post" action="${root}/myestimate">
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
                           <div class="cate_wrap">
                                <span>대분류</span>
                                <select class="cate1">
                                    <option selected value="none">선택</option>
                                </select>
                            </div>
                            <div class="cate_wrap">
                                <span>중분류</span>
                                <select class="cate2">
                                    <option selected value="none">선택</option>
                                </select>
                            </div>
                            <div class="cate_wrap">
                                <span>소분류</span>
                                <select class="cate3" name="cateCode">
                                    <option selected value="none">선택</option>
                                </select>
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
                            <label for="job-price">가격</label>
                                <input type="text" class="form-control" id="job-price" name="job-price" placeholder="5000">
                            </div>
                            <div id="customOptionsContainer">
                                 <div id="customOptionsContainer">
                                    <div class="custom-options-box">
                                        <div class="custom-options-content">
                                            <input type="text" class="custom-options-title" name="additionalOptions[].title" placeholder="제목을 입력하세요">
                                            <input type="text" class="custom-price-amount" name="additionalOptions[].price" placeholder="1000원 추가시">
                                            <input type="text" class="custom-options-work" name="additionalOptions[].work" placeholder="일수 입력하세요">
                                        </div>
                                    </div>
                                </div>
                                <button type="button" onclick="addCustomOptions()">추가옵션 추가</button>
                            </div>
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
                            <input type="hidden" name="is-main-image" id="is-main-image">
                            <label for="is-main-image">메인 이미지</label>
                        </div>
                        <div class="image-preview" id="main-image-preview-container">
                            <img id="main-image-preview" src="">
                                <textarea id="editor" name="main-image" class="custom-toolbar"></textarea>
                        </div>

                        <button type="button" onclick="resetMainImage()">초기화</button>

                        <hr>

                        <!-- Single file input field visible to the user -->
                        <div class="form-group form-control-lg image-container">
                            <div id="job-subimage-container">
                                <label for="is-sub-images">상세 이미지(선택)</label>
                                <textarea id="editor1" name="sub-image" class="custom-toolbar"></textarea>
                            </div>
                           
                        </div>

                       
                           

                            
                        </div>
    
    
                        <!-- FAQ -->
                        <div class="tab-pane fade show" id="list-faq" role="tabpanel" aria-labelledby="list-faq-list">
                            <div class="custom-qa-box">
                                <div class="custom-qa-content">
                                    <div id="customQAGroupContainer"></div>
                                </div>
                                <button id="addQuestionButton" type="button">질문 추가</button>
                            </div>
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

    <script>
        let cateList = $.parseJSON('${estiCatevoList}');
        
        let cate1Array = new Array();
        let cate2Array = new Array();
        let cate3Array = new Array();
        let cate10Obj = new Object();
        let cate20Obj = new Object();
        let cate30Obj = new Object();
    
        let cateSelect1 = $(".cate1");		
        let cateSelect2 = $(".cate2");
        let cateSelect3 = $(".cate3");
    
        for(let i = 0; i < cateList.length; i++){
            if(cateList[i].estimateCatScope === "1"){
                let cate1Obj = new Object();
                
                cate1Obj.cateName = cateList[i].estimateCatName;
                cate1Obj.cateCode = cateList[i].estimateCatNo;
                cate1Obj.cateParent = cateList[i].aboveCatNo;
                
                cate1Array.push(cate1Obj);				
            }	
        }
    
        $(document).ready(function(){
            
        });
    
        /* 카테고리 배열 초기화 메서드 */
        function makeCateArray(obj, array, cateList, estimateCatScope){
            for(let i = 0; i < cateList.length; i++){
                if(cateList[i].estimateCatScope === estimateCatScope){
                    obj = new Object();
                    
                    obj.cateName = cateList[i].estimateCatName;
                    obj.cateCode = cateList[i].estimateCatNo;
                    obj.cateParent = cateList[i].aboveCatNo;
                    
                    array.push(obj);                
                    
                }
            }
        }
            
        /* 배열 초기화 */
        let cate1Obj = new Object();
        let cate2Obj = new Object();
        let cate3Obj = new Object();
        
        makeCateArray(cate1Obj, cate1Array, cateList, "1");
        makeCateArray(cate2Obj, cate2Array, cateList, "2");
        makeCateArray(cate3Obj, cate3Array, cateList, "3");
        
        for(let i = 0; i < cate1Array.length; i++){
            cateSelect1.append("<option value='" + cate1Array[i].cateCode + "'>" + cate1Array[i].cateName + "</option>");
        }

       /* 중분류 <option> 태그 */
        $(cateSelect1).on("change",function(){
            
            let selectVal1 = $(this).find("option:selected").val();	
            
            cateSelect2.children().remove();
            cateSelect3.children().remove();
            
            cateSelect2.append("<option value='none'>선택</option>");
            cateSelect3.append("<option value='none'>선택</option>");
            
            for(let i = 0; i < cate2Array.length; i++){
                if(selectVal1 === cate2Array[i].cateParent){
                    cateSelect2.append("<option value='"+cate2Array[i].cateCode+"'>" + cate2Array[i].cateName + "</option>");	
                }
            }// for
            
        });

        /* 소분류 <option>태그 */
        $(cateSelect2).on("change",function(){
            
            let selectVal2 = $(this).find("option:selected").val();
            
            cateSelect3.children().remove();
            
            cateSelect3.append("<option value='none'>선택</option>");		
            
            for(let i = 0; i < cate3Array.length; i++){
                if(selectVal2 === cate3Array[i].cateParent){
                    cateSelect3.append("<option value='"+cate3Array[i].cateCode+"'>" + cate3Array[i].cateName + "</option>");	
                }
            }// for		
            
        });	


        //이미지관련(그냥 textarea로 넣어버림)
        document.addEventListener("DOMContentLoaded", function() {
			ClassicEditor.create(document.querySelector("#editor"), {
				toolbar: {
					items: [
						"imageUpload",
					],
					shouldNotGroupWhenFull: true
				},
				language: "kr",
				htmlEncodeOutput: false, // 엔터를 &nbsp;로 변환하지 않음
				entities: false // 공백 문자 등의 HTML 엔티티 변환 해제
			})
				.then(editor => {
					editor.plugins.get("FileRepository").createUploadAdapter = loader => {
						return {
							upload: async () => {
								const file = await loader.file;
								const imageUrl = await uploadImage(file);
								return {
									default: imageUrl
								};
							}
						};
					};

					function uploadImage(file) {
						return new Promise((resolve, reject) => {
							const formData = new FormData();
							formData.append("file", file);

							fetch("${root}/upload/community", {
								method: "POST",
								body: formData
							})
								.then(response => response.json())
								.then(data => {
									const imageUrl = data.filePath;
									resolve(imageUrl);
								})
								.catch(error => {
									console.error(error);
									reject(error);
								});
						});
					}
				})
				.catch(error => {
					console.error(error);
				});
		});

        document.addEventListener("DOMContentLoaded", function() {
			ClassicEditor.create(document.querySelector("#editor1"), {
				toolbar: {
					items: [
						"imageUpload",
					],
					shouldNotGroupWhenFull: true
				},
				language: "kr",
				htmlEncodeOutput: false, // 엔터를 &nbsp;로 변환하지 않음
				entities: false // 공백 문자 등의 HTML 엔티티 변환 해제
			})
				.then(editor => {
					editor.plugins.get("FileRepository").createUploadAdapter = loader => {
						return {
							upload: async () => {
								const file = await loader.file;
								const imageUrl = await uploadImage(file);
								return {
									default: imageUrl
								};
							}
						};
					};

					function uploadImage(file) {
						return new Promise((resolve, reject) => {
							const formData = new FormData();
							formData.append("file", file);

							fetch("${root}/upload/community", {
								method: "POST",
								body: formData
							})
								.then(response => response.json())
								.then(data => {
									const imageUrl = data.filePath;
									resolve(imageUrl);
								})
								.catch(error => {
									console.error(error);
									reject(error);
								});
						});
					}
				})
				.catch(error => {
					console.error(error);
				});
		});
        
    </script>
    

</body>
</html>