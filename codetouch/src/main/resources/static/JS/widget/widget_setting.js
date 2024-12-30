function initializeWidgetSettings(){
    const widgetBtn = document.getElementById("widgetBtn");
    const templateBtn = document.getElementById("templateBtn");
    const pagination = document.getElementById("pagination");
    const prevPage = document.getElementById("prevPage");
    const nextPage = document.getElementById("nextPage");
    const variationContainer = document.getElementById("variationContainer");
    const section = document.querySelector('[id*="section"]');

    // 전역 변수 선언
    let isDragging = false; // 드래그 상태 플래그
    let draggingClone = null; // 드래그 중 생성된 복사본
    let offsetX, offsetY; // 마우스와 요소 간의 오프셋

    // 드래그 중 마우스 이동 처리 (스로틀링 적용)
    let lastMoveTime = 0;
    let currentPage = 0;
    let itemsPerPage = 4;
    let currentData = [];

    function loadPagination(data, isTemplate = false) {
        currentData = data;
        currentPage = 0;
        itemsPerPage = isTemplate ? 3 : 4; // 템플릿은 3개씩 페이징
        renderPagination();
    }

    function renderPagination() { // 위젯 페이징 처리
        pagination.innerHTML = "";
        const start = currentPage * itemsPerPage;
        const end = start + itemsPerPage;
        const pageData = currentData.slice(start, end);

        pageData.forEach((item) => {
            const div = document.createElement("div");
            div.classList.add("page");
            div.textContent = item;
            div.addEventListener("click", () => loadVariations(item));
            pagination.appendChild(div);
        });
    }

    // 일단 보류
    // let eventInitialized = false;

    function loadVariations(type) {
        // variationContainer 비우기
        variationContainer.innerHTML = "";

        // 데이터 가져오기
        const data = variationData[type] || [];
        const typeName = type.split(" ")[1];

        const makeDiv = (div, item) => {
            div.id = item.id;
            div.style.position = "relative";
            div.style.left = "50px";
            div.style.top = "50px";
            div.style.width = "100px";
            div.style.height = "50px";
        }
//////////요소 타입별 생성 foreach문 여기서 작성해야함
        data.forEach((item) => {
            // 새로운 div 생성
            const div = document.createElement("div");
            if (typeName === "버튼") {
                div.classList.add("button");
                div.innerHTML = `<button class="${item.style}">${item.text}</button>`;
            } else if(typeName === "텍스트"){
                div.classList.add("text");
                div.innerHTML = `<div class="${item.style}">${item.text}</div>`;
            } else if(typeName === "이미지"){
                div.classList.add("img");
                div.innerHTML = `<div class="${item.style}">${item.text}</div>`;
            }

            // 이벤트 추가
            makeDiv(div, item);
            addDragEvent(div);
            variationContainer.appendChild(div);
        });

        WidgetAddEvent('image');
        WidgetAddEvent('text');
        WidgetAddEvent('button');
        WidgetAddEvent('checkbox');
        // 일단 보류
    }

    prevPage.addEventListener("click", () => {
        if (currentPage > 0) {
            currentPage--;
            renderPagination();
        }
    });

    nextPage.addEventListener("click", () => {
        if ((currentPage + 1) * itemsPerPage < currentData.length) {
            currentPage++;
            renderPagination();
        }
    });

    widgetBtn.addEventListener("click", () => loadPagination(widgetElements)
        , console.log() );
    templateBtn.addEventListener("click", () => loadPagination(templateElements, true)
        , console.log());

    loadPagination(widgetElements);

    ///////////////////////////////////////////클릭 / 이동 이벤트 /////////////////////////
    document.addEventListener("mousemove", (event) => {
        const now = Date.now();
        if (now - lastMoveTime < 16) return; // 약 60fps로 제한
        lastMoveTime = now;

        if (isDragging && draggingClone) {

            const sectionRect = section.getBoundingClientRect();

            // 항상 마우스 위치에 따라 이동
            draggingClone.style.position = "absolute";  // 중요한 부분 추가
            draggingClone.style.left = `${event.clientX - offsetX}px`;
            draggingClone.style.top = `${event.clientY - offsetY}px`;
            // section 내부에 들어가면 50px 단위로 스냅
            const isInsideSection = (
                event.clientX > sectionRect.left &&
                event.clientX < sectionRect.right &&
                event.clientY > sectionRect.top &&
                event.clientY < sectionRect.bottom
            );

            if (isInsideSection) {
                const snappedLeft = Math.round((event.clientX - offsetX - sectionRect.left) / 50) * 50;
                const snappedTop = Math.round((event.clientY - offsetY - sectionRect.top) / 50) * 50;
                draggingClone.style.left = `${sectionRect.left + snappedLeft}px`;
                draggingClone.style.top = `${sectionRect.top + snappedTop}px`;
            }
        }
    });





    // 드래그 종료 시 처리
    document.addEventListener("mouseup", (event) => {
        if (isDragging) {
            isDragging = false; // 드래그 상태 비활성화

            if (draggingClone) {
                const targetSection = findSectionUnderMouse(event); // 현재 마우스 아래의 섹션 찾기

                if (targetSection) {
                    // section 내부에서만 요소 추가
                    const sectionRect = targetSection.getBoundingClientRect();
                    const isInsideSection = (
                        event.clientX > sectionRect.left &&
                        event.clientX < sectionRect.right &&
                        event.clientY > sectionRect.top &&
                        event.clientY < sectionRect.bottom
                    );

                    if (isInsideSection) {
                        const newDiv = document.createElement("div");
                        const timestamp = Date.now();
                        const cloneClass = draggingClone.classList[0]; // 복사본의 첫 번째 클래스 이름 추출
                        newDiv.classList.add(cloneClass); // 동적으로 클래스 추가
                        newDiv.style.width = `${draggingClone.offsetWidth}px`;
                        newDiv.style.height = `${draggingClone.offsetHeight}px`;
                        newDiv.style.position = "absolute";

                        // draggingClone 위치를 그대로 사용
                        newDiv.style.left = draggingClone.style.left;
                        newDiv.style.top = draggingClone.style.top;

                        // 자식 요소 복제 (버튼, 이미지, 입력창 포함)
                        const childNodes = draggingClone.childNodes;
                        childNodes.forEach(node => {
                            const tagName = node.tagName;
                            const nodeAddClassName = `clone-${timestamp}`;
                            node.classList.add(nodeAddClassName);
                            node.id = `${tagName.toLowerCase()}-${timestamp}`;
                            newDiv.appendChild(node.cloneNode(true));
                        });

                        targetSection.appendChild(newDiv);
                        addDragEvent(newDiv); // 복제된 요소에 드래그 이벤트 추가
                        document.body.removeChild(draggingClone);
                        newDiv.addEventListener("click", (e)=> {
                            settingElements(newDiv);
                        })

                        newDiv.addEventListener('contextmenu',(e)=>{
                            e.preventDefault();  // 기본 우클릭 메뉴 방지
                            const targetElement = newDiv;
                            const deleteButton = document.createElement('button');
                            deleteButton.textContent = '삭제';
                            deleteButton.classList.add('delete-button');

                            // 우클릭 위치에 버튼 위치 설정
                            deleteButton.style.left = `${e.pageX + 100}px`;  // 우클릭한 요소 바로 오른쪽에 버튼 위치
                            deleteButton.style.top = `${e.pageY+100}px`;  // 우클릭한 위치에 버튼을 표시

                            targetElement.appendChild(deleteButton);  // body에 버튼 추가

                            // 삭제 버튼 클릭 시 삭제 확인
                            deleteButton.addEventListener('click', () => {
                                if (confirm("정말 이 태그를 삭제하시겠습니까?")) {
                                    targetElement.remove();  // 해당 태그 삭제
                                }
                                deleteButton.remove();  // 삭제 후 버튼 제거
                            });

                            // 다른 곳을 클릭하면 삭제 버튼 숨기기
                            document.addEventListener('click', () => {
                                deleteButton.remove();  // 다른 곳을 클릭하면 버튼 제거
                            }, { once: true });  // 한번만 실행되도록 설정
                        })
                    }
                }
                // 복사본 제거
                draggingClone = null;
            }
        }
    });

    // 동적으로 추가된 요소에 대한 설정을 적용하는 함수
    section.addEventListener('click', (e) => {
        const target = e.target;

        // 클릭된 요소가 특정 클래스(예: 'button')를 가졌을 때만 처리
        if (target.classList.contains('set-widget')) {
            applySettingsToWidget(target);
        }
    });

    function applySettingsToWidget(widget) {
        // 여기서 설정을 적용하는 메서드 실행
        // 예시로 settingElements을 호출
        settingElements(`clone-${widget.id}`);
    }


    function addDragEvent(element) {
        let isElementDragging = false;
        let elementOffsetX, elementOffsetY;
        element.addEventListener("mousedown", (event) => {
            if (event.target.closest(".setting")) return;

            isElementDragging = true;
            elementOffsetX = event.clientX - element.getBoundingClientRect().left;
            elementOffsetY = event.clientY - element.getBoundingClientRect().top;
            element.style.cursor = "grabbing";
            event.preventDefault();
        });

        document.addEventListener("mousemove", (event) => {
            if (isElementDragging) {
                const sectionRect = element.closest('.section').getBoundingClientRect();

                // 50px 단위로 움직임 계산
                const newX = Math.round((event.clientX - sectionRect.left - elementOffsetX) / 50) * 50;
                const newY = Math.round((event.clientY - sectionRect.top - elementOffsetY) / 50) * 50;

                element.style.left = `${Math.max(0, Math.min(newX, sectionRect.width - element.offsetWidth))}px`;
                element.style.top = `${Math.max(0, Math.min(newY, sectionRect.height - element.offsetHeight))}px`;
            }
        });

        document.addEventListener("mouseup", () => {
            if (isElementDragging) {
                isElementDragging = false;
                element.style.cursor = "grab";
            }
        });
    }

    // WidgetAddEvent 함수 추가 -> 다양한 id 기반 이벤트 처리
    function WidgetAddEvent(elem) {

        const originalWidgets = document.querySelectorAll(`[id^="${elem}"]`);
        originalWidgets.forEach((element) => {
            element.addEventListener("mousedown", (event) => {
                isDragging = true; // 드래그 상태 활성화

                offsetX = event.clientX - element.getBoundingClientRect().left;
                offsetY = event.clientY - element.getBoundingClientRect().top;

                draggingClone = element.cloneNode(true);

                draggingClone.classList.add("dragging");
                draggingClone.id = ""; // ID 제거하여 중복 방지
                document.body.appendChild(draggingClone);

                const inputElement = draggingClone.querySelector("input");
                if (inputElement) {
                    inputElement.onmousedown = null; // 이벤트 전파 차단 해제
                }
                draggingClone.style.left = `${event.clientX - offsetX}px`;
                draggingClone.style.top = `${event.clientY - offsetY}px`;
                draggingClone.style.width = `${element.offsetWidth}px`;
                draggingClone.style.height = `${element.offsetHeight}px`;

                event.preventDefault(); // 기본 동작 방지
            });
        });
    }

    function findSectionUnderMouse(event) {
        const sections = document.querySelectorAll('[id^="section"]');
        for (const section of sections) {
            const rect = section.getBoundingClientRect();
            if (
                event.clientX > rect.left &&
                event.clientX < rect.right &&
                event.clientY > rect.top &&
                event.clientY < rect.bottom
            ) {
                return section;
            }
        }
        return null;
    }

}
