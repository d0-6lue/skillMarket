/**
     * SVG viewBox에서,
     * percent에 해당하는 x,y 좌표를 반환
     * viewBox의 원 반지름이 1이므로,
     * x는 cos theta, y는 sin theta 임
     */
function getCoordinatesForPercent(percent) {
    const x = Math.cos(2 * Math.PI * percent);
    const y = Math.sin(2 * Math.PI * percent);
    return [x, y];
  }
  
  /**
   * categoryStaticData를 순회하며,
   * 해당 데이터를 DoughnutChartPate의 DOM으로 변환
   * 
   */
  // 데이터
  ;

    const total  = memberCnt+freeCnt;
    

    const percentA = (memberCnt / total);
    const percentB = (freeCnt / total);

  const data = [
    { percent: percentA, category: 'A' },
    { percent: percentB, category: 'B' }
  ];
   
  function getDoughnutChartPaths(data) {
    let accumulatedPercent = 0;
    const paths = data.map(({ percent, category }, idx) => {
      const [startX, startY] = getCoordinatesForPercent(accumulatedPercent);
      accumulatedPercent += percent;
      const [endX, endY] = getCoordinatesForPercent(accumulatedPercent);
      const isLargeArcFlag = percent > 0.5 ? 1 : 0;

      return getCategoryDataPath({ percent, category }, { startX, startY, endX, endY, isLargeArcFlag }, idx);
    }).join('');

    return paths;
  }
  
  function getCategoryDataPath({ percent, category }, { startX, startY, endX, endY, isLargeArcFlag }, idx) {

    const targetRad = 2 * Math.PI * percent; // 원호의 각도 (라디안)
    const targetRestRad = 2 * Math.PI * (1 - percent); // 남은 부분의 각도 (라디안)
    const animationDuration = 0.1; // 애니메이션 지속 시간

    const color = category === 'A' ? '#1F77B4' : '#FF7F0E'; // 카테고리에 따른 색상 설정
    const path = document.createElementNS("http://www.w3.org/2000/svg", 'path'); // SVG path 요소 생성
    path.setAttribute('d', `M ${startX} ${startY} A 1 1 0 ${isLargeArcFlag} 1 ${endX} ${endY} L 0 0`); // path의 경로 설정
    path.setAttribute('fill', 'none'); // 채우기 없음
    path.setAttribute('stroke-width', '0.5'); // 선의 두께 설정
    path.setAttribute('stroke', color); // 선의 색상 설정
    path.setAttribute('stroke-dasharray', `${targetRad} ${targetRestRad}`); // 선의 점선 패턴 설정
    path.setAttribute('stroke-dashoffset', `${targetRad}`); // 선의 시작점 설정

    const animate = document.createElementNS('http://www.w3.org/2000/svg','animate'); // SVG animate 요소 생성
    animate.setAttribute('attributeName', 'stroke-dashoffset'); // 애니메이션 대상 속성
    animate.setAttribute('begin', `${animationDuration * idx}`); // 애니메이션 시작 시간
    animate.setAttribute('from', `${targetRad}`); // 애니메이션 시작 값
    animate.setAttribute('to', '0.025'); // 애니메이션 종료 값
    animate.setAttribute('dur', `${animationDuration}`); // 애니메이션 지속 시간
    animate.setAttribute('fill', 'freeze'); // 애니메이션 종료 후 상태 유지

    path.appendChild(animate); // path 요소에 animate 요소 추가

    // 호버 애니메이션 추가
    const hoverAnimationDuration = 0.5;
    
    path.addEventListener('mouseenter', () => {
      path.style.transition = `transform ${hoverAnimationDuration}s ease-in`;
      path.style.transform = 'scale(1.2)';
    });

    path.addEventListener('mouseleave', () => {
      path.style.transition = `transform ${hoverAnimationDuration}s`;
      path.style.transform = 'scale(1)';
      setTimeout(() => {
        path.style.transition = '';
      }, hoverAnimationDuration * 1000);
    });

    return path.outerHTML;
  }

  const svg = document.querySelector('svg');
  svg.innerHTML = getDoughnutChartPaths(data);

  // 애니메이션 시작 시간 지연을 통해 초기에 보이지 않게 함
  setTimeout(() => {
    const paths = svg.querySelectorAll('path');
    paths.forEach((path, index) => {
      path.style.opacity = 1;
      path.style.transitionDelay = `${index * 0.1}s`;
    });
  }, 100);