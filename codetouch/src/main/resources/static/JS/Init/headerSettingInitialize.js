import {makePage, movePage, getPageList, scheduleDelete, deletePage} from "/JS/Manager/pageManager.js";
import {treeStep, addNode, deleteNode, updateNode, resetTreeStep, showTreeStep} from "/JS/Manager/treeManager.js";
import {getHistoryState,
    goForward,
    goBack,
    setHistoryParent,
    setHistoryLevel,
    addHistoryRecord} from "/JS/Manager/historyManager.js";

const pageList = getPageList();
let isTop = true;
let headerElement;
let header = null;
let clonedElements = [];

const styles = {
    isTop: {
        header: 'padding-bottom: 30px; top: 0; height: auto; width: 100%; text-align: center; flex-direction: row; box-sizing: border-box; display: flex; justify-content: space-between; align-items: center;',
        body: 'margin: 0; padding: 0; box-sizing: border-box; min-height: 100%; display: flex; flex-direction: column;',
        main: 'flex: 1; display: flex; flex-direction: row; box-sizing: border-box;',
        treeBtn: 'top: 0; position: sticky; top: 0; z-index: 10; padding: 10px;',
        menuWrapper: 'display: flex; flex-direction: column; position: relative; height: 100%; padding: 10px;',
        menu: 'display: flex; flex-direction: row; gap: 10px;', // 가로로 배치
        // 로그인
        // 로고
    },
    isSide: {
        header: 'width: 20%; height: 100%; text-align: center; padding: 10px; box-sizing: border-box; flex-direction: column; align-items: center; gap: 12px; display: flex;',
        body: 'margin: 0; padding: 0; box-sizing: border-box; min-height: 100%; display: flex; flex-direction: row;',
        main: 'flex: 1; display: flex; flex-direction: column; box-sizing: border-box;',
        menu: 'display: flex; flex-direction: column; gap: 10px;', // 세로로 배치
        menuWrapper: 'display: flex; flex-direction: column; position: relative; height: 10%; padding: 10px;',
        treeBtn: '',
        // menuWrapper: 'width: 100%; display: flex; flex-direction: column; gap: 10px; align-items: flex-start; padding: 10px; box-sizing: border-box; overflow: auto;',
        // 로그인
        // 로고
    },
};

export function cloneBodyChild(){
    // header를 제외한 body의 모든 자식들을 클론해서 저장해둔다.
    const body = document.getElementById("body");
    const page = body.querySelector(".section-wrapper");
    if(page){
        console.log("페이지 저장함.", page)
        window.setPageList(page);
    }

    clonedElements.push(page.cloneNode(true));

    document.getElementById('addSectionButton').style.display = 'none';
    document.getElementById('headerSettingButton').style.display = 'none';
    document.getElementById('allSaveButton').style.display = 'none';
    document.getElementById('postSiteButton').style.display = 'none';
    // header를 제외한 모든 자식 요소를 클론
    // Array.from(body.children).forEach(child => {
    //     if (child.tagName.toLowerCase() !== "header") {
    //         clonedElements.push(child.cloneNode(true)); // 깊은 복사
    //     }
    // });
}

export function initialize(position){
    // position에 따라 스타일 선택
    const styleKey = position === 'isSide' ? styles.isSide : styles.isTop;
    isTop = position === 'isTop';

    const headerTags = document.getElementsByTagName('header'); // 오타 수정 및 변수명 일관성 유지
    if (headerTags.length > 0) { // HTMLCollection에 요소가 있는지 확인
        header = headerTags[0]; // 첫 번째 <header> 요소를 사용

        organizeNodeElementsIntoTreeStep(header);
        initializeButtons();

        if(!header.firstChild){
            header = createHeader(styleKey); // 새 <header> 생성
        }
    }

    const page = document.getElementById('wrapper');
    //const page = body.querySelector(".section-wrapper");
    while (page.firstChild) {
        page.removeChild(page.firstChild);
    }

    headerTags[0].parentNode.replaceChild(header, headerTags[0]);

    applyStylesFromText(page, styleKey.body);
    //page.appendChild(header); // body에 <header> 추가

    const main = createMainContent(styleKey);
    page.appendChild(main);

    page.style.display = 'flex';

    showSetting('header');
    addEventButton(styleKey);
    refreshTree();

    showTreeStep(0);
}

