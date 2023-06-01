$(document).ready(function() {
    $(".cat_select_meddle_option").hide();

    let BMScheck = "";
    let flag = false;


    $(".meddle_cat_area").click(function () {
        
        if (flag) { console.log("더블클릭컷");return;}
         flag = true;
        setTimeout(() => flag = false , 200); //0.2초 후에

        $(".cat_box_first_td").css("display","none")


        const id = $(this).attr("id");
		const lastUnderscoreIndex = id.lastIndexOf("_");
		const nox = id.substring(lastUnderscoreIndex + 1);

        
        
        $(".meddle_cat_area").each(function () {
            if (!$(this).is("#meddle_cat_area_"+nox)) {
                $(this).hide();
            }
            
        })

        console.log(nox);
        

        console.log($("#meddle_cat_box_"+ nox ).css("display"));
        
        $("#meddle_cat_box_"+ nox ).css("display", "none");

        const meddleCatArea = $(this).attr("id")
        const lI = meddleCatArea.lastIndexOf("_");
		const no = meddleCatArea.substring(lI + 1);
        const name = $("#list_big_name_"+no).text();
        const tag =  $('#meddle_cat_area_'+no);

        const originHtml = $("#cat_THEAD").html();

        const idName = tag.attr('id'); // 아이디 가져오기
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
            $("#path_"+BMScheck).css("font-size","20");
            $("#path_"+BMScheck).css("color","#999a98");
            BMScheck = "";

            $("#titleInput").css("display" , "none")
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
            $("#path_"+BMScheck).css("font-size","25");
            $("#path_"+BMScheck).css("color","#212529");
            $("#titleInput").css("display" , "block")
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
        console.log(option);
        console.log(name);
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

        console.log(name === "");

        if (name === "") {
            console.log(BMScheck);
            $("#path_"+BMScheck).text("새로운 카테고리");
            return;
        }
        $("#path_"+BMScheck).text(name);
        

    })


    function category() {
    
        const big =  $("#input_big").val();
        const meddle =  $("#input_meddle").val();
        const small =  $("#input_small").val();
        const name = $("#titleInput").val()

        console.log(BMScheck);
    
        console.log(big);
        console.log(meddle);
        console.log(name);
    

        if (name === "") {
            alert("카테고리 이름을 채우세요😓")
            return;
        }
    
        $.ajax({
            url: "/skillmarket/admin/category/edit", // 이미지 업로드를 처리하는 서버 엔드포인트 URL
            type: "POST",
            // dataType: "json",
            data: {
               big:big,
               meddle:meddle,
               name:name,
               small:small,
               BMScheck:BMScheck,
            },
            success: function(ok) {
                
                alert("카테고리추가 성공")
    
            },
            error: function(xhr, status, error) {
                alert("실패...")
            }
        })
    }


    $(".cat_box_td").click(function () {
        
        const id = $(this).attr("id");
		const lastUnderscoreIndex = id.lastIndexOf("_");
		const no = id.substring(lastUnderscoreIndex + 1);

        $(".cat_box_td").css("display", "none")

        $(".meddle_tr_"+no).css("display", "table");
        console.log($("#meddle_tr_"+no).css());



        $(".thead_center_size").text("")

    })

    $(".cat_box_td_m_").click(function () {
        
        const id = $(this).attr("id");
		const lastUnderscoreIndex = id.lastIndexOf("_");
		const no = id.substring(lastUnderscoreIndex + 1);
        console.log(no);
        const name = $(".cat_box_first_td_name_"+no)

        $(".cat_box_td_m_").css("display", "none")

        $(".small_tr_"+no).css("display", "table");
        console.log($("#meddle_tr_"+no).css());
        

    })

});



