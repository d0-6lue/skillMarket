$(document).ready(function() {

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

        $("#meddle_cat_area_1").attr("id", clickCatArea);


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
    
    




});