// Wrapper에서 변화 감지 (새로운 section-wrapper 추가)
// 1. 최상위 Observer: .wrapper
const wrapper = document.querySelector('.wrapper');

window.addEventListener('load', () =>{
    // 기존 섹션을 옵저버에 추가하는 예시
    document.querySelectorAll('.section-wrapper').forEach(section => {
        observeSectionWrapper(section);
    });

    document.querySelectorAll('.section').forEach(section => {
        observeSection(section);
    })

    document.querySelectorAll('.section-setting-btn').forEach(button => {
        button.addEventListener('click', (e) => {
            sectionSettingStart(button.parentElement);
        })
    })

    document.querySelectorAll('.section-setting-end-btn').forEach(button => {
        button.addEventListener('click', (e) => {
            sectionSettingEnd(button.parentElement);
        })
    })
})

const wrapperObserver = new MutationObserver((mutationsList) => {
    for (const mutation of mutationsList) {
        if (mutation.type === 'childList') {
            // section-wrapper 추가 감지
            mutation.addedNodes.forEach(node => {
                if (node.classList && node.classList.contains('section-wrapper')) {

                    // section-wrapper 내부의 모든 section 탐색
                    const sections = node.querySelectorAll('.section');
                    sections.forEach(section => {

                        // 각 section에 observer 추가
                        observeSection(section);
                        // section 설정 버튼과 설정추가
                        addSectionSettingEvent(section);

                        const addDragButtonEvents = document.querySelectorAll('.set-widget')
                        addDragButtonEvents.forEach(elem => {
                            window.addDragEvent(elem);
                        })
                    });

                    // section-wrapper 내부를 감지하는 Observer 추가
                    observeSectionWrapper(node);
                }
            });
        }
    }
});

wrapperObserver.observe(wrapper, { childList: true });

// 2. section-wrapper 내부 감지: .section
function observeSectionWrapper(sectionWrapper) {
    const sectionObserver = new MutationObserver((mutationsList) => {
        for (const mutation of mutationsList) {
            if (mutation.type === 'childList') {
                // 자식 노드 추가 감지
                mutation.addedNodes.forEach(node => {
                    if (node.classList && node.classList.contains('section')) {

                        // section 내부의 변화 감지 추가
                        observeSection(node);
                        // section 설정 버튼과 설정추가
                        addSectionSettingEvent(node);
                    }
                });
            }
        }
    });

    // section-wrapper 내부의 section 요소 감지 시작
    sectionObserver.observe(sectionWrapper, { childList: true });
}

// 3. section 내부 변화 감지: div 추가 등
function observeSection(section) {
    const sectionChildObserver = new MutationObserver((mutationsList) => {
        for (const mutation of mutationsList) {
            if (mutation.type === 'childList') {
                mutation.addedNodes.forEach(node => {
                    window.setPageList(section.parentElement);
                });
            }
        }
    });

    // section 내부의 모든 변화 감지 시작
    sectionChildObserver.observe(section, { childList: true });
}

function addSectionSettingEvent(node){
    const sectionSettingBtn = node.querySelector('.section-setting-btn');
    sectionSettingBtn.addEventListener('click', e => {
        e.stopPropagation();

        sectionSettingStart(node);
    })

    const EndSectionSettingBtn = node.querySelector('.section-setting-end-btn');
    EndSectionSettingBtn.addEventListener('click', e => {
        e.stopPropagation();

        sectionSettingEnd(node);
    })
}

function sectionSettingStart(node){
    const setting = document.getElementById('setting');
    setting.style.display = 'flex';

    const header = document.getElementById('header');
    header.style.display = 'none';

    const sectionWrapper = document.querySelector('.addSectionButtonWrapper');
    sectionWrapper.style.display = 'none';

    const footer = document.getElementById('footer');
    footer.style.display = 'none';

    const top_button = document.getElementById('top_button');
    top_button.style.display = 'none';

    const sections = node.parentElement.querySelectorAll('.section');
    sections.forEach(section => {
        if (section.id !== node.id) {
            section.style.display = 'none';
        }
    })

    const startButton = node.querySelector('.section-setting-btn');
    startButton.style.display = 'none';

    const endButton = node.querySelector('.section-setting-end-btn');
    endButton.style.display = 'block';
}

function sectionSettingEnd(node){
    const setting = document.getElementById('setting');
    setting.style.display = 'none';

    const header = document.getElementById('header');
    header.style.display = 'flex';

    const sectionWrapper = document.querySelector('.addSectionButtonWrapper');
    sectionWrapper.style.display = 'flex';

    const footer = document.getElementById('footer');
    footer.style.display = 'block';

    const top_button = document.getElementById('top_button');
    top_button.style.display = 'flex';

    const sections = node.parentElement.querySelectorAll('.section');
    sections.forEach(section => {
        if (section.id !== node.id) {
            section.style.display = 'block';
        }
    })

    const startButton = node.querySelector('.section-setting-btn');
    startButton.style.display = 'block';

    const endButton = node.querySelector('.section-setting-end-btn');
    endButton.style.display = 'none';
}