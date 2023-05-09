// 결제수단 선택

const radioBtns = document.querySelectorAll('input[name="purchase-method-radio"]');

radioBtns.forEach( radioBtn => {

    radioBtn.addEventListener("click" , changeMethod)
});

function changeMethod() {
    radioBtns.forEach( radioBtn => {

        const id = radioBtn.getAttribute("id");
        const target = document.querySelector('label[for="' + id + '"]');

        if(radioBtn.checked) {
            target.classList.add("method-elem-active");
        }
        else {
            target.classList.remove("method-elem-active");
        }

        if(id == "skillpoint") {

        }
        else if(id == "credit-card") {

        }
        else if(id == "account-transfer") {

        }
        else if(id == "mobile-phone-payment") {

        }
    
    });
}


// ---------------------------------------------------------------
let optionNum = 0;
const maxOptionNume = 2;

const projectPrice = 3000000;
const projectDay = 300;
const option1Price = 100000;
const option1Day = 1;
const option2Price = 500000;
const option2Day = 5;

const removeOptionBtn = document.querySelector(".remove-elem");

// 옵션 추가
const addOptionBtn = document.querySelector(".add-elem");
addOptionBtn.addEventListener("click", addOption);
function addOption() {
    const tableElem = document.querySelectorAll(".table-body-elem");


    // table-body-elem
    const tableBodyElem = document.createElement("div");
    tableBodyElem.classList.add("table-body-elem");
    tableBodyElem.classList.add("horizontal-alignment");


    // purchase-item
    const purchaseItem = document.createElement("div");
    purchaseItem.classList.add("regular");
    purchaseItem.classList.add("purchase-item");
    tableBodyElem.append(purchaseItem);

    // select-option
    const selectOption = document.createElement("select");
    selectOption.name = "select-option";
    selectOption.classList.add("select-option");

    const optionElem = document.createElement("option");
    optionElem.disabled = true;
    optionElem.defaultSelected = true;
    optionElem.hidden = true;
    optionElem.value = "";
    optionElem.innerText = "--옵션을 선택해주세요--";

    selectOption.append(optionElem)
    for(var i = 1; i <= 2; i++){
        const optionElem = document.createElement("option");
        optionElem.value = "option" + i;
        optionElem.innerText = "옵션" + i;

        selectOption.append(optionElem)
    }
    purchaseItem.append(selectOption);

    // purchase-quantity
    const purchaseQuantity = document.createElement("div");
    purchaseQuantity.classList.add("purchase-quantity");

    const quantityInput = document.createElement("input");
    quantityInput.type = "number";
    quantityInput.min = "0";
    quantityInput.value = "0";
    quantityInput.classList.add("regular");
    quantityInput.classList.add("quantity-input");
    purchaseQuantity.append(quantityInput);
    tableBodyElem.append(purchaseQuantity);

    // purchase-day
    const purchaseDay = document.createElement("div");
    purchaseDay.classList.add("regular");
    purchaseDay.classList.add("purchase-day");
    purchaseDay.classList.add("align-center");
    tableBodyElem.append(purchaseDay);

    // purchase-price
    const purchasePrice = document.createElement("div");
    purchasePrice.classList.add("regular");
    purchasePrice.classList.add("purchase-price");
    purchasePrice.classList.add("align-end");
    tableBodyElem.append(purchasePrice);
    

    tableElem[tableElem.length - 2].after(tableBodyElem);

    optionNum++;

    if(optionNum == maxOptionNume){
        addOptionBtn.classList.add("add-elem-inactive");
    }

    removeOptionBtn.classList.add("remove-elem-active");

    changeLineValue();
};

