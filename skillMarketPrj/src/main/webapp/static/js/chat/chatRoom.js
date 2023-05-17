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

    const categoryNo = requestCategory.options[requestCategory.selectedIndex].value

    const content = document.querySelector('#request-content-textarea');


    let msg = {
        'type' : 'request',
        'categoryNo' : categoryNo,
        'content' : content.value,
        'senderNo' : loginMemberNo,
        'roomNo' : paramNo,
    }
    
    //옵션 추가
    if(categoryNo == 300) {
        const addOptionSelect = document.querySelector('.add-option-select');
        const addOptionNo = addOptionSelect.options[addOptionSelect.selectedIndex].value;

        const quantity = document.querySelector('.quantity-input').value;

        msg.addOptionNo = addOptionNo;
        msg.quantity = quantity;

    }
    //옵션 삭제
    else if(categoryNo == 400) {
        const select = document.querySelector('.delete-option-select');
        const deleteOptionNo =  select.options[select.selectedIndex].value;

        msg.deleteOptionNo = deleteOptionNo;
    }
    // 기한 단축
    else if(categoryNo == 500) {
        const numInput = document.querySelector(".request-input");

        msg.period = numInput.value;
    }
    // 기한 연장
    else if(categoryNo == 600) {
        const numInput = document.querySelector(".request-input");

        msg.period = numInput.value;
    }
    // 선금 요청
    else if(categoryNo == 800) {
        const numInput = document.querySelector(".request-input");

        msg.handsel = numInput.value * 10000;
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
            chatSender.innerText = sellerNick;

            chatInfo.append(chatSender);
        }

        const chatEnrollDate = document.createElement("span");
        chatEnrollDate.classList.add("chat-enroll-date");
        chatEnrollDate.classList.add("regular");
        chatEnrollDate.innerText = enrollDate;

        chatInfo.append(chatEnrollDate)
        // ------------------------------------------------

        // chat-contents---------------------------------------------
        const chatContent = document.createElement("div");
        chatContent.classList.add("chat-contents");
        chatContent.classList.add("regular");
        chatContent.innerText = content;
        // ---------------------------------------------


        chat.append(chatInfo);
        chat.append(chatContent);
        // -----------------------------------------------------
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
// contextPath
function getContextPath(){
    const hostIndex = location.href.indexOf( location.host ) + location.host.length;

    const contextPath = location.href.substring( hostIndex, location.href.indexOf( '/', hostIndex + 1 ));

    return contextPath;
}
// 요청 카테고리 번호 가져오기
const requestCategory = document.querySelector("#request-select");
requestCategory.addEventListener('change', getOption)
function getOption() {

    const contextPath = getContextPath();

    const catValue = requestCategory.options[requestCategory.selectedIndex].value

    const changeArea = document.querySelector(".change-area");

    let requestObject = {
        catNo : catValue,
        requestMsg : "",
        requestChangeValue : ""
    }

    if(catValue == 100){
        changeArea.innerText = '거래완료';
    }
    else if(catValue == 200){
        changeArea.innerText = '거래취소';
    }
    else if(catValue == 300){
        // 옵션 추가

        changeArea.replaceChildren("");
        
        fetch(contextPath + "/option/get?cat=" + catValue + "&no=" + paramNo)
        .then( (response) => response.json() )
        .then( (data) => {

            appendAddOption(data);
            
            addOptionEvent();

        })
        .catch( err => {
        })

    }
    else if(catValue == 400){

        changeArea.replaceChildren("");

        fetch(contextPath + "/option/get?cat=" + catValue + "&no=" + paramNo)
        .then( (response) => response.json() )
        .then( (data) => {

            appendDeleteOption(data);
            
        })
        .catch( err => {
        })

    }
    else if(catValue == 500){
        appendInputNum(changeArea);

    }
    else if(catValue == 600){
        appendInputNum(changeArea);

    }
    else if(catValue == 700){
        changeArea.innerText = '수정요청';
    }
    else if(catValue == 800){
        appendInputNum(changeArea);
        changeArea.append('만원');

    }

    return requestObject;

}
function appendInputNum(area) {
        const numInput = document.createElement("input");
        numInput.classList.add("request-input");
        numInput.type = 'number';
        numInput.min = '1';
        numInput.value = '1';
        numInput.style.width = '100px';
        numInput.style.textAlign = 'right';

        area.innerText = "";
        area.replaceChildren("");
        area.append(numInput);
}

function appendAddOption( data ) {

    const changeArea = document.querySelector(".change-area");

    const select = document.createElement('select');
    select.classList.add('add-option-select');

    const selectOption = document.createElement('option');
    selectOption.value = "";
    selectOption.innerText = "--옵션을 선택해주세요--";
    selectOption.hidden = true;
    select.append(selectOption);

    data.forEach(option => {
    
        const selectOption = document.createElement('option');

        selectOption.value = option.estimateOptionNo;
        selectOption.innerText = option.estimateOptionName;
        selectOption.name = option.estimateOptionPrice;
        selectOption.id = option.estimateOptionPeriod;

        select.append(selectOption);

    });

    changeArea.append(select);

    const inputQuantity = document.createElement("input");
    inputQuantity.classList.add('quantity-input');
    inputQuantity.type = 'number';
    inputQuantity.min = '1';
    inputQuantity.value = '0';
    inputQuantity.disabled = true;
    inputQuantity.style.width = '100px';
    inputQuantity.style.textAlign = 'right';

    changeArea.append(inputQuantity);

    changeArea.append("옵션 추가요금");
    const surcharge = document.createElement('span');
    surcharge.classList.add('surcharge');
    surcharge.classList.add('bold');
    surcharge.innerText = "0";

    changeArea.append(surcharge);

    changeArea.append("옵션 추가기간");
    const extraPreiod = document.createElement('span');
    extraPreiod.classList.add('extraPreiod');
    extraPreiod.classList.add('bold');
    extraPreiod.innerText = "0";

    changeArea.append(extraPreiod);
}

function addOptionEvent(){

    const addOptionSelect = document.querySelector('.add-option-select');

    
    addOptionSelect.addEventListener('change', function() {
        
        const quantity = document.querySelector('.quantity-input');

        quantity.value = 0;
        quantity.disabled = false;

        quantity.addEventListener('change', function() {

            const surcharge = document.querySelector('.surcharge');
            const extraPreiod = document.querySelector('.extraPreiod');

            surcharge.innerText = quantity.value * addOptionSelect.options[addOptionSelect.selectedIndex].name ;
            extraPreiod.innerText = quantity.value * addOptionSelect.options[addOptionSelect.selectedIndex].id ;

        })

    })

}


function appendDeleteOption(data) {

    const changeArea = document.querySelector(".change-area");

    const select = document.createElement("select");
    select.classList.add("delete-option-select");

    const selectOption = document.createElement('option');
    selectOption.value = "";
    selectOption.innerText = "--취소하실 옵션을 선택해주세요--";
    selectOption.hidden = true;
    select.append(selectOption);

    data.forEach(option => {

        const selectOption = document.createElement("option");
        selectOption.value = option.quotationOptionNo;
        selectOption.innerText = option.estimateOptionName 
        + "  /  수량 : " + option.quotationOptionQuantity 
        + "  /  금액 : " + option.quotationOptionQuantity * option.estimateOptionPrice;
        select.append(selectOption);

    })

    changeArea.append(select);
}