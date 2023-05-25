function getRandomFileName() {
  const characters = 'abcdefghijklmnopqrstuvwxyz0123456789';
  const length = 8;
  let fileName = '';

  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    fileName += characters.charAt(randomIndex);
  }

  return fileName;
}


function previewMainImage(input) {
  const image = document.getElementById('main-image-preview');
  const container = document.getElementById('main-image-preview-container');

  while (container.firstChild) {
    container.removeChild(container.firstChild);
  }

  if (input.files && input.files[0]) {
    const reader = new FileReader();

    reader.onload = function (e) {
      const img = document.createElement('img');
      console.log(img);
      img.setAttribute('id', 'main-image-preview');
      img.setAttribute('src', e.target.result);
      img.setAttribute('alt', '메인이미지 미리보기');
      container.appendChild(img);
    };

    reader.readAsDataURL(input.files[0]);
  }
}

function removeMainImage() {
  const container = document.getElementById('main-image-preview-container');

  while (container.firstChild) {
    container.removeChild(container.firstChild);
  }
}

function resetMainImage() {
  const mainImageInput = document.getElementById('main-file-upload');
  mainImageInput.value = '';

  const mainImagePreview = document.getElementById('main-image-preview');
  if (mainImagePreview) {
    mainImagePreview.src = '';
    mainImagePreview.removeAttribute('data-is-main-image');
  }
}

// Event handler for sub-file-upload change event
document.querySelector('#sub-file-upload').addEventListener('change', function() {
  const previewContainer = document.querySelector('#subimage-preview-container');
  const existingImages = previewContainer.querySelectorAll('img');

  const files = this.files;
  const totalImageCount = existingImages.length + files.length;

  if (totalImageCount > 6) {
    alert('최대 6장까지 선택할 수 있습니다.');
    this.value = '';
    return;
  }

  for (let i = 0; i < files.length; i++) {
    const file = files[i];

    if (file.size > 10 * 1024 * 1024) {
      alert('10MB 이하의 이미지만 업로드할 수 있습니다.');
      continue;
    }

    const reader = new FileReader();

    reader.onload = function(e) {
      const img = document.createElement('img');
      const fileName = getRandomFileName(); // 랜덤한 파일 이름 생성
      img.id = `subimage-preview-${fileName}`; // 랜덤한 파일 이름을 이미지의 ID로 설정

      img.src = e.target.result;
      img.style.width = '200px';
      img.style.height = '200px';
      img.style.marginRight = '10px';
      img.style.border = '1px solid #ccc';
      img.setAttribute('data-file-name', fileName); // 파일 이름을 img 요소에 저장
      const imgas = previewContainer.appendChild(img);
      console.log(imgas);
    };

    reader.readAsDataURL(file);
  }
});


function toggleMainImage(checkbox) {
  const mainImagePreview = document.getElementById('main-image-preview');
  if (mainImagePreview) {
    mainImagePreview.setAttribute('data-is-main-image', checkbox.checked);
  }
  console.log('Main Image Checkbox:', checkbox.checked);
}

