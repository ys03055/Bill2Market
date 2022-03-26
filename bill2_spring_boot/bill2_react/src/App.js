import "antd/dist/antd.css";   //antd 라이브러리 불러오기
import logo from './logo.svg';
import './App.css';
import {Switch, Route} from 'react-router-dom'
import Loginpage from "./login/login.js";
import SignUpPage from "./signup/signup";   //리엑트 컴포넌트는 항상 대문자로 시작해야 불러올 수 있습니다.
                                            //이거 하나때문에 디버깅하는데 3시간 날림 ㅋ.ㅋ

function App() {
  return (
  <div>
    <Switch>
      <Route exact = {true} path ="/">
        <Loginpage />
      </Route>
      <Route exact = {true} path = "/signup">
        <SignUpPage />
      </Route>
    </Switch>
    </div>
  );
}

export default App;
