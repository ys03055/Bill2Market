import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
// import Axios from "axios";

function App() {
    const [user,setUser] = useState([]);
    useEffect(() => {
      fetch("/api/example").then((response) => {
          return response.json();
        })
        .then(function (data) {
          setUser(data);
        });
  }, []);

    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <p>
            asdasda
          </p>
          <ul>
             {user.map((text, index) => <li key={`${index}-${text}`}>{text}</li>)}
          </ul>
        </header>
      </div>
   );
}

export default App;