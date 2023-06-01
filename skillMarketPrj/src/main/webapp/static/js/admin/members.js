window.onload = function() {

    $('#msgTbody').hide()


    // 상태별 필터
    $('#status_Sort').click(function () {

        //값에 따라 1번째 클릭은 오름차순 2번째는 내림차순 3번째는 기본값으로 돌아가게

        //정렬 순서 가져오기
        const sortOrder = $(this).data('sort_order');

        if (sortOrder === undefined || sortOrder === 3) {
            console.log('오름차순');
            $(this).data('sort_order' , 1)
            console.log($(this).data);
        }

        else if (sortOrder === 1) {
            console.log('내림차순');
            $(this).data('sort_order', 2)
        }

        else if (sortOrder === 2) {
            console.log('기본값');
            $(this).data('sort_order' , 3)
        }

    });




      $('#search_input').on('input', function() {
        const inputValue = $(this).val().trim(); // 입력된 값 가져오기
        
        let found = false; // 일치하는 회원을 찾았는지 여부를 나타내는 플래그
    
        $('.list_memberId_td').each(function() {
            const text = $(this).text().trim(); // .list_memberId_td의 텍스트 가져오기
            if (text.includes(inputValue)) {
              $(this).closest('tr').show(); // 부모 <tr> 요소 보이기
              found = true; // 일치하는 회원을 찾았으므로 플래그 설정
            } else {
              $(this).closest('tr').hide(); // 부모 <tr> 요소 숨기기
            }
          });
      
          if (!found) {
            $('#msgTbody').show(); // 일치하는 회원이 없는 경우 메시지 출력
          } else {
            $('#msgTbody').hide(); // 일치하는 회원이 있는 경우 메시지 숨기기
          }
        });


    // 상태요약 정렬
    $("#freeLancerModal_open").click(function () {

      console.log();
      
    })


    $(".member_TR").click(function () {
      console.log(this);

      const id = $(this).attr("id");
      const lastUnderscoreIndex = id.lastIndexOf("_");
      const no = id.substring(lastUnderscoreIndex + 1);

      console.log(no);

      $("#modal_memberDetail_"+no).css("display","block")

    })


    // 모달창 닫기 버튼 클릭 시
    $(".close").click(function() {
      $(".modal").css("display", "none"); // 모달창을 숨김
  });



  $("#submitBtn").click(function () {

    const Btnclass = $(this).attr("class");
		const index = Btnclass.lastIndexOf("_");
		const memberNo = Btnclass.substring(index + 1);



    $.ajax({
      url: "/skillmarket/admin/members/stop", // 이미지 업로드를 처리하는 서버 엔드포인트 URL
      type: "POST",
      // dataType: "json",
      data: {
        memberNo:memberNo,
      },
      success: function(ok) {
          
          alert("계정 정지 완료")

      },
      error: function(xhr, status, error) {
          alert("실패...")
      }

    })
  })


  $("#reportMemberModal_open").click(function () {

    console.log(1);
    
    $(".member_TR").each(function () {
      const firstClass = $(".member_TR").attr("class").split(" ")[0];
      const index = firstClass.lastIndexOf("_");
      const memberNo = firstClass.substring(index + 1);
      console.log(firstClass);
      
      if (memberNo === 3 || memberNo === 4) {
        $("#member_TR_"+memberNo).show(); // 부모 <tr> 요소 보이기
        // found = true; // 일치하는 회원을 찾았으므로 플래그 설정
      } else {
        $("#member_TR_"+memberNo).hide(); // 부모 <tr> 요소 숨기기
      }

    })
   
  
  
  })

};