function addEventButton(styleKey) {
    if (document.getElementById('tree-btn')){
        return
    }

    const menuWrapperDiv = document.getElementById('menuWrapper');
    const checkButton = document.getElementById('treeBtn');
    const addButton = document.createElement('button');

    addButton.id = 'tree-btn';
    addButton.className = 'tree-btn';
    addButton.textContent = '+';
    addButton.addEventListener('click', (event) => {
        event.stopPropagation();
        addSiblingNode(addButton);
    });

    checkButton.appendChild(addButton);
    menuWrapperDiv.prepend(checkButton);
}

function initializeButtons() {
    // 1. treeStep 순회
    treeStep.forEach((nodesAtLevel, level) => {
        if (!nodesAtLevel || nodesAtLevel.length === 0) return;

        // 2. 각 노드 처리
        nodesAtLevel.forEach(node => {
            if (node.id === 'header'){
                return
            }
            const nodeElement = document.getElementById(node.id);
            if (!nodeElement) {
                console.warn(`노드 ID ${node.id}에 해당하는 DOM 요소를 찾을 수 없습니다.`);
                return;
            }

            // 2.1 자식 확인: 자식이 없는 경우에만 childButton 추가
            const hasChildren = treeStep[level + 1]?.some(childNode => childNode.parentId === node.id);
            if (!hasChildren) {
                //addChildButton(nodeElement);
                const childButton = createButton('tree-btn child-btn', '+', addChildNode);
                //nodeElement.appendChild(childButton);
                //console.log(nodeElement.firstElementChild)
                nodeElement.firstElementChild.appendChild(childButton);
            }
        });

        // 3. 레벨별 마지막 노드 처리
        if (level !== 0){
            const lastNode = nodesAtLevel[nodesAtLevel.length - 1];
            if (lastNode) {
                const lastNodeElement = document.getElementById(lastNode.id);
                if (lastNodeElement) {
                    //addNeighborButton(lastNodeElement);
                    const neighborBtn = createButton('tree-btn neighbor-btn', '+', addNeighborNode);
                    lastNodeElement.appendChild(neighborBtn);
                }
            }
        }

    });
}

function organizeNodeElementsIntoTreeStep(headerElement) {
    resetTreeStep(); // treeStep 초기화

    // 루트 노드 추가
    const rootNode = {
        id: headerElement.id || `node-${Date.now()}`,
        content: headerElement.tagName.toLowerCase(),
        parentId: null
    };
    addNode(0, rootNode);

    // 최상위 노드 처리
    const menuElement = headerElement.querySelector('#menu');
    if (menuElement) {
        Array.from(menuElement.children).forEach(child => {
            processNode(child, null, 0); // 최상위 노드 탐색
        });
    }

    /**
     * 특정 노드를 처리하고, 자식 노드를 재귀적으로 탐색
     * @param {HTMLElement} element - 현재 처리할 노드
     * @param {string|null} parentId - 상위 노드 ID
     * @param {number} level - 현재 노드 레벨
     */
    function processNode(element, parentId, level) {
        // `node-`로 시작하는 ID를 가진 노드만 처리
        if (element.id && element.id.startsWith('node-')) {
            const nodeContentElement = element.querySelector('.tree-box');
            const node = {
                id: element.id,
                content: nodeContentElement ? nodeContentElement.textContent.trim() : '',
                parentId: parentId
            };

            // 현재 레벨에 노드 추가
            if (!treeStep[level]) {
                treeStep[level] = [];
            }
            addNode(level, node);
        }

        // 자식 노드 탐색
        Array.from(element.children).forEach(child => {
            if (child.tagName.toLowerCase() === 'ul') {
                // `ul` 내부의 `li` 요소들만 처리
                Array.from(child.children).forEach(grandChild => {
                    if (grandChild.tagName.toLowerCase() === 'li' && grandChild.id.startsWith('node-')) {
                        processNode(grandChild, element.id, level + 1);
                    }
                });
            } else {
                // 다른 일반적인 자식 요소 처리
                processNode(child, element.id, level + 1);
            }
        });
    }
}

