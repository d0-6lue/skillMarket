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