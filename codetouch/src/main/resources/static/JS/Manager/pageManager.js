import {fetchGet} from "/JS/Fetch/FetchServer.js";

window.pageList = new Map();
export let site_id = 0;
const deleteList = [];

export async function setViewSite(){
    const siteUrl = window.location.pathname.split("/")[1];

    const data = await fetchGet("customer", `/${siteUrl}/정보`);

    const wrapper = document.getElementById('wrapper');

    // 기존 header 요소의 부모를 찾음
    const parent = document.getElementById('header').parentNode;

// 새로운 header 요소 생성
    const newHeader = document.createElement('div');
    newHeader.innerHTML = data.header;

// 새로운 header의 내부가 이미 <header>라면 교체
    if (newHeader.firstElementChild.tagName.toLowerCase() === 'header') {
        const replacement = newHeader.firstElementChild;
        parent.replaceChild(replacement, document.getElementById('header'));
    } else {
        console.error('data.header에 올바른 <header> 태그가 포함되어 있지 않습니다.');
    }

// 새로운 footer 요소 생성
    const newFooter = document.createElement('div'); // 임시 div 생성
    newFooter.innerHTML = data.footer; // data.footer 내용을 삽입

// 새로운 footer가 <footer> 태그를 포함하고 있는지 확인
    if (newFooter.firstElementChild && newFooter.firstElementChild.tagName.toLowerCase() === 'footer') {
        const replacement = newFooter.firstElementChild; // <footer> 태그 추출
        parent.replaceChild(replacement, document.getElementById('footer')); // 기존 footer 교체
    } else {
        console.error('data.footer에 <footer> 태그가 포함되어 있지 않습니다.');
    }

    const pageData = JSON.parse(data.page);
    pageData.forEach(page => {
        if (page.id === "main-page") {
            wrapper.innerHTML = page.content;
        }

        window.setPageList(page);
    })

}

export async function setSite(){
    const siteUrl = window.location.pathname.split("/")[3];
    const data = await fetchGet("customer", `/고객/디자인불러오기/${siteUrl}`);

    site_id = data.site_id;

    const wrapper = document.getElementById('wrapper');

    // 기존 header 요소의 부모를 찾음
    const parent = document.getElementById('header').parentNode;

// 새로운 header 요소 생성
    const newHeader = document.createElement('div');
    newHeader.innerHTML = data.header;

// 새로운 header의 내부가 이미 <header>라면 교체
    if (newHeader.firstElementChild.tagName.toLowerCase() === 'header') {
        const replacement = newHeader.firstElementChild;
        parent.replaceChild(replacement, document.getElementById('header'));
    } else {
        console.error('data.header에 올바른 <header> 태그가 포함되어 있지 않습니다.');
    }

// 새로운 footer 요소 생성
    const newFooter = document.createElement('div'); // 임시 div 생성
    newFooter.innerHTML = data.footer; // data.footer 내용을 삽입

// 새로운 footer가 <footer> 태그를 포함하고 있는지 확인
    if (newFooter.firstElementChild && newFooter.firstElementChild.tagName.toLowerCase() === 'footer') {
        const replacement = newFooter.firstElementChild; // <footer> 태그 추출
        parent.replaceChild(replacement, document.getElementById('footer')); // 기존 footer 교체
    } else {
        console.error('data.footer에 <footer> 태그가 포함되어 있지 않습니다.');
    }

    const pageData = JSON.parse(data.page);
    pageData.forEach(page => {
        if (page.id === "main-page") {
            wrapper.innerHTML = page.content;
        }
        // const addDragButtonEvents = document.querySelectorAll('.set-widget')
        // addDragButtonEvents.forEach(elem => {
        //     window.addDragEvent(elem);
        // })
        window.setPageList(page);
    })

}

export function getPageList() {
    return pageList;
}

export function makePage(){
    const makeID = Date.now();
    const page = document.createElement('div');
    page.id = `page-${makeID}`;
    page.className = `section-wrapper`;
    page.style.cssText = "width: 100%; height: 100%"

    const inSection = document.createElement('section');
    inSection.className = `section`;
    inSection.id = `section-${makeID}`;

    const sectionSettingButton = document.createElement('button')
    sectionSettingButton.className = 'section-setting-btn setting-btn'
    sectionSettingButton.textContent = "셋팅"

    const sectionSettingEndButton = document.createElement('button')
    sectionSettingEndButton.className = 'section-setting-end-btn setting-btn'
    sectionSettingEndButton.style.display = 'none'
    sectionSettingEndButton.textContent = "저장"

    inSection.appendChild(sectionSettingButton);
    inSection.appendChild(sectionSettingEndButton);
    page.appendChild(inSection);

    window.setPageList(page);

    return page.id;
}

window.setPageList = (page) => {
    if (page instanceof Node){
        pageList.set(page.id, page)
    }else{
        pageList.set(page.id, page.content)
    }
}

// 안씀
export function initPageList(page){
    if (pageList.has(page.id)){
        return;
    }
    pageList.set(page.id, page.content)

}

function createPage(id) {

}

export function scheduleDelete(pageId){
    deleteList.push(pageId);
}

export function deletePage(){
    deleteList.forEach(pageId => {
        pageList.delete(pageId);

        const index = deleteList.indexOf(pageId);
        if (index !== -1) {
            deleteList.splice(index, 1);
        }

    })

    // 삭제 리스트 초기화
    deleteList.length = 0;
}

window.getPageByKey = function(key) {
    if (pageList.has(key)){
        return pageList.get(key);
    }else{
        return null;
    }
}

const moveFuncStyle =
    ' top: 0; left: 0; position: absolute;' +
    ' width: 100%; height: 100%;' +
    ' zIndex: 999; background: rgba(0, 0, 0, 0);' +
    ' padding: 0;';

export function movePage(pageId) {
// HTML 문자열로 onclick을 포함한 자식 요소 추가
    return `
    <div class="page-btn" style="${moveFuncStyle}" data-page-id="${pageId}" onclick="(function(event) {
        event.stopPropagation(); // 버블링 방지
        const wrapper = document.getElementById('wrapper');
        if (window.isSetting === false){
        
            return;
        }
        
        while(wrapper.firstChild){
            wrapper.removeChild(wrapper.firstChild);
        }
        
        const inner = getPageByKey('${pageId}');
        if (typeof inner === 'string'){
            wrapper.innerHTML = inner;    
        }else{
            wrapper.appendChild(inner);
        }
        
        
    })(event)">
    </div>
    `;
}