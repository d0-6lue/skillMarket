// 로그인 모달

const openBtn = document.querySelector("#login-modal-add-btn");
const modal = document.querySelector("#modal");
const closeBtn = document.querySelector("#close-btn");
if(openBtn != null){
    openBtn.addEventListener("click", function(){
        modal.classList.add('show');
        document.body.style.overflow = 'hidden';
    });
}

closeBtn.addEventListener("click", function(){
    modal.classList.remove('show');
    document.body.style.removeProperty('overflow');
})

//----------------------------------------------------------------





















