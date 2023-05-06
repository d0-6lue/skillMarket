// 요청하기 눌렀을때 모달 활성화
const requestModalBtn = document.querySelector(".request-btn");
requestModalBtn.addEventListener("click", requestModalToggle);

//요청 취소 버튼(모달 비활성화)
const modalCancleBtn = document.querySelector(".request-modal-cancle-btn");
modalCancleBtn.addEventListener("click" , requestModalToggle);

// 모달 토글 함수
function requestModalToggle() {
    const requestModal = document.querySelector(".request-modal");

    requestModal.classList.toggle("modal-active")
}