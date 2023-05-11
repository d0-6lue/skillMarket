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

    // 탭 요소가 끝이아니면 다음 탭 추가함
    if (currentTabIndex < tabIds.length - 1) {
        currentTabIndex++;
        const tabToShow = new bootstrap.Tab(document.getElementById(tabIds[currentTabIndex] + '-list'));
        tabToShow.show();
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