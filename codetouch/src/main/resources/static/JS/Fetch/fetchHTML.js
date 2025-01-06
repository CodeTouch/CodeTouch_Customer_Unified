function fetchHTMLtoBody(filePath, callback1,callback2) {
    fetch(filePath)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Failed to load ${filePath}: ${response.statusText}`);
        }
        return response.text();
      })
      .then((html) => {
       document.body.insertAdjacentHTML('beforeend', html);
        // HTML 로드 후 콜백 실행
        if (callback1 && typeof callback1 === 'function') {
          callback1();
        }
        if (callback2 && typeof callback2 === 'function') {
          callback2();
        }
      })
      .catch((error) => {
        console.error('Error loading HTML:', error);
      });
  }
  
function fetchHTMLtoSetting(filePath, callback1,callback2) {
    fetch(filePath)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Failed to load ${filePath}: ${response.statusText}`);
        }
        return response.text();
      })
      .then((html) => {
       let setting = document.getElementById('setting');
       setting.insertAdjacentHTML('beforeend', html);
        // HTML 로드 후 콜백 실행
        if (callback1 && typeof callback1 === 'function') {
          callback1();
        }
        if (callback2 && typeof callback2 === 'function') {
          callback2();
        }
      })
      .catch((error) => {
        console.error('Error loading HTML:', error);
      });
  }
  