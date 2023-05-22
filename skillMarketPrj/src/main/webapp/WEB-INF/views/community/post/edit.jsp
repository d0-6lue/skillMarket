<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>커뮤니티에 의해 편집되는 페이지</title>

    <!-- ckeditor js -->
    <script src="https://cdn.ckeditor.com/ckeditor5/37.1.0/classic/ckeditor.js"></script>

    <!-- 공통 CSS (쓰기, 편집) -->
    <link rel="stylesheet" href="${root}/static/css/community/post/commuedit.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <div id="wrap">
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <main>
            <div class="post-write-box">
                <form action="${root}/community/post/edit?no=${vo.boardNo}" method="post" class="write-box-set" onsubmit="updateContent()">
                    <select class="topic-select-box" name="freeBoardCategoryNo" required>
                        <option disabled="disabled" value="">주제 선택</option>
                        <c:forEach var="topic" items="${cvolist}">
                            <option value="${topic.freeBoardCategoryNo}">${topic.freeBoardCategoryName}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <div id="title-box">
                        <input class="title-text-box" type="text" id="title" name="title" placeholder="제목을 입력해주세요" required value="${vo.freeBoardTitle}" />
                        <br>
                        <br>
                    </div>
                    <div id="editor-container">
						<textarea id="editor" name="editordata" class="custom-toolbar">${vo.freeBoardContent}</textarea>
                        <div id="html-output"></div>
                    </div>
                    <input class="btn btn-primary" type="submit" value="글쓰기">
                </form>
            </div>
        </main>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

    <script>
		document.addEventListener("DOMContentLoaded", function() {
			ClassicEditor.create(document.querySelector("#editor"), {
				toolbar: {
					items: [
						"undo",
						"redo",
						"|",
						"bold",
						"italic",
						"underline",
						"|",
						"imageUpload",
						"link",
						"|",
						"bulletedList",
						"numberedList",
						"indent",
						"outdent",
						"|",
						"alignment",
						"blockQuote",
						"insertTable",
						"mediaEmbed",
						"undo",
						"redo"
					]
				},
				language: "kr",
				extraPlugins: [imageUploadAdapter]
			})
				.then(editor => {
					editor.setData(`${vo.freeBoardContent}`);
				})
				.catch(error => {
					console.error(error);
				});
	
			function imageUploadAdapter(editor) {
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
								const imageUrl =   data.filePath; // Update the image URL based on your server path
								resolve(imageUrl);
							})
							.catch(error => {
								console.error(error);
								reject(error);
							});
					});
				}
			}
		});
	</script>
	
</body>
</html>
