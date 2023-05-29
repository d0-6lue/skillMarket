// WebSocket------------------------------------------------------------------
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

    // 모든 채팅 내역 가져오기
    loadChat();

}

// WebSocket 서버로 부터 메시지가 오면 호출 되는 함수
webSocket.onmessage = function(message) {

    // 서버로 부터 받은 message의 data를 JSON으로 (jsonMessage)
    jsonMessage = JSON.parse(message.data);

    // type 꺼내기
    const type = jsonMessage.type;

    // type == 'load'  =>  첫번째이니깐 모든 채팅을 가져와서 그림.
    if(type == 'load') {
        addChatAll(jsonMessage.chatList);
    }
    // type == 'add'  =>  중간부터 받는 처음 받은거 이후의 내용이니 더하는 식으로
    else if(type == 'add') {
        addChat(jsonMessage.chatList);
    }

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
        'lastChatNo' : lastChatNo,
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

        msg.handsel = String(numInput.value * 10000);
    }

    webSocket.send(JSON.stringify(msg));
    

    // 요청 모달창 닫기
    requestModalRemove();
    
}
const request = document.querySelector(".request-modal-btn");
request.addEventListener("click" , sendRequest);

//-----------------------------------------------------------------------------------------------------------------------------------------

// 모든 채팅 더하기
function addChatAll(chatList) {

    const chatBox = document.querySelector(".chat-box");
    chatBox.replaceChildren("");

    addChat(chatList, chatBox);
    
    // 스크롤 제일 아래로
    chatBox.scrollTop = chatBox.scrollHeight;
    
}

// 새로운 채팅 더하기
function addChat(chatList) {

    const chatBox = document.querySelector(".chat-box");

    printChat(chatList, chatBox)
    
    // 스크롤 제일 아래로
    chatBox.scrollTop = chatBox.scrollHeight;
    
}

