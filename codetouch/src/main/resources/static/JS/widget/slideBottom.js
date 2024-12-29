function slideBottom() {
  const toggleBtn = document.getElementById('toggleBtn');
  const offcanvas = document.getElementById('offcanvasBottom');
  let isOffcanvasOpen = false;

  if (!toggleBtn || !offcanvas) {
    console.error('Required elements not found!');
    return;
  }

  // 슬라이드 애니메이션 처리
  function toggleOffcanvas() {
    if (isOffcanvasOpen) {
      offcanvas.style.transform = 'translateY(100%)';
      toggleBtn.style.bottom = '10px';
      setTimeout(() => {
        offcanvas.classList.remove('show');
        isOffcanvasOpen = false;
      }, 500);
    } else {
      offcanvas.classList.add('show');
      offcanvas.style.transform = 'translateY(0)';
      toggleBtn.style.bottom = `${offcanvas.clientHeight}px`;
      isOffcanvasOpen = true;
    }
  }

  // 버튼 클릭 이벤트
  toggleBtn.addEventListener('click', toggleOffcanvas);
}
