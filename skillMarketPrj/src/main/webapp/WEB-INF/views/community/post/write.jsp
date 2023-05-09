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
<!-- 글쓰기 페이지 js -->
<script defer src="${root}/static/js/community/commuwrite.js"></script>

<!-- 부트스트랩 css js -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>

	<div id="wrap">

		<main>
			<!-- header -->
			<%@ include file="/WEB-INF/views/common/header.jsp" %>

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
				
				
			</div>

			<!-- footer  -->
			<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
		</main>
		

	</div>	
</body>
</html>