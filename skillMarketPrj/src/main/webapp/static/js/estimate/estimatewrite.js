$(document).ready(function() {
    // 탭 버튼 클릭 이벤트 처리
    $('.tab-btn').click(function() {
      // 활성화된 탭 버튼의 클래스를 제거하고 클릭한 탭 버튼에 active 클래스 추가
      $('.tab-btn.active').removeClass('active');
      $(this).addClass('active');
      
      // 클릭한 탭 버튼의 data-tab 속성 값을 가져와 해당 ID를 가진 탭 콘텐츠를 보여줌
      var target = $(this).data('tab');
      $('.tab-content.active').removeClass('active');
      $(target).addClass('active');
    });
  });