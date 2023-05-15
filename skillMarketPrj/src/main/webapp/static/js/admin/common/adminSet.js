window.onload = function(){

    // span 0 이면 숫자 잘안보이게
    const sapns = document.querySelectorAll("span");
        
    sapns.forEach(span => {
        const value = parseInt(span.textContent);
        
        if (value === 0) {
            span.style.color = '#dddddd';
        }
        
    })


    $(document).ready(function() {
        // 모달창 열기 버튼 클릭 시
        $("#openModalBtn").click(function() {
            $("#myModal").css("display", "block"); // 모달창을 보이게 함
            $('#summernote').summernote(); // 썸머노트를 초기화 함
        });

        // 모달창 닫기 버튼 클릭 시
        $(".close").click(function() {
            $("#myModal").css("display", "none"); // 모달창을 숨김
        });

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

        submitBtn.addEventListener("click", async () => {
        // 제목과 내용을 가져온다
        const title = document.querySelector("#modal_div input").value;
        const content = await $('#summernote').summernote('code');
    
        // 카테고리를 가져온다
        const categorySelect = document.querySelector("#cat_select");
        const category = categorySelect.value;
    
        // 카테고리가 선택되지 않았으면 alert를 띄운다
        if (category === "default") {
            alert("카테고리를 선택해주세요");
            return;
        }
        
        

        // 제목이나 내용이 입력되지 않았으면 alert를 띄운다
        if (!title || content.trim() === '<p><br></p>' ) {
        alert("제목과 내용을 입력해주세요");
        return;
        }

        if (content.replace(/(<([^>]+)>)/gi, '').trim().length <20 ) {
        alert("내용을 입력하세요");
        return;
        }

       

    
        // 서버에 데이터를 보내는 비동기 요청을 보낸다
        const response = await fetch("http://example.com/api/notice", {
        method: "POST",
        body: JSON.stringify({
            title,
            content,
            category,
        }),
        headers: {
            "Content-Type": "application/json",
        },
        });
    
        if (response.ok) {
        alert("등록되었습니다.");
        // 등록이 성공하면 모달을 닫는다
        const modal = document.querySelector("#myModal");
        modal.style.display = "none";
        } else {
        alert("등록에 실패하였습니다.");
        }
    });
        

}
