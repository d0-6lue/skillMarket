<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/find/findid.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    
    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="${root}/static/svg/메인로고.svg" alt="로고이미지">
                    </a>
                </div>
    
            </div>
            
        </div>


    </div>

    <main>
        <form action="${root}/find/search-id" method="post" onsubmit="return emailCheck()">
            <div class="find-body">
                <div class="find-title bold">아이디 / 비밀번호 찾기</div>
                <div class="choice-find-btn">
                    <a href="${root}/forgot-id" class="findid-btn bold"><div>아이디</div></a>
                    <a href="${root}/forgot-pwd" class="findpwd-btn bold"><div>비밀번호</div></a>
                </div>
                
                <div>
                    <div class="findid-title bold">아이디 찾기</div>
                    <div>이메일 주소를 입력해주시면 해당 이메일로 인증번호를 보내드립니다.</div>
                    <div class="findid-email-title bold">이메일 주소</div>
                    <div>
                        <input type="email" name="memberEmail" class="findid-email" placeholder="이메일을 입력해주세요">
                        <button type="button" class="email-btn bold" onclick="sendKeyToEmail()">이메일 발송</button>
                    </div>
                        <div class="findid-email-title bold">인증번호</div>
                        <div>
                            <input type="hidden" name="hiddenKey" value="">
                            <input type="text" name="keyNum" placeholder="인증번호를 입력해주세요">
                            <button type="button" class="email-btn bold" onclick="checkKeyNum()">인증번호 확인</button>
                        </div>
                    <div class="id-search-btn-area">
                        <input type="submit" value="아이디 찾기" class="id-search-btn bold">
                    </div>
                </div>
                    
                    
                    
            </div>
        </form>
    </main>

</body>
</html>

<script>
    let keyNum = "";
    function sendKeyToEmail(){
        alert("인증번호를 발송했습니다.");
        const sendEmail = document.querySelector("input[name=memberEmail]").value;
        $.ajax({
            url : '${root}/mail-id',
            type : 'POST',
            data : {
                memberEmail : sendEmail
            },
            success : function(e){
                keyNum = e;
            },
            error : function(e){
                console.log(e);
            }
        });
    }

    function checkKeyNum(){
        const sendKeyValue = document.querySelector("input[name=keyNum]").value;

        if(sendKeyValue == keyNum){
            alert("인증번호가 일치합니다");
            $('input[name=hiddenKey]').attr('value',"1");
        }else{
            alert("인증번호가 일치하지않습니다.");
            $('input[name=hiddenKey]').attr('value',"");
        }
    }

    function emailCheck(){
        const hiddenKey = document.querySelector("input[name=hiddenKey]").value;
        const sendEmail = document.querySelector("input[name=memberEmail]").value;
        let checkEmail = false;

        if(hiddenKey == ""){
            alert("인증번호 검사해주세요");
            return false;
        }

        $.ajax({
            url : '${root}/join/check-email',
            type : 'POST',
            async : false,
            data : {
                memberEmail : sendEmail
            },
            success : function(e){
                if(e == "0"){
                    checkEmail = true;
                }else{
                    alert("해당 이메일로 가입한 아이디가 없습니다.")
                    checkEmail = false;
                }
            },
            error : function(e){
                console.log(e);
            }
        });

        return checkEmail;
        
    }

</script>

