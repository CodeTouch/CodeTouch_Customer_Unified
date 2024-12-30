let targetNode = null;

function settingElements(tag) {
    const clickElem = tag.querySelector(`[class*="clone"]`);
    targetNode = clickElem;
    const backgroundColorInput = document.getElementById('backgroundColor');
    const textColorInput = document.getElementById('textColor');
    const fontSizeInput = document.getElementById('fontSize');
    const fontWeightInput = document.getElementById('fontWeight');
    const fontFamilyInput = document.getElementById('fontFamily');
    const textAlignInput = document.getElementById('textAlign');
    const lineHeightInput = document.getElementById('lineHeight');
    const letterSpacingInput = document.getElementById('letterSpacing');
    const borderWidthInput = document.getElementById('borderWidth');
    const borderColorInput = document.getElementById('borderColor');
    const borderStyleInput = document.getElementById('borderStyle');
    const radiusInput = document.getElementById('radius');
    const widthInput = document.getElementById('width');
    const heightInput = document.getElementById('height');
    const marginInput = document.getElementById('margin');
    const paddingInput = document.getElementById('padding');
    const opacityInput = document.getElementById('opacity');
    const hyperlinkInput = document.getElementById('hyperlink');
    const imageURLInput = document.getElementById('imageURL');
    const textDirectionInput = document.getElementById('textDirection');

    //RGB를 16진수로 변경하는 메서드
    // function rgbToHex(rgb) {
    //     const result = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
    //     return result ? `#${((1 << 24) | (parseInt(result[1]) << 16) | (parseInt(result[2]) << 8) | parseInt(result[3])).toString(16).slice(1)}` : rgb;
    // }

    function rgbToHex(rgba) {
        // rgba 문자열에서 RGB 값 추출
        const match = rgba.match(/^rgba?\((\d+),\s*(\d+),\s*(\d+)/);
        if (!match) return "#000000"; // 기본값: 검정색

        const r = parseInt(match[1]);
        const g = parseInt(match[2]);
        const b = parseInt(match[3]);

        // RGB 값을 16진수로 변환
        return `#${((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1).toUpperCase()}`;
    }

    const handlers = new Map();  // Map으로 리스너를 저장
    const key = 'setting';
        clickElem.addEventListener('dblclick', () => {
            console.log(11);
            const handlerKey = `click-${clickElem.id || clickElem.className || clickElem.tagName}`;
            console.log(handlerKey);
            //클릭된 요소의 속성을 가져오기
            const elemStyle = getComputedStyle(clickElem);
            //클릭된 요소의 속성을 setting에 input의 value로 넣기
            //좌측 설정
            backgroundColorInput.value = rgbToHex(elemStyle.backgroundColor);
            borderStyleInput.value = elemStyle.borderStyle;
            borderColorInput.value = rgbToHex(elemStyle.borderColor);
            borderWidthInput.value = parseInt(elemStyle.borderWidth,10); 
            radiusInput.value = parseInt(elemStyle.borderRadius,10); 
            widthInput.value = parseInt(elemStyle.width,10); 
            marginInput.value = parseInt(elemStyle.margin,10);
            heightInput.value = parseInt(elemStyle.height,10);
            paddingInput.value = parseInt(elemStyle.padding,10);
            opacityInput.value = parseFloat(elemStyle.opacity);
            hyperlinkInput.value = clickElem.dataset.hyperlink;
            imageURLInput.value = elemStyle.backgroundImage;
            //우측 설정
            textColorInput.value = rgbToHex(elemStyle.color);
            fontSizeInput.value = parseInt(elemStyle.fontSize,10);
            fontWeightInput.value = elemStyle.fontWeight === 'normal' ? '400' : (elemStyle.fontWeight === 'bold' ? '700' : elemStyle.fontWeight);
            
            let fontFamilyValue = elemStyle.fontFamily.split(',')[0];  // 첫 번째 폰트만 추출하고 공백 제거
            fontFamilyValue = fontFamilyValue.replace(/['"]+/g, '');  // 인용부호 제거
            fontFamilyInput.value = fontFamilyValue;  // select 값으로 설정
            
            textAlignInput.value = elemStyle.textAlign;
            lineHeightInput.value = 1;
            let letterSpacingValue = elemStyle.letterSpacing;
            // 'normal'인 경우 0으로 처리
            if (letterSpacingValue === 'normal') {
                letterSpacingValue = 0;
            } else {
                // 숫자만 추출 (px 단위 처리)
                letterSpacingValue = parseFloat(letterSpacingValue);
            }
            letterSpacingInput.value = letterSpacingValue;
            textDirectionInput.value = elemStyle.direction;
            console.log('aa');
///////////////////왼쪽 설정///////////////////////////////////////////////////////////////////////////////////////////////////////
            //배경색
            if (elemStyle.backgroundColor) {
                    // 기존 이벤트 리스너 제거 후 새로 추가


                const E_backgroundColor = (e) => {
                    targetNode.style.backgroundColor = e.target.value;
                }

                if (handlers.has(key)) {
                    //기존 이벤트 삭제
                    backgroundColorInput.removeEventListener('input', handlers.get(key));
                    // 수정한 input의 value를 elem의 backgroundColor에 넣기
                    backgroundColorInput.addEventListener('input', E_backgroundColor);
                    handlers.set(key, E_backgroundColor);
                } else {
                    // 수정한 input의 value를 elem의 backgroundColor에 넣기
                    backgroundColorInput.addEventListener('input', E_backgroundColor);
                    handlers.set(key, E_backgroundColor);
                }
            }

            //경계선 종류
            if(elemStyle.borderStyle){
                const E_borderStyle = (e) =>{
                    targetNode.style.borderStyle = e.target.value;
                }
                if(!handlers[handlerKey]){
                    borderStyleInput.addEventListener('input', E_borderStyle);
                    handlers[handlerKey] = E_borderStyle;
                }else{
                    borderStyleInput.removeEventListener('input', handlers[handlerKey]);
                    borderStyleInput.addEventListener('input', E_borderStyle);
                    handlers[handlerKey] = E_borderStyle;
                }
            }
            
            //경계선색
            if(elemStyle.borderColor){
                const E_borderColor = (e) =>{
                    targetNode.style.borderColor = e.target.value;
                }
                if(!handlers[targetNode + '-borderColor']){
                    borderColorInput.addEventListener('input', E_borderColor);
                    handlers[targetNode + '-borderColor'] = E_borderColor;
                }else{
                    borderColorInput.removeEventListener('input', handlers[targetNode + '-borderColor']);
                    borderColorInput.addEventListener('input', E_borderColor);
                    handlers[targetNode + '-borderColor'] = E_borderColor;
                }
            }
            
            //경계선 두께
            if(elemStyle.borderWidth){
                const E_borderWidth = (e) =>{
                    targetNode.style.borderWidth = `${e.target.value}px`;
                }
                if(!handlers[targetNode + '-borderWidth']){
                    borderWidthInput.addEventListener('input', E_borderWidth);
                    handlers[targetNode + '-borderWidth'] = E_borderWidth;
                }else{
                    borderWidthInput.removeEventListener('input', handlers[targetNode + '-borderWidth']);
                    borderWidthInput.addEventListener('input', E_borderWidth);
                    handlers[targetNode + '-borderWidth'] = E_borderWidth;
                }
            }

            //모서리 둥글기
            if(elemStyle.borderRadius){
                const E_borderRadius = (e) =>{
                    targetNode.style.borderRadius = `${e.target.value}px`;
                }
                if(!handlers[targetNode + '-borderRadius']){
                    radiusInput.addEventListener('input', E_borderRadius);
                    handlers[targetNode + '-borderRadius'] = E_borderRadius;
                }else{
                    radiusInput.removeEventListener('input', handlers[targetNode + '-borderRadius']);
                    radiusInput.addEventListener('input', E_borderRadius);
                    handlers[targetNode + '-borderRadius'] = E_borderRadius;
                }
            }

            //넓이
            if(elemStyle.width){
                // width 변경 이벤트 리스너 함수
                const E_width = (e) => {
                    targetNode.style.width = `${e.target.value}px`;
                };
                // 기존 이벤트 리스너 제거 후 새로 추가
                if (!handlers[targetNode + '-width']) {
                    //수정한 input의 value를 elem의 width에 넣기
                    widthInput.addEventListener('input', E_width);
                    handlers[targetNode + '-width'] = E_width;  // 리스너를 저장
                } else {
                    //기존 이벤트 삭제
                    widthInput.removeEventListener('input', handlers[targetNode + '-width']);
                    //수정한 input의 value를 elem의 width에 넣기
                    widthInput.addEventListener('input', E_width);
                    handlers[targetNode + '-width'] = E_width;  // 업데이트된 리스너 저장
                }
            }

            //안쪽 여백
            if(elemStyle.margin){
                const E_margin = (e) => {
                    targetNode.style.margin = `${e.target.value}px`;
                };
                if (!handlers[targetNode + '-margin']) {
                    marginInput.addEventListener('input', E_margin);
                    handlers[targetNode + '-margin'] = E_margin;  // 리스너를 저장
                } else {
                    marginInput.removeEventListener('input', handlers[targetNode + '-margin']);
                    marginInput.addEventListener('input', E_margin);
                    handlers[targetNode + '-margin'] = E_margin;  // 업데이트된 리스너 저장
                }
            }

            //높이
            if(elemStyle.height){
                const E_height = (e) => {
                    targetNode.style.height = `${e.target.value}px`;
                };
                if (!handlers[targetNode + '-height']) {
                    heightInput.addEventListener('input', E_height);
                    handlers[targetNode + '-height'] = E_height;  // 리스너를 저장
                } else {
                    heightInput.removeEventListener('input', handlers[targetNode + '-height']);
                    heightInput.addEventListener('input', E_height);
                    handlers[targetNode + '-height'] = E_height;  // 업데이트된 리스너 저장
                }
            }

            //바깥 여백
            if(elemStyle.padding){
                const E_padding = (e) => {
                    targetNode.style.padding = `${e.target.value}px`;
                };
                if (!handlers[targetNode + '-padding']) {
                    paddingInput.addEventListener('input', E_padding);
                    handlers[targetNode + '-padding'] = E_padding;  // 리스너를 저장
                } else {
                    paddingInput.removeEventListener('input', handlers[targetNode + '-padding']);
                    paddingInput.addEventListener('input', E_padding);
                    handlers[targetNode + '-padding'] = E_padding;  // 업데이트된 리스너 저장
                }
            }

            //투명도
            if(elemStyle.opacity){
                const E_opacity = (e) => {
                    let opacity = parseFloat(e.target.value);
                    if (opacity < 0) opacity = 0;
                    if (opacity > 1) opacity = 1;
                    targetNode.style.opacity = opacity;
                };
                if(!handlers[targetNode+ '-opacity']){
                    opacityInput.addEventListener('input', E_opacity);
                    handlers[targetNode+ '-opacity'] = E_opacity;
                }else{
                    opacityInput.removeEventListener('input' , handlers[targetNode+ '-opacity']);
                    opacityInput.addEventListener('input' , E_opacity);
                    handlers[targetNode+ '-opacity'] = E_opacity;
                }
            }

            // 링크
            const hrefValue = targetNode.getAttribute('href');
            // `hyperlinkInput`에 클릭한 `elem`의 href 값을 출력
            if (hrefValue) {
                hyperlinkInput.value = hrefValue;  // href가 있으면 그 값을 input에 설정
            } else {
                hyperlinkInput.value = '';  // href가 없으면 input을 비워놓기
            }
            // input 값이 변경될 때마다 해당 elem의 href 속성 변경
            const updateHref = () => {
                let newUrl = hyperlinkInput.value;
                // 현재 클릭한 elem의 href만 업데이트
                if (targetNode.getAttribute('href') !== newUrl) {
                    targetNode.setAttribute('href', newUrl);  // input 값으로 elem의 href 속성 변경
                    newUrl = "";
                }
            };
            if(!handlers[targetNode+ '-href']){
                hyperlinkInput.addEventListener('input', updateHref);
                handlers[targetNode+ '-href'] = updateHref;
            }else{
                hyperlinkInput.removeEventListener('input' , handlers[targetNode+ '-href']);
                hyperlinkInput.addEventListener('input' , updateHref);
                handlers[targetNode+ '-href'] = updateHref;
            }

            // 이미지 주소
            const backgroundImageValue = elemStyle.backgroundImage ? elemStyle.backgroundImage.slice(5, -2) : '';
            imageURLInput.value = backgroundImageValue || '';  // 배경 이미지 URL을 input에 설정
            // input 값이 변경될 때마다 해당 elem의 backgroundImage 속성 변경
            const updateImage = () => {
                const newImage = imageURLInput.value;
                if (targetNode.style.backgroundImage !== `url('${newImage}')`) {
                    targetNode.style.backgroundImage = `url('${newImage}')`;  // 새 이미지 URL로 backgroundImage 업데이트
                    targetNode.style.backgroundSize = '100% 100%';  // 배경 이미지를 부모 요소 크기에 맞게 설정
                    targetNode.style.backgroundRepeat = 'no-repeat';  // 이미지 반복 방지
                }
            };

            // 중복 리스너 관리
            if (!handlers[targetNode + '-image']) {
                imageURLInput.addEventListener('input', updateImage);
                handlers[targetNode + '-image'] = updateImage;
            } else {
                imageURLInput.removeEventListener('input', handlers[targetNode + '-image']);
                imageURLInput.addEventListener('input', updateImage);
                handlers[targetNode + '-image'] = updateImage;
            }

// ///////////////////오른쪽 설정///////////////////////////////////////////////////////////////////////////////////////////////////////

            //글자색
            if(elemStyle.color){
                const E_color = (e) => {
                    targetNode.style.color = e.target.value;
                };
                if(!handlers[targetNode+ '-color']){
                    textColorInput.addEventListener('input', E_color);
                    handlers[targetNode+ '-color'] = E_color;
                }else{
                    textColorInput.removeEventListener('input' , handlers[targetNode+ '-color']);
                    textColorInput.addEventListener('input' , E_color);
                    handlers[targetNode+ '-color'] = E_color;
                }
            }

            //글자 크기
            if(elemStyle.fontSize){
                const E_fontSize = (e) =>{
                    targetNode.style.fontSize = `${e.target.value}px`;
                };
                if(!handlers[targetNode+ '-fontSize']){
                    fontSizeInput.addEventListener('input', E_fontSize);
                    handlers[targetNode+ '-fontSize'] = E_fontSize;
                }else{
                    fontSizeInput.removeEventListener('input' , handlers[targetNode+ '-fontSize']);
                    fontSizeInput.addEventListener('input' , E_fontSize);
                    handlers[targetNode+ '-fontSize'] = E_fontSize;
                }
            }

            //글자 굵기
            if (elemStyle.fontWeight) {
                const E_fontWeight = (e) => {
                    targetNode.style.fontWeight = e.target.value;
                };

                if (!handlers[targetNode + '-fontWeight']) {
                    fontWeightInput.addEventListener('change', E_fontWeight);
                    handlers[targetNode + '-fontWeight'] = E_fontWeight;
                } else {
                    fontWeightInput.removeEventListener('change', handlers[targetNode + '-fontWeight']);
                    fontWeightInput.addEventListener('change', E_fontWeight);
                    handlers[targetNode + '-fontWeight'] = E_fontWeight;
                }
            }

            //글꼴
            if (elemStyle.fontFamily) {
                const E_fontFamily = (e) => {
                    targetNode.style.fontFamily = e.target.value;
                };
                if (!handlers[targetNode + '-fontFamily']) {
                    fontFamilyInput.addEventListener('change', E_fontFamily);
                    handlers[targetNode + '-fontFamily'] = E_fontFamily;
                } else {
                    fontFamilyInput.removeEventListener('change', handlers[targetNode + '-fontFamily']);
                    fontFamilyInput.addEventListener('change', E_fontFamily);
                    handlers[targetNode + '-fontFamily'] = E_fontFamily;
                }
            }

            //글자 정렬
            if (elemStyle.textAlign) {
                const E_textAlign = (e) => {
                    targetNode.style.textAlign = e.target.value;
                };

                if (!handlers[targetNode + '-textAlign']) {
                    textAlignInput.addEventListener('change', E_textAlign);
                    handlers[targetNode + '-textAlign'] = E_textAlign;
                } else {
                    textAlignInput.removeEventListener('change', handlers[targetNode + '-textAlign']);
                    textAlignInput.addEventListener('change', E_textAlign);
                    handlers[targetNode + '-textAlign'] = E_textAlign;
                }
            }

            //줄 간격
            if (elemStyle.lineHeight) {
                const E_lineHeight = (e) => {
                    targetNode.style.lineHeight = e.target.value;
                };

                if (!handlers[targetNode + '-lineHeight']) {
                    lineHeightInput.addEventListener('input', E_lineHeight);
                    handlers[targetNode + '-lineHeight'] = E_lineHeight;
                } else {
                    lineHeightInput.removeEventListener('input', handlers[targetNode + '-lineHeight']);
                    lineHeightInput.addEventListener('input', E_lineHeight);
                    handlers[targetNode + '-lineHeight'] = E_lineHeight;
                }
            }

            //글자 간격
            if (elemStyle.letterSpacing) {
                const E_letterSpacing = (e) => {
                    targetNode.style.letterSpacing = `${e.target.value}px`;
                };

                if (!handlers[targetNode + '-letterSpacing']) {
                    letterSpacingInput.addEventListener('input', E_letterSpacing);
                    handlers[targetNode + '-letterSpacing'] = E_letterSpacing;
                } else {
                    letterSpacingInput.removeEventListener('input', handlers[targetNode + '-letterSpacing']);
                    letterSpacingInput.addEventListener('input', E_letterSpacing);
                    handlers[targetNode + '-letterSpacing'] = E_letterSpacing;
                }
            }

            //글자 방향
            if (elemStyle.direction) {
                const E_direction = (e) => {
                    targetNode.style.direction = e.target.value
                };

                if (!handlers[targetNode + '-direction']) {
                    textDirectionInput.addEventListener('input', E_direction);
                    handlers[targetNode + '-direction'] = E_direction;
                } else {
                    textDirectionInput.removeEventListener('input', handlers[targetNode + '-direction']);
                    textDirectionInput.addEventListener('input', E_direction);
                    handlers[targetNode + '-direction'] = E_direction;
                }
            };
        });
       
   // });
}