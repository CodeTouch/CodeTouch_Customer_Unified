//const URL_TO_CUSTOMER = "http://192.168.5.72:9999"
const URL_TO_CUSTOMER = "http://192.168.5.58:9999"
const URL_TO_COMPANY = "http://192.168.5.15:8888"
// 기본 패치 함수 정의
async function fetchRequest(url, options = {}) {
    const controller = new AbortController();
    const id = setTimeout(() => controller.abort(), 3000);

    const defaultHeaders = {
        'Content-Type': 'application/json',
        'withCredentials': true,
    };

    const config = {
        ...options,
        signal: controller.signal, // 타임아웃 컨트롤러 연결
        headers: {
            ...defaultHeaders,
            ...options.headers, // 사용자 정의 헤더 병합
        },
    };

    try {
        const response = await fetch(url, config);

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // 응답이 비어있는지 확인
        if (response.status === 204 || response.headers.get("Content-Length") === "0") {
            return null; // 비어있는 응답 처리
        }

        // JSON 응답 파싱
        return await response.json(); // JSON 데이터를 반환
    } catch (error) {
        if (error.name === 'AbortError') {
            console.error('Fetch 요청 타임아웃:', error);
        } else {
            console.error('Fetch 요청 실패:', error);
        }
        throw error; // 에러를 호출한 곳으로 전달
    } finally {
        clearTimeout(id); // 타임아웃 해제
    }
}

// GET 요청 함수
export async function fetchGet(to, endPoint) {
    if (!endPoint) {
        console.error('endPoint가 제공되지 않았습니다.');
        return null;
    }

    const url = (to === "company" ? URL_TO_COMPANY : URL_TO_CUSTOMER) + endPoint;

    try {
        const data = await fetchRequest(url, {
            method: 'GET',
        });

        console.log('GET 응답 데이터:', data);
        return data; // 데이터 반환
    } catch (error) {
        console.error('GET 요청 에러:', error);
        return null; // 실패 시 null 반환
    }
}


// POST 요청
export async function fetchPost(to, endPoint, data) {
    if (endPoint === null) {
        return;
    }

    const url = (to === "company" ? URL_TO_COMPANY : URL_TO_CUSTOMER) + endPoint;

    // 데이터를 처리하여 bodyData에 할당
    let bodyData;
    if (typeof data === "number") {
        bodyData = data;
    } else if (typeof data === "string") {
        bodyData = { message: data }; // 단순 문자열일 경우 메시지로 처리
    } else if (data instanceof Map) {
        bodyData = Object.fromEntries(data); // Map을 객체로 변환
    } else if (typeof data === "object" && data !== null) {
        bodyData = { ...data }; // 객체일 경우 그대로 복사
    } else {
        throw new Error("Invalid data type. Expected string, Map, or object.");
    }

    try {
        const response = await fetchRequest(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(bodyData),
            //body: bodyData,
        });
        console.log('POST 응답 데이터:', response);
    } catch (error) {
        console.error('POST 요청 에러:', error);
    }
}



