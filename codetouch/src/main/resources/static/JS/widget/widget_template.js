const widgetElements = [
    "🖼 이미지",
    "📄 텍스트",
    "🔍 버튼",
    "🌢 아이콘",
    "🌍 지도",
    "🎞️ 동영상",
    "✋ 여백",
    "─ 가로선"
];

const templateElements = [
    "🛒 상품 템플릿",
    "💳 결제 템플릿",
    "📦 장바구니",
    "🚪 로그인/회원가입",
    "📋 게시판",
    "✏️ 입력폼"
];

const buttonVariations = [
    { id: "button1", text: "Read More", style: "set-widget custom-btn btn-1" },
    { id: "button2", text: "Read More", style: "set-widget custom-btn btn-2" },
    { id: "button3", text: "<span>Read More</span>", style: "set-widget custom-btn btn-3" },
    { id: "button4", text: "<span>Read More</span>", style: "set-widget custom-btn btn-4" },
    { id: "button5", text: "<span>Read More</span>", style: "set-widget custom-btn btn-5" },
    { id: "button6", text: "<span>Read More</span>", style: "set-widget custom-btn btn-6" },
    { id: "button7", text: "<span>Read More</span>", style: "set-widget custom-btn btn-7" },
    { id: "button8", text: "<span>Read More</span>", style: "set-widget custom-btn btn-8" },
    { id: "button9", text: "Read More", style: "set-widget custom-btn btn-9" },
    { id: "button10", text: "Read More", style: "set-widget custom-btn btn-10" },
    { id: "button11", text: "Read More", style: "set-widget custom-btn btn-11" },
    { id: "button12", text: "<span>Click!</span><span>Read More</span>", style: "set-widget custom-btn btn-12" },

];

const templateVariations = [
    { id: "template1", text: "Grid Layout", style: "custom-tpl tpl-1" },
    { id: "template2", text: "List Layout", style: "custom-tpl tpl-2" },
    { id: "template3", text: "Minimal", style: "custom-tpl tpl-3" },
    { id: "template4", text: "Detailed", style: "custom-tpl tpl-4" }
];

const templateVariations1 = [
    { id: "template1", text: "Grid Layout", style: "custom-tpl tpl-1" },
    { id: "template2", text: "List Layout", style: "custom-tpl tpl-2" },
    { id: "template3", text: "Minimal", style: "custom-tpl tpl-3" },
    { id: "template4", text: "Detailed", style: "custom-tpl tpl-4" },
    { id: "template1", text: "Grid Layout", style: "custom-tpl tpl-1" },
    { id: "template2", text: "List Layout", style: "custom-tpl tpl-2" },
    { id: "template3", text: "Minimal", style: "custom-tpl tpl-3" },
    { id: "template4", text: "Detailed", style: "custom-tpl tpl-4" }
];

const variationData = {
    //위젯
    "🖼 이미지" : templateVariations,
    "📄 텍스트" : templateVariations1,
    "🔍 버튼" : templateVariations,
    "🌢 아이콘" : templateVariations1,
    "🌍 지도" : templateVariations,
    "🎞️ 동영상" : templateVariations,
    "✋ 여백" : templateVariations,
    "─ 가로선" : templateVariations,
    //템플릿
    "🔍 버튼": buttonVariations,
    "🛒 상품 템플릿": templateVariations
};
