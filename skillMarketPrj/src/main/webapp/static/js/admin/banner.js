check();
let isUploading = false;


function check() {
    for (var n = 1; n <= 4; n++) {
          $("#Yn_check_" + n).parent().siblings().find(".edit_btn").prop("disabled", false);
        }
}




$('input[type="color"]').on('input', function() {
    var inputId = $(this).attr('id'); // 변경된 input 요소의 id 가져오기
    var num = inputId.slice(-1); // loop.index + 1 값을 추출

    // .colorpicker_back_num 클래스를 가진 요소의 값을 변경
    $('.colorpicker_back_' + num).val($(this).val());
});



$(".delete_bth").click(function() {
    if (isUploading) {
        alert("다른작업중입니다")
        return;
      }

    isUploading = true;

    const secondClass = $(this).attr("class").split(" ")[1];
    const modal_upload_img = $(this).parent().find("#modal_upload_img");
    
    const no = secondClass.split("_").pop();


    const confirmDelete = confirm("⚠ 진짜 삭제 하시나요?");

    if (confirmDelete) {
        $(modal_upload_img).empty();
        $(modal_upload_img).replaceWith(`
            <div class="modal_img_area" id="modal_upload_img">
                <label class="modal_upload_img" id="modal_upload_img_${no}">
                    이미지 업로드
                    <input type="file" class="imageUpload" id="imageUpload_Id_${no}" value="이미지 업로드">
                </label>
            </div>
        `);
        

        $.ajax({
            url: "/skillmarket/admin/banner/delete", // 이미지 업로드를 처리하는 서버 엔드포인트 URL
            type: "POST",
            data: {no:no},
            success: function(ok) {
                alert("삭제성공")
                $(".img_edit_" + no).css("display", "none");
                updateBanner(no); // 리스트 이미지 제거
                isUploading = false;
            },
            error: function(xhr, status, error) {
                console.log("에러ㅠㅠ");
                isUploading = false;
            }

        });
    }
});




$(".edit_btn").click(function () {
    const id = $(this).parent().parent().attr("id");
    const no = id.substring(9);
    openModalAndUploadImage(no);
})

$(".banner_add_area").click(function() {
    const id = $(this).parent().attr("id");
    const no = id.substring(9);
    openModalAndUploadImage(no);
    console.log(123);
});

// 배너 업로드 + 썸네일
function openModalAndUploadImage(no) {
   


    const id = $(this).parent().attr("id");
    const openModal = "#img_edit" + no;
    // 모달 열기
    $('.img_edit_' + no).css("display", "block");

    $('#imageUpload_Id_' + no).change(function() {
        const input = $(this);
        const reader = new FileReader();

        reader.onload = function(e) {
            const image = new Image();
            image.src = e.target.result;

            image.onload = function() {
                const canvas = document.createElement('canvas');
                const ctx = canvas.getContext('2d');

                const maxWidth = 500;
                const maxHeight = 500;

                let width = image.width;
                let height = image.height;

                // 이미지 비율 유지
                if (width > height) {
                    if (width > maxWidth) {
                        height *= maxWidth / width;
                        width = maxWidth;
                    }
                } else {
                    if (height > maxHeight) {
                        width *= maxHeight / height;
                        height = maxHeight;
                    }
                }

                canvas.width = width;
                canvas.height = height;

                ctx.drawImage(image, 0, 0, width, height);

                canvas.toBlob(function(blob) {
                    if (isUploading) {
                        return;
                      }
                    
                    isUploading = true;
                    
                    const formData = new FormData();
                    formData.append("image", blob);
                    formData.append("no", no);

                    $.ajax({
                        url: "/skillmarket/admin/banner/upload", // 이미지 업로드를 처리하는 서버 엔드포인트 URL
                        type: "POST",
                        data: formData,
                        processData: false,
                        contentType: false,
                        success: function(savedImagePath) {
                            // 서버 응답에서 저장된 파일 경로 받기
                            console.log(savedImagePath);

                            // 이미지 경로 변경
                            const imageElement = input.closest('.modal_img_area_box').find('img.modal_img_area');

                            // 라벨 및 자식 변경
                            const labelElement = input.closest('.modal_upload_img');
                            labelElement.html('<img class="modal_img_box" id="modal_img_' + no + '" src="' + root + savedImagePath + '" alt="배너이미지"' + no + '">');

                            
                           
                            
                            
                            $("#img_area_"+no).find(".banner_add_area :first-child").html(' <img class="banner_aticle_img_box" id="modal_img_' + no + '" src="' + root + savedImagePath + '" alt="배너이미지">')
                            
                            console.log($("#img_area_"+no).find(".banner_add_area :first-child").html());

                            $("#Yn_check_" + no).parent().siblings().find(".edit_btn").prop("disabled", false);
                            isUploading = false;
                        },
                        error: function(xhr, status, error) {
                            console.log("에러ㅠㅠ");
                            isUploading = false;
                        }
                    });
                });

            };
        };

        reader.readAsDataURL(input[0].files[0]);
    });

    // 등록하기
    $(".submitBtn_NO_" + no).click(function() {
        if (isUploading) {
            return;
          }
    
        isUploading = true;

        // 모달 내부에서 선택된 배경색 가져오기
        const backgroundColor = $("#back_color_" + no).val();

        // img 요소의 src 속성 값 가져오기
        const src = $("#modal_img_" + no).attr("src");

        // 잘라낼 부분 구하기
        const filename = src.substring(src.lastIndexOf("/") + 1);


        // AJAX 요청 데이터 생성
        const formData = new FormData();
        formData.append("no", no);
        formData.append("backgroundColor", backgroundColor);
        formData.append("file", filename);

        // AJAX 요청 보내기
        $.ajax({
            url: "/skillmarket/admin/banner",
            type: "POST",
            data: formData,
            processData: false,
            contentType: false,
            success: function(updateBanner) {
                // 성공적으로 요청이 처리되었을 때의 동작
                console.log("AJAX 요청 성공");
                console.log(ok);
                alert("등록 성공! 👍");

                isUploading = false;
            },
            error: function(xhr, status, error) {
                // 요청 처리 중 에러가 발생했을 때의 동작
                console.log("AJAX 요청 에러");
                console.log(error);
                isUploading = false;
            }
        });
    });

    // 모달창 닫기 버튼 클릭 시
    $(".close").click(function() {
        $(".img_edit_" + no).css("display", "none");
        isUploading = false;
    });


    
}

//리스트 이미지 제거
function updateBanner(no) {
    

    $("#banner_add_area_"+no).html(`
        
            <div>
                <span>배너 추가</span> 
                <span class="material-symbols-outlined">
                add_circle
                </span>
            </div>
            
            <input type="text" id="Yn_check_1" value="XXX" style="display: none;">
            <input type="color" value="#737373" id="back_color_area" class="colorpicker_back_1" disabled></input>
         
    `)
   
}