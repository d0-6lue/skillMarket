//-----------------------------------------------------------------------------------
// 입력 유효성 검사


// 공백 - 정규 표현식
const spaceRegExp = /\s/g;
// 한글 자음만 -정규 표현식
const koreanConsonantsRegExp = /[ㄱ-ㅎ]/g;
// 한글 - 정규 표현식
const koreanRegExp = /[ㄱ-ㅣ가-힣]/g;
// 숫자로 시작 - 정규표현식
const startNumRegExp = /^\d.*/;
// 숫자 - 정규표현식
const numRegExp = /\d/g;
// 소문자 -
const smallLetterRegExp = /[a-z]/g;
// 대문자 -
const capitalLetterRegExp = /[A-Z]/g;
// 특수문자 - 정규표현식
const regExp1 = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;


// 아이디 중복 검사
const idInputArea = document.querySelector("input[name=memberId]");

idInputArea.addEventListener("change", function() {
    const idCheckAlter = document.querySelector("#check-id-availability");

    
    const idString = idInputArea.value;

    //길이
    if(idString.length < 6 ){
        idCheckAlter.innerText = "아이디가 6자 미만입니다."
    }
    else if(idString.length > 12){
        idCheckAlter.innerText = "아이디가 12자 보다 많습니다."
    }
    // 공백
    else if (spaceRegExp.test(idString) == true){
        idCheckAlter.innerText = "아이디에 공백문자는 사용 할 수 없습니다."
    }
    // 특수문자
    else if(regExp1.test(idString) == true){
        idCheckAlter.innerText = "아이디에 특수문자는 사용 할 수 없습니다."
    }
    // 한글
    else if(koreanRegExp.test(idString)){
        idCheckAlter.innerText = "아이디에는 한글을 사용 할 수 없습니다."
    }
    // 숫자로 시작
    else if(startNumRegExp.test(idString)){
        idCheckAlter.innerText = "아이디는 숫자로 시작 할 수 없습니다."
    }
    else {
        idCheckAlter.innerText = "";
    }
    idCheckAlter.style.color = "red";

});

// 아이디 중복 검사
const idCheckBtn = document.querySelector("#duplicate-check-btn");
idCheckBtn.addEventListener("click", function(){
    const memberId = document.querySelector("input[name=memberId").value;
    const idCheckInput = document.querySelector("input[name=idCheckInput]");
    console.log(memberId);
    const idData = {
        'memberId' : memberId
    };
    console.log(idData);

    $.ajax({
        url : '/skillmarket/join/check-id',
        type : 'POST',
        data : idData,
        success : function(e){
            if(e == 0){
                $('input[name=idCheckInput]').attr('value',"");
                alert("사용 불가능한 아이디입니다.");
            }else{
                $('input[name=idCheckInput]').attr('value',1);
                alert("사용가능한 아이디입니다.");
            }

        },
        error : function(e){
            console.log(e);
        }

    })
    
})

// 비밀번호는 새로입력할 때마다 초기화
const pwd1InputArea = document.querySelector("input[name=memberPwd]");

// pwd1InputArea.addEventListener("focus", function() {
//     pwd1InputArea.value = null;
// })


// 비밀번호 유효성 검사
pwd1InputArea.addEventListener("change", function() {
    const pwd1CheckAlter = document.querySelector("#check-pwd1-availability");

    
    const pwd1String = pwd1InputArea.value;

    //길이
    if( pwd1String.length < 8 ){
        pwd1CheckAlter.innerText = "비밀번호가 8자 미만입니다."
    }
    else if( pwd1String.length > 20 ){
        pwd1CheckAlter.innerText = "비밀번호가 20자 보다 많습니다."
    }
    // 공백
    else if ( spaceRegExp.test(pwd1String) == true ){
        pwd1CheckAlter.innerText = "비밀번호에 공백문자는 사용 할 수 없습니다."
    }
    // 한글
    else if( koreanRegExp.test(pwd1String) ){
        pwd1CheckAlter.innerText = "비밀번호에 한글을 사용 할 수 없습니다."
    }
    // 특수문자 미포함
    else if( regExp1.test(pwd1String) == false ){
        pwd1CheckAlter.innerText = "비밀번호에는 특수문자를 포함해야 합니다."
    }
    // 숫자 미포함
    else if( numRegExp.test(pwd1String) == false ){
        pwd1CheckAlter.innerText = "비밀번호에는 숫자 포함해야 합니다."
    }
    // 영문 소문자 미포함
    else if( smallLetterRegExp.test(pwd1String) == false ){
        pwd1CheckAlter.innerText = "비밀번호에는 영문 소문자를 포함해야 합니다."
    }
    else {
        pwd1CheckAlter.innerText = "";
    }
    pwd1CheckAlter.style.color = "red";

});

// 비밀번호 확인도 새로입력할 때마다 초기화
const pwd2InputArea = document.querySelector("input[name=memberPwd2]");

pwd2InputArea.addEventListener("focus", function() {
    pwd2InputArea.value = null;
})


