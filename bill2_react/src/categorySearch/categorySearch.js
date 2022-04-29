import React, {Fragment, useEffect, useState} from "react";
import axios from "axios";
import "./categorySearch.css";
// import store from "../store";
import {Button, Card, Col, Row} from "antd";
import {HeartFilled, HeartOutlined} from "@ant-design/icons";
import HeaderPage from "../header/header";
import ProductListPage from "../productList/productList";
import { useSelector, useDispatch } from "react-redux";

function CategorySearchPage()  {
    const value = useSelector(state => state.value)
    // const label = useSelector(state => state.label)
    // console.log(label);

    let [latitude, setLatitude] = useState(0);
    let [longitude, setLongitude] = useState(0);

    const [orderType, setOrderType] = useState('DISTANCE');
    const page = 0;
    const [categoryitemList, setCategoryitemList] = useState([]);
    // const [createDate, setCreateDate] = useState([moment().format('YYYY 년 MM월 DD일 HH시')]);

    useEffect(() => {
        if (navigator.geolocation) { // GPS를 지원하면 사용자 local에서 위도 경도 불러오는 부분
            navigator.geolocation.getCurrentPosition(function (position) {
                setLatitude(position.coords.latitude);
                setLongitude(position.coords.longitude);
                console.log(position.coords.latitude)
                console.log(position.coords.longitude)
                onCategory(position.coords.latitude, position.coords.longitude)

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
    const onCategory = (latitude, longitude) => {
        axios.get( 'http://localhost:8080/items/search-category?categoryBig='+value[0]+'&categoryMiddle='+value[1]+'&categorySmall='+value[2]
            +'&latitude='+latitude+'&longitude='+longitude+'&orderType='+orderType+'&page='+page,
            {headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}
            ,
        )
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    console.log(response);
                    setCategoryitemList(response.data.data.content);



                }
            })
            .catch(res => {

            })

    };
    //찜하기
    console.log(categoryitemList);


    const addBasket2 = (itemId,isLike) => { //찜하기가 안된상태에서 찜하기를 눌렀을때

        axios.post("http://localhost:8080/baskets?itemId="+itemId,
            {},{headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}


        ).then(response => {


            onCategory(latitude, longitude);

        })
            .catch(error => {

                console.log(error.response);
            })


    }
    const delBasket2 = (itemId,isLike) => { //찜하기가 안된상태에서 찜하기를 눌렀을때

        axios.delete("http://localhost:8080/baskets?itemId="+itemId,
            {headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}


        ).then(response => {


            onCategory(latitude, longitude);


        })
            .catch(error => {

                console.log(error.response);
            })


    }


    function format  (date) {

        return date.getFullYear() + "년 " + date.getMonth() + "월 " + date.getDate() + "일 " + date.getHours() + "시" ;

    }
    const changeOrderType = () => {
        setOrderType('DISTANCE')
        onCategory(latitude, longitude)
    }
    const changeOrderExType = () => {
        setOrderType('EXPENSIVE')
        onCategory(latitude, longitude)
    }
    const changeOrderInExType = () => {
        setOrderType('INEXPENSIVE')
        onCategory(latitude, longitude)
    }
    const changeOrderReType = () => {
        setOrderType('RECENTLY')
        onCategory(latitude, longitude)
    }

    return (
        <Fragment>
            <title>빌리마켓</title>
            <body>
            <header>
                <HeaderPage></HeaderPage>

            </header>

            <main>
                <h3>카테고리 물품 보여주기</h3>
                <div className="row">
                    <div className="itemSearch">

                        <div className="itemSearchResult">
                            <span></span>
                            의 검색결과
                            <span>  개</span>
                        </div>

                        <div className="itemSort">
                            <a className="accuracy" onClick={changeOrderType}>거리순</a>
                            <a className="recent" onClick={changeOrderReType}>최신순</a>
                            <a className="lowPrice" onClick={changeOrderExType}>저가순</a>
                            <a className="highPrice" onClick={changeOrderInExType}>고가순</a>
                        </div>

                    </div>
                    <Row  gutter={24}>

                        {categoryitemList.map(categoryitem => {


                            return (

                                <Col span={4.5} className="col">
                                    <Card  hoverable
                                           key={categoryitem.itemId} className="cards">


                                        {categoryitem.isLike?

                                            <HeartFilled onClick ={()=>{delBasket2(categoryitem.itemId,categoryitem.isLike)
                                            }}></HeartFilled>:
                                            <HeartOutlined onClick ={()=>addBasket2(categoryitem.itemId,categoryitem.isLike)}></HeartOutlined>
                                        }

                                        <span> <h2 className="title"> 제목: {categoryitem.itemTitle}</h2>
                                            {categoryitem.contractStatus === "GENERAL" ?
                                                <p ></p>:
                                                categoryitem.contractStatus === "RENTAL" ?
                                                    <p className="rental">대여중</p>:
                                                    <p className="reservation">예약중</p>}</span>
                                        <p>게시일: {format(new Date(categoryitem.createDate))}</p>
                                        <p>대여료: {categoryitem.price}</p>
                                        <p>보증금: {categoryitem.deposit}</p>
                                        <p>아이템 위치: {categoryitem.itemAddress}</p>
                                        {/*<p>대여상태: {item.contractStatus}</p>*/}

                                        <img className="phoneImage" src={categoryitem.itemPhoto}/>



                                    </Card>
                                </Col>
                            )
                        })}
                    </Row>

                </div>
                <Button justify={'center'}>더보기</Button>
            </main>

            </body>



        </Fragment>
    )


}

;
export default CategorySearchPage;
