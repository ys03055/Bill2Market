import React, {Fragment, Component, useState, useEffect} from "react";
import "./header.css";
import {NavLink, Link} from "react-router-dom";
import {Cascader} from "antd";


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


    const onChange = (value) => {
        console.log(value);

    }

    const onload = () => { //자동 새로고침 nickName값이 바로 안들어감
        if(!window.location.hash) {
            window.location = window.location + '#loaded';
            window.location.reload();
        }
    }

    const isLogin = () => { //로그아웃 상태 일 때 로그인이 필요한 기능이라는 alert 메세지
        if (logged === false) {
            alert("로그인 이후 사용 가능합니다.")
        }
    }

    const tokenRemove = () => { //저장되어있는 모든 토큰 값을 삭제
        localStorage.removeItem('token');
        localStorage.removeItem('nickName');
    }

    const onLogin = () => { //로그인일 경우 logged를 true로 설정
        setLogged(true);

    }

    const onLogout = () => { //로그아웃일 경우 logged를 false로 설정, 및 모든 토큰값 삭제
        tokenRemove();
        setLogged(false);
    }

    useEffect(() => { // useEffect를 사용하여 초기값을 설정하고, login과 logout일 때를 분리
        const token = localStorage.getItem('token');

        if (token === null) {
            onLogout();
            console.log("로그아웃 상태!")

        } else {
            onLogin();
            console.log("로그인 상태!")
            onload();
        }
    })


    const nick = localStorage.getItem('nickName'); // return안에서 nickName을 끌어오기위해서 변수 생성

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
                    <span className="link_text"> {nick} 님 환영합니다.</span>
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
                <Link to='/' onClick={isLogin}><img src={require("./HeaderImage/sell.png")} height="40px"
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