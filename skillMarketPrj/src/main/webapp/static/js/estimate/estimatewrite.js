
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

// 메인 이미지 이벤트 처리
document.querySelector('#main-file-upload').addEventListener('change', function() {
    const isMainImageCheckbox = document.querySelector('#is-main-image');
    isMainImageCheckbox.checked = true;
});



//상세 이미지 이벤트 처리
document.querySelector('#sub-file-upload').addEventListener('change', function() {
    const previewContainer = document.querySelector('#subimage-preview-container');
    const existingImages = previewContainer.querySelectorAll('img');

    const files = this.files;
    const totalImageCount = existingImages.length + files.length;

    if (totalImageCount > 6) {
        alert('최대 6장까지 선택할 수 있습니다.');
        this.value = ''; // 파일 선택 취소
        return;
    }

    for (let i = 0; i < files.length; i++) {
        const file = files[i];

        if (file.size > 10 * 1024 * 1024) {
            alert('10MB 이하의 이미지만 업로드할 수 있습니다.');
            continue; // 다음 파일로 넘어감
        }

        const reader = new FileReader();

        reader.onload = function(e) {
            const img = document.createElement('img');
            img.src = e.target.result;
            img.style.width = '200px';
            img.style.height = '200px';
            img.style.marginRight = '10px';
            img.style.border = '1px solid #ccc'; // 보더 추가
            previewContainer.appendChild(img);
        };

        reader.readAsDataURL(file);
    }
});

// 상세이미지 초기화 버튼 이벤트 처리
document.querySelector('#cancel-subimage-button').addEventListener('click', function() {
    const subimageInput = document.querySelector('#sub-file-upload');
    subimageInput.value = ''; // 파일 선택 취소
    const previewContainer = document.querySelector('#subimage-preview-container');
    previewContainer.innerHTML = ''; // 미리보기 영역 초기화
});

function toggleMainImage(checkbox) {
    const mainImagePreview = document.querySelector('#main-image-preview');
    if (mainImagePreview) {
        mainImagePreview.setAttribute('data-is-main-image', checkbox.checked);
    }
}

reader.onload = function(e) {
    var img = document.createElement('img');
    img.setAttribute('id', imageId);
    img.setAttribute('src', e.target.result);
    img.setAttribute('alt', '이미지 미리보기');
    img.setAttribute('data-is-main-image', document.querySelector('#is-main-image').checked);
    container.appendChild(img);
}






