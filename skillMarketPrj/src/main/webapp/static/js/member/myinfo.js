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

// 연락처 유효성 검사
const phoneRegExp = /(^01([0|1|6|7|8|9]))(\d{3,4})(\d{4}$)/;

const phoneInputArea = document.querySelector("input[name=memberPhone]");

phoneInputArea.addEventListener("change", function(){
    const phoneCheckAlter = document.querySelector("#check-phone-availability");

    const phoneString = phoneInputArea.value;
    if(phoneRegExp.test(phoneString) == false) {
        phoneCheckAlter.innerText = "연락처 형식에 맞게 작성해주세요."
    }
    else if(phoneString.length > 12) {
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