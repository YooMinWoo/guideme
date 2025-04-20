import React from 'react';
import axios from 'axios';

function selectData() {
  axios.get('/default')
    .then((res) => {
      alert(res.data); // 👉 서버에서 받은 문자열을 alert로 출력
    })
    .catch((err) => {
      console.error(err);
      alert('요청에 실패했습니다.');
    });
}

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <button onClick={selectData}>조회</button>
      </header>
    </div>
  );
}

export default App;