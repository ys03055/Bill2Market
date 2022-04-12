import React, {Fragment, useState, useEffect} from "react";
import "./header.css";
import {NavLink, Link} from "react-router-dom";
import {Cascader} from "antd";
import axios from "axios";


function HeaderPage()  {
                {/* 초기화면에서 액세스 권한관련 자바 스크립트  */}
    const options = [
        {
            value: '남성',
            label: '남성',
            children: [
                {value: '상의',
                    label: '상의',
                    children: [
                        {
                            value: '셔츠',
                            label: '셔츠',},],
                },
                {
                    value: '하의',
                    label: '하의',
                    children: [
                        {
                            value: '바지',
                            label: '바지',
                        },
                    ],

                },
            ],
        },
        {
            value: '여성',
            label: '여성',
            children: [
                {
                    value: '상의',
                    label: '상의',
                    children: [
                        {
                            value: '블라우스',
                            label: '블라우스',
                        },
                    ],
                },
                {
                    value: '하의',
                    label: '하의',
                    children: [
                        {
                            value: '치마',
                            label: '치마',
                        },
                    ],
                },
            ],
        },
    ];

    const [logged, setLogged] = useState(false); //현재 로그 상태에 따라서 로그인상태인지 아웃상태인지 판단
    const [nickName, setNickName] = useState('');

    const onChange = (value) => {
        console.log(value);

    }

    const isLogin = () => { //로그아웃 상태 일 때 로그인이 필요한 기능이라는 alert 메세지
        if (logged === false) {
            alert("로그인 이후 사용 가능합니다.")
        }
    }

    const tokenRemove = () => { //저장되어있는 모든 토큰 값을 삭제
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('nickName');
    }

    const onLogin = () => { //로그인일 경우 logged를 true로 설정
        setLogged(true);

    }

    const onLogout = () => { //로그아웃일 경우 logged를 false로 설정, 및 모든 토큰값 삭제
        tokenRemove();
        setLogged(false);
    }

    const getNickName = () => { //닉네임을 가져오는 함수

        axios.get("/clients/me", {
            headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("token")
            }
        }).then(response => {
            sessionStorage.setItem('nickName', response.data.data.nickname)
            setNickName(sessionStorage.getItem('nickName'))
        })
            .catch(error => {
                console.log(error.response.data);
            })


    }

    useEffect(() => { // useEffect를 사용하여 초기값을 설정하고, login과 logout일 때를 분리
        const token = sessionStorage.getItem('token');

        if (token === null) {
            onLogout();
            console.log("로그아웃 상태!")

        } else {
            onLogin();
            console.log("로그인 상태!")
            getNickName()
        }
    })


    return (
        <Fragment>
         {/* NavLink 이미지 클릭시 화면이동 */}
          <div>
            <NavLink to={"/MainPage"} >
            <img src={require("./HeaderImage/billi.png")} className = "img_logo"/>
            </NavLink>

          </div>
            <div className="links">
                {logged === false ?
                    <span className="link_text"> </span>
                    :
                    <span className="link_text"> {nickName} 님 환영합니다.</span>
                }

                {logged === false ?
                    <Link to='/login' className="link_text">로그인/회원가입</Link>
                    :
                    <Link to='/' onClick={onLogout} className="link_text">로그아웃</Link>
                }

                {/*???님 환영합니다.*/}
                <Link to='/' onClick={isLogin} className="link_text">마이페이지</Link>
            </div>

            <div className="links_button">
                <Link to='/write' onClick={isLogin}><img src={require("./HeaderImage/sell.png")} height="40px"
                                                         width="40px"/> 글쓰기</Link>
                <Link to='/' onClick={isLogin}><img src={require("./HeaderImage/chat.png")} height="40px"
                                                         width="40px"/> 채팅</Link>
                <Link to='/' onClick={isLogin}><img src={require("./HeaderImage/bell.png")} height="40px"
                                                         width="40px"/> 알림</Link>
            </div>



            

            {/* 검색창 껍데기 */}
            <form>
                <fieldset>
                  <legend className="visually-hidden"/>
                  <div className="search_box">
                    <input type="text" maxLength="225" tabIndex="1" />
                    <button type="submit" tabIndex="2">
                      검색
                    </button>
                  </div>
                </fieldset>
            </form>

               {/* 카테고리 껍데기  */}
              <nav>
                  <Cascader
                      options={options}
                      expandTrigger="hover"
                      onChange={onChange}
                      placeholder="카테고리"
                  />


              </nav>

        

       

    </Fragment>
    )

}

export default HeaderPage;