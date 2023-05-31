$(document).ready(function() {

    let BMScheck = "";

    let flag = false;

    $(".meddle_cat_area").click(function () {
        
        if (flag) { console.log("더블클릭컷");return;}
         flag = true;
        setTimeout(() => flag = false , 200); //0.2초 후에

        

        const meddleCatArea = $(this).attr("id")
        const lastUnderscoreIndex = meddleCatArea.lastIndexOf("_");
		const no = meddleCatArea.substring(lastUnderscoreIndex + 1);
        const name = $("#list_big_name_"+no).text();
        const tag =  $('#meddle_cat_area_'+no);

        const originHtml = $("#cat_THEAD").html();

        const id = tag.attr('id'); // 아이디 가져오기
        const className = tag.attr('class'); // 클래스 가져오기

        console.log(id);
        console.log(className);

        $("#meddle_cat_area_1").attr("id", "clickCatArea");


        // .removeAttr("id").removeClass();
        // .attr("id", "meddle_cat_area_1").addClass("meddle_cat_area");

        $("#cat_THEAD").html(`
        <tr>
            <th>
                <span>
                    ${name} > 중분류
                </span>
            </th>
            <th>
                <span class="thead_center_size">
                    ${name}
                </span>
            </th>
            <th>
                <div>
                    <input type="text" placeholder="카테고리 검색🔍"> 
                </div>
            </th>
        </tr>
        `)

        $("#meddle_cat_box_"+no).css("display","grid")

        
        $("#clickCatArea").click( function () {
    
            $("#cat_THEAD").html(originHtml);
            $("#clickCatArea").attr("id", "meddle_cat_area_1").addClass("meddle_cat_area");
    
        }) 
        
        
        
    })
    
    

    //모달 닫기
    $(".close").click(function () {
        $(".modal").css("display" , "none")
    })

    //모달 열기
    $(".add_cat_btn").click(function () {
        
        $(".modal").css("display" , "block")

    })

   
    //추가버튼 선택
    $(".add_cat_btn_S").click(function () {

        const id = $(this).attr("id");
        const lastUnderscoreIndex = id.lastIndexOf("_");
        const no = id.substring(lastUnderscoreIndex + 1);

        
        const Btnclass = $(this).attr("class");
		const index = Btnclass.lastIndexOf("_");
		const classNo = Btnclass.substring(index + 1);

        console.log(no);

        
        // 
        if ($(this).hasClass("checked")) {
            
            if (no === 'meddle') {
                $("#cat_select_big").prop("disabled", true);
            }
            if (no === 'small') {
                $("#cat_select_big").prop("disabled", true);
                $("#cat_select_meddle").prop("disabled", true);
            }
            
            BMScheck = "";

            $(this).removeClass("checked");
        }

        else {
            
            if (no === 'meddle') {
                
                $("#cat_select_big").prop("disabled", false);
                
            }
            if (no === 'small') {
                $("#cat_select_big").prop("disabled", false);
                $("#cat_select_meddle").prop("disabled", false);
                
            }
            
            BMScheck = no;

            $(this).addClass("checked");
        }
            
        
        

    })

    //등록
    $("#submitBtn").click(function () {



        category();


    })

    // 모달 카테고리 대
    $("#cat_select_big").change(function () {
        
        const option =  $("#cat_select_big").val();
        const name =  $("#cat_select_big_option_"+option).text();

        var x = option.slice(0, 1) // 첫숫자 자르기

        $('.cat_select_meddle_option').each(function() {
            const number = $(this).val().slice(0, 1); // .list_memberId_td의 텍스트 가져오기
            if (number == option) {
              $(this).show(); // 부모 <tr> 요소 보이기
            //   found = true; // 일치하는 회원을 찾았으므로 플래그 설정
            } else {
              $(this).hide(); // 부모 <tr> 요소 숨기기
            }
        });

        $("#input_big").val(option);
        $("#path_big").text(name);

    })

    // 모달 카테고리 중
    $("#cat_select_meddle").change(function () {
        
        const option =  $("#cat_select_meddle").val();

        const name =  $("#cat_select_meddle_option_"+option).text();

        var x = option.slice(0, 1)

        $('.cat_select_small_option').each(function() {
            const number = $(this).val().slice(0, 1); // .list_memberId_td의 텍스트 가져오기
            if (number == option) {
              $(this).show(); // 부모 <tr> 요소 보이기
            //   found = true; // 일치하는 회원을 찾았으므로 플래그 설정
            } else {
              $(this).hide(); // 부모 <tr> 요소 숨기기
            }
        });
        
        $("#input_meddle").val(option);
        $("#path_meddle").text(name);

    })

    // 모달 카테고리 소
    $("#cat_select_small").change(function () {
        
        const option =  $("#cat_select_small").val();

        const name =  $("#cat_select_small_option_"+option).text();

        $("#input_big").val(option);
        $("#path_small").text(name);

    })

    

    // 카테고리 이름 입력
    $("#titleInput").on('input',function () {
        
        const name = $("#titleInput").val()

        console.log(BMScheck);

        $("#path_"+BMScheck).text(name);


    })


});




function category() {

    const big =  $("#input_big").val();
    const meddle =  $("#input_meddle").val();
    const small =  $("#input_small").val();
    const name = $("#titleInput").val()


    if (big === "" || meddle === "" || name === "") {
        alert("모든 카테고리를 채우세요 😓")
        return;
    }

    $.ajax({
        url: "/skillmarket/admin/category/edit", // 이미지 업로드를 처리하는 서버 엔드포인트 URL
        type: "POST",
        dataType: "json",
        data: {
           big:big,
           meddle:meddle,
           name:name,
        },
        success: function(count) {
            
            alert("답변성공")
            isUploading = false;

        },
        error: function(xhr, status, error) {
            alert("실패...")
            isUploading = false;
        }
    })
}