// 옵션 삭제
removeOptionBtn.addEventListener("click" , function() {

    const purchaseTableBody = document.querySelector(".purchase-table-body");

    const tableElem = document.querySelectorAll(".table-body-elem");

    purchaseTableBody.removeChild(tableElem[tableElem.length - 2]);

    optionNum--;

    if(optionNum == 0 ){
        removeOptionBtn.classList.remove("remove-elem-active");
    } 

    if(optionNum < maxOptionNume){
        addOptionBtn.classList.remove("add-elem-inactive");
    }

});


// 수량 변경시 날짜랑 금액 변경
const tableBodyElems = document.querySelectorAll(".table-body-elem");

const elem = tableBodyElems[0];

const inputNumber = elem.querySelector(".quantity-input");

inputNumber.addEventListener("change", function changeValue() {

    const number = inputNumber.value;

    const purchaseDay = elem.querySelector(".purchase-day");
    purchaseDay.innerText = ( number * projectDay ) + " 일";

    const pPrice = elem.querySelector(".purchase-price");
    const newPrice = number * projectPrice;
    const localPrice = newPrice.toLocaleString("ko-KR");
    pPrice.innerText = "₩ " + localPrice;


    setTotalPrice();

})

// 수량 바뀌면 기간이랑 금액 변경
function changeLineValue() {
    const tableBodyElems = document.querySelectorAll(".table-body-elem");

    for(let i = 1; i < tableBodyElems.length - 1; i++) {

        const elem = tableBodyElems[i];
    
        const inputNumber = elem.querySelector(".quantity-input");
    
        inputNumber.addEventListener("change", function changeValue() {
    
            const optionValue = elem.querySelector(".select-option").value;

            const number = inputNumber.value;
    
            const purchaseDay = elem.querySelector(".purchase-day");
            if(optionValue == "option1") {
                purchaseDay.innerText = ( number * option1Day ) + " 일";
            }
            else if(optionValue == "option2") {
                purchaseDay.innerText = ( number * option2Day ) + " 일";
            }
            else {
                purchaseDay.innerText = " 일";
            }
    
            const pPrice = elem.querySelector(".purchase-price");
            if(optionValue == "option1") {
                newPrice = ( number * option1Price );
                const localPrice = newPrice.toLocaleString("ko-KR");
                pPrice.innerText = "₩ " + localPrice;
            }
            else if(optionValue == "option2") {
                newPrice = ( number * option2Price );
                const localPrice = newPrice.toLocaleString("ko-KR");
                pPrice.innerText = "₩ " + localPrice;
            }
            else {
                purchaseDay.innerText = "";
            }
            
            setTotalPrice();
    
        })
    
    }
    

}


// 총 작업일 / 총 결제금액
function setTotalPrice() {
    let totalDay = 0;
    let totalPrice = 0;

    const tableBodyElems = document.querySelectorAll(".table-body-elem");

    for(let i = 0 ; i < tableBodyElems.length - 1 ; i++){

        const elem = tableBodyElems[i];

        const lineDay = elem.querySelector(".purchase-day").innerText;
        const conversionDay = Number(lineDay.substring( 0, (lineDay.length-2) ));

        totalDay += conversionDay;

        const linePrice = elem.querySelector(".purchase-price").innerText;
        const conversionPrice = Number( linePrice.substring(2).split(",").join("") );

        totalPrice += conversionPrice;

    }

    const totalDayResult = document.querySelector(".total-day-result");
    totalDayResult.innerText = totalDay + " 일";

    const totalPriceResult = document.querySelector(".total-payment-result");

    const localPrice = totalPrice.toLocaleString("ko-KR");
    totalPriceResult.innerText = "₩ " + localPrice;

}



// 구매하기 버튼
function getContextPath(){
    const hostIndex = location.href.indexOf( location.host ) + location.host.length;

    const contextPath = location.href.substring( hostIndex, location.href.indexOf( '/', hostIndex + 1 ));

    return contextPath;
}

const purchaseBtn = document.querySelector(".purchase-btn");
purchaseBtn.addEventListener("click", () => {
    const contextPath = getContextPath();

    location.href = contextPath + "/purchase/completed";
})