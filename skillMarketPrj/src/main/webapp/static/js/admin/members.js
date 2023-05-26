window.onload = function() {


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


    // $('#search_input').on('input', function() {
        
    //       const inputValue = $(this).val().trim(); // 입력된 값 가져오기
          
    //       console.log(inputValue);

    //       $('.list_memberId_td').each(function() {
    //         var text = $(this).text().trim(); // .list_memberId_td의 텍스트 가져오기
    //         if (text.includes(inputValue)) {
    //           $(this).closest('tr').show(); // 부모 <tr> 요소 보이기
    //         } else {
    //           $(this).closest('tr').hide(); // 부모 <tr> 요소 숨기기
    //         }
    //       });
        
    //   });


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





};
