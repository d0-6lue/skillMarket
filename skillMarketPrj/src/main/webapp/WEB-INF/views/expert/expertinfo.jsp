<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/setup.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="${root}/static/css/expert/expertinfo.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
</head>
<body>

    <%@ include file="/WEB-INF/views/common/header.jsp" %>


    
    <main>
        
        <nav>
            <div class="nav-myinfo-area">

                <div class="nav-myinfo-title bold">
                    <div>프로필 정보</div>
                </div>
                <ul>
                    <li><a href="${root}/my-info">내 정보</a></li>
                    <li><a class="bold" href="${root}/expert-info">전문가 정보</a></li>
                    <li><a href="${root}/my-info/change-pwd">비밀번호 변경</a></li>
                    <li><a href="${root}/my-info/delete-member">회원 탈퇴</a></li>
                </ul>
            </div>
    
        </nav>
        <form action="${root}/expert-info" method="post">
            <div class="register-area">
                <div class="register-title bold">전문가 정보</div>
                <div class="register-body">
                    <div class="register-content">
                        <div class="register-content-text2">자기소개</div>
                        <textarea name="expertIntroduction" placeholder="자기소개">${loginExpert.freelancerInroduction}</textarea>
                        <div class="register-content-text">연락가능시간</div>
                        <input type="text" name="expertTime" placeholder="연락가능시간를 입력해주세요" value="${loginExpert.freelancerContactTime}">
                        <div class="register-content-text">전문분야</div>
                        <select name="expertField">
                            <c:forEach items="${categoryList}" var="list">
                            	<option id="category-list" value="${list.estimateCatName}">${list.estimateCatName}</option>
                            </c:forEach>
                        </select>
                        <div class="register-content-text">경력사항</div>
                        <div class="register-career-area">
                            <div class="career-content-text">총기간</div>
                            <select name="expertCareerDuring">
                                <option id="career-during" value="1년">1년</option>
                                <option id="career-during" value="2년">2년</option>
                                <option id="career-during" value="3년">3년</option>
                                <option id="career-during" value="4년">4년</option>
                                <option id="career-during" value="5년">5년</option>
                                <option id="career-during" value="6년">6년</option>
                                <option id="career-during" value="7년">7년</option>
                                <option id="career-during" value="8년">8년</option>
                                <option id="career-during" value="9년">9년</option>
                                <option id="career-during" value="10년이상">10년이상</option>
                            </select>
                            <div class="career-content-text">경력사항</div>
                            <button id="career-modal-add-btn" class="career-btn" type="button">경력 추가하기</button>
                            <div id="career-input-area">
								<c:forEach items="${careerList}" var="cList">
									<input readonly class="expertCareer" name="expertCareer" value="${cList.careerCompany} ${cList.careerDept} ${cList.careerResp} ${cList.careerLocation} ${cList.careerEmpDate}"><button type="button" class="expert-career-btn">X</button>
								</c:forEach>
                            </div>
                        </div>
                        <div class="register-content-text">학력</div>
                        <div class="education-area">
                            <div class="career-content-text">학력·전공</div>
                            <button id="education-modal-add-btn" class="education-btn" type="button">학력 추가하기</button>
                            <div id="education-input-area">
								<c:forEach items="${educationList}" var="eList">
									<input readonly class="expertEducation" name="expertEducation" value="${eList.eduSch} ${eList.eduDep} ${eList.eduStatus}"><button type="button" class="expert-education-btn">X</button>
								</c:forEach>
                            </div>
                        </div>
                        <div class="register-submit-btn">
                            <input class="bold" type="submit" value="수정 완료">
                        </div>

                    </div>

                </div>

            </div>

        </form>

    </main>

    <!-- 경력 모달 -->

    <div class="c-modal" id="c-modal">
        <div class="c-modal-body">
            <div class="c-m-head">
                <div class="c-close-btn" id="c-close-btn">
                    <span class="material-symbols-outlined">close</span>
                </div>
                <div class="c-m-main">
                    <div class="c-m-head-title bold">경력사항</div>
                    <div>
                        <div class="c-m-content-title bold">회사명</div>
                        <input class="c-m-content-box-input" type="text" name="careerCompany">
                    </div>
                    <div>
                        <div class="c-m-content-title bold">근무부서</div>
                        <input class="c-m-content-box-input" type="text" name="careerDepartment">
                    </div>
                    <div>
                        <div class="c-m-content-title bold">직위</div>
                        <select class="c-m-content-box-select" name="careerPosition">
                            <option value="사원">사원</option>
                            <option value="대리">대리</option>
                            <option value="과장">과장</option>
                            <option value="차장">차장</option>
                            <option value="부장">부장</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div>
                        <div class="c-m-content-title bold">근무지역</div>
                        <select class="c-m-content-box-select" name="careerRegion">
                            <option value="서울">서울</option>
                            <option value="경기">경기</option>
                            <option value="인천">인천</option>
                            <option value="강원">강원</option>
                            <option value="충북">충북</option>
                            <option value="충남">충남</option>
                            <option value="전북">전북</option>
                            <option value="전남">전남</option>
                            <option value="경북">경북</option>
                            <option value="경남">경남</option>
                            <option value="제주">제주</option>
                        </select>
                    </div>
                    <div>
                        <div class="c-m-content-title bold">근무기간</div>
                        <select class="c-m-content-box-select" name="careerDate">
                            <option value="1년">1년</option>
                            <option value="2년">2년</option>
                            <option value="3년">3년</option>
                            <option value="4년">4년</option>
                            <option value="5년이상">5년이상</option>
                        </select>
                    </div>
                    <button class="c-m-content-btn bold" type="button" onclick="addCareerBtn()">확인</button>
                </div>
                    
            </div>
            
            
        </div>
    </div>

    <!-- 학력 모달 -->

    <div class="e-modal" id="e-modal">
        <div class="e-modal-body">
            <div class="e-m-head">
                <div class="e-close-btn" id="e-close-btn">
                    <span class="material-symbols-outlined">close</span>
                </div>
                <div class="e-m-main">
                    <div class="e-m-head-title bold">학력사항</div>
                    <div>
                        <div class="e-m-content-title bold">학교명</div>
                        <input class="e-m-content-box-input" type="text" name="educationSchool">
                    </div>
                    <div>
                        <div class="e-m-content-title bold">전공명</div>
                        <input class="e-m-content-box-input" type="text" name="educationMajor">
                    </div>
                    <div>
                        <div class="e-m-content-title bold">재학상태</div>
                        <select class="e-m-content-box-select" name="educationStatus">
                            <option value="재학">재학</option>
                            <option value="졸업">졸업</option>
                            <option value="졸업예정">졸업예정</option>
                        </select>
                    </div>
                    <button class="e-m-content-btn bold" type="button" onclick="addEducationBtn()">확인</button>
                </div>
                    
            </div>
            
            
        </div>
    </div>

