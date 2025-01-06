
// 스크롤바가 필요할 때만 동작하도록 설정
const allScrollableElements = document.querySelectorAll('*');

allScrollableElements.forEach((el) => {
    if (el.scrollHeight > el.clientHeight || el.scrollWidth > el.clientWidth) {
    el.style.overflow = 'auto'; // 스크롤이 필요한 경우만 표시
    }
});