function createHeader(styleKey) {
    //const styleKey = JSON.stringify(key);
    const header = document.createElement('header');
    header.id = 'header';
    applyStylesFromText(header, styleKey.header);

    const logoWrapper = document.createElement('div');
    logoWrapper.style.cssText = 'top: 10px; position: relative; width: 100px; height: 100px; display: inline-block;';

    const logo = document.createElement('img');
    logo.id = 'logo';
    logo.style.cssText = 'width: 100%; height: 100%; display: block;';

    const overlay = document.createElement('div');
    overlay.style.cssText = `
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: rgba(255, 255, 255, 0);
        cursor: pointer;
    `;
    overlay.innerHTML = movePage("main-page"); // HTML 생성
    logoWrapper.appendChild(logo);
    logoWrapper.appendChild(overlay);
    header.appendChild(logoWrapper);

    const menuWrapper = document.createElement('div');
    menuWrapper.id = 'menuWrapper';
    applyStylesFromText(menuWrapper, styleKey.menuWrapper);
    header.appendChild(menuWrapper);

    const menu = document.createElement('menu');
    menu.id = 'menu';
    applyStylesFromText(menu, styleKey.menu);

    const treeBtn = document.createElement('div');
    treeBtn.id = 'treeBtn';
    treeBtn.className = 'tree-node';
    applyStylesFromText(treeBtn, styleKey.treeBtn);

    const addButton = document.createElement('button');
    addButton.id = 'tree-btn';
    addButton.className = 'tree-btn';
    addButton.textContent = '+';
    addButton.addEventListener('click', (event) => {
        event.stopPropagation();
        addSiblingNode(addButton);
    });
    treeBtn.appendChild(addButton);

    menuWrapper.appendChild(treeBtn);
    menuWrapper.appendChild(menu);

    const section = document.createElement('div');
    section.id = 'section-header';
    section.style.cssText = 'position: relative; top: 10px; width: 300px; height: 100px; background-color: white; border: 1px solid #ccc;';
    header.appendChild(section);

    return header;
}

function createMainContent(styleKey) {
    const main = document.createElement('main');
    main.id = 'main';
    applyStylesFromText(main, styleKey.main);

    const content = document.createElement('div');
    content.id = 'content';
    content.style.cssText = 'flex: 1; display: flex; justify-content: center; align-items: center; gap: 20px; padding: 20px; box-sizing: border-box;';

    const logoBoxWrapper = createLogoSetting();
    const treeBoxWrapper = createTreeBox();
    const settingAreaWrapper = createSettingArea();

    content.appendChild(logoBoxWrapper);
    content.appendChild(treeBoxWrapper);
    content.appendChild(settingAreaWrapper);

    main.appendChild(content);

    return main;
}

function createLogoSetting() {
    const logoBox = document.createElement('div');
    logoBox.id = 'logoBox';
    logoBox.style.cssText = 'min-width: 1%; min-height: 1%; background-color: #f9f9f9; border: 1px solid #ccc; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); display: flex; flex-direction: column; align-items: flex-start; gap: 10px; padding: 10px; box-sizing: border-box;';

    const logoImg = document.createElement('img');
    logoImg.id = 'logoImg';
    logoImg.style.cssText = 'width: 300px; height: 300px; display: block;';


    const logoButton = document.createElement('button');
    logoButton.id = 'logoButton';
    logoButton.style.cssText = 'width: 100%; height: 100px; display: block;';
    logoButton.textContent = '이미지 선택';

    // 버튼 클릭 시 파일 선택 및 이미지 표시
    logoButton.addEventListener('click', () => {
        const logo = document.getElementById('logo');
        const fileInput = document.createElement('input');
        fileInput.type = 'file';
        fileInput.accept = 'image/*';
        fileInput.onchange = (e) => {
            const file = e.target.files[0];
            if (file) {
                let url = URL.createObjectURL(file);
                logoImg.src = url;
                logo.src = url;
            } // 이미지 URL 설정
        };
        fileInput.click();
    });

    logoBox.appendChild(logoImg);
    logoBox.appendChild(logoButton);

    return logoBox;
}