</body>
</html>

<script>

//미리보기
// const fileTag = document.querySelector("input[type=file]");
// const previewArea = document.querySelector(".profile-img");


// fileTag.onchange = function(e){
    
//     if(fileTag.files.length == 0){		//취소누른상태
//         return;
//     }

//     for(let i = 0 ; i < fileTag.files.length; i++){
//         const fr = new FileReader();
//         fr.readAsDataURL(fileTag.files[i]);

//         fr.onload = function(e){
//             previewArea.innerHTML = '';
//             const imgTag = document.createElement('img');
//             imgTag.src = e.target.result;
//             imgTag.alt = "미리보기이미지사진";
//             previewArea.appendChild(imgTag);
//         };
//     }


// };

// 경력사항 모달

const openBtn = document.querySelector("#career-modal-add-btn");
const modal = document.querySelector("#c-modal");
const closeBtn = document.querySelector("#c-close-btn");
const careerArea = document.querySelector("#career-input-area");
const company = document.querySelector("input[name=careerCompany]");
const department = document.querySelector("input[name=careerDepartment]")
const postion = document.querySelector("select[name=careerPosition]");
const region = document.querySelector("select[name=careerRegion]");
const date = document.querySelector("select[name=careerDate]");

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


function addCareerBtn(){
    const cvalue = company.value;
    const dvalue = department.value;
    const pvalue = postion.value;
    const rvalue = region.value;
    const datevalue = date.value;

    
    if(company.value == "" && department.value == ""){
        alert("값을 입력해주세요");
        return false;
    }
    modal.classList.remove('show');
    document.body.style.removeProperty('overflow');
    const inputTag = document.createElement('input');
    inputTag.className = "expertCareer";
    inputTag.name = "expertCareer";
    inputTag.value = cvalue + ' ' + dvalue + ' ' + pvalue + ' ' + rvalue + ' ' + datevalue;
    inputTag.readOnly = true;
    careerArea.appendChild(inputTag);
    const buttonTag = document.createElement('button');
    buttonTag.type = 'button';
    buttonTag.innerHTML = "X";
    buttonTag.onclick = removeInput;

    careerArea.appendChild(buttonTag);

    company.value = "";
    department.value = "";
    postion.value = "사원";
    region.value = "서울";
    date.value = "1년";
}

