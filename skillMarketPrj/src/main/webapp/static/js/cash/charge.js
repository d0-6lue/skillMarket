const amount = (new URLSearchParams(location.search).get('amount'));
const chargePoint = document.querySelector(".charge-point");
const amount_ = parseInt(amount);
chargePoint.value = amount_.toLocaleString("ko-KR");


const memberPoint = document.querySelector('.holding-point').innerText;

const holdingPoint = document.querySelector(".holding-point");
holdingPoint.innerText = memberPoint.toLocaleString("ko-KR");

const resultPoint = document.querySelector(".result-point");
let result = (Number)(amount_) + (Number)(memberPoint);
resultPoint.innerText = result.toLocaleString("ko-KR");




// 결제수단 선택

const radioBtns = document.querySelectorAll('input[name="purchase-method-radio"]');

radioBtns.forEach( radioBtn => {

    radioBtn.addEventListener("click" , e => changeMethod(e))
});

function changeMethod(e) {
    radioBtns.forEach( radioBtn => {

        const id = radioBtn.getAttribute("id");

        const target = document.querySelector('label[for="' + id + '"]');

        if(radioBtn.checked) {
            target.classList.add("elem-active");

            if(id == "credit-card") {
                purchaseDetailAreaToCC();
            }
            else if(id == "account-transfer") {
                purchaseDetailAreaToAT();
            }
            else if(id == "mobile-phone-payment") {
                purchaseDetailAreaToMP();
            }
        }
        else {
            target.classList.remove("elem-active");
        }
    
    });
}


function purchaseDetailAreaToCC() {

    const detailArea = document.querySelector(".purchase-detail-area");

    detailArea.replaceChildren("");

    detailArea.append("신용카드 거래~~~")

}
purchaseDetailAreaToCC();

function purchaseDetailAreaToAT() {

    const detailArea = document.querySelector(".purchase-detail-area");

    detailArea.replaceChildren("");

    detailArea.append("계좌이체 거래~~~")

}

function purchaseDetailAreaToMP() {

    const detailArea = document.querySelector(".purchase-detail-area");

    detailArea.replaceChildren("");

    detailArea.append("휴대폰 결제 거래~~~")

}