function createTreeBox() {
    const treeBoxWrapper = document.createElement('div');

    const navButtons = document.createElement('div');
    navButtons.className = 'navigation-buttons';

    const backButton = document.createElement('button');
    backButton.textContent = '◀';
    backButton.addEventListener('click', () => handleNavigation('back'));

    const forwardButton = document.createElement('button');
    forwardButton.textContent = '▶';
    forwardButton.addEventListener('click', () => handleNavigation('forward'));

    const currentTreeNode = document.createElement('span');
    currentTreeNode.className = 'currentTreeNode';
    currentTreeNode.textContent = '메뉴 설정';

    navButtons.appendChild(backButton);
    navButtons.appendChild(currentTreeNode);
    navButtons.appendChild(forwardButton);

    const box1 = document.createElement('div');
    box1.className = 'box';
    box1.style.cssText = 'width: 250px; height: 450px; background-color: #f9f9f9; border: 1px solid #ccc; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); display: flex; flex-direction: column; align-items: flex-start; gap: 10px; padding: 10px; box-sizing: border-box; overflow-y: auto;';
    box1.innerText = '트리 구조';

    treeBoxWrapper.appendChild(navButtons);
    treeBoxWrapper.appendChild(box1);

    return treeBoxWrapper;
}

function createSettingArea() {
    const settingAreaWrapper = document.createElement('div');
    settingAreaWrapper.style.cssText = 'text-align: center;';

    const h2CurrentTreeNode = document.createElement('h2');
    const currentTreeNodeSpan = document.createElement('span');
    currentTreeNodeSpan.className = 'currentTreeNode';
    currentTreeNodeSpan.textContent = '기본 설정';

    h2CurrentTreeNode.appendChild(currentTreeNodeSpan);

    const box2 = document.createElement('div');
    box2.id = 'settingBox';
    box2.className = 'box';
    box2.style.cssText = 'width: 250px; height: 300px; background-color: #f9f9f9; border: 1px solid #ccc; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); display: flex; flex-direction: column; justify-content: center; align-items: center; font-size: 1.2em;';

    // // 설정 UI를 innerHTML로 생성
    // box2.innerHTML = `
    //     <div>
    //         <label for="header-bgColor">배경색:</label>
    //         <input id="header-bgColor" type="color" value="#ffffff">
    //     </div>
    //     <div>
    //         <span>헤더 스타일: </span>
    //         <button id="toggle-btn">헤더 토글</button>
    //     </div>
    // `;

    // box2를 DOM에 추가한 이후 toggle-btn 참조 및 이벤트 리스너 추가
    settingAreaWrapper.appendChild(box2); // 먼저 DOM에 추가
    // const toggleBtn = box2.querySelector('#toggle-btn');
    // toggleBtn.addEventListener('click', toggleHeader);
    //
    // const saveBtn = document.getElementById('save-btn');
    // if (saveBtn) {
    //     saveBtn.addEventListener('click', () => saveSettings('header'));
    // }

    const cancelButton = document.createElement('button');
    cancelButton.textContent = '취소';

    const saveButton = document.createElement('button');
    saveButton.textContent = '저장';
    saveButton.addEventListener('click', saveDomAsHtml);

    const settingBtnWrapper = document.createElement('div');
    settingBtnWrapper.appendChild(cancelButton);
    settingBtnWrapper.appendChild(saveButton);

    settingAreaWrapper.appendChild(h2CurrentTreeNode);
    settingAreaWrapper.appendChild(settingBtnWrapper);

    return settingAreaWrapper;
}