function removeInput(e){
    console.log(e);
    console.log(e.t);
    e.target.previousSibling.remove();
    e.target.remove();
}

// // 학력 모달

const eduOpenBtn = document.querySelector("#education-modal-add-btn");
const eduModal = document.querySelector("#e-modal");
const eduCloseBtn = document.querySelector("#e-close-btn");
const educationArea = document.querySelector("#education-input-area");
const school = document.querySelector("input[name=educationSchool]");
const major = document.querySelector("input[name=educationMajor]");
const schoolStatus = document.querySelector("select[name=educationStatus]")

if(eduOpenBtn != null){
    eduOpenBtn.addEventListener("click", function(){
        eduModal.classList.add('show');
        document.body.style.overflow = 'hidden';
    });
}

eduCloseBtn.addEventListener("click", function(){
    eduModal.classList.remove('show');
    document.body.style.removeProperty('overflow');
})


function addEducationBtn(){
    const svalue = school.value;
    const mvalue = major.value;
    const statusvalue = schoolStatus.value;

    if(school.value == "" && major.value == ""){
        alert("값을 입력해주세요");
        return false;
    }

    eduModal.classList.remove('show');
    document.body.style.removeProperty('overflow');
    const inputTag = document.createElement('input');
    inputTag.className = "expertEducation";
    inputTag.name = "expertEducation";
    inputTag.value = svalue + ' ' + mvalue + ' ' + statusvalue;
    inputTag.readOnly = true;
    educationArea.appendChild(inputTag);
    const buttonTag = document.createElement('button');
    buttonTag.type = 'button';
    buttonTag.innerHTML = "X";
    buttonTag.onclick = removeInput;

    educationArea.appendChild(buttonTag);

    school.value = "";
    major.value = "";
    schoolStatus.value = "재학";
}

// 카테고리 가져오기
function selectCategory(){
    const categoryList = document.querySelectorAll("#category-list");

    for(let i = 0; i < categoryList.length; i++){
        if(categoryList[i].value == '${loginExpert.fieldOfExpertise}'){
            categoryList[i].selected = true;
        }
        
    }

}

selectCategory();

// 경력 지우기
const careerBtn = document.querySelectorAll(".expert-career-btn");
for(let i = 0; i < careerBtn.length; i++){
    careerBtn[i].addEventListener("click", function(e){
        e.target.previousSibling.remove();
        e.target.remove();
    });
}

// 학력 지우기
const educationBtn = document.querySelectorAll(".expert-education-btn");
for(let i = 0; i < educationBtn.length; i++){
    educationBtn[i].addEventListener("click", function(e){
        e.target.previousSibling.remove();
        e.target.remove();
    });
}

// 총 경력기간 가져오기
function selectcareerDuring(){
    const careerDuring = document.querySelectorAll("#career-during");

    for(let i = 0; i < careerDuring.length; i++){
        if(careerDuring[i].value == "${careerList[0].careerDate}"){
            careerDuring[i].selected = true;
        }
    }
}

selectcareerDuring();




</script>
