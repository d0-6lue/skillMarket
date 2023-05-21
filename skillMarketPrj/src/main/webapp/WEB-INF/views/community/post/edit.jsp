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
</head>
<body>
    <div id="wrap">
        <%@ include file="/WEB-INF/views/common/header.jsp" %>
        <main>
            <div class="post-write-box">
                <form action="${root}/community/post/write" method="post" class="write-box-set" onsubmit="updateContent()">
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
						<div id="content" style="display: none;">
							<c:out value="${vo.freeBoardContent}" escapeXml="false" />
						</div>
						<div id="html-output"></div>
					</div>					
                    <input class="btn btn-primary" type="submit" value="수정하기">
                </form>
            </div>
        </main>
    </div>
    
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>

	<script>
		document.addEventListener("DOMContentLoaded", function() {
			const imageUploadAdapter = (editor) => {
				editor.plugins.get("FileRepository").createUploadAdapter = (loader) => {
					return {
						upload: () => {
							return loader.file.then(file => {
								return new Promise((resolve, reject) => {
									const formData = new FormData();
									formData.append("file", file);
		
									fetch("${root}/upload/community", {
										method: "POST",
										body: formData
									})
									.then(response => response.json())
									.then(data => {
										const imageUrl = "${root}/upload/community/" + data.filePath; // Update the image URL based on your server path
										resolve({ default: imageUrl });
									})
									.catch(error => {
										console.error(error);
										reject(error);
									});
								});
							});
						}
					};
				};
			}
		
			ClassicEditor
				.create(document.querySelector("#editor"), {
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
					const freeBoardContent = "${vo.freeBoardContent}";
					if (freeBoardContent !== null && typeof freeBoardContent !== "undefined" && freeBoardContent !== "") {
						editor.setData(freeBoardContent);
					}
				})
				.catch(error => {
					console.error(error);
				});
		});
		</script>
		
	
	
	

</body>
</html>
