<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티에 글쓰는 페이지</title>

<!-- include summernote css/js-->
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="${root}/static/js/summernote/summernote-lite.js"></script>
	<script src="${root}/static/js/summernote/lang/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="${root}/static/css/summernote/summernote-lite.css">

<!-- 글쓰기 페이지 css  -->
<link rel="stylesheet" href="${root}/static/css/community/post/commuwrite.css">
<!-- 글쓰기 페이지 js -->
<script defer src="${root}/static/js/community/commuwrite.js"></script>

<!-- 부트스트랩 css js -->


</head>
<body>

	<div id="wrap">

		<!-- header -->
		<%@ include file="/WEB-INF/views/common/header.jsp" %>
		<main>
			
			<div class="post-write-box">
				<form action="${root}/community/post/write" method="post" class="write-box-set">
					<select class="topic-select-box">
						<option disabled="disabled" value=""> 주제 선택 </option>
						<option  value="all"> 전체 </option>
						<option  value="together"> 함께해요 </option>
						<option  value="qna"> 궁금해요 </option>
						<option  value="how-much"> 얼마예요 </option>
					</select>
					<br>
					<div id="title-box">
						<input class="title-text-box" type="text" id="title" name="title" placeholder="제목을 입력해주세요" required />
					<br>
					<br>
					</div>		
					<textarea id="summernote" name="editordata"></textarea>
					<input class="btn btn-primary" type="submit" value="등록">
				</form>

				<script>
					$(document).ready(function() {
					$('#summernote').summernote({
						height: 500,                 // 에디터 높이
						minHeight: null,             // 최소 높이
						maxHeight: null,             // 최대 높이
						focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
						lang: "ko-KR",					// 한글 설정
						placeholder: '게시글 내용을 적어주세요 ~'  	//placeholder 설정
						
						});
				});


				</script>
				
			</div>

				
		</main>
		
		<!-- footer  -->
		<%@ include file="/WEB-INF/views/common/footer.jsp" %>
	</div>	
</body>
</html>