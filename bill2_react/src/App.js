import './App.css';
import "antd/dist/antd.css";   //antd 라이브러리 불러오기
import Routers from "./Routers";

function App() {
    return (
      <div>
          <Routers/> //라우터 따로 분리
         </div>
   );
}

export default App;