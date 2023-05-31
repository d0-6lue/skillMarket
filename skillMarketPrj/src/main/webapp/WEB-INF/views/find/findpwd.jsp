<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/find/findpwd.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    
    <div class="header">

        <div class="header-area">
            
            <div class="header-detailarea">
                
                <div class="header-logo">
                    <a href="/skillmarket/home" class="header-logoimg">
                        <img src="static/svg/메인로고.svg" alt="로고이미지">
                    </a>
                </div>
    
            </div>
            
        </div>


    </div>

    <main>
        <form action="/skillmarket/find/search-pwd" method="post" onsubmit="return emailCheck()">
            <div class="find-body">
                <div class="find-title bold">아이디 / 비밀번호 찾기</div>
                <div class="choice-find-btn">
                    <a href="${root}/forgot-id" class="findid-btn bold"><div>아이디</div></a>
                    <a href="${root}/forgot-pwd" class="findpwd-btn bold"><div>비밀번호</div></a>
                </div>
                
                <div>
                    <div class="findid-title bold">비밀번호 찾기</div>
                    <div>아이디와 이메일 주소를 입력해주시면 해당 이메일로 인증번호를 보내줍니다.</div>
                    <div class="findid-email-title bold">아이디</div>
                    <input type="text" name="memberId" class="findid-email" placeholder="아이디를 입력해주세요">
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
        let checkIdEmail = 0;
        const sendEmail = document.querySelector("input[name=memberEmail]").value;
        const sendId = document.querySelector("input[name=memberId]").value;
        
        $.ajax({
            url : '${root}/check-id-email',
            type : 'POST',
            async : false,
            data : {
                memberId : sendId,
                memberEmail : sendEmail
            },
            success : function(e){
                console.log(e);
                if(e == "0"){
                    checkIdEmail = 1;
                }else{
                    alert("해당 아이디와 이메일이 일치하지 않습니다.")
                }
            },
            error : function(e){
                console.log(e);
            }
            
        });
        
        if(checkIdEmail != 1){
            return ;
        }
        
        alert("인증번호를 발송했습니다.");

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

        if(hiddenKey == ""){
            alert("인증번호 검사해주세요");
            return false;
        }

        
    }

</script>