// 채팅리스트 => 채팅 
function printChat(chatList, chatBox) {

    chatList.forEach(chatVo => {
        
        const chatNo = chatVo.chatNo;
        const senderNo = chatVo.chatSenderNo;
        const sender = chatVo.chatSender;
        const content = chatVo.chatContent;
        const enrollDate = chatVo.chatEnrollDate;
        const chatRead = chatVo.chatRead;
        const chatStatus = chatVo.chatStatus;
        const chatAttchment = chatVo.chatAttchment;

        // chat ----------------------------------------------------
        const chat = document.createElement("div");
        chat.classList.add("chat");

        //보낸사람 썸네일 thumbnail
        if( senderNo != loginMemberNo ) {
            const thumbnail = document.createElement('img');
            thumbnail.src = root +'/static/img/profile/' + chatVo.chatSenderProfile;
            thumbnail.alt = '프로필 사진';
            thumbnail.classList.add('thumbnail');
            chat.append(thumbnail);
        }

        const infoContent = document.createElement('div');
        infoContent.classList.add('info-content');


        // chat-info ------------------------------------------------
        const chatInfo = document.createElement("div");
        chatInfo.classList.add("chat-info");

        if( senderNo != loginMemberNo ) {
            const chatSender = document.createElement("span");
            chatSender.classList.add("chat-sender");
            chatSender.classList.add("bold");
            chatSender.innerText = sender;

            chatInfo.append(chatSender);
        }

        const chatEnrollDate = document.createElement("span");
        chatEnrollDate.classList.add("chat-enroll-date");
        chatEnrollDate.classList.add("bold");
        chatEnrollDate.innerText = enrollDate;

        chatInfo.append(chatEnrollDate)
        // ------------------------------------------------

        // chat-contents---------------------------------------------
        const chatContent= document.createElement("div");
        chatContent.classList.add("chat-contents");
        chatContent.classList.add("regular");
        if(chatVo.chatRequest == 'O') {
        
            const requestArea = document.createElement("div");
            requestArea.classList.add("request_")

            const requestTitle = document.createElement("div");
            requestTitle.classList.add("request-title");
            requestTitle.classList.add("bold");
            requestTitle.innerText = '요청서';
            requestArea.append(requestTitle);

            const requestContent = document.createElement("div");
            requestContent.classList.add("request-content");
            requestContent.classList.add("bold");
            requestContent.innerText = content;
            
            const reply = document.createElement('div');
            reply.classList.add('reply');
            
            if(chatVo.requestStatusNo == 2) {
                reply.innerText = '수락됨';
                reply.style.color = 'blue';
            }
            else if(chatVo.requestStatusNo == 3) {
                reply.innerText = '거절됨';
                reply.style.color = 'red';
            }
            requestContent.append(reply);

            requestArea.append(requestContent);

            requestCheckBtn = document.createElement("button");
            requestCheckBtn.classList.add("request-check-btn");
            requestCheckBtn.classList.add("regular");
            requestCheckBtn.innerText = '확인하기';
            requestCheckBtn.addEventListener('click', (event) => 
                checkRequest(event, chatVo) 
            );
            requestArea.append(requestCheckBtn);

            chatContent.append(requestArea);
        }
        else {
            chatContent.innerText = content;
        }
        // ---------------------------------------------

        infoContent.append(chatInfo);
        infoContent.append(chatContent);

        chat.append(infoContent);
        // -----------------------------------------------------
        // 보낸이가 본인일경우
        if( senderNo == loginMemberNo ) {
            chat.classList.add("my-chat");
            chatInfo.classList.add("my-chat-info");
        }

        chatBox.append(chat);


        // 읽음여부
        if( senderNo == loginMemberNo ) {
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

}


//-----------------------------------------------------------------------------------------------------------------------------------------

// 요청하기 눌렀을때 활성화
const requestModalBtn = document.querySelector(".request-btn");
requestModalBtn.addEventListener("click", requestModalAdd);
// 모달창 닫기 버튼
const closeModalBtn = document.querySelector(".close-modal");
const closeModalBtn_ = document.querySelector(".close-modal_");
closeModalBtn.addEventListener("click", requestModalRemove);
closeModalBtn_.addEventListener("click", requestModalRemove_);
//요청 취소 버튼
const modalCancleBtn = document.querySelector(".request-modal-cancle-btn");
modalCancleBtn.addEventListener("click" , requestModalRemove);
function requestModalAdd() {
    const requestModal = document.querySelector(".request-modal");

    const requestSelect = document.querySelector("#request-select");
    requestSelect.disabled = false;

    requestModal.classList.add("modal-active")
}
function requestModalAdd_() {
    const requestModal = document.querySelector(".request-modal_");

    requestModal.classList.add("modal-active")
}
function requestModalRemove() {
    const requestModal = document.querySelector(".request-modal");

    // 요청 모달창 내용 초기화
    const requestSelect = document.querySelector("#request-select");

    requestSelect.options[0].selected = true;
 
    const requestContent = document.querySelector("#request-content-textarea")
    requestContent.readOnly = false;
    requestContent.value = "";

    const changeArea = document.querySelector(".change-area");
    changeArea.replaceChildren("");

    const requestBtn = document.querySelector(".request-modal-btn");
    requestBtn.innerText = '요청하기';

    const cancleBtn = document.querySelector(".request-modal-cancle-btn");
    cancleBtn.innerText = '취소';

    requestModal.classList.remove("modal-active")
}

function requestModalRemove_() {
    const requestModal = document.querySelector(".request-modal_");

    requestModal.classList.remove("modal-active")
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

function appendInputNum_(area, inputNo) {
    const numInput = document.createElement("input");
    numInput.classList.add("request-input");
    numInput.type = 'number';
    numInput.min = '1';
    numInput.value = inputNo;
    numInput.readOnly = true;
    numInput.style.width = '100px';
    numInput.style.textAlign = 'right';

    area.innerText = "";
    area.replaceChildren("");
    area.append(numInput);
}

function appendAddOption(data) {

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

function appendAddOption_(data, optionNo, inputNo) {

    const changeArea = document.querySelector(".change-area_");

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

    select.options[optionNo].selected = true;
    select.disabled = true;
    changeArea.append(select);

    const inputQuantity = document.createElement("input");
    inputQuantity.classList.add('quantity-input');
    inputQuantity.type = 'number';
    inputQuantity.min = '1';
    inputQuantity.value = inputNo;
    inputQuantity.disabled = true;
    inputQuantity.style.width = '100px';
    inputQuantity.style.textAlign = 'right';

    changeArea.append(inputQuantity);

    changeArea.append("옵션 추가요금");
    const surcharge = document.createElement('span');
    surcharge.classList.add('surcharge');
    surcharge.classList.add('bold');
    surcharge.disabled = true;
    surcharge.innerText = inputNo * select.options[optionNo].name;

    changeArea.append(surcharge);

    changeArea.append("옵션 추가기간");
    const extraPreiod = document.createElement('span');
    extraPreiod.classList.add('extraPreiod');
    extraPreiod.classList.add('bold');
    extraPreiod.innerText = inputNo * select.options[optionNo].id;

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

    });

    changeArea.append(select);
}

function appendDeleteOption_(data, optionNo) {

    const changeArea = document.querySelector(".change-area_");

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

    });

    select.options[optionNo].selected = true;
    select.disabled = true;

    changeArea.append(select);
}

//-----------------------------------------------------------------------------
//요청서 확인
function checkRequest(event, chatVo) {

    console.log(chatVo);

    const requestCheckModal = document.querySelector(".request-modal_");
    requestCheckModal.classList.add('modal-active');
    
    const requestSelect = document.querySelector("#request-select_");
    const no = chatVo.requestCatNo;
    requestSelect.options[no/100].selected = true;
    requestSelect.disabled = true;

    const requestContent = document.querySelector("#request-content-textarea_")
    requestContent.value = chatVo.chatContent;
    requestContent.readOnly = true;

    //
    const contextPath = getContextPath();

    const catValue = requestCategory.options[requestCategory.selectedIndex].value

    const changeArea = document.querySelector(".change-area_");

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

            appendAddOption_(data, chatVo.optionNo, chatVo.inputNo);

        })
        .catch( err => {
        })
    }
    else if(catValue == 400){
        changeArea.replaceChildren("");

        fetch(contextPath + "/option/get?cat=" + catValue + "&no=" + paramNo)
        .then( (response) => response.json() )
        .then( (data) => {

            appendDeleteOption_(data, chatVo.optionNo);
            
        })
        .catch( err => {
        })
    }
    else if(catValue == 500){
        appendInputNum_(changeArea, chatVo.inputNo);
        changeArea.append('일');
    }
    else if(catValue == 600){
        appendInputNum_(changeArea, chatVo.inputNo);
        changeArea.append('일');
    }
    else if(catValue == 700){
        changeArea.innerText = '수정요청';
    }
    else if(catValue == 800){
        appendInputNum_(changeArea, chatVo.inputNo);
        changeArea.append('만원');
    }

    const approveRequestBtn = document.querySelector(".request-approve-btn");
    approveRequestBtn.addEventListener('click', () => ApproveRequest(chatVo));

    const refuseRequestBtn = document.querySelector(".request-refuse-btn");
    refuseRequestBtn.addEventListener('click', () => RefuseRequest(chatVo));

    if(chatVo.requestStatusNo != 1){
        approveRequestBtn.disabled = true;
        refuseRequestBtn.disabled = true;
    }

    requestModalAdd_();
}

function ApproveRequest(chatVo){
    let msg = {
        'type' : 'requestReply',
        'requestNo' : chatVo.requestNo,
        'reply' : 'approve',
        'senderNo' : loginMemberNo,
        'roomNo' : paramNo,
    }
    
    requestModalRemove_();

    webSocket.send(JSON.stringify(msg));
}

function RefuseRequest(chatVo) {
    let msg = {
        'type' : 'requestReply',
        'requestNo' : chatVo.requestNo,
        'reply' : 'refuse',
        'senderNo' : loginMemberNo,
        'roomNo' : paramNo,
    }
    
    requestModalRemove_();

    webSocket.send(JSON.stringify(msg));
}