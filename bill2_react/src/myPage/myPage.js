import React, {useEffect, useState} from "react";
import HeaderPage from "../header/header";
import "./myPage.css";
import {useDispatch, useSelector} from "react-redux";
import axios from "axios";
import MyLendListPage from "./myLendList";

function MyPage () {
    const dispatch = useDispatch();
    const [tabIndex, setTabIndex] = useState(0);
    const [ready, setReady] = useState(0);

    const tabHandler = (index) => {
        setTabIndex(index);
    }
    const nickNameHandler = () => {
        if(ready === 0) {
            setReady(1)
        } else {
            setReady(0)
            dispatch({type: "NICKNAME", payload: userNick})
        }

    }
    const getClientInfo = () => {
        axios.get("clients/me", {
            headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("token")
            }
        }).then(response => {
            sessionStorage.setItem('client_index', response.data.data.clientIndex)
        })
            .catch(error => {
                console.log(error.response.data);
            })
    }

    const onSubmit = () => {
        const clientIndex = sessionStorage.getItem('client_index');
        console.log(clientIndex)
        const option = {
            url : "/clients/"+clientIndex+"/nickname",
            method: 'PUT',
            header: {
                Authorization: 'Bearer ' + sessionStorage.getItem("token")
            },
            data: 'nickname=' + userNick
        }

        axios(option)
            .then(res=>{
                if (res.status >= 200 && res.status <= 204) {
                    console.log(res.data.message);
                }
            }).catch(res=>{
            alert(res.response.data.message);
        });
    };

    const nickName = useSelector(state => state.nickName);
    const [userNick, setUserNick] = useState(nickName);

    const nickNameChange = (e) => {
        console.log(userNick)
        setUserNick(e.target.value)

    }
    const TapContent = [
        {
            myTitle:(
                <li className="tabTitle1" onClick={()=>tabHandler(0)}>내가 올린 물품</li>
            ),
            myContent:(
                <div>
                <MyLendListPage/>
                </div>
            )
        },
        {
            myTitle:(
                <li className="tabTitle" onClick={()=>tabHandler(1)}>내가 빌린 물품</li>
            ),
            myContent:(
                <div>빌린 물품 내용</div>
            )
        },
        {
            myTitle:(
                <li className="tabTitle" onClick={()=>tabHandler(2)}>찜 목록</li>
            ),
            myContent:(
                <div>찜 목록</div>
            )
        },
        {
            myTitle:(
                <li className="tabTitle" onClick={()=>tabHandler(3)}>후기</li>
            ),
            myContent:(
                <div>후기</div>
            )
        }
    ]


    useEffect(() => {
        getClientInfo()
        console.log(userNick)
        console.log(nickName)
        },[])

    return(
        <div>
            <title></title>
            <header>
                <HeaderPage></HeaderPage>
            </header>

            <div className = "myPageWrap">
                <div className = "user">
                    <div className= "userPhoto">

                    </div>
                    {ready === 0 ?
                    <div className = "userContent" >
                        {nickName}
                    <button className="nickNameChangeBtn" onClick= {nickNameHandler}>닉네임 수정</button>
                    </div>
                        :
                    <div className = "userContent" >
                        <input type="text" maxLength="40" onChange={nickNameChange} value={userNick} />
                    <button className="nickNameChangeBtn" onClick={() => {
                        nickNameHandler()
                        onSubmit()}}>수정</button>
                    </div>
                        }

                </div>
                
                <div>
                    <ul className = "tab">
                        {TapContent.map((section, index)=>{
                            return section.myTitle
                            })}
                    </ul>

                    <div className = "tabCont">
                        <div className = "tabContDetail">
                        {TapContent[tabIndex].myContent}
                        </div>
                    </div>
                </div>

            </div>

        </div>
    )
}
export default MyPage;