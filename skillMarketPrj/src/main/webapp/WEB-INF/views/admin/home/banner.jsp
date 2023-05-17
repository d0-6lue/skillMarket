<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <div id="wrap">
        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>

        <header>
                
            <h1>배너 관리</h1>
        
        </header>

        <article>
            <div id="banner_area">
                <div class="banner_area_col">
                    <div>
                        <div>배너 1</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn openModalBtn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <div>
                            <span>배너 추가</span> 
                            <span class="material-symbols-outlined">
                            add_circle
                            </span>
                        </div>
                        <div id="img_area" style="display: none;">

                        </div>
                        <input type="file" style="display: none;" accept="image/jpeg,image/x-png,image/gif">
                    </div>
                </div>

                <div class="banner_area_col">
                    <div>
                        <div>배너 2</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <div>
                            <span>배너 추가</span> 
                            <span class="material-symbols-outlined">
                            add_circle
                            </span>
                        </div>
                        <div id="img_area" style="display: none;">

                        </div>
                        <input type="file" style="display: none;" accept="image/jpeg,image/x-png,image/gif">
                    </div>
                </div>

                <div class="banner_area_col">
                    <div>
                        <div>배너 3</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <div>
                            <span>배너 추가</span> 
                            <span class="material-symbols-outlined">
                            add_circle
                            </span>
                        </div>
                        <div id="img_area" style="display: none;">

                        </div>
                        <input type="file" style="display: none;" accept="image/jpeg,image/x-png,image/gif">
                    </div>
                </div>

                <div class="banner_area_col">
                    <div>
                        <div>배너 4</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <div>
                            <span>배너 추가</span> 
                            <span class="material-symbols-outlined">
                            add_circle
                            </span>
                        </div>
                        <div id="img_area" style="display: none;">

                        </div>
                        <input type="file" style="display: none;" accept="image/jpeg,image/x-png,image/gif">
                    </div>
                </div>
            </div>
        </article>

    </div>


    <!-- 모달 -->
	<div id="bannerEdit" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h1>배너 수정</h1>
            <div id="modal_div">
               
                <img src="" alt="배너이미지">
                <input type="color">
            </div>
            
            <button id="submitBtn">등록하기</button>
        </div>
    </div>

</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/banner.css">
<script src="${root}/static/js/admin/banner.js"></script>
