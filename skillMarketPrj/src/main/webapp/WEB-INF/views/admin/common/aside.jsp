<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>

<!-- 구글폰트 아이콘 -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
<!--  -->
<link rel="stylesheet" href="${root}/static/css/admin/common/aside.css">
<script src="${root}/static/js/admin/common/adminSet.js"></script>
<!-- 썸머노트 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="${root}/static/js/summernote/summernote-lite.js"></script>
<script src="${root}/static/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${root}/static/css/summernote/summernote-lite.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<!--  -->



<aside>
    <a href="${root}/admin/home">
        <div id="logo-area">
            <div>
                <img src="${root}/static/svg/관리자 페이지 로고.svg" alt="logo">
            </div>
        </div>
    </a>
    
    <div class="side_meue" id="side_1">
        <ul>
            <li>
                <a href="">관리자</a>
            </li>
            <li>
                <a href="${root}/home">사이트 바로가기</a>
            </li>

        </ul>
    </div>
    
    <div class="side_meue" id="side_2">
        <ul>
            <li>
                <a href="${root}/admin/home">대시보드</a>
            </li>
            <li>
                <a href="${root}/admin/members">회원 관리</a>
            </li>
            <li>
                <a href="${root}/admin/report">신고 내역</a>
            </li>
            <li>
                <a href="${root}/admin/inquiry">문의 내역</a>
            </li>
            <li>
                <a href="${root}/admin/notice">공지 작성</a>
            </li>
            <li>
                <a href="${root}/admin/banner">배너 관리</a>
            </li>
            <li>
                <a href="${root}/admin/FAQ">FAQ 관리</a>
            </li>
            <li>
                <a href="${root}/admin/category">카테고리 관리</a>
            </li>
        </ul>
    </div>
</aside>

<article></article>