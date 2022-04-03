import React , {useState} from "react";
import { Form, Input, DatePicker ,Button,  Divider,} from 'antd';
import "./signUp.css";
// import Post from "./address.js";
// import { SearchOutlined } from '@ant-design/icons';
import axios from "axios";
import {useNavigate} from "react-router-dom"
// import ReactDom from 'react-dom';
// import LoginPage from '../login/login'



function SignUpPage () {

    const navigate = useNavigate();



    const onSubmit = (values) => {
        console.log(clientId+ " " + password + " " + nickname + " ");
        axios.post("http://localhost:8080/auth/signup ", {
            clientId: clientId,
            password: password,
            clientName: clientName,
            nickname: nickname,
            birthdate: birthdate,
            phoneNumber: phoneNumber,
            email: email
        })
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    alert('회원가입에 성공했습니다. ^____^');
                    navigate("/Main")
                }
            })
            .catch(res => {
                alert(res.response.data.message);
            })

    };



    //state 선언    ''로 공백상태로 둠 -> 각 항목 기입창에 onchange 사용해서 state 바뀌도록 함 -> value 값 변경
    const [clientId, setClientId] = useState('');
    const [password, setPassword] = useState('');
    // const [confirm_password, setConfirmPassword] = useState('');
    const [clientName, setClientName] = useState('');
    const [nickname, setNickName] = useState('');
    const [birthdate, setBirthdate] = useState('');
    // const [clientAddress, setClientAddress] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const [email, setEmail] = useState('');







    //Handler 구현    //받아온 값 전달
    const onClientIdHandler = (event) => {
        setClientId(event.currentTarget.value)
    }
    const onPasswordHandler = (event) => {
        setPassword(event.currentTarget.value)
    }
    // const onConfirm_password = (event) => {
    //   setConfirmPassword(event.currentTarget.value)
    // }
    const onClientNameHandler = (event) => {
        setClientName(event.currentTarget.value)
    }
    const onNickNameHandler = (event) => {
        setNickName(event.currentTarget.value)
    }
    const onBirthDateHandler = (event) => {
        setBirthdate(event.currentTarget.value)
    }
    // const onClientAddressHandler = (event) => {
    //   setClientAddress(event.currentTarget.value)
    // }
    const onPhoneNumHandler = (event) => {
        setPhoneNumber(event.currentTarget.value)
    }
    const onEmailHandler = (event) => {
        setEmail(event.currentTarget.value)
    }
    const onSubmitHandler = (event) => {
        event.preventDefault();




        console.log('clientId', clientId)
        console.log('password', password)
        console.log('ClientName', clientName)
        console.log('nickname', nickname)
        console.log('birthdate',birthdate)
        console.log('PhoneNumber', phoneNumber)
        console.log('email', email)

        // let body = {
        //     clientId: clientId,
        //     password: password,
        //     confirm_password: confirm_password,
        //     clientName: clientName,
        //     nickname: nickname,
        //     birthdate: birthdate,
        //     clientAddress: clientAddress,
        //     phoneNumber: phoneNumber,
        //     email: email,
        // }

    }





//   const [popup, setPopUp] = useState(false);

//   const openPopUp = () => {        //주소 검색 팝업창 오픈
//       setPopUp(true)
//   }


//   const closePopUp = () => {     //주소 검색 팝업창 클로우즈
//       setPopUp(false)
//   }

