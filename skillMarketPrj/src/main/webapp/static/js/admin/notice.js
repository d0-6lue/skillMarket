window.onload = function() {
   
	
	// 상세 모달 열기
	$(".notice_List").click(function () {
		
		const id = $(this).attr("id");
        const lastUnderscoreIndex = id.lastIndexOf("_");
        const no = id.substring(lastUnderscoreIndex + 1);

		$("#noticeDetail_"+no).css("display", "block"); // 모달창을 보이게 함

		$('#noticeDetailContent_'+ no).summernote({
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

		$('#noticeDetailContent_'+ no).summernote('disable');

	})
	


	// 공지 수정
	$(".submit_btn").click(function () {

		console.log($(this).attr("class"));

		const Btnclass = $(this).attr("class");
		const index = Btnclass.lastIndexOf("_");
		const classNo = Btnclass.substring(index + 1);
		console.log(classNo);
		

		$('#noticeDetailContent_'+ classNo).summernote('disable');
		$("#noticeDetail_"+ classNo).find('.note-toolbar').css('display','block');
		$('#noticeDetailContent_'+ classNo).summernote('enable');
		
		$("#cat_select_detail_"+classNo).prop("disabled", false);
		$("#cat_input_detail_"+classNo).prop("readonly", false);

		$("button#submit_btn_"+classNo).css("display" , "none");
		$("button#edit_btn_"+classNo).css("display" , "block");
	})
	
	// 모달창 닫기 버튼 클릭 시
	$(".close").click(function() {

		const id = $(this).attr("id");
        const lastUnderscoreIndex = id.lastIndexOf("_");
        const no = id.substring(lastUnderscoreIndex + 1);

		$("#noticeDetail_"+ no).css("display", "none"); // 모달창을 숨김
		$("#noticeDetail_"+ no).find('.note-toolbar').css('display','none');
		$('#noticeDetailContent_'+ no).summernote('disable');

		$("#cat_select_detail_"+ no).prop("disabled", true);
		$("#cat_input_detail_"+ no).prop("readonly", true);

		$("button#submit_btn_"+no).css("display" , "block");
		$("button#edit_btn_"+no).css("display" , "none");
	});


	$(".edit_btn").click(function () {
		
		//번호
		//카테고리
		//제목
		//콘텐츠

		console.log(123);

		const id = $(this).attr("id");
        const lastUnderscoreIndex = id.lastIndexOf("_");
        const no = id.substring(lastUnderscoreIndex + 1);

		const title = $("#cat_input_detail_"+no).val();
		const select = $("#cat_select_detail_"+no).val();
		const name = $(`#cat_select_detail_option_${no}:selected`).text();
		const content = $("#noticeDetailContent_"+no).summernote('code');
		const hit = $("#hit_"+no).text();

		console.log(name);
		
		$.ajax({
			url : '/skillmarket/admin/notice/edit',
			type : 'post',
			dataType: 'json',
			data : {
				no:no,
				title:title,
				select:select,
				content:content,
			},
			success : function(update) {
				alert("수정완료")

				$("#notice_List_"+no).html(`
					<td>
						${ no }
					</td>
					<td>
						[${ name }]${ title }
					</td>
					<td>
						${ hit }
					</td>
				`);

			},
			error : function(error){
				alert(error)
			},
		})
		
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
    $("#close_my").click(function() {
		console.log(123);
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