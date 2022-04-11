import React, {Fragment, useEffect, useMemo, useState, useRef} from "react";
import "./productList.css";
import {Link} from "react-router-dom";
import {Card, Col, Row, Avatar, Button} from 'antd';
import { HeartOutlined } from '@ant-design/icons';
import Meta from "antd/es/card/Meta";
import axios from "axios";


function ProductListPage() {
    let latitude = 0, longitude = 0;
    const page = 0;
    const [itemList, setItemList] = useState([]);

    useEffect(() => {
        if (navigator.geolocation) { // GPS를 지원하면 사용자 local에서 위도 경도 불러오는 부분
            navigator.geolocation.getCurrentPosition(function (position) {
                latitude = position.coords.latitude;
                longitude = position.coords.longitude;
                console.log(latitude, longitude)
                onSubmit()
            }, function (error) {
                console.error(error);
            }, {
                enableHighAccuracy: true,
                maximumAge: 0,
                timeout: Infinity
            });
        } else {
            alert('GPS를 지원하지 않습니다');
        }

        const onSubmit = () => {
            axios({
                    url : 'http://localhost:8080/items?latitude='+latitude+'&longitude='+longitude+'&page='+page ,
                    method : 'get'
                }
            )
                .then((response) => {
                    if (response.status >= 200 && response.status <= 204) {
                        setItemList(response.data.data.content);
                        console.log(itemList);
                    }
                })
                .catch(res => {

                })

        };
    }, []);

    return (
        <Fragment>

                    <div className="card">
                        <Row  gutter={24} justify={"center"}>
                            {itemList.map(item => {
                                return (
                            <Col span={4.5} className="cards">
                                <Card  hoverable
                                      actions={[<HeartOutlined/>]} key={item.itemId} >
                                  <h2> 제목: {item.itemTitle}</h2>
                                    <p>게시일: {item.createDate}</p>
                                    <p>대여료: {item.price}</p>
                                    <p>보증금: {item.deposit}</p>
                                    <p>위치: {item.itemAddress}</p>
                                    <p>대여상태: {item.contractStatus}</p>
                                    {/*이미지 넣는 부분*/}
                                    {/*<img className="phoneImage" src={item.photo_url} />*/}


                                </Card>
                            </Col>
                                )
                            })}
                        </Row>
                    </div>


        </Fragment>
    )
}
export default ProductListPage;