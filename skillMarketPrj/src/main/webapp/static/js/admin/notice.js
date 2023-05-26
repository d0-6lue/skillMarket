window.onload = function() {
   
	const tbody =  document.querySelectorAll("tbody tr");

	tbody.forEach((e)=>{
		e.addEventListener("click",(event)=>{
			const bno = event.target.parentNode.children[0].innerText;
			console.log(bno);

			
	
			const noticeDetail = document.querySelectorAll(".noticeDetail");
			
			const selectModal =  document.getElementById("noticeDetail_"+ bno);

			

			$(selectModal).css("display", "block"); // 모달창을 보이게 함
			$("#noticeDetailContent_" + bno).innerHTML = "${ modal.notiContent }";
			

			// 모달창 닫기 버튼 클릭 시
			$(".close").click(function() {
				$("#noticeDetail_"+ bno).css("display", "none"); // 모달창을 숨김
				$("#noticeDetail_"+ bno).find('.note-toolbar').css('display','none')
			});
			
			// 썸머노트 에디터 초기화
			$('#noticeDetailContent_'+ bno).summernote({
				lang: 'ko-KR', // 한글 언어 설정
				height: 475.219, // 에디터 높이 설정
				toolbar: [
					// 에디터 툴바 옵션 설정
						['style', ['bold', 'italic', 'underline', 'clear']],
						['font', ['strikethrough', 'superscript', 'subscript']],
						['fontsize', ['fontsize']],
						['color', ['color']],
						['para', ['ul', 'ol', 'paragraph']],
						['height', ['height']],
						['insert', ['picture', 'link', 'video']],
						['view', ['fullscreen', 'codeview']],
				],
			});
			
			$('#noticeDetailContent_'+ bno).summernote('disable');
			
			console.log($('#noticeDetailContent_'+ bno).find('disable'));

			// 수정 버튼 클릭 시 썸머노트 에디터 활성화
			const editButton = $("#noticeDetail_"+ bno).find('#submitBtn1');
			console.log(editButton);
			// const editButton = document.getElementById('submitBtn1');
			editButton.click(function() {
				$("#noticeDetail_"+ bno).find('.note-toolbar').css('display','block')
				console.log($("#noticeDetail_"+ bno));
				
				const noticeDetailContent = document.getElementById('noticeDetailContent_'+ bno);
				$('#noticeDetailContent_'+ bno).summernote('enable');
				
			});
	
		} )
	

	})
	
	
	
	
	
	
// 모달==========================================================================
	//  공지 모달창 열기 버튼 클릭 시
    $("#openModalBtn").click(function() {
        $("#myModal").css("display", "block"); // 모달창을 보이게 함
		
        $('#summernote').summernote({		   // 썸머노트를 초기화 함
			lang: 'ko-KR', // 한글 언어 설정
			height: 475.219, // 에디터 높이 설정
			toolbar: [
				// 에디터 툴바 옵션 설정
					['style', ['bold', 'italic', 'underline', 'clear']],
					['font', ['strikethrough', 'superscript', 'subscript']],
					['fontsize', ['fontsize']],
					['color', ['color']],
					['para', ['ul', 'ol', 'paragraph']],
					['height', ['height']],
					['insert', ['picture', 'link', 'video']],
					['view', ['fullscreen', 'codeview']],
			],
			callbacks : {
				onImageUpload : imageUpload
			  }
		}); 
		$("#myModal").find('.note-toolbar').css('display','block')
		

    });

	function imageUpload(fileList) {
		
		const fd = new FormData();
		for(let f of fileList) {
			fd.append("f",f)
		}
		fd.append("newNoticeNo",newNoticeNo)

		console.log(fd.newNoticeNo);

		$.ajax({
			url : '/skillmarket/admin/notice/upload',
			type : 'post',
			data : fd,
			dataType : 'json',
			processData : false,
			contentType : false,
			success : function(changeNameList) {
				console.log(changeNameList);
				for(let name of changeNameList) {
					console.log(name);
					$('#summernote').summernote('insertImage' , '/skillmarket/static/img/notice/' + name)
				}
			},
			error : function(error){
				console.log(error);
			},
		})

	} 


    // 모달창 닫기 버튼 클릭 시
    $(".close").click(function() {
        $("#myModal").css("display", "none"); // 모달창을 숨김
    });

    

    //셀렉트 기본값 삭제
    const catSelect = document.querySelector("#cat_select");
    catSelect.addEventListener("change", () => {
	    if (catSelect.value !== "default") {
	        const defaultOption = catSelect.querySelector('[value="default"]');
	        defaultOption.style.display = "none";
	        }
    });

	const submitBtn = document.querySelector("#submitBtn");
	const titleInput = document.querySelector("#titleInput");
	const contentContainer = document.querySelector("#summernote");
	
	
	console.log(titleInput);

	submitBtn.addEventListener("click", () => {
	    // 제목을 가져온다
		
	    const title = titleInput.value;
		console.log(title);
	
	    // 내용을 가져온다
	    const content = $('#summernote').summernote('code');
	
	    // 카테고리를 가져온다
	    const categorySelect = document.querySelector("#cat_select");
	    const category = categorySelect.value;
		
	
	    // 이미지들을 가져온다
	    const images = [];
	    const imageElements = document.querySelectorAll(".note-editable img");
	    imageElements.forEach((imageElement) => {
	        images.push(imageElement.src);
	    });
	
	    // 카테고리가 선택되지 않았으면 alert
	    if (category === "default") {
	        alert("카테고리를 선택해주세요");
	        return;
	    }
	
	    // 제목이 비어있는지 확인
	    if (!title) {
	        alert("제목을 입력해주세요");
	        titleInput.style.border = "1px solid red";
	        return;
	    }
	
	    // 내용이 비어있는지 확인
	    if (!content || content.trim().length === 0) {
	        alert("내용을 입력해주세요");
	        contentContainer.style.border = "1px solid red";
	        return;
	    }
	
	    // 내용의 길이가 5자 이하인 경우 알림
	    if (content.replace(/(<([^>]+)>)/gi, '').trim().length < 5) {
	        alert("내용은 5자 이상 입력해주세요");
	        contentContainer.style.border = "1px solid red";
	        return;
	    }
	
	    // 서버에 데이터를 보내는 비동기 요청
	    fetch("/skillmarket/admin/notice", {
			method: "POST",
			body: JSON.stringify({
				title,
				content,
				category,
			}),
			headers: {
				"Content-Type": "application/json",
			},
		})
			.then((response) => response.json())
			.then((data) => {
				if (data.result === "success") {
					alert("등록되었습니다.");
					// Close the modal if the registration is successful
					const modal = document.querySelector("#myModal");
					modal.style.display = "none";
				} else {
					alert("등록에 실패하였습니다.");
				}
			})
			.catch((error) => {
				console.error("Error:", error);
			});
	});
	
	// 포커스를 떠날 때 테두리 스타일을 원래 값으로 복구한다
	titleInput.addEventListener("blur", () => {
	    if (titleInput.value) {
	        titleInput.style.border = "";
	    }
	});
	
	// 포커스를 떠날 때 테두리 스타일을 원래 값으로 복구한다
	contentContainer.addEventListener("blur", () => {
	    if (contentContainer.innerHTML.trim().length > 0) {
	        contentContainer.style.border = "";
	    }
	});


	// 상태요약 정렬
	$("#freeLancerModal_open").click(function () {

		console.log(123);
		
	})



}