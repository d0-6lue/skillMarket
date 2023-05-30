$(document).ready(function() {

    $(".close").click(function() {


        const id = $(this).attr("id");
        const lastUnderscoreIndex = id.lastIndexOf("_");
        const no = id.substring(lastUnderscoreIndex + 1);

        $(".inquiry_modal_"+ no).css("display", "none"); // 모달창을 숨김
        $("#inquiry_summer_note_"+no).summernote("disable");
        $("#inquiry_detailContent_"+no).find(".note-toolbar").css("display" , "none");
        $("#modal_Title_"+no).prop("readonly",true);
        $("#cat_select_"+no).prop("disabled", true);
        $(".inquiry_edit_btn_"+no).text("수정하기")
        $(".inquiry_edit_btn_"+no).removeClass("submitBtn_onclick_"+no);
        
        isSubmitting = false;
    });


    $("tr").click(function () {
        
        const id = $(this).attr("id");
        const lastUnderscoreIndex = id.lastIndexOf("_");
        const no = id.substring(lastUnderscoreIndex + 1);

        $(".inquiry_modal_"+no).css("display", "block");

    })



    $("#submitBtn").click(function () {
        
        const Btnclass = $(this).attr("class");
		const lastUnderscoreIndex = Btnclass.lastIndexOf("_");
		const no = Btnclass.substring(lastUnderscoreIndex + 1);

        console.log(no);

        const answer =  $("inquiry_A_").val();

        $.ajax({
            url: "/skillmarket/admin/inquiry/answer", // 이미지 업로드를 처리하는 서버 엔드포인트 URL
            type: "POST",
            data: {
                no:no,
                answer:answer,
                
            },
            success: function(ok) {
                
                alert("답변성공")
                isUploading = false;
            },
            error: function(xhr, status, error) {
                console.log("에러ㅠㅠ");
                isUploading = false;
            }
        })
    })
});