function handleNavigation(direction) {
    const result = direction === 'back' ? goBack() : goForward();
    if (result) {
        refreshTree();
    }
}

function applyStylesFromText(element, cssText) {
    cssText.split(';').forEach(styleRule => {
        const [key, value] = styleRule.split(':').map(part => part.trim());
        if (key && value) {
            element.style[key] = value;
        }
    });
}

function toggleHeader() {
    const header = document.getElementById('header');
    const body = document.getElementById('body');
    const main = document.getElementById('main');

    // header 자식 요소 중 id가 login과 logo인 요소 찾기
    const section = header.querySelector('#section-header');
    const menuWrapper = header.querySelector('#menuWrapper');

    // menuWrapper 안에 메뉴와 버튼의 위치를 바꿈
    const menu = menuWrapper.querySelector('#menu');
    const treeBtn = menuWrapper.querySelector('#treeBtn');

    // 요소가 존재하지 않으면 함수 종료
    if (!section || !menuWrapper) {
        console.error('section 또는 logo 요소를 찾을 수 없습니다.');
        return;
    }

    if (isTop) {
        // isSide 상태로 전환
        applyStylesFromText(header, styles.isSide.header);
        applyStylesFromText(body, styles.isSide.body);
        applyStylesFromText(main, styles.isSide.main);
        //applyStylesFromText(menuWrapper, styles.isSide.menuWrapper)
        applyStylesFromText(menu, styles.isSide.menu);
        // login을 logo 앞에 삽입
        header.insertBefore(section, menuWrapper);
        menuWrapper.insertBefore(menu, treeBtn);
    } else {
        // isTop 상태로 전환
        applyStylesFromText(header, styles.isTop.header);
        applyStylesFromText(body, styles.isTop.body);
        applyStylesFromText(main, styles.isTop.main);
        applyStylesFromText(treeBtn, styles.isTop.treeBtn);
        applyStylesFromText(menu, styles.isTop.menu);
        //applyStylesFromText(menuWrapper, styles.isTop.menuWrapper)
        // logo를 login 앞에 삽입
        header.insertBefore(menuWrapper, section);
        menuWrapper.insertBefore(treeBtn, menu);
    }

    isTop = !isTop;
}

export function saveDomAsHtml() {
    const element = document.getElementById('header');

    if (element) {
        headerElement = removeButtonTags(element.outerHTML); // DOM 전체를 HTML 문자열로 변환

        let wrapper = document.getElementById('wrapper');
        let child = wrapper.firstElementChild; // 첫 번째 자식부터 시작

        while (child) {
            const nextSibling = child.nextElementSibling; // 삭제할 자식의 다음 형제 요소를 미리 저장

            // header가 아닌 경우만 삭제
            if (child.id !== "header") {
                try {
                    wrapper.removeChild(child); // wrapper에서 child 삭제
                } catch (e) {
                    console.log("에러난 아이디", child);
                }
            }

            child = nextSibling; // 다음 자식 요소로 이동
        }
        const existingHeader = document.getElementById('header');
        existingHeader.outerHTML = headerElement;

        clonedElements.forEach(elem =>{
            wrapper.appendChild(elem);
        })

        // document.querySelector("button").addEventListener("click", () => {
        //     cloneBodyChild()
        //     initialize("isTop");
        // });

        buttonDisplay();
        window.isSetting = !window.isSetting;
        clonedElements = [];
        deletePage();
        resetTreeStep();
    } else {
        console.error(`Element with id "header" not found.`);
    }
}

function buttonDisplay(){
    document.getElementById('addSectionButton').style.display = 'block';
    document.getElementById('headerSettingButton').style.display = 'block';
    document.getElementById('allSaveButton').style.display = 'block';
    document.getElementById('postSiteButton').style.display = 'block';
}

function removeButtonTags(htmlString) {
    return htmlString.replace(/<button[\s\S]*?<\/button>/gi, '');
}

export function getHeaderElement(){
    return headerElement;
}

