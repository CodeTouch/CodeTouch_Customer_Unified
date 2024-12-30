// treeManager.js

// Proxy 핸들러와 treeStep 정의
const nodeHandler = {
    set(target, property, value) {
        target[property] = value;
        showTreeStep(); // UI 갱신
        return true;
    }
};

const treeStepHandler = {
    set(target, property, value) {
        if (Array.isArray(value)) {
            target[property] = value.map(node => new Proxy(node, nodeHandler));
        } else {
            target[property] = value;
        }
        showTreeStep();
        return true;
    },
    deleteProperty(target, property) {
        delete target[property];
        showTreeStep();
        return true;
    }
};

const treeStep = new Proxy([], treeStepHandler);

// 주요 함수들
function addNode(level, node) {
    treeStep[level] = treeStep[level] || [];
    const proxiedNode = new Proxy(node, nodeHandler);
    treeStep[level].push(proxiedNode);
}

function deleteNode(level, nodeId) {
    if (!treeStep[level]) return;
    const index = treeStep[level].findIndex(node => node.id === nodeId);
    if (index === -1) return;
    treeStep[level].splice(index, 1);
}

function updateNode(level, nodeId, updates) {
    const node = treeStep[level]?.find(node => node.id === nodeId);
    if (!node) return;
    Object.assign(node, updates);
}

// treeStep 초기화 함수
function resetTreeStep(newTreeStep = []) {
    // treeStep 배열 비우기
    for (let i = 0; i < treeStep.length; i++) {
        delete treeStep[i];
    }

    // 새 데이터를 treeStep에 추가
    newTreeStep.forEach((level, index) => {
        treeStep[index] = level.map(node => new Proxy(node, nodeHandler));
    });

    showTreeStep();
}

// 자식을 가진지 확인
function hasChildren(nodeId) {
    // treeStep 순회
    for (const level of treeStep) {
        if (level) {
            // 현재 레벨에서 부모 ID가 nodeId인 노드가 있는지 확인
            const childNode = level.find(node => node.parentId === nodeId);
            if (childNode) {
                return true; // 자식 노드가 있으면 true 반환
            }
        }
    }
    return false; // 자식 노드가 없으면 false 반환
}

// 자신의 부모 id 확인
export function findParentNode(nodeId){
    for (const level of treeStep){
        if (level){
            const currentNode = level.find(node => node.id === nodeId);

            const parentNode = level.find(node => node.parentId === currentNode.id);
            if (parentNode){
                return parentNode.id;
            }
        }
    }
}

// showTreeStep은 외부에서 UI를 제어하는 함수로 가정
function showTreeStep(level = 0) {
    //console.log(`Level ${level} UI 업데이트 호출`);
}


// 내보내기
export { treeStep, addNode, deleteNode, updateNode, resetTreeStep, showTreeStep, hasChildren };
