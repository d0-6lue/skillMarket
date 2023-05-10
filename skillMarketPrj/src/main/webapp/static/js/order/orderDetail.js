function getContextPath(){
    const hostIndex = location.href.indexOf( location.host ) + location.host.length;

    const contextPath = location.href.substring( hostIndex, location.href.indexOf( '/', hostIndex + 1 ));

    return contextPath;
}

const orderDetailBtn = document.querySelector(".message-check-btn");
orderDetailBtn.addEventListener("click", locationOrderDetail );
function locationOrderDetail() {

    const contextPath = getContextPath();

    location.href = contextPath + "/chat/room";
};


function computeProgress() {
    let startDay = document.querySelector(".start-day").innerText;
    let deadline = document.querySelector(".deadline").innerText;

    startDay = new Date(startDay);
    deadline = new Date(deadline);

    const today = new Date();

    const totalDay = deadline.getTime(); - startDay.getTime();
    const progressDay = today.getTime() - startDay.getTime();

    const progress = document.querySelector(".progress-rate");
    progress.innerText = ( (progressDay / totalDay) * 10000 ).toFixed(2) + "%";
}

computeProgress();