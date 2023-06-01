<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카테고리 관리</title>
</head>
<body>

    <div id="wrap">

        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>
        
        <header>
            
            <h1>카테고리 관리</h1>
        
        </header>
        
        <article>

            <div>
    
                <div class="article_area" id="article_area_1">
                    <div class="container_admin" id="container_area_1">
                        <div>
                            <div class="subtitle">
                                <h3>인기 카테고리</h3>
                            </div>
                            <div class="statistics statistics_rank">
                                <div>
                                    <span>1위</span>
                                    <span>${rankCat[0].estimateCatName}</span>
                                </div>
                                <div>
                                    <span>2위</span>
                                    <span>${rankCat[1].estimateCatName}</span>
                                </div>
                                <div>
                                    <span>3위</span>
                                    <span>${rankCat[0].estimateCatName}</span>
                                </div>
                            </div>
                            
                        </div>
                        
                    </div>
                </div>
    
                <div class="article_area" id="article_area_2">
                    <div class="container_admin" id="container_area_2">

                        <div>
                            <!-- 박스 상단 -->
                            <div class="subtitle">
                                <h3>카테고리 리스트</h3>
                                
                                <div >
                                    <button id="openModalBtn" class="add_cat_btn">카테고리 추가</button>
                                </div>
                            </div>

                            <!-- 테이블 -->
                            <div class="statistics statistics_list_2">
                                <table>

                                    <!-- 테이블 헤드 -->
                                    <thead id="cat_THEAD" class="scroll_tbody">
                                        <tr>
                                            <th>
                                                <span></span>
                                            </th>
                                            <th>
                                                <span class="thead_center_size">대분류</span>
                                            </th>
                                            <th>
                                                <span>
                                                    <div>
                                                        
                                                    </div>
                                                </span>
                                            </th>
                                        </tr>
                                    </thead>
                                    
                                    <!-- 테이블 바디 -->
                                    <tbody class="scroll_tbody">
                                        
                                        <c:forEach items="${ catArrList }" var="list_big">
                                            
                                            <c:if test="${ list_big.estimateCatScope == '1' }">

                                                <tr >

                                                    <td id="cat_box_first_td_${ list_big.estimateCatNo }" 
                                                    class="
                                                    cat_box_td  
                                                    cat_box_first_td_above_${ list_big.aboveCatNo }
                                                    cat_box_first_td_name_${ list_big.estimateCatName }
                                                    ">
                                                        <span id="list_big_name_${ list_big.estimateCatNo }">${ list_big.estimateCatName }</span>
                                                    </td>

                                                </tr>

                                            </c:if>


                                        </c:forEach>

                                        <c:forEach items="${ catArrList }" var="list_meddle">
                                            
                                            <c:if test="${ list_meddle.estimateCatScope == '2' }" >

                                                <tr id="cat_box_tr_m_${ list_meddle.estimateCatNo }" 
                                                class="
                                                cat_box_td_m  
                                                meddle_tr_${list_meddle.aboveCatNo}" 
                                                style="display: none;
                                                ">

                                                    <td id="cat_box_first_td_${ list_meddle.estimateCatNo }" 
                                                    class="
                                                    cat_box_first_td  
                                                    cat_box_first_td_above_${ list_meddle.aboveCatNo } cat_box_first_td_name_${ list_meddle.estimateCatName }
                                                    ">
                                                        <span id="list_meddle_name_${ list_meddle.estimateCatNo }">${ list_meddle.estimateCatName }</span>
                                                    </td>

                                                </tr>

                                            </c:if>

                                        </c:forEach>

                                        <c:forEach items="${ catArrList }" var="list_small">
                                            
                                            <c:if test="${ list_small.estimateCatScope == '3' }">

                                                <tr class="small_tr_${list_small.aboveCatNo}" style="display: none;">

                                                    <td id="cat_box_first_td_${ list_small.estimateCatNo }" class="cat_box_first_td  cat_box_first_td_above_${ list_small.aboveCatNo }">
                                                        <span id="list_small${ list_small.estimateCatNo }">${ list_small.estimateCatName }</span>
                                                    </td>

                                                </tr>

                                            </c:if>

                                        </c:forEach>


                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
    
            </div>
    
        </article>

    </div>
	
    <!-- 모달 -->
	<div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h1>카테고리 추가</h1>
            <div id="modal_div">
                
                <div id="add_cat_select_area">
                    <div id="add_big" class="add_cat_btn_S">대분류 추가</div>
                    <div id="add_meddle" class="add_cat_btn_S">중분류 추가</div>
                    <div id="add_small" class="add_cat_btn_S">소분류 추가</div>

                    <select name="" id="cat_select_big" class="cat_select_box" disabled>
                    
                        <option class="cat_select_option" value="default">대분류</option>
                        <c:forEach items="${catArrList}" var="big_select">
                            <c:if test="${ big_select.estimateCatScope == '1' }">
                                <option id="cat_select_big_option_${big_select.estimateCatNo}" class="cat_select_big_option cat_select_option" value="${ big_select.estimateCatNo }">${ big_select.estimateCatName }</option>
                                
                            </c:if>    
    
                        </c:forEach>
    
                    </select>

                    <c:forEach items="${catArrList}" var="big_select">
                            <c:if test="${ big_select.estimateCatScope == '1' }">
                                <input type="text" id="big_adove_${big_select.estimateCatNo}" value="${big_select.aboveCatNo}" style="display: none;">
                                
                            </c:if>    
    
                    </c:forEach>

                    <c:if test="${ small_select.estimateCatScope == '2' }">
                        <input type="text" id="big_adove_${big_select.estimateCatNo}" value="${big_select.aboveCatNo}" style="display: none;">
                    </c:if>
                    <select name="" id="cat_select_meddle" class="cat_select_box" disabled>
    
                        <option class="cat_select_meddle_option" value="default">중분류</option>
                        <c:forEach items="${catArrList}" var="meddle_select">
                            <c:if test="${ meddle_select.estimateCatScope == '2' }">
                                <option id="cat_select_meddle_option_${ meddle_select.estimateCatNo }" class="cat_select_meddle_option cat_select_option"  value="${ meddle_select.estimateCatNo }">${ meddle_select.estimateCatName }</option>
                                
                            </c:if>    
        
                        </c:forEach>
    
                    </select>

                    <c:forEach items="${catArrList}" var="meddle_select">

                        <c:if test="${ meddle_select.estimateCatScope == '2' }">
                            <input type="text" id="meddle_adove_${meddle_select.estimateCatNo}" value="${meddle_select.aboveCatNo}" style="display: none;">

                        </c:if>
                    </c:forEach>
        
                

                    <select name="" id="cat_select_small" class="cat_select_box" disabled>
    
                        <option value="default" class="cat_select_option">소분류</option>
                        <c:forEach items="${catArrList}" var="small_select">
                            <c:if test="${ small_select.estimateCatScope == '3' }">
                                <option id="cat_select_small_option_${ small_select.estimateCatNo }"  class="cat_select_small_option cat_select_option" value="${ small_select.estimateCatNo }">${ small_select.estimateCatName }</option>
                            </c:if>    
                            
                        </c:forEach>
                        
                    </select>

                    <c:forEach items="${catArrList}" var="small_select">
                        
                            <c:if test="${ small_select.estimateCatScope == '3' }">
                                <input type="text" id="small_adove_${small_select.estimateCatNo}" value="${small_select.aboveCatNo}" style="display: none;">
                            </c:if>    
                            
                    </c:forEach>

                </div>

            </div>
            <div id="titleInput_area">
                <div id="span_area">
                    <span id="path_big">대분류</span>
                    <span>></span>
                    <span id="path_meddle">중분류</span>
                    <span>></span>
                    <span id="path_small">소분류</span>
                </div>
                


                <input id="titleInput" type="text" name="title" placeholder="새로운 카테고리" style="display: none;">

                <div id="input_box" style="display: none;">
                    <input id="input_big" type="text" name="big" placeholder="big" >
                    <input id="input_meddle" type="text" name="meddle" placeholder="meddle" >
                    <input id="input_small" type="text" name="small" placeholder="small" >
                    <input id="input_no" type="text" name="no" placeholder="no" style="display: none;">
                </div>
                
            </div>
            <button id="submitBtn">등록하기</button>
        </div>
      </div>


</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">
<link rel="stylesheet" href="${root}/static/css/admin/category.css">
<script src="${root}/static/js/admin/category.js"></script>