//   const PopupDom = ({ children }) => {
//     const el = document.getElementById('popupDom');
//     return ReactDom.createPortal(children, el);
// };
    // let Post = (data) => {
    //   setInputs({...Input, address: data.fullAddress, zonecode : data.zonecode});
    //   props.onClose;
    // }

    // const getClientAddress = (clientAddress) => {setClientAddress(clientAddress)}







    return (
        <div className = "signup_container">
            <Form
                name = "signup"
                onFinish={onSubmit}  //콜백함수 구현 , 값 받아서 values에 넣음
                onSubmit={onSubmitHandler}
            >
                <h1 className="signUp_Header">빌리 마켓 회원가입</h1>



                <Form.Item

                    name = "clientId"
                    label="아이디"
                    rules = {[{    //입력이 안되면 메세지 뜨는 속성
                        required :true,
                        message : "아이디를 입력해주세요!" },
                    ]}
                >
                    <Input value={clientId} onChange= {onClientIdHandler}/>
                </Form.Item>

                <Form.Item

                    name = "password"
                    label="비밀번호"
                    rules = {[{    //입력이 안되면 메세지 뜨는 속성
                        required :true,
                        message : "비밀번호를 입력해주세요!" },
                    ]}
                    hasFeedback   //입력 창 옆 체크&x 표시
                >
                    <Input.Password value={password} onChange= {onPasswordHandler}/>
                </Form.Item>

                <Form.Item
                    name="confirm_password"
                    label="비밀번호 확인"
                    dependencies={['password']}
                    hasFeedback
                    rules={[
                        {
                            required: true,
                            message: '비밀번호를 입력해주세요!',
                        },
                        ({ getFieldValue }) => ({
                            validator(_, value) {
                                if (!value || getFieldValue('password') === value) {
                                    return Promise.resolve();
                                }

                                return Promise.reject(new Error('입력하신 비밀번호가 일치하지 않습니다.'));
                            },
                        }),
                    ]}
                >
                    <Input.Password/>
                </Form.Item>

                <Form.Item

                    name = "clientName"
                    label="이름"
                    rules = {[{    //입력이 안되면 메세지 뜨는 속성
                        required :true,
                        message : "이름을 입력해주세요!" },
                    ]}
                >
                    <Input value={clientName} onChange= {onClientNameHandler}/>
                </Form.Item>

                <Form.Item

                    name = "nickname"
                    label="닉네임"
                    tooltip="빌리마켓에서 이름 대신 보여집니다. 멋진 닉네임을 지어보세요!"
                    rules = {[{    //입력이 안되면 메세지 뜨는 속성
                        required :true,
                        message : "닉네임을 입력해주세요!" },
                    ]}
                >
                    <Input value={nickname} onChange= {onNickNameHandler}/>
                </Form.Item>

                <Form.Item

                   name = "birthdate"
                   label="생년월일"
                   tooltip="2000-10-18처럼 '-'을 넣어주세요."
                   rules = {[{    //입력이 안되면 메세지 뜨는 속성*/}
                     required :true,
                    message : "생년월일를 입력해주세요!" },
                   ]}
                >
                    <Input value={birthdate} onChange= {onBirthDateHandler} />

                </Form.Item>



                {/* <Form.Item

          name = "clientAddress"
          label="주소"
          rules = {[{    //입력이 안되면 메세지 뜨는 속성
          required :true,
           message : "주소를 입력해주세요!" },
          ]}
          >
          <div>
            <Button icon={<SearchOutlined />} onClick={openPopUp}>주소 검색</Button>
          <div id='popupDom'>
                {popup && (
                    <PopupDom>
                        <Post onClose={closePopUp} />
                    </PopupDom>
                )}
            </div>
            </div>
            <input/> */}
                {/* <Input value={clientAddress} getClientAddress = {getClientAddress}/> */}
                {/* value={clientAddress} onChange= {onClientAddressHandler} */}







                {/* </Form.Item> */}



                <Form.Item

                    name = "phoneNumber"
                    label="전화번호"
                    rules = {[{    //입력이 안되면 메세지 뜨는 속성
                        required :true,
                        message : "전화번호를 입력해주세요!" },
                    ]}

                >
                    <Input type="number" value={phoneNumber} onChange= {onPhoneNumHandler}/>
                </Form.Item>

                <Form.Item

                    name = "email"
                    label="이메일"
                    rules = {[{
                        type: 'email',
                        message : "이메일 양식을 확인해주세요!"
                    },  //입력이 안되면 메세지 뜨는 속성
                        {
                            required :true,
                            message : "이메일을 입력해주세요!"
                        },

                    ]}
                >
                    <Input type="email" value={email} onChange= {onEmailHandler} />
                </Form.Item >


                <Divider>

                </Divider>

                {/* <Form.Item>
            <Button className="authen_Button" type="primary" htmlType="submit">
              휴대폰 본인인증하기
            </Button>
          </Form.Item> */}



                <Form.Item>
                    <Button
                        type="primary" onSubmit={onSubmitHandler} className="signUp_Button"  htmlType="submit">
                        회원가입하기
                    </Button>
                </Form.Item>


            </Form>







        </div>

    )


};


export default SignUpPage;