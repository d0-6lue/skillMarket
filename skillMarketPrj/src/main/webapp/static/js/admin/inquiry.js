$(document).ready(function() {

    $('#msgTbody').hide();

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


    $("tbody.scroll_tbody tr").click(function () {
        
        const id = $(this).attr("id");
        const lastUnderscoreIndex = id.lastIndexOf("_");
        const no = id.substring(lastUnderscoreIndex + 1);

        $(".inquiry_modal_"+no).css("display", "block");

    })



    $(".inquiry_edit_btn").click(function () {
        
        const Btnclass = $(this).attr("class");
		const lastUnderscoreIndex = Btnclass.lastIndexOf("_");
		const no = Btnclass.substring(lastUnderscoreIndex + 1);
        const empty =  $(this).find("input").val();

        const answer =  $("#inquiry_A_"+no).val();
        if (answer.length < 5  ) {
            alert("5자 이상 적어주세요")
            return;
        }

        if (empty === "notEmpty") {
            if (confirm("수정하시겠습니까?")) {
                editIquiry(no,answer,empty);
            }
        }
        else {
            if(confirm("등록하시겠습니까?")){
                editIquiry(no,answer,empty)
            }
        }
        
        
    })


    $(".NCunt").click(function () {
       
        
        let found = false; // 일치하는 문의을 찾았는지 여부를 나타내는 플래그
        
        $('.qnaStatus').each(function() {
            const text = $(this).text().trim(); // .list_memberId_td의 텍스트 가져오기
            if (text.includes("❌")) {
              $(this).closest('tr').show(); // 부모 <tr> 요소 보이기
              found = true; // 일치하는 회원을 찾았으므로 플래그 설정
            } else {
              $(this).closest('tr').hide(); // 부모 <tr> 요소 숨기기
            }
          });
      
          if (!found) {
            $('#msgTbody').html("<td>모든 문의를 처리 했습니다 😎</td>");
            $('#msgTbody').show(); // 일치하는 회원이 없는 경우 메시지 출력
          } else {
            $('#msgTbody').html("");
            $('#msgTbody').hide(); // 일치하는 회원이 있는 경우 메시지 숨기기
          }
        });

})


















function editIquiry(no,answer,empty) {
    $.ajax({
        url: "/skillmarket/admin/inquiry/answer", // 이미지 업로드를 처리하는 서버 엔드포인트 URL
        type: "POST",
        dataType: "json",
        data: {
            no:no,
            answer:answer,
            empty:empty,
        },
        success: function(count) {
            
            alert("답변성공")
            isUploading = false;

            $("#qnaStatus_"+no).text("✔️")
            // 카운트 새로 작성
            $(".NCunt").text(`${ count.statusNCnt }`)

            if ($(".NCunt").text() === "0") { $(".NCunt").css("color","#dddddd");}
            else{$(".NCunt").css("color","#e56c57");}

            $(".YCunt").text(`${ count.statusYCnt }`)
            if ($(".YCunt").text() === "0") { $(".YCunt").css("color","#dddddd");}
            else{$(".YCunt").css("color","#525252");}
        },
        error: function(xhr, status, error) {
            alert("실패...")
            isUploading = false;
        }
    })
}

