// 결제수단 선택

const radioBtns = document.querySelectorAll('input[name="purchase-method-radio"]');

radioBtns.forEach( radioBtn => {

    radioBtn.addEventListener("click" , changeMethod)
});

function changeMethod() {
    radioBtns.forEach( radioBtn => {

        const id = radioBtn.getAttribute("id");
        const target = document.querySelector('div[id="' + id + '"]');

        if(radioBtn.checked) {
            target.classList.add("method-elem-active");
        }
        else {
            target.classList.remove("method-elem-active");
        }
    
    });
}


function setTotalPrice() {
    const elems = document.querySelectorAll(".table-body-elem");
    const totalPrice = 0;

    elems.forEach( elem => {
        const priceValue = elem.querySelector(".puarchase-price").value;
        const price = priceValue.replace
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