function refreshTree() {
    const currentState = getHistoryState();
    displayTree(currentState.historyLevel, currentState.historyParent);
}

function displayTree(level, parentId = null) {
    // 트리 데이터가 초기화되지 않았거나 빈 경우
    if (!treeStep || treeStep.length === 0) {
        console.error("treeStep is not initialized or empty.");
        return;
    }

    // 유효한 레벨 검증
    if (level < 0 || !treeStep[level]) {
        console.error(`Invalid level: ${level}`);
        return;
    }

    // 부모 ID로 현재 레벨 필터링
    const currentNodes = level === 0
        ? treeStep[level] // 최상위 노드
        : treeStep[level]?.filter(node => node.parentId === parentId) || [];

    // 자식 노드가 없는 경우
    if (currentNodes.length === 0) {
        console.log("No nodes to display.");
        if (level > 0) {
            console.log("현재 노드 출력의 레벨", level)
            displayTree(level - 1, parentId); // 이전 레벨 표시
        }
        return;
    }

    // 기록 관리
    if (parentId !== null) {
        setHistoryLevel(level);
        setHistoryParent(parentId);
        addHistoryRecord(level, parentId);
    }

    // 트리 노드 렌더링
    const box1 = document.querySelector('.box');
    while(box1.firstChild){
        box1.removeChild(box1.firstChild);
    }
    currentNodes.forEach(node => {
        const boxItem = document.createElement('div');
        boxItem.id = `${node.id}-box1`;
        boxItem.style.cssText = `
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ccc;
            background-color: #f0f0f0;
            font-size: 1em;
        `;
        boxItem.textContent = node.content;

        // 클릭 시 자식 노드 표시
        boxItem.addEventListener('click', () => {
            displayTree(level + 1, node.id); // 다음 레벨의 자식 노드 표시
        });

        // 클릭 시 설정 UI 표시
        boxItem.addEventListener('click', () => {
            showSetting(node.id);
        });

        box1.appendChild(boxItem);
    });

    // 현재 노드 상태 표시
    const currentTreeNode = document.querySelectorAll('.currentTreeNode');
    const currentNode = parentId === null
        ? '메뉴설정'
        : treeStep.flat().find(n => n.id === parentId)?.content;

    currentTreeNode.forEach(currentSelect => {
        currentSelect.textContent = currentNode || '메뉴설정';
    });
}

function addSiblingNode(button) {
    const newId = `node-${Date.now()}`;
    const newContent = '메뉴';

    addNode(0, { id: newId, content: newContent, parentId: null });

    // Create sibling node element
    const siblingNode = document.createElement('li');
    siblingNode.id = newId;
    siblingNode.style.cssText = 'position: relative; z-index: 1000;';
    siblingNode.className = 'tree-node';

    const container = document.createElement('div');
    container.className = 'siblingInDiv';
    container.style.cssText = 'display: flex; align-items: center; gap: 10px;';

    const treeBox = document.createElement('div');
    treeBox.className = 'tree-box';
    treeBox.style.cssText = `
        border: 1px solid #ccc;
    `;
    treeBox.textContent = newContent;

    const childButton = createButton('tree-btn child-btn', '+', addChildNode);

    container.appendChild(treeBox);
    container.appendChild(childButton);
    const pageId = makePage();

    siblingNode.appendChild(container);
    siblingNode.insertAdjacentHTML('beforeend', movePage(pageId));

    console.log(pageList);


    document.getElementById('menu').appendChild(siblingNode);

    refreshTree();
}

function createButton(className, textContent, eventHandler) {
    const button = document.createElement('button');
    button.className = className;
    button.textContent = textContent;
    button.style.zIndex = "999";
    button.style.width = "30px";
    button.style.height= "30px";
    button.addEventListener('click', (event) => {
        event.stopPropagation();
        eventHandler(event.target);
    });
    return button;
}

