// 왼쪽 사이드바 요소들 배열에 담음
const tabIds = ['list-home', 'list-price', 'list-service', 'list-image', 'list-faq'];

// 현재 탭 요소
let currentTabIndex = 0;

// 버튼 요소 가져옴
const button = document.getElementById('nextButton');

// 각 사이드바 요소에 이벤트 리스너 추가함
tabIds.forEach((tabId, index) => {
    const tabElement = document.getElementById(tabId + '-list');
    tabElement.addEventListener('click', function() {
        currentTabIndex = index;
        updateButton();
    });
});

button.addEventListener('click', function(e) {
    e.preventDefault();

    // 탭 요소가 끝이 아니면 다음 탭을 보여줍니다.
    if (currentTabIndex < tabIds.length - 1) {
        currentTabIndex++;
        const tabToShow = new bootstrap.Tab(document.getElementById(tabIds[currentTabIndex] + '-list'));
        tabToShow.show();
    } else {
        // 폼을 제출합니다.
        document.getElementById('estimate-post-btn').submit();
    }
    updateButton();
});

function updateButton() {
    // 마지막 탭이면 
    if (currentTabIndex === tabIds.length - 1) {
        button.setAttribute('type', 'submit');
        button.value = '제출';
    } else {
        button.setAttribute('type', 'button');
        button.value = '다음';
    }
}


// Q&A 박스 생성
const addQuestionButton = document.getElementById('addQuestionButton');
const customQAContent = document.querySelector('.custom-qa-content');

addQuestionButton.addEventListener('click', function() {
    const customQAGroup = document.createElement('div');
    customQAGroup.classList.add('custom-qa-group');

    const customQAQuestionTitle = document.createElement('div');
    customQAQuestionTitle.classList.add('custom-qa-question-title');
    customQAQuestionTitle.innerHTML = `
        <label for="custom-question-title">제목</label>
        <input type="text" class="custom-question-title" placeholder="질문 제목을 입력하세요">
    `;

    const customQAQuestion = document.createElement('div');
    customQAQuestion.classList.add('custom-qa-question');
    customQAQuestion.innerHTML = `
        <label for="custom-question">Q.</label>
        <input type="text" class="custom-question" placeholder="질문을 입력하세요">
    `;

    const customQAAnswer = document.createElement('div');
    customQAAnswer.classList.add('custom-qa-answer');
    customQAAnswer.innerHTML = `
        <label for="custom-answer">A.</label>
        <input type="text" class="custom-answer" placeholder="답변을 입력하세요">
    `;

    customQAGroup.appendChild(customQAQuestionTitle);
    customQAGroup.appendChild(customQAQuestion);
    customQAGroup.appendChild(customQAAnswer);
    customQAContent.appendChild(customQAGroup);
});



//추가옵션 박스
const customOptionsContainer = document.getElementById('customOptionsContainer');
const addCustomOptionsButton = document.getElementById('addCustomOptionsButton');
let customOptionsCount = 0;

addCustomOptionsButton.addEventListener('click', function() {
    customOptionsCount++;
    const customOptionsBox = createCustomOptionsBox(customOptionsCount);
    customOptionsContainer.appendChild(customOptionsBox);
});

function createCustomOptionsBox(count) {
    const customOptionsBox = document.createElement('div');
    customOptionsBox.classList.add('custom-options-box');

    const customOptionsContent = document.createElement('div');
    customOptionsContent.classList.add('custom-options-content');

    const titleInput = document.createElement('input');
    titleInput.classList.add('custom-options-title');
    titleInput.setAttribute('type', 'text');
    titleInput.setAttribute('name', `additionalOptions[${count - 1}].title`);
    titleInput.setAttribute('placeholder', '제목을 입력하세요');

    const priceInput = document.createElement('input');
    priceInput.classList.add('custom-price-amount');
    priceInput.setAttribute('type', 'text');
    priceInput.setAttribute('name', `additionalOptions[${count - 1}].price`);
    priceInput.setAttribute('placeholder', '1000원 추가시');

    const workInput = document.createElement('input');
    workInput.classList.add('custom-options-work');
    workInput.setAttribute('type', 'text');
    workInput.setAttribute('name', `additionalOptions[${count - 1}].work`);
    workInput.setAttribute('placeholder', '일수 입력하세요');

    customOptionsContent.appendChild(titleInput);
    customOptionsContent.appendChild(priceInput);
    customOptionsContent.appendChild(workInput);

    customOptionsBox.appendChild(customOptionsContent);

    return customOptionsBox;
}



//카테고리 대중소 하기 ~



