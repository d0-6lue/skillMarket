<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티에 글쓰는 페이지</title>

<!-- include summernote css/js-->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<!-- 글쓰기 페이지 css  -->
<link rel="stylesheet" href="${root}/static/css/community/post/commuwrite.css">

</head>
<body>

	<div id="wrap">

		<main>
			<!-- header -->
			<%@ include file="/WEB-INF/views/common/header.jsp" %>

			<div class="post-write-box">
				<form method="post" class="write-box-set">
					<div id="title-box">
						<label for="title">제목</label>
						<br>
						<input class="title-text-box" type="text" id="title" name="title" required />
						<br/>
						<h3>-- 사진은 5개까지만 올릴수있습니다 --</h3>
						<br>
					</div>		
					<textarea id="summernote" name="editordata"></textarea>
				</form>
				
				<!-- 써머노트 js입니다 -->			
				<script>
					$(document).ready(function() {
						$('#summernote').summernote({
							height: 800,                 // 에디터 높이
							minHeight: null,             // 최소 높이
							maxHeight: null,             // 최대 높이
							focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
							lang: "ko-KR",					// 한글 설정
							placeholder: '최대 2048자까지 쓸 수 있습니다'	//placeholder 설정
							});
					});
				</script>
			</div>

			<!-- footer  -->
			<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
		</main>
		

	</div>	
</body>
</html>