// 비밀번호 확인 검사
pwd2InputArea.addEventListener("change", function() {
    const pwd1String = pwd1InputArea.value;
    const pwd2String = pwd2InputArea.value;

    const pwd2CheckAlter = document.querySelector("#check-pwd2-availability");

    if(pwd1String != pwd2String) {
        pwd2CheckAlter.innerText = "비밀번호와 값이 같지 않습니다."
    }
    else {
        pwd2CheckAlter.innerText = "";
    }
    pwd2CheckAlter.style.color = "red";
})


// 닉네임 중복 검사


const nickCheckBtn = document.querySelector("#duplicate-check-nick-btn");
nickCheckBtn.addEventListener("click", function(){

    const memberNick = document.querySelector("input[name=memberNick]").value;
    console.log(memberNick);
    const NickData = {
        'memberNick' : memberNick
    };

    $.ajax({
        url : '/skillmarket/join/check-nick',
        type : 'POST',
        data : NickData,
        success : function(e){
            if(e == 0){
                $('input[name=nickCheckInput]').attr('value',"");
                alert("사용 불가능한 닉네임입니다.")
            }else{
                $('input[name=nickCheckInput]').attr('value',1);
                alert("사용가능한 닉네임입니다.");
            }

        },
        error : function(e){
            console.log(e);
        }

    })

})

// 닉네임 유효성 검사
const nickInputArea = document.querySelector("input[name=memberNick]");

nickInputArea.addEventListener("change", function() {
    const nickCheckAlter = document.querySelector("#check-nick-availability");

    
    const nickString = nickInputArea.value;

    // 공백
    if (spaceRegExp.test(nickString) == true){
        nickCheckAlter.innerText = "닉네임에 공백문자는 사용 할 수 없습니다."
    }
    // 특수문자
    else if(regExp1.test(nickString) == true){
        nickCheckAlter.innerText = "닉네임에 특수문자는 사용 할 수 없습니다."
    }
    // 자음만 쓴 경우
    else if(koreanConsonantsRegExp.test(nickString) == true){
        nickCheckAlter.innerText = "닉네임에 자음만 사용 할 수 없습니다."
    }
    else {
        nickCheckAlter.innerText = "";
    }
    nickCheckAlter.style.color = "red";

});


// 이메일 유효성 검사
const emailRegExp = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/;

const emailInputArea = document.querySelector("input[name=memberEmail]");

emailInputArea.addEventListener("change", function(){
    const emailCheckAlter = document.querySelector("#check-email-availability");

    const emailString = emailInputArea.value;
    if(emailRegExp.test(emailString) != true) {
        emailCheckAlter.innerText = "이메일 형식에 맞게 작성해주세요."
    }
    else {
        emailCheckAlter.innerText = "";
    }
    emailCheckAlter.style.color = "red";
});

// 이메일 중복 검사
const emailCheckBtn = document.querySelector("#duplicate-check-email-btn");
emailCheckBtn.addEventListener("click", function(){

    const memberEmail = document.querySelector("input[name=memberEmail]").value;
    const EmailData = {
        'memberEmail' : memberEmail
    };

    $.ajax({
        url : '/skillmarket/join/check-email',
        type : 'POST',
        data : EmailData,
        success : function(e){
            if(e == 0){
                $('input[name=emailCheckInput]').attr('value',"");
                alert("중복된 이메일입니다.")
            }else{
                $('input[name=emailCheckInput]').attr('value',1);
                alert("사용가능한 이메일입니다.");
            }

        },
        error : function(e){
            console.log(e);
        }

    })

});


// 연락처 유효성 검사
const phoneRegExp = /(^01([0|1|6|7|8|9]))(\d{3,4})(\d{4}$)/;

const phoneInputArea = document.querySelector("input[name=memberPhone]");

phoneInputArea.addEventListener("change", function(){
    const phoneCheckAlter = document.querySelector("#check-phone-availability");

    const phoneString = phoneInputArea.value;
    if(phoneRegExp.test(phoneString) == false) {
        phoneCheckAlter.innerText = "연락처 형식에 맞게 작성해주세요.";
    }
    else if(phoneString.length > 11) {
        phoneCheckAlter.innerText = "연락처 형식에 맞게 작성해주세요.";
    }
    else if(phoneString.length < 11) {
        phoneCheckAlter.innerText = "연락처 형식에 맞게 작성해주세요.";
    }
    else {
        phoneCheckAlter.innerText = "";
    }
    phoneCheckAlter.style.color = "red";
});

// 전체 value 값 없을 때 회원가입 막기

const nameInputArea = document.querySelector("input[name=memberName]");
const address1InputArea = document.querySelector("input[name=memberAddress]");
const address2InputArea = document.querySelector("input[name=memberAddress2]");

const inputList = document.querySelectorAll("input");
function submitCheck(){
    for(let i = 0; i < inputList.length; i++){
        if(inputList[i].value == ""){
            inputList[i].focus();
            return false;
        }
    }
}
