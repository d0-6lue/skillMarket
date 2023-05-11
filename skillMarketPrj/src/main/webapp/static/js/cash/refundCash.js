const inputNumber = document.querySelector('.user-input');
inputNumber.addEventListener("change", getResultNumber);
function getResultNumber() {
    
    const holdingPoint = document.querySelector(".point");
    const resultNumber = document.querySelector(".result-number");

    result = ((holdingPoint.innerText).split(",").join("")) - (Number)(inputNumber.value);

    if(result < 0){ result = 0;}

    resultNumber.innerText = result.toLocaleString("ko-KR");

}

getResultNumber();
