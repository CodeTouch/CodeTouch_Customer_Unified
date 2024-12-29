import {fetchGet, fetchPost} from "../Fetch/FetchServer.js";
import {cloneBodyChild, initialize} from "./headerSettingInitialize.js";
import {setSite} from "../Manager/pageManager.js";

window.isSetting = true;

export async function InitEditSite() {
    await setSite();
}

document.getElementById('addSectionButton').addEventListener("click", () => {
    const newSection = document.createElement('section')
    newSection.id = `section-${Date.now()}`
    newSection.className = 'section';

    const sectionSettingButton = document.createElement('button')
    sectionSettingButton.className = 'section-setting-btn setting-btn'
    sectionSettingButton.textContent = "셋팅"

    const sectionSettingEndButton = document.createElement('button')
    sectionSettingEndButton.className = 'section-setting-end-btn setting-btn'
    sectionSettingEndButton.style.display = 'none'
    sectionSettingEndButton.textContent = "저장"

    newSection.appendChild(sectionSettingButton)
    newSection.appendChild(sectionSettingEndButton)
    document.querySelector('.section-wrapper').insertAdjacentElement("beforeend", newSection);
})

document.getElementById('postSiteButton').addEventListener("click", () => {
    fetchPost("customer", "/고객/페이지게시", 1)
})

document.getElementById("headerSettingButton").addEventListener("click", () => {
    if(isSetting === true){
        cloneBodyChild()
        initialize("isTop");
        console.log(pageList);
        window.isSetting = !window.isSetting;
    }
});

document.getElementById("allSaveButton").addEventListener("click", () => {
    const pages = [];
    pageList.forEach((value, key) => {
        let pageContent;
        if (value instanceof Node){
            pageContent = value.outerHTML;
        }else{
            pageContent = value;
        }
        pages.push({
            id: key, // 페이지 id
            content: pageContent,
        });
    });

    const sendHeader = document.getElementById('header').outerHTML;
    const sendFooter = document.getElementById('footer').outerHTML;

    const siteData = {
        "site_id": "1", // 사이트 아이디
        "header": sendHeader, // HTML 문자열을 그대로 사용
        "page": pages, // 페이지 객체 배열을 그대로 사용
        "footer": sendFooter, // HTML 문자열을 그대로 사용
    };

    console.log(JSON.stringify(pages));

    fetchPost("customer", "/고객/디자인저장", siteData);
})
