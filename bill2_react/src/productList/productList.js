import React, {Fragment, useEffect, useMemo, useState, useRef} from "react";
import "./productList.css";
import {Link, useNavigate} from "react-router-dom";
import {Card, Col, Row, Avatar, Button, Badge} from 'antd';
import { HeartOutlined,HeartFilled, BorderOutlined } from '@ant-design/icons';
import Meta from "antd/es/card/Meta";
import axios from "axios";
import moment from "moment";
import ProductViewDetailsPage from "../productViewDetails/productViewDetails";
import {useSelector, useDispatch, batch} from "react-redux";

function ProductListPage() {
    const dispatchLocation = useDispatch();
    let [latitude, setLatitude] = useState(0);
    let [longitude, setLongitude] = useState(0);

    let [page, setPage] = useState(0);
    const [last, setLast] = useState(false);
    const [itemList, setItemList] = useState([]);
    // const [createDate, setCreateDate] = useState([moment().format('YYYY 년 MM월 DD일 HH시')]);

    const navigate = useNavigate();


    const toProductViewDetailsPage = (itemId) => {
        navigate("/ProductViewDetails" , {state : itemId});
        }
    const increasePage = () => {
        setPage(++page);

        onSubmit(latitude, longitude)
    };

    useEffect(() => {

        if (navigator.geolocation) { // GPS를 지원하면 사용자 local에서 위도 경도 불러오는 부분
            navigator.geolocation.getCurrentPosition(function (position) {
                setLatitude(position.coords.latitude);
                setLongitude(position.coords.longitude);

                onSubmit(position.coords.latitude, position.coords.longitude)


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

        }, []);

    const onSubmit = (latitude, longitude) => {
        axios.get( 'http://localhost:8080/items?latitude='+latitude+'&longitude='+longitude+'&page='+page ,
            {headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}
            ,
        )
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    console.log(response.data.data.pageable.pageNumber)

                    setItemList(itemList.concat(response.data.data.content));
                    setLast(response.data.data.last);


                    // batch(() => {
                        dispatchLocation({type: "LATITUDE", payload: latitude})
                        dispatchLocation({type: "LONGITUDE", payload: longitude})


                    // })


                }
            })
            .catch(res => {

            })

    };


    const addBasket = (itemId,isLike) => { //찜하기가 안된상태에서 찜하기를 눌렀을때

        axios.post("http://localhost:8080/baskets?itemId="+itemId,
            {},{headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}


        ).then(response => {
            onSubmit(latitude, longitude);
        })
            .catch(error => {
                console.log(error.response);
            })
    }

    const delBasket = (itemId,isLike) => { //찜하기가 안된상태에서 찜하기를 눌렀을때

        axios.delete("http://localhost:8080/baskets?itemId="+itemId,
            {headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}


        ).then(response => {
            onSubmit(latitude, longitude);
        })
            .catch(error => {
                console.log(error.response);
            })
    }
    function format  (date) {

        return date.getFullYear() + "년 " + date.getMonth() + "월 " + date.getDate() + "일 " + date.getHours() + "시" ;

    }


    return (
        <Fragment>

            <div className="row">

                <Row  gutter={24}>

                    {itemList.map(item => {


                        return (

                            <Col span={4.5} className="col">
                                <Card  hoverable
                                       key={item.itemId} className="cards">

                                    {item.isLike?

                                        <HeartFilled onClick ={()=>{delBasket(item.itemId,item.isLike)}}>

                                        </HeartFilled> :

                                        <HeartOutlined onClick ={()=>addBasket(item.itemId,item.isLike)}>

                                        </HeartOutlined>
                                    }


                                    <span> <h2 className="title"
                                               onClick={ () => {toProductViewDetailsPage(item.itemId) }}>
                                        제목: {item.itemTitle}</h2>

                                        {item.contractStatus === "GENERAL" ?
                                            <p ></p>:
                                            item.contractStatus === "RENTAL" ?
                                                <p className="rental">대여중</p>:
                                                <p className="reservation">예약중</p>}</span>
                                    <p>게시일: {format(new Date(item.createDate))}</p>
                                    <p>대여료: {item.price}</p>
                                    <p>보증금: {item.deposit}</p>
                                    <p>아이템 위치: {item.itemAddress}</p>
                                    {/*<p>대여상태: {item.contractStatus}</p>*/}

                                    <img className="phoneImage" src={item.itemPhoto}/>


                                </Card>
                            </Col>
                        )
                    })}
                </Row>

            </div>
            {last === true?
            <Button disabled>더보기</Button>:
            <Button onClick={increasePage}>더보기</Button>
            }



        </Fragment>

    )
}
export default ProductListPage;

