import HeaderPage from "../header/header";
import React, {useEffect, useState} from "react";
import {Badge, Divider, Card, Avatar, Layout, BackTop, Input, Button} from 'antd';
import "./chat.css";

import {useNavigate} from "react-router-dom"
import Meta from "antd/es/card/Meta";
import {SendOutlined} from "@ant-design/icons";



function ChatPage () {



                return (
                    <Layout className="chatPageMain">
                        <header>
                            <HeaderPage></HeaderPage>
                        </header>

                        <Divider></Divider>

                        <Layout className="chatPage_container">
                           <Card className="chatCard"
                           hoverable>

                               <Meta
                                   avatar={<Badge size="small" count={5}><Avatar src=""/> </Badge>}
                                   title={"임용덕"}
                                   description={"안녕하세요"}
                               />
                               <p className="date">4.27</p>
                           </Card>

                            <Card className="chatCard"
                                  hoverable>
                                <Meta
                                    avatar={<Badge size="small" count={7}><Avatar src=""/> </Badge>}
                                    title={"덕스"}
                                    description={"물품 네고좀요"}
                                />
                                <p className="date">4.27</p>
                            </Card>

                            <Card className="chatCard"
                                  hoverable>
                                <Meta
                                    avatar={<Badge size="small" count={99}><Avatar src=""/> </Badge>}
                                    title={"푸들"}
                                    description={"물품 상태 어때여?"}
                                />
                                <p className="date">4.27</p>
                            </Card>

                            <Card className="chatCard"
                                  hoverable>
                                <Meta
                                    avatar={<Badge size="small" count={88}><Avatar src=""/> </Badge>}
                                    title={"룰라"}
                                    description={"ㅎㅇ요"}
                                />
                                <p className="date">4.27</p>
                            </Card>

                            <Card className="chatCard"
                                  hoverable>
                                <Meta
                                    avatar={<Badge size="small" count={46}><Avatar src=""/> </Badge>}
                                    title={"한미니"}
                                    description={"네고 1000원 가능요?"}
                                />
                                <p className="date">4.27</p>
                            </Card>





                        </Layout>

                        <Layout className="chatPageDetails_container">
                            <div className="receiveMessage">
                                <div className = "receiveMessageBox">안녕</div>
                                <div className="timeBox">오후 2:20</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ</div>
                                <div className="timeBox">오후 2:22</div>
                            </div>

                            <div className="receiveMessage">
                                <div className = "receiveMessageBox">안녕ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ</div>
                                <div className="timeBox">오후 2:24</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 호롤로로로로로로로롤로로로로로롤ㄹㄹㄹ</div>
                                <div className="timeBox">오후 2:27</div>
                            </div>

                            <div className="receiveMessage">
                                <div className = "receiveMessageBox">안녕ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ</div>
                                <div className="timeBox">오후 2:30</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ</div>
                                <div className="timeBox">오후 2:34</div>
                            </div>

                            <div className="receiveMessage">
                                <div className = "receiveMessageBox">안녕ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ</div>
                                <div className="timeBox">오후 2:50</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅎ</div>
                                <div className="timeBox">오후 2:55</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅎ</div>
                                <div className="timeBox">오후 2:55</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅎ</div>
                                <div className="timeBox">오후 2:55</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅎ</div>
                                <div className="timeBox">오후 2:55</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅎ</div>
                                <div className="timeBox">오후 2:55</div>
                            </div>

                            <div className="sendMessage">
                                <div className = "sendMessageBox">안녕 ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅎ</div>
                                <div className="timeBox">오후 2:55</div>
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