function addChildNode(button) {
    const parentNode = button.closest('li');
    const parentId = parentNode.id;

    const currentLevel = treeStep.findIndex(level =>
        level.some(node => node.id === parentId)
    );

    if (currentLevel === -1) {
        console.error('Parent node not found in treeStep.');
        return;
    }

    const newId = `node-${Date.now()}`;
    const newContent = '하위 메뉴';

    if (!treeStep[currentLevel + 1]) {
        treeStep[currentLevel + 1] = [];
    }

    addNode(currentLevel + 1, { id: newId, content: newContent, parentId });

    const childNode = document.createElement('ul');
    childNode.className = 'tree-node';
    childNode.innerHTML = `
        <li id="${newId}" class="tree-node">
            <div style="display: flex; align-items: center; gap: 10px;">
                <div class="tree-box" style="
                    border: 1px solid #ccc;
                    font-size: 8px;
                    width: 30px;
                ">${newContent}</div>
            </div>
        </li>
    `;

    const childBtn = createButton('tree-btn child-btn', '+', addChildNode);
    const neighborBtn = createButton('tree-btn neighbor-btn', '+', addNeighborNode);

    const nodeDiv = childNode.querySelector('div');
    nodeDiv.appendChild(childBtn);
    //childNode.querySelector('li').appendChild(neighborBtn);
    //childNode.querySelector('ul').appendChild(neighborBtn);
    parentNode.appendChild(childNode);
    childNode.appendChild(neighborBtn);

    const pageMove = parentNode.querySelector('.page-btn');
    if (pageMove) {
        pageMove.style.pointerEvents = 'none';
    }

    button.remove();

    refreshTree();
}

function addNeighborNode(button) {
    const siblingNode = button.closest('ul');
    //const parentNode = siblingNode.closest('ul').parentElement;
    const parentNode = siblingNode.parentElement;
    const parentId = parentNode ? parentNode.id : null;

    // 부모 레벨 찾기
    const parentLevel = treeStep.findIndex(level =>
        level.some(node => node.id === parentId)
    );

    if (parentLevel === -1) {
        console.error('Parent node not found in treeStep.');
        return;
    }

    // 현재 노드 레벨은 부모 레벨 + 1
    const currentLevel = parentLevel + 1;

    // 새로운 레벨을 treeStep에 추가
    if (!treeStep[currentLevel]) {
        treeStep[currentLevel] = [];
    }

    const newId = `node-${Date.now()}`;
    const newContent = '하위 메뉴';

    // 노드 추가
    addNode(currentLevel, { id: newId, content: newContent, parentId });

    const neighborNode = document.createElement('li');
    neighborNode.id = newId;
    neighborNode.style.cssText = 'position: relative; z-index: 1000;';
    neighborNode.className = 'tree-node';

    const contentDiv = document.createElement('div');
    contentDiv.style.cssText = 'display: flex; align-items: center; gap: 10px;';

    const treeBox = document.createElement('div');
    treeBox.className = 'tree-box';
    treeBox.style.cssText = `
        border: 1px solid #ccc;
    `;
    treeBox.textContent = newContent;

    const childButton = createButton('tree-btn child-btn', '+', addChildNode);
    //const neighborButton = createButton('tree-btn neighbor-btn', '+', addNeighborNode);

    contentDiv.appendChild(treeBox);
    contentDiv.appendChild(childButton);

    neighborNode.appendChild(contentDiv);
    //neighborNode.appendChild(neighborButton);

    //siblingNode.appendChild(neighborNode);
    siblingNode.insertBefore(neighborNode, siblingNode.lastElementChild);
    //parentNode.insertBefore(neighborNode, parentNode.lastElementChild);

    refreshTree();
}

