// historyManager.js

// History 데이터를 관리하는 모듈
const historyState = {
    historyLevel: 0, // 현재 레벨
    historyParent: null, // 현재 부모 ID
    historyIDs: [] // 이동 기록 (레벨과 부모 ID)
};

// History 초기화
function resetHistory() {
    historyState.historyLevel = 0;
    historyState.historyParent = null;
    historyState.historyIDs = [];
}

// History 레벨 설정
function setHistoryLevel(level) {
    historyState.historyLevel = level;
    console.log(`History Level 설정됨: ${level}`);
}

// History 부모 ID 설정
function setHistoryParent(parentId) {
    historyState.historyParent = parentId;
    console.log(`History Parent 설정됨: ${parentId}`);
}

// History에 새 기록 추가
function addHistoryRecord(level, parentId) {
    historyState.historyIDs.push({ level, parentId });
    console.log(`History 추가됨: Level=${level}, ParentId=${parentId}`);
}

// History 뒤로가기
function goBack() {
    if (historyState.historyLevel > 0) {
        historyState.historyLevel--;
        const previousHistory = historyState.historyIDs[historyState.historyLevel];
        historyState.historyParent = previousHistory?.parentId || null;
        console.log(`뒤로가기: Level=${historyState.historyLevel}, ParentId=${historyState.historyParent}`);
        return previousHistory;
    } else {
        console.log("최 상위 레벨입니다.");
        return null;
    }
}

// History 앞으로가기
function goForward() {
    if (historyState.historyLevel < historyState.historyIDs.length - 1) {
        historyState.historyLevel++;
        const nextHistory = historyState.historyIDs[historyState.historyLevel];
        historyState.historyParent = nextHistory?.parentId || null;
        console.log(`앞으로가기: Level=${historyState.historyLevel}, ParentId=${historyState.historyParent}`);
        return nextHistory;
    } else {
        console.log("최 하위 레벨입니다.");
        return null;
    }
}

// History 상태 가져오기
function getHistoryState() {
    return { ...historyState };
}

// 모듈 내보내기
export {
    resetHistory,
    setHistoryLevel,
    setHistoryParent,
    addHistoryRecord,
    goBack,
    goForward,
    getHistoryState
};
