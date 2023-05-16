window.onload = function() {
    // 모달창 열기 버튼 클릭 시
    $("#openModalBtn").click(function() {
        $("#myModal").css("display", "block"); // 모달창을 보이게 함
        $('#summernote').summernote(); // 썸머노트를 초기화 함
    });

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
	const titleInput = document.querySelector("#modal_div input");
	const contentContainer = document.querySelector("#summernote");
	
	
	

	submitBtn.addEventListener("click", () => {
	    // 제목을 가져온다
		
	    const title = titleInput.value;
	
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
	
	    // 카테고리가 선택되지 않았으면 alert를 띄운다
	    if (category === "default") {
	        alert("카테고리를 선택해주세요");
	        return;
	    }
	
	    // 제목이 비어있는지 확인한다
	    if (!title) {
	        alert("제목을 입력해주세요");
	        titleInput.style.border = "1px solid red";
	        return;
	    }
	
	    // 내용이 비어있는지 확인한다
	    if (!content || content.trim().length === 0) {
	        alert("내용을 입력해주세요");
	        contentContainer.style.border = "1px solid red";
	        return;
	    }
	
	    // 내용의 길이가 5자 이하인 경우 알림을 띄운다
	    if (content.replace(/(<([^>]+)>)/gi, '').trim().length < 5) {
	        alert("내용은 5자 이상 입력해주세요");
	        contentContainer.style.border = "1px solid red";
	        return;
	    }
	
	    // 서버에 데이터를 보내는 비동기 요청을 보낸다
	    fetch("/skillmarket/admin/notice", {
	        method: "POST",
	        body: JSON.stringify({
	            title,
	            content,
	            category,
	            images,
	        }),
	        headers: {
	            "Content-Type": "application/json",
	        },
	    })
	        .then((response) => {
	            if (response.ok) {
	                alert("등록되었습니다.");
	                // 등록이 성공하면 모달을 닫는다
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





}