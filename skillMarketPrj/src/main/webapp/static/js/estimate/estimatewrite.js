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

const addQuestionButton = document.getElementById('addQuestionButton');
const customQAContent = document.querySelector('.custom-qa-content');

addQuestionButton.addEventListener('click', function() {
    const customQAGroup = document.createElement('div');
    customQAGroup.classList.add('custom-qa-group');

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
titleInput.setAttribute('placeholder', '제목을 입력하세요');

const priceInput = document.createElement('input');
priceInput.classList.add('custom-price-amount');
priceInput.setAttribute('type', 'text');
priceInput.setAttribute('placeholder', '1000원 추가시');

const workSelect = document.createElement('select');
workSelect.classList.add('custom-options-work');

for (let i = 0; i <= 6; i++) {
const option = document.createElement('option');
option.setAttribute('value', i);
option.textContent = i;
workSelect.appendChild(option);
}

customOptionsContent.appendChild(titleInput);
customOptionsContent.appendChild(priceInput);
customOptionsContent.appendChild(workSelect);

customOptionsBox.appendChild(customOptionsContent);

return customOptionsBox;
}

//이미지 관련 JS

function previewImage(input, imageId) {
    var container = document.getElementById(imageId + '-container');
    var image = document.getElementById(imageId);

    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }

    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            var img = document.createElement('img');
            img.setAttribute('id', imageId);
            img.setAttribute('src', e.target.result);
            img.setAttribute('alt', '이미지 미리보기');
            container.appendChild(img);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function removeImage(containerId) {
    var container = document.getElementById(containerId);

    while (container.firstChild) {
        container.removeChild(container.firstChild);
    }
}

//카테고리 관련 js
