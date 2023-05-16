function getContextPath(){
    const hostIndex = location.href.indexOf( location.host ) + location.host.length;

    const contextPath = location.href.substring( hostIndex, location.href.indexOf( '/', hostIndex + 1 ));

    return contextPath;
}

const orderDetailBtn = document.querySelector(".message-check-btn");
orderDetailBtn.addEventListener("click", locationOrderDetail );
function locationOrderDetail() {

    const contextPath = getContextPath();
    const urlSearchParam = new URLSearchParams(location.search)

    let serchParam = window.location.search;
    const no = (serchParam.substring(serchParam.indexOf('=')+1));
    
    location.href = contextPath + "/chat/room?no=" + no;
};

// 진행률
function computeProgress() {
    let startDay = document.querySelector(".start-day").innerText;
    let deadline = document.querySelector(".deadline").innerText;

    startDay = new Date(startDay);
    deadline = new Date(deadline);

    let today_ = new Date();

    today_ = today_.getFullYear() + "-" + 0+(today_.getMonth()+1) + "-" + today_.getDate();
    const today = new Date(today_);

    const totalDay = deadline.getTime() - startDay.getTime();
    const progressDay = today.getTime() - startDay.getTime();

    const progress = document.querySelector(".progress-rate");
    progress.innerText = ( (progressDay / totalDay) * 10000 ).toFixed(2) + "%";
}
computeProgress();
