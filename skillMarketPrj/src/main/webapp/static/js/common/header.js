const openBtn = document.querySelector("#login-modal-add-btn");
const modal = document.querySelector("#modal");
const closeBtn = document.querySelector("#close-btn");
openBtn.addEventListener("click", function(){
    modal.classList.add('show');
    document.body.style.overflow = 'hidden';
});
closeBtn.addEventListener("click", function(){
    modal.classList.remove('show');
    document.body.style.removeProperty('overflow');
})


//----------------------------------------------------------------
// 디테일 카테고리 호버
const bcDesign = document.querySelector(".bc-design");

bcDesign.addEventListener("mouseover", detailCategoryVisible);
function detailCategoryVisible() {
    const detailCategoryArea = document.querySelector(".detail-category-area");

    detailCategoryArea.classList.add("detail-category-area-active");

    
}

bcDesign.addEventListener("mouseout", detailCategoryInvisible);
function detailCategoryInvisible() {
    const detailCategoryArea = document.querySelector(".detail-category-area");

    detailCategoryArea.classList.remove("detail-category-area-active");
}


const detailCategoryArea = document.querySelector(".detail-category-area");

detailCategoryArea.addEventListener("mouseover", detailCategoryVisible);
function detailCategoryVisible() {
    detailCategoryArea.classList.add("detail-category-area-active");
}

detailCategoryArea.addEventListener("mouseout", detailCategoryInvisible);
function detailCategoryInvisible() {
    detailCategoryArea.classList.remove("detail-category-area-active");
}
//----------------------------------------------------------------