function showSetting(nodeId) {
    const mainContent = document.getElementById('settingBox');
    const node = treeStep.flat().find(n => n.id === nodeId);

    if (!node) {
        console.error(`Node with ID ${nodeId} not found`);
        return;
    }

    // DOM 갱신
    mainContent.innerHTML = (node.id === "header") ? createHeaderSettingUI() : createNodeSettingUI(node);

    // 헤더 전용 이벤트 리스너 추가
    if (node.id === "header") {
        const toggleBtn = document.getElementById('toggle-btn');
        if (toggleBtn) {
            toggleBtn.addEventListener('click', toggleHeader);
        }

        const bgColorInput = document.getElementById('header-bgColor');
        if (bgColorInput) {
            bgColorInput.addEventListener('input', () => {
                document.getElementById('header').style.backgroundColor = bgColorInput.value;
            });
        }
    }

    // 공통 이벤트 리스너 추가
    const saveBtn = document.getElementById('save-btn');
    if (saveBtn) {
        saveBtn.addEventListener('click', () => saveSettings(nodeId));
    }

    const deleteNodeBtn = document.getElementById('delete-node-btn');
    if (deleteNodeBtn) {
        deleteNodeBtn.addEventListener('click', () => deleteNodeToId(nodeId));
    }
}

// 셋팅 저장과 반영 ( 현재 로컬에서만 )
function saveSettings(nodeId) {
    const currentTreeNode = document.querySelectorAll('.currentTreeNode'); // 현재 선택 정보
    const node = treeStep.flat().find(n => n.id === nodeId); // 해당 노드 찾기
    const menuDOM = document.getElementById(nodeId);

    if (!node) {
        console.error(`Node with ID ${nodeId} not found`);
        return;
    }

    const newContent = document.getElementById('node-content')?.value;

    if (newContent) {
        // 데이터 갱신
        node.content = newContent;
        menuDOM.content = newContent;

        // 화면 갱신
        const nodeElement = document.querySelector(`#${nodeId} .tree-box`);
        if (nodeElement) {
            nodeElement.textContent = newContent; // 화면의 해당 노드 내용 갱신
        }

        currentTreeNode.forEach(currentSelect => {
            currentSelect.innerHTML = `${node.content}`;
        });
    }

    // 업데이트된 데이터 저장
    const currentLevel = treeStep.findIndex(level => level.some(n => n.id === nodeId));
    if (currentLevel !== -1) {
        updateNode(currentLevel, nodeId, { content: newContent});
    }

    // 설정 화면 갱신 및 트리 리프레시
    showSetting(nodeId);
    refreshTree();
}

function createHeaderSettingUI() {
    return `
        <div>
            <label for="header-bgColor">배경색:</label>
            <input id="header-bgColor" type="color" value="#ffffff">
        </div>
        <div>
            <span>헤더 스타일:</span>
            <button id="toggle-btn">헤더 토글</button>
        </div>
    `;
}

function createNodeSettingUI(node) {
    const hasChildren = treeStep.flat().some(n => n.parentId === node.id);

    return `
        <div>
            <label for="node-content">내용:</label>
            <input id="node-content" type="text" value="${node.content}">
        </div>
        <button id="save-btn">저장</button>
        <button id="delete-node-btn">메뉴 제거</button>
    `;
}

function deleteNodeToId(nodeId) {
    const level = treeStep.findIndex(level => level.some(node => node.id === nodeId));
    const nodeElement = document.getElementById(nodeId);
    const nodeParent = nodeElement.parentElement;
    const nodeGrandParent = nodeParent.parentElement;

    console.log(nodeParent)
    const pageElement = nodeElement.querySelector('.page-btn');
    if (pageElement){
        const pageId = pageElement.dataset.pageId;
        scheduleDelete(pageId);
    }

    if (nodeElement) nodeElement.remove();

    if (!nodeParent.querySelector('.tree-node')){
        const childButton = createButton('tree-btn child-btn', '+', addChildNode);
        nodeParent.parentElement.querySelector('.siblingInDiv').appendChild(childButton);

        nodeParent.remove();

        // 부모의 페이지 이동 에서 다시 포인터 이벤트 활성화

        const pageMove = nodeGrandParent.querySelector('.page-btn');
        console.log(nodeGrandParent)
        if (pageMove) {
            pageMove.style.pointerEvents = 'auto';
            console.log("동작확인2")
        }
    }

    deleteNode(level, nodeId);

    refreshTree();
}

function selectImage(){

}