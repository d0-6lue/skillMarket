// 버튼 누르면 금액 변경
const amountBtns = document.querySelectorAll(".amount-btn");
amountBtns.forEach( btn => {

    btn.addEventListener("click", ()=>{
        const result = document.querySelector(".result-amount");

        result.value = btn.value;
    } )

});


// 충전하기 버튼 location.href
function getContextPath(){
    const hostIndex = location.href.indexOf( location.host ) + location.host.length;
    const contextPath = location.href.substring( hostIndex, location.href.indexOf( '/', hostIndex + 1 ));

    return contextPath;
}
const chargeBtn = document.querySelector(".charge-btn");
chargeBtn.addEventListener("click", (a)=>{

    let result = document.querySelector(".result-amount");
    result = result.value;

    const contextPath = getContextPath();

    location.href = contextPath + "/cash/charge?amount=" + result;
})