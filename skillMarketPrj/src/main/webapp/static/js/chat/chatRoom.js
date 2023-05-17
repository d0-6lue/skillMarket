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
    regist();

    // 채팅내역 가져오기
    loadChat();

}


// WebSocket 서버로 부터 메시지가 오면 호출 되는 함수
webSocket.onmessage = function(message) {

    jsonMessage = JSON.parse(message.data);

    const type = jsonMessage.type;

    if(type == 'load') {

        printChatAll(jsonMessage.chatList);

    }
    else if(type == 'add') {

        addChat(jsonMessage.chatList);

    }



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
// 유저 번호는 loginMemberNo
// 마지막 채팅 번호
let lastChatNo = 0;

// regist
function regist() {

    let msg = {
        'type' : 'regist',
        'senderNo' : loginMemberNo,
        'roomNo' : paramNo,
    }

    webSocket.send(JSON.stringify(msg));

}
// 첫실행때 로드하기
function loadChat() {

    let msg = {
        'type' : 'loadChat',
        'senderNo' : loginMemberNo,
        'roomNo' : paramNo,
    }

    webSocket.send(JSON.stringify(msg));

}

// 채팅 보냈을때
function sendChat() {

    const chatContent = document.querySelector(".chat-msg");

    if(chatContent.value != ""){
        let msg = {
            'type' : 'sendChat',
            'senderNo' : loginMemberNo,
            'roomNo' : paramNo,
            'content' : chatContent.value,
            'lastChatNo' : lastChatNo,
        }
        
        webSocket.send(JSON.stringify(msg));
        
        chatContent.value = "";

    }

}
// 버튼 클릭 할 때 or enter 눌렀을 때 sendChat()
const sendBtn = document.querySelector(".chat-send-btn");
sendBtn.addEventListener("click", function(){
    sendChat();
} )
const input = document.querySelector(".chat-msg");
input.addEventListener("keyup" , function(event) {
    if (event.keyCode === 13) {
        document.querySelector(".chat-send-btn").click();
      }
})

// 요청사항 보내기
function sendRequest() {

    let msg = {
        'type' : 'request',
    }
    
    webSocket.send(JSON.stringify(msg));
    
    requestModalToggle();
    
}
const request = document.querySelector(".request-modal-btn");
request.addEventListener("click" , function() {

    sendRequest();

})

//-----------------------------------------------------------------------------------------------------------------------------------------

// 모든 채팅 그리기
function printChatAll(chatList) {

    const chatBox = document.querySelector(".chat-box");
    chatBox.replaceChildren("");

    chatList.forEach(chatVo => {
        
        const chatNo = chatVo.chatNo;
        const sender = chatVo.chatSender;
        const content = chatVo.chatContent;
        const enrollDate = chatVo.chatEnrollDate;
        const chatRead = chatVo.chatRead;
        const chatStatus = chatVo.chatStatus;
        const chatAttchment = chatVo.chatAttchment;

        // chat ----------------------------------------------------
        const chat = document.createElement("div");
        chat.classList.add("chat");

        // chat-info ------------------------------------------------
        const chatInfo = document.createElement("div");
        chatInfo.classList.add("chat-info");

        if( sender != loginMemberNo ) {
            const chatSender = document.createElement("span");
            chatSender.classList.add("chat-sender");
            chatSender.classList.add("regular");
            chatSender.innerText = sender;

            chatInfo.append(chatSender);
        }

        const chatEnrollDate = document.createElement("span");
        chatEnrollDate.classList.add("chat-enroll-date");
        chatEnrollDate.classList.add("regular");
        chatEnrollDate.innerText = enrollDate;

        chatInfo.append(chatEnrollDate)
        // chat-info ------------------------------------------------

        // chat-contents---------------------------------------------
        const chatContent = document.createElement("div");
        chatContent.classList.add("chat-contents");
        chatContent.classList.add("regular");
        chatContent.innerText = content;
        // chat-contents---------------------------------------------


        chat.append(chatInfo);
        chat.append(chatContent);
        // chat -----------------------------------------------------
        // 보낸이가 본인일경우
        if( sender == loginMemberNo ) {
            chat.classList.add("my-chat");
            chatInfo.classList.add("my-chat-info");
        }

        chatBox.append(chat);
        // 읽음여부
        if( sender == loginMemberNo ) {
            const readCheck = document.createElement("div");
            readCheck.classList.add("regular");
            readCheck.classList.add("readcheck");
            readCheck.classList.add("my-chat");

            if(chatRead == 'O'){
                readCheck.innerText = "읽음";
            }
            else if(chatRead == 'X') {
                readCheck.innerText = "안읽음";
            }

            chatBox.append(readCheck);
        }
        
        lastChatNo = chatNo;

    }); // forEach
    
    // 스크롤 제일 아래로
    chatBox.scrollTop = chatBox.scrollHeight;
    
}

// 새로운 채팅 더하기
function addChat(chatList) {

    const chatBox = document.querySelector(".chat-box");

    chatList.forEach(chatVo => {
        
        const chatNo = chatVo.chatNo;
        const sender = chatVo.chatSender;
        const content = chatVo.chatContent;
        const enrollDate = chatVo.chatEnrollDate;
        const chatRead = chatVo.chatRead;
        const chatStatus = chatVo.chatStatus;
        const chatAttchment = chatVo.chatAttchment;

        // chat ----------------------------------------------------
        const chat = document.createElement("div");
        chat.classList.add("chat");

        // chat-info ------------------------------------------------
        const chatInfo = document.createElement("div");
        chatInfo.classList.add("chat-info");

        if( sender != loginMemberNo ) {
            const chatSender = document.createElement("span");
            chatSender.classList.add("chat-sender");
            chatSender.classList.add("regular");
            chatSender.innerText = sender;

            chatInfo.append(chatSender);
        }

        const chatEnrollDate = document.createElement("span");
        chatEnrollDate.classList.add("chat-enroll-date");
        chatEnrollDate.classList.add("regular");
        chatEnrollDate.innerText = enrollDate;

        chatInfo.append(chatEnrollDate)
        // chat-info ------------------------------------------------

        // chat-contents---------------------------------------------
        const chatContent = document.createElement("div");
        chatContent.classList.add("chat-contents");
        chatContent.classList.add("regular");
        chatContent.innerText = content;
        // chat-contents---------------------------------------------


        chat.append(chatInfo);
        chat.append(chatContent);
        // chat -----------------------------------------------------
        // 보낸이가 본인일경우
        if( sender == loginMemberNo ) {
            chat.classList.add("my-chat");
            chatInfo.classList.add("my-chat-info");
        }

        chatBox.append(chat);
        // 읽음여부
        if( sender == loginMemberNo ) {
            const readCheck = document.createElement("div");
            readCheck.classList.add("regular");
            readCheck.classList.add("readcheck");
            readCheck.classList.add("my-chat");

            if(chatRead == 'O'){
                readCheck.innerText = "읽음";
            }
            else if(chatRead == 'X') {
                readCheck.innerText = "안읽음";
            }

            chatBox.append(readCheck);
        }
        
        lastChatNo = chatNo;

    }); // forEach
    
    // 스크롤 제일 아래로
    chatBox.scrollTop = chatBox.scrollHeight;
    
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