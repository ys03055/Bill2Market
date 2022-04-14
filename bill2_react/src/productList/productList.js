import React, {Fragment, useEffect, useMemo, useState, useRef} from "react";
import "./productList.css";
import {Link} from "react-router-dom";
import {Card, Col, Row, Avatar, Button, Badge} from 'antd';
import { HeartOutlined,HeartFilled, BorderOutlined } from '@ant-design/icons';
import Meta from "antd/es/card/Meta";
import axios from "axios";
import moment from "moment";

function ProductListPage() {
    let latitude = 0, longitude = 0;
    const page = 0;
    const [itemList, setItemList] = useState([]);
    // const [createDate, setCreateDate] = useState([moment().format('YYYY 년 MM월 DD일 HH시')]);

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
                        console.log(response.data.data.content);
                        console.log(itemList.createDate);
                        // setCreateDate(itemList.createDate);
                        // console.log(createDate);

                    }
                })
                .catch(res => {

                })

        };

    }, []);

    //찜하기
    const [liked, setLiked] = useState(false); //
    const addBasket = () => { //찜하기가 안된상태에서 찜하기를 눌렀을때

        axios.post("http://localhost:8080/baskets", {
            itemId : itemList.itemId,
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
            }
        }).then(response => {
            setLiked(true);
            console.log(liked);


        })
            .catch(error => {

                console.log(liked);
            })


    }
    const delBasket = () => {

        axios.delete("/baskets", {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem("token")
            }
        }).then(response => {
            setLiked(false);

        })
            .catch(error => {

            })


    }
    // useEffect(()=>{
    //
    //
    //    addBasket();
    //    delBasket();
    //
    // }
    // )
    const wishAddHandler = () => {
        setLiked(!liked)
    }
    function format  (date) {

        return date.getFullYear() + "년 " + date.getMonth() + "월 " + date.getDate() + "일 " + date.getHours() + "시" ;

    }


    return (
        <Fragment>

            <div className="row">
                <Row  gutter={24}>

                    {itemList.map(item => {

                        // console.log(item.createDate.format('YYYY 년 MM월 DD일 HH시'));
                        console.log(item.createDate);
                        console.log(format(new Date(item.createDate)));
                        return (

                            <Col span={4.5} className="col">
                                <Card  hoverable
                                       key={item.itemId} className="cards">

                                    <span> <HeartOutlined onClick ={addBasket}></HeartOutlined> </span>
                                   <span> <h2 className="title"> 제목: {item.itemTitle}</h2>
                                    {item.contractStatus === "GENERAL" ?
                                        <p ></p>:
                                     item.contractStatus === "RENTAL" ?
                                        <p className="rental">대여중</p>:
                                        <p className="reservation">예약중</p>}</span>
                                    <p>게시일: {format(new Date(item.createDate))}</p>
                                    <p>대여료: {item.price}</p>
                                    <p>보증금: {item.deposit}</p>
                                    <p>아이템 위치: {item.itemAddress}</p>
                                    <p>대여상태: {item.contractStatus}</p>

                                    <img className="phoneImage" src={item.itemPhoto}/>



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