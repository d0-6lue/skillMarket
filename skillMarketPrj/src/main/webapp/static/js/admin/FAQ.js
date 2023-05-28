$(document).ready(function() {
   
	let isSubmitting = false;

	const tbody =  document.querySelectorAll("tbody tr");

	// 모달창 닫기 버튼 클릭 시
	$(".close").click(function() {


		const id = $(this).attr("id");
		const lastUnderscoreIndex = id.lastIndexOf("_");
		const no = id.substring(lastUnderscoreIndex + 1);
		$("#FAQ_detail_"+ no).css("display", "none"); // 모달창을 숨김
		$("#FAQ_summer_note_"+no).summernote("disable");
		$("#FAQ_detailContent_"+no).find(".note-toolbar").css("display" , "none");
		$("#modal_Title_"+no).prop("readonly",true);
		$(".cat_select_FAQ_"+no).prop("disabled", true);
		$(".FAQ_edit_btn_"+no).text("수정하기")
		$(".FAQ_edit_btn_"+no).removeClass("submitBtn_onclick_"+no);
		
		isSubmitting = false;
	});



	// TR 클릭시 모달 열림
	$(".FAQ_Detail_TR").click(function () {
		const id = $(this).attr("id");
		const lastUnderscoreIndex = id.lastIndexOf("_");
		const no = id.substring(lastUnderscoreIndex + 1);


		$("#FAQ_detail_"+no).css("display", "block");

		$("#FAQ_summer_note_"+no).summernote({
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

		$("#FAQ_summer_note_"+no).summernote('disable');
		
	})

	// 수정버튼 클릭시
	$(".FAQ_edit_btn").click(function () {

		if(isSubmitting == true) {
			return;
		}
		
		isSubmitting = true; 

		
		const Btnclass = $(this).attr("class");
		const lastUnderscoreIndex = Btnclass.lastIndexOf("_");
		const no = Btnclass.substring(lastUnderscoreIndex + 1);

		
		
		$(".FAQ_edit_btn").removeClass("FAQ_edit_btn");

		$("#FAQ_summer_note_"+no).summernote("enable");
		$("#FAQ_detailContent_"+no).find(".note-toolbar").css("display" , "block");
		$("#modal_Title_"+no).prop("readonly",false);
		$(".FAQ_edit_btn_"+no).text("등록하기")
		$(".cat_select_FAQ_"+no).prop("disabled", false);

		$(".FAQ_edit_btn_"+no).addClass("submitBtn_onclick_"+no);
		
		const isDisabled = $(".cat_select_FAQ_" + no).prop("disabled");
		
		const category = $(".cat_select_FAQ_"+no).val();

		
		console.log($("#FAQ_Detail_"+no).html());

		if (!submitBtn_onclick(no)) {
			console.log("ㅠㅠ");
			isSubmitting = false; 
			return;
		}

	})

//등록하기 클릭시
function submitBtn_onclick(no){
			
	if (confirm("수정하시겠습니까?")) {
		
		const title = $("#modal_Title_"+no).val();
		const content = $("#FAQ_summer_note_"+no).summernote("code");
		const category = $(".cat_select_FAQ_"+no).val();

		console.log(category);

		$.ajax({
			url: "/skillmarket/admin/FAQ/edit",
			method: "POST",
			data: { 
			  faqNo: no,
			  title: title,
			  content: content,
			  category: category
			},
			dataType: "json",
			success: function(response) {
				alert("수정 성공 🎉");
				updateList(response);
				$(".FAQ_edit_btn_"+no).text("수정하기")
				$(".FAQ_edit_btn_"+no).removeClass("submitBtn_onclick_"+no);
				isSubmitting = false; 
			  
			},
			error: function(xhr, status, error) {
				alert("수정 실패 ☠");
				$(".FAQ_edit_btn_"+no).text("수정하기")
				$(".FAQ_edit_btn_"+no).removeClass("submitBtn_onclick_"+no);
				isSubmitting = false; 
			}
		  });
	  }
	else {
		$(".FAQ_edit_btn_"+no).text("수정하기")
		$(".FAQ_edit_btn_"+no).removeClass("submitBtn_onclick_"+no);
		$(".FAQ_edit_btn_"+no).addClass("FAQ_edit_btn");
		return;
	}
};



function updateList(update) {
	
	if(isSubmitting == true) {
		return;
	}

	isSubmitting = true;

	$("#FAQ_Detail_" + update.faqNo).html(`
		<td>
		${update.faqNo}
		</td>
		<td>
		${update.faqCatName}
		</td>
		<td>
		${update.faqQContent}
		</td>
		<td>
		${update.faqHit}
		</td>
	`);


	isSubmitting = false;
	}



















    
})