<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/setup.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <div id="wrap">
        
        <!-- aside -->
        <%@ include file="/WEB-INF/views/admin/common/aside.jsp"%>

        <header>
                
            <h1>배너 관리</h1>
        
        </header>

        <article>
            <div id="banner_area">
                <div class="banner_area_col">
                    <div>
                        <div>배너 1</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <span>배너 추가</span> 
                        <span class="material-symbols-outlined">
                        add_circle
                        </span>
                        <input type="file" style="display: none;">
                    </div>
                </div>

                <div class="banner_area_col">
                    <div>
                        <div>배너 2</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <div>
                            <span>배너 추가</span> 
                            <span class="material-symbols-outlined">
                            add_circle
                            </span>
                        </div>
                        
                    </div>
                </div>

                <div class="banner_area_col">
                    <div>
                        <div>배너 3</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <span>배너 추가</span> 
                        <span class="material-symbols-outlined">
                        add_circle
                        </span>
                    </div>
                </div>

                <div class="banner_area_col">
                    <div>
                        <div>배너 4</div>
                        <input type="button" value="수정 / 삭제" class="edit_btn" disabled>
                    </div>
                    <div class="banner_add_area">
                        <span>배너 추가</span> 
                        <span class="material-symbols-outlined">
                        add_circle
                        </span>
                    </div>
                </div>
            </div>
        </article>

    </div>


</body>
</html>

<link rel="stylesheet" href="${root}/static/css/admin/common/article.css">

<style>

    article {
        background-color: #eeeded;
    }

    #banner_area {
        width: 95%;
        height: 95%;
        display: grid;
        grid-template-rows: repeat(4 , 1fr);
        grid-gap: 15px;
    }

    #banner_area > div {
        width: 100%;
        height: 100%;
        box-shadow: 1px 2px 0px rgba(0, 0, 0, 0.25);
        border-radius: 10px;
        background-color: #FDFDFD;
        justify-self: center;
        align-self: center;
        display: grid;
        grid-template-columns: 1.2fr 3fr;
       
        text-align: center;
       
    }

    .banner_area_col {
        height: 80%;
        padding: 10px;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .banner_area_col>div {
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        font-size: 25px;
        color: #3A3A3A;
        font-weight: 700;
    }

    

    .banner_area_col > div:first-child {
        border-right: 2px solid  #d1d1d1;
    }

    .banner_area_col  input {
        width: 140px;
        height: 40px;
        font-weight: 600;
        font-size: 17px;
        color: #545353;
        background-color: #FFD15A;
        margin-top: 10px;
        border: none;
    }

    .banner_area_col > div:nth-child(2){
        border-radius: 10px;
        border: 1px solid CBCBCB;
        display: flex;
        flex-direction: row;
        align-items: center;
        margin-left: 10px; 
        background-color: #F4F3F3;
    }

    .banner_area_col > div:nth-child(2) span {
        margin-left: 10px;
        pointer-events: none;
    }

    .banner_area_col > div:nth-child(2) input {
        display: none;
    }

    .edit_btn {
         border-radius: 10px;
    }

    .edit_btn:disabled {
        background-color: #afa99b;
    }


</style>

  <script>
    const bannerArea =  document.querySelectorAll(".banner_add_area");
    const editBtn = document.querySelectorAll(".edit_btn");


    bannerArea.forEach((e)=>{
        e.addEventListener("click",(event)=>{
            
            const inputEl = event.currentTarget.querySelector("input[type='file']");
            const inputFile = '<input type="file" style="display: none;">';
            console.log(inputEl);
            
            const pel = event.target.parentElement.children[1];

            inputEl.click();
           

        })

        e.addEventListener("change",(event)=>{
            console.log(event.target.parentElement.parentElement.children[0].children[1] );
            event.target.parentElement.parentElement.children[0].children[1].disabled = false;
        })

        
    })  
    
   
    


  </script>