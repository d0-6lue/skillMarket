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
    
    });
}


// 옵션 추가
const addOptionBtn = document.querySelector(".add-elem");
addOptionBtn.addEventListener("click", addOption);
function addOption() {
    const tableElem = document.querySelectorAll(".table-body-elem");
    console.log(tableElem.length);


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
};


// 총금액
const projectPrice = 3000000;
const option1Price = 100000;
const option2Price = 500000;
function setTotalPrice() {
    const totalPrice = 0;
    const elems = document.querySelectorAll(".table-body-elem");

    elems.forEach( elem => {

    } )
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