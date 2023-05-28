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
                <div class="banner_area_col" id="img_area_1">
                    <div>
                        <div>배너 1</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn openModalBtn" disabled>
                    </div>
                    <c:if test="${empty bannerList[0] or bannerList[0].bannerStatus == 'X'}">
                        <div class="banner_add_area">
                            <div>
                                <span>배너 추가</span> 
                                <span class="material-symbols-outlined">
                                add_circle
                                </span>
                            </div>
                            
                            <input type="text" id="Yn_check_1" value="XXX" style="display: none;">
                            <input type="color" value="#737373" id="back_color_area" class="colorpicker_back_1" disabled></input>
                        </div>        
                    </c:if>
                    <c:if test="${not empty bannerList[0] and bannerList[0].bannerStatus ne 'X'}">
                        <div class="banner_add_area" >
                            <img class="banner_aticle_img_box" id="modal_img_1" src="${root}/static/img/banner/배너이미지1.png" alt="배너이미지">
                            <input type="text" id="Yn_check_1" value="YYY" style="display: none;">
                            <input type="color" value="${bannerList[0].bannerBackgroundcolor}" id="back_color_area" class="colorpicker_back_1" disabled></input>
                        </div>
                    </c:if>
                </div>

                <div class="banner_area_col" id="img_area_2">
                    <div>
                        <div>배너 2</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn openModalBtn" disabled>
                    </div>
                    <c:if test="${empty bannerList[1] or bannerList[1].bannerStatus == 'X'}">
                        <div class="banner_add_area">
                            <div>
                                <span>배너 추가</span> 
                                <span class="material-symbols-outlined">
                                add_circle
                                </span>
                            </div>
                            <input type="text" id="Yn_check_2" value="XXX" style="display: none;">
                            <input type="color" value="#737373" id="back_color_area" class="colorpicker_back_2" disabled></input>
                        </div>        
                    </c:if>
                    <c:if test="${not empty bannerList[1] and bannerList[1].bannerStatus ne 'X'}">
                        <div class="banner_add_area" >
                            <img class="banner_aticle_img_box" id="modal_img_2" src="${root}/static/img/banner/배너이미지2.png" alt="배너이미지">
                            <input type="text" id="Yn_check_2" value="YYY" style="display: none;">
                            <input type="color" value="${bannerList[1].bannerBackgroundcolor}" id="back_color_area" class="colorpicker_back_2" disabled></input>
                        </div>
                    </c:if>
                </div>

                <div class="banner_area_col" id="img_area_3">
                    <div>
                        <div>배너 3</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn openModalBtn" disabled>
                    </div>
                    <c:if test="${empty bannerList[2] or bannerList[2].bannerStatus == 'X'}">
                        <div class="banner_add_area">
                            <div>
                                <span>배너 추가</span> 
                                <span class="material-symbols-outlined">
                                add_circle
                                </span>
                            </div>
                            <input type="text" id="Yn_check_3" value="XXX" style="display: none;">
                            <input type="color" value="#737373" id="back_color_area" class="colorpicker_back_3" disabled></input>
                        </div>        
                    </c:if>
                    <c:if test="${not empty bannerList[2] and bannerList[2].bannerStatus ne 'X'}">
                        <div class="banner_add_area" >
                            <img class="banner_aticle_img_box" id="modal_img_3" src="${root}/static/img/banner/배너이미지3.png" alt="배너이미지">
                            <input type="text" id="Yn_check_3" value="YYY" style="display: none;">
                            <input type="color" value="${bannerList[2].bannerBackgroundcolor}" id="back_color_area" class="colorpicker_back_3" disabled></input>
                        </div>
                    </c:if>
                </div>

                <div class="banner_area_col" id="img_area_4">
                    <div>
                        <div>배너 4</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn openModalBtn" disabled>
                    </div>
                    <c:if test="${empty bannerList[3] or bannerList[3].bannerStatus == 'X'}">
                        <div class="banner_add_area">
                            <div>
                                <span>배너 추가</span> 
                                <span class="material-symbols-outlined">
                                add_circle
                                </span>
                            </div>
                            <input type="text" id="Yn_check_4" value="XXX" style="display: none;">
                            <input type="color" value="#737373" id="back_color_area" class="colorpicker_back_4" disabled>
                        </div>        
                    </c:if>
                    <c:if test="${not empty bannerList[3] and bannerList[3].bannerStatus ne 'X'}">
                        <div class="banner_add_area" >
                            <img class="banner_aticle_img_box" id="modal_img_4" src="${root}/static/img/banner/배너이미지4.png" alt="배너이미지">
                            <input type="text" id="Yn_check_4" value="YYY" style="display: none;">
                            <input type="color" value="${bannerList[3].bannerBackgroundcolor}" id="back_color_area" class="colorpicker_back_4" disabled></input>
                        </div>
                    </c:if>
                </div>
            </div>
        </article>

    </div>

       
    <!-- 모달 -->

    <c:set var="initialCount" value="${bannerList.size()}" />
    
    <c:forEach items="${bannerList}" var="banner" varStatus="loop">
        
        <div id="bannerEdit" class="modal img_edit_${loop.index + 1}">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h1>배너 ${loop.index + 1}</h1>
                <div class="modal_img_area_box">
                    <c:if test="${not empty banner and banner.bannerStatus ne 'X'}">
                            <div class="modal_img_area" id="modal_upload_img">
                                <img class="modal_img_box" id="modal_img_${loop.index + 1}" src="${root}/static/img/banner/배너이미지${loop.index + 1}.png" alt="배너이미지">
                            </div>
                            <input type="color" id="back_color_${loop.index + 1}" class="modal_img_area_box_backGround" value="${banner.bannerBackgroundcolor}">
                    </c:if>
                    <c:if test="${empty banner or banner.bannerStatus == 'X'}">
                            <div class="modal_img_area" id="modal_upload_img" >
                                <label  class="modal_upload_img" id="modal_upload_img_${loop.index + 1}">
                                    이미지 업로드
                                    <input type="file" class="imageUpload" id="imageUpload_Id_${loop.index + 1}" value="이미지 업로드">
                                </label>
                            </div>
                            <input type="color" id="back_color_${loop.index + 1}" class="modal_img_area_box_backGround colorpicker_back_${loop.index + 1}" value="#737373">
                    </c:if>
                    
                </div>
                <div id="modal_div" class="delete_bth delete_bth_${loop.index + 1}">
                    ⚠ 삭제 하기
                </div>
                <button id="submitBtn" class="submitBtn_NO_${loop.index + 1}">등록하기</button>
            </div>
        </div>

    </c:forEach>
    
    <c:if test="${bannerList.size() < 4}">
        <c:set var="remainingItems" value="${4 - bannerList.size()}" />
        
        
        <c:forEach begin="${initialCount}" end="${initialCount + remainingItems-1}" varStatus="loop">
        <div id="bannerEdit" class="modal img_edit_${loop.index + 1}">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h1>배너 ${loop.index + 1}</h1>
                <div class="modal_img_area_box">
                    <c:if test="${not empty banner and banner.bannerStatus ne 'X'}">
                            <div class="modal_img_area" id="modal_upload_img">

                                <img class="modal_img_box" id="modal_img_${loop.index + 1}" src="${root}/static/img/banner/배너이미지${loop.index + 1}.png" alt="배너이미지">

                            </div>
                            <input type="color" id="back_color_${loop.index + 1}" class="modal_img_area_box_backGround" value="${banner.bannerBackgroundcolor}">
                    </c:if>
                    <c:if test="${empty banner or banner.bannerStatus == 'X'}">
                        <div class="modal_img_area" id="modal_upload_img" >

                            <label  class="modal_upload_img" id="modal_upload_img_${loop.index + 1}">
                                이미지 업로드
                                <input type="file" class="imageUpload" id="imageUpload_Id_${loop.index + 1}" value="이미지 업로드">
                            </label>

                        </div>
                        <input type="color"  id="back_color_${loop.index + 1}" class="modal_img_area_box_backGround" value="#737373">
                    </c:if>
                    
                </div>
                <div id="modal_div" class="delete_bth delete_bth_${loop.index + 1}">
                    ⚠ 삭제 하기
                </div>
                <button id="submitBtn" class="submitBtn_NO_${loop.index + 1}">등록하기</button>
            </div>
        </div>
        
        </c:forEach>

    </c:if>
    
 
<script>
   const root = "${root}";
    const log = "${bannerList[0].bannerBackgroundcolor}";
    console.log(log);
</script>

</body>
</html>

<link rel="stylesheet" hsref="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/banner.css">
<script src="${root}/static/js/admin/banner.js"></script>
