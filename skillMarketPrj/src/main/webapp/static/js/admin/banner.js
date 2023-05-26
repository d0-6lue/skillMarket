const bannerArea =  document.querySelectorAll(".banner_add_area");
const editBtn = document.querySelectorAll(".edit_btn");


bannerArea.forEach((e)=>{
    e.addEventListener("click",(event)=>{
        
        const inputEl = event.currentTarget.querySelector("input[type='file']");
        const inputFile = '<input type="file" style="display: none;">';
        // console.log(inputEl);
        
        const pel = event.target.parentElement.children[1];

        inputEl.click();
        

    })

    e.addEventListener("change",(event)=>{
        console.log(event.target.parentElement.parentElement.children[0].children[1]);
        const editBtn = event.target.parentElement.parentElement.children[0].children[1];
        const textArea = event.target.parentElement.parentElement.children[1].children[0];
        const imgArea =  event.target.parentElement.parentElement.children[1].children[1];
        
        // imgArea.disabled = false;
        imgArea.style.backgroundColor = "#FFD15A";
        imgArea.style.display = "block";
        textArea.style.display = "none";
        
        const fr = new FileReader();
        fr.readAsDataURL(event.target.files[0]);

        fr.onload = function(event){
            const imgTag = document.createElement('img');

            const maxHeight = 150;
            const ratio = imgTag.width / imgTag.maxHeight

            if (imgTag.height > maxHeight) {
                imgTag.height = maxHeight;
                imgTag.width = maxHeight * ratio;
            }

            imgTag.style.objectFit = "cover";
            imgTag.src = event.target.result;
            imgTag.alt = "미리보기이미지사진";
            // imgTag.height = 150 ;
            imgArea.innerHTML = ""; // 이미지 영역 초기화
            imgArea.appendChild(imgTag);
            };
            
        if(imgArea.innerHTML !== null) {
                editBtn.disabled = false;
                console.log(editBtn);
            }

        })

    
    })  

    

// 모달창 열기 버튼 클릭 시
$(".openModalBtn").click(function() {
    $("#bannerEdit").css("display", "block"); // 모달창을 보이게 함
    $('#summernote').summernote(); // 썸머노트를 초기화 함
});

// 모달창 닫기 버튼 클릭 시
$(".close").click(function() {
    $("#bannerEdit").css("display", "none"); // 모달창을 숨김
});    