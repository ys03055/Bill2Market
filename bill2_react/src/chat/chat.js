import HeaderPage from "../header/header";
import React, {useEffect, useRef, useState} from "react";
import {Badge, Divider, Card, Avatar, Layout, BackTop, Input, Button, Image} from 'antd';
import "./chat.css";

import {useLocation, useNavigate} from "react-router-dom"
import Meta from "antd/es/card/Meta";
import {SendOutlined} from "@ant-design/icons";
import axios from "axios";
import SockJS from "sockjs-client";
import Stomp from "stompjs";






function ChatPage () {


    const [chatList, setChatList] = useState([]);
    const [key,setKey] = useState([]);
    const [test2,setTest2] = useState("");
    const [itemId,setItemId ] = useState(useLocation().state) ;
    const [itemPic, setItemPic] = useState("");
    const [itemTitle, setItemTitle] = useState("");
    const [itemPrice, setItemPrice] = useState("");
    const [itemDeposit, setItemDeposit] = useState("");
    const [sockChatId, setSockChatId] = useState("");
    const [lastMessage,  setLastMessage] = useState("");



    // let sock = new SockJS('http://localhost:8080/bill2-ws')
    // let client = Stomp.over(sock);




    const AWS = require("aws-sdk");


    const s3 = new AWS.S3 ({
        accessKeyId: "AKIA5JDRDAAYWXFYKPXK",
        secretAccessKey: "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",
    });



    const keyFunc = (url) => {

        let keyArray = url.split("/");
        let key = keyArray[3] + "/" + keyArray[4];
        const params = {
            Bucket: "bill2market",
            Key: key.toString()
        }
        s3.getObject(params, (err, data) => {
            if (err) {
                throw err;
            }
            let messageLast = data.Body.toString('utf-8');
            let messageLast2 = messageLast.split("\n")
            let messageLength = messageLast2.length-2;
            setTest2(messageLast2[messageLength]);
            console.log(messageLast2[messageLength]);


        });
        return test2
        // return  chatMessageType(test2);


    }


    let chatTime = test2.split(":")[0]+ ":" + test2.split(":")[1];
    let chatMessageSplitType = test2.split(" ")[2];
    let chatMessageSplitContent = test2.split(" ")[4];


    const chatMessageType = () => {
        if (chatMessageSplitType === "0") {
            setLastMessage(chatMessageSplitContent)
            console.log(lastMessage);
            return lastMessage;
        }
        else if (chatMessageSplitType === "1") {
            setLastMessage("이미지 파일을 보냈습니다.");
            return lastMessage;

        }
        else if (chatMessageSplitType === "2") {
            setLastMessage("거래가 요청되었습니다.");
            return lastMessage;

        }
        else if (chatMessageSplitType === "3") {
            setLastMessage("거래가 수락되었습니다.");
            return lastMessage;

        }
        else if (chatMessageSplitType === "4") {
            setLastMessage("계약기간 만료가 임박했습니다.");
            console.log(lastMessage);
            return lastMessage;

        }
        else if (chatMessageSplitType === "5") {
            setLastMessage("계약기간이 만료되었습니다.");
            return lastMessage;

        }
        else {
            setLastMessage("거래가 종료되었습니다.");
            return lastMessage;

        }
    }


    // 0 : 일반 텍스트
    //
    // 1 : 이미지파일의 S3 링크
    //
    // 2 : 거래요청 메세지 (contract_id, start_date, end_date 저장)
    //
    // 3 : 거래수락 메세지 (contract_id)
    //
    // 4 : 계약기간 만료 임박 알림 메세지 (contract_id)
    //
    // 5 : 계약기간 만료 알림 메세지 (contract_id)
    //
    // 6 : 거래종료 메세지 (contract_id)



    const chatInfo = () => {
        axios.get("/chats/client", {
            headers: {Authorization: 'Bearer ' + sessionStorage.getItem("token")}})
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setChatList(response.data.data);
                    // setSockChatId(chatList.chatId);
                    console.log(chatList);
                }
            })
            .catch(res => {
                console.log("fail");
            })};



    const ChatItemProduct  = () => {
        console.log(itemId);
        axios.get("/items/" + itemId , {headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("token")
            }})

            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setItemPic(response.data.data.item.photos[2].itemPhoto);
                    setItemTitle(response.data.data.item.itemTitle);
                    setItemPrice(response.data.data.item.price);
                    setItemDeposit(response.data.data.item.deposit);

                }
            })
            .catch(res => {
                console.log("fail");
            })
    }




    useEffect(() => {

        chatInfo ();



    }, []);



    useEffect(() => {
        ChatItemProduct ();

    }, [itemId]);



    // useEffect(() => {
    //     client.connect({}, () =>{
    //         console.log("소켓 서버 연결 성공")
    //         client.subscribe("/sub/chat/"+chatList.chatId);
    //         client.send(` /pub/chat/message`);
    //
    //     })
    //    client.disconnect({}, () =>{
    //        console.log("소켓 서버 연결 해제 성공")
    //    })
    //
    // }, [])




    // // Create Message
    //
    // client.send(`/app/chat/userId}`,{},JSON.stringify(userId))
    //
    // client.subscribe('/queue/addChatToClient/'+auth.user.id, function(messageDTO){
    //     const messagedto = JSON.parse(messageDTO.body)
    // })






    return (
        <Layout className="chatPageMain">
            <header>
                <HeaderPage></HeaderPage>
            </header>

            <Divider></Divider>

            <Layout className="chatPage_container">
                {chatList.map(chat => {
                        return (
                            <Card className="chatCard"
                                  hoverable>

                                <Meta
                                    avatar={<Badge size="small" count={111}><Avatar src=""/> </Badge>}
                                    title={chat.nickname}
                                    description= {keyFunc(chat.fileName)}
                                />
                                <p className="date">{chatTime}</p>
                            </Card>
                        )
                    }
                )}




            </Layout>

            <Layout className="chatPageDetails_container">
                <Card className="chatProductCard">
                    <Image className="chatItemImage" src={itemPic}/>
                    <p className="chatItemTitle">{itemTitle}</p>
                    <p className="chatItemPrice">대여료 : {itemPrice}원</p>
                    <p className="chatItemDeposit">보증금 : {itemDeposit}원</p>
                </Card>

                <div className="receiveMessage">
                    <div className = "receiveMessageBox">안녕</div>
                    <div className="timeBox">오후 2:20</div>
                </div>

                <div className="sendMessage">
                    <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ</div>
                    <div className="timeBox">오후 2:22</div>
                </div>



                {/*<BackTop>*/}
                {/*    <div className="backTopButton">처음 대화 보기</div>*/}
                {/*</BackTop>*/}
                <Input className="inputMessage"
                       placeholder="메세지를 입력하세요.">

                </Input>
                <Button className="chatSendButton">
                    <SendOutlined />
                </Button>

            </Layout>




        </Layout>
    )







};


export default ChatPage;
