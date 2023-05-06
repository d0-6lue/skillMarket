<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티에 글쓰는 페이지</title>
</head>
<body>

	<div id="wrap">

		<main>
			<!-- header -->
			<%@ include file="/WEB-INF/views/common/header.jsp" %>

			<h1>글쓰기</h1>
			<form action="${root}/community/post/write" method="post">
				<input type="hidden" name="topicid" value="${param.topicid}" />
				<label for="title">제목</label>
				<input type="text" id="title" name="title" required />
				<br />
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="10" cols="50" required></textarea>
				<br />
				<button type="submit">작성하기</button>
			</form>

			<!-- footer  -->
			<%@ include file="/WEB-INF/views/common/footer.jsp" %>	
		</main>
		

	</div>	
</body>
</html>