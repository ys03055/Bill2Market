import { Form, Input, Button, Checkbox, Divider, Modal } from 'antd';  //ant design form 사용
import { UserOutlined, LockOutlined } from '@ant-design/icons';  //터미널에서 npm install antd 입력 후 설치
import "./login.css";
import {Link, useLocation } from "react-router-dom"
import React, {useEffect} from "react";
import axios from "axios";
import ReactModal from 'react-modal';

function LoginPage () {

    const onSubmit = (values) => {
        const data = {
            clientId    : values.id,
            password    : values.password
        }

        const option = {
            url : 'auth/login',
            method: 'POST',
            header: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            data: data
        }

        axios(option)
            .then(res=>{
                localStorage.setItem('token', res.data.data);
            }).catch(res=>{
            alert(res.response.data.message);
        });
    };

    const onSubmitFailed = (errorInfo) => {  //exception 발생 시 에러 원인 불러오기
        console.log("로그인에 실패했습니다",errorInfo);  //서버로 요청하는 값
    };

    const location = useLocation();

    const initializeNaverLogin = () => {
        if (!location.hash){
            const naverLogin = new window.naver.LoginWithNaverId({
                clientId: "7uNJD1fpryntJskxi0Z1",
                callbackUrl: "http://localhost:3000/login",
                isPopup: false,
                loginButton: { color: 'green', type: 3, height: '47' },
            });
            naverLogin.init();
        }else{
            const token = location.hash.split('=')[1].split('&')[0];

            console.log(token);
            let option = {
                url : '/auth/naver-login',
                method: 'POST',
                header: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                data: "access_token=" + token
            }

            axios(option)
                .then(res=>{
                    if(!res.data.success || res.data.code == 1){ // 회원가입, 닉네임 입력 필요
                        // const NicknameModal = () =>{
                        //     return(
                        //         <Modal open={true}>
                        //             <div>모달입니다.</div>
                        //         </Modal>
                        //     );
                        // }

                        // option = {
                        //     url: '/clients/' + sessionStorage.getItem("client_index") + '/nickname',
                        //     method: 'UPDATE',
                        //     header: {
                        //         'Accept': 'application/json',
                        //         'Content-Type': 'application/json',
                        //     },
                        //     data: "nickname=" // + [입력받은 닉네임]
                        // }
                        // axios(option).then(res2=>{
                        //
                        // }).catch(res2=>{
                        //     alert(res2.response.data.message);
                        // });

                    }else{ // 로그인 성공
                        sessionStorage.setItem('client_index', res.data.clientIndex)
                        localStorage.setItem('token', res.data.token);
                        window.history.push("/*");
                    }
                }).catch(res=>{
                alert(res.response.data.message);
            });
        }
    };

    useEffect(() => {
        initializeNaverLogin();
    }, []);



    return (

        <div className = "login_container">
            <Form
                name = "login"
                onFinish={onSubmit}  //콜백함수 구현 , 값 받아서 values에 넣음
                onFinishFailed={onSubmitFailed} //
                autoComplete="off"   //자동완성 끄기
                initialValues={{
                    remember: true,  //아이디 기억할 때 값 기억하는 거
                }}
            >

                <h1>로그인</h1>
                <p>신규 회원이신가요?
                    <Link className='signUp_Link' to={'/signUp'}>
                        빌리마켓 회원가입하기
                    </Link>
                </p>
                {/* 회원가입 화면 구현하면 링크 넣을 예정 */}

                <Form.Item

                    name ="id" //values에 들어갈 key 값
                    rules = {[{    //입력이 안되면 메세지 뜨는 속성
                        required :true,
                        message : "아이디를 입력해주세요!"
                    },
                    ]}
                >

                    <Input
                        prefix={<UserOutlined className="site-form-item-icon" />} //아이디 입력창 옆 아이콘
                        placeholder="아이디를 입력하세요." />

                </Form.Item>


                <Form.Item
                    name ="password"  //values에 들어갈 key 값
                    rules = {[{
                        required :true,  //비밀번호 미입력시 메세지 뜨는 속성
                        message : "비밀번호를 입력해주세요!"
                    },
                    ]}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon" />} //비밀번호 입력창 옆 아이콘
                        type="password"
                        placeholder="비밀번호를 입력하세요."/>
                </Form.Item>

                <Form.Item
                    name="remember"  //values에 들어갈 key 값
                    valuePropName="checked">
                    <Checkbox>아이디 기억하기</Checkbox>
                </Form.Item>
                {/* 아이디 기억하기 체크 박스 */}

                <Form.Item>
                    <Link className='LoginMainPage' to={'/MainPage'} >
                        <Button type="primary" htmlType="submit" className='login_button'>
                            로그인
                        </Button>
                    </Link>
                </Form.Item>
                {/* 로그인 버튼 구현 */}

                <Divider>
                </Divider>
                {/* 보기 쉽게 구분선 구현 */}
                <div className='sns_login'>

                    {/* <a href={KAKAO_AUTH_URL}> */}
                    <div className='kakao_btn'>
                    </div>
                    {/* </a> */}

                    {/* 카카오 로그인 사진 클릭 시 로그인되는 거 구현 예정 */}


                    {/* <a href={NAVER_AUTH_URL}> */}
                    <div id='naverIdLogin'>
                    </div>
                    {/* </a> */}

                    {/* 네이버 로그인 사진 클릭 시 로그인되는 거 구현 예정 */}

                </div>

                <Divider>
                </Divider>
                {/* 보기 쉽게 구분선 구현 */}

                <a className='underbar'>

                    {/* <Link className='findId_Link' to={'/findIdPage'}> */}
                    <a href="">아이디찾기</a>
                    {/* </Link> */}

                    {/* <Link className='findPassword_Link' to={'/findPasswordPage'}> */}
                    <a href="">비밀번호찾기</a>
                    {/* </Link> */}
                    <Link className='signUp_Link' to={'/signup'}>
                        <a>회원가입</a>
                    </Link>
                </a>

                {/* 화면에 띄어쓰기 어떻게 보여주는지 몰라서 야매로 일단 화면에 띄웠습니다 ㅠㅠ */}
                {/* 아시면 제발 알려주세요. */}

            </Form>


        </div>

    )
}

export default LoginPage;