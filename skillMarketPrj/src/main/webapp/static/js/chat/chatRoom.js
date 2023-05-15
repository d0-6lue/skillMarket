// WebSocket
const path = window.location.pathname;

const root = path.substring(
    0, (path.substring(1, path.length)).indexOf('/') + 1
)

const host = window.location.host;

// WebSocket 오브젝트 생성 (자동으로 접속 시작함. OnOpen 함수 호출)
const webSocket = new WebSocket("ws://" + host + root + "/websocket");


// WebSocket 서버와 접속이 되면 호출되는 함수
webSocket.onopen = function(message) {

    // register 전송
    register();

    // 채팅내역 가져오기
    // loadChat();

}


// WebSocket 서버로 부터 메시지가 오면 호출 되는 함수
webSocket.onmessage = function(message) {

    // var loginNick = sessionStorage.getItem('loginNick');

    // const replyMsg = JSON.parse(message.data);

    // // 채팅 화면에 그리기
    // loadChat(replyMsg, loginNick);

    // chatBox.scrollTop = chatBox.scrollHeight;

}


// WebSocket 서버와 접속이 끊기면 호출되는 함수
webSocket.onclose = function(message) {

}


// WebSocket 서버와 통신 중에 에러가 발생하면 호출되는 함수
webSocket.onerror = function(message) {

}

//-----------------------------------------------------------------------------------------------------------------------------------------
// 견적서번호
const paramNo = (window.location.search).substring( (window.location.search).indexOf('=')+1);

function register() {

    let msg = {
        'type' : 'register',
        'sender' : loginMemberNo,
        'roomNo' : paramNo
    }

    webSocket.send(JSON.stringify(msg));

}


//-----------------------------------------------------------------------------------------------------------------------------------------

// 요청하기 눌렀을때 모달 활성화
const requestModalBtn = document.querySelector(".request-btn");
requestModalBtn.addEventListener("click", requestModalToggle);

//요청 취소 버튼(모달 비활성화)
const modalCancleBtn = document.querySelector(".request-modal-cancle-btn");
modalCancleBtn.addEventListener("click" , requestModalToggle);

// 모달 토글 함수
function requestModalToggle() {
    const requestModal = document.querySelector(".request-modal");

    requestModal.classList.toggle("modal-active")
}