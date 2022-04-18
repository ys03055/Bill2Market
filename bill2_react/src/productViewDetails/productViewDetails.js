import React, {useEffect, useState} from "react";
import {Form, Image, Input, Button, Divider, Tag, Row, Col, Card, Avatar} from 'antd';
import "./productViewDetails.css";
import axios from "axios";
import HeaderPage from "../header/header";
import Meta from "antd/es/card/Meta";
import {HeartFilled, HeartOutlined} from "@ant-design/icons";
import {useLocation} from "react-router-dom";

// import {useNavigate} from "react-router-dom"



function ProductViewDetailsPage () {

    const [itemId,setItemId ] = useState(useLocation().state) ;





    const [productDetailsView, setProductDetailsView] = useState({
        categoryBig: "",
        categoryMiddle: "",
        categorySmall: "",
        contractStatus: "",
        createDate: "",
        deposit: "",
        endDate: "",
        itemAddress: "",
        itemContent: "",
        itemLatitude: "",
        itemLongitude: "",
        itemQuality: "",
        itemTitle: "",
        ownerId: "",
        price: "",
        startDate: "",
        updateDate: "",
        views: "",


    });

    const [productPicture, setProductPicture] = useState([]);
    const [productThumbnail, setProductThumbnail] = useState("");

    const [productOwnerInfo, setProductOwnerInfo] = useState({
        nickname: "",
        trustPoint: ""
    });

    const [productBasket, setProductBasket] = useState("");

    // const [productReview, setProductReview] = useState({
    //     reviewTitle: "",
    //     reviewContent: ""
    // });

//
//

    const [visible, setVisible] = useState(false);

    const [onClickBasketButton, setOnClickBasketButton] = useState(false);

    // const navigate = useNavigate();

    //조회수 post 해서 1씩 올리는 방식 구현 요청
    function ItemProduct (){
        console.log(itemId)
        axios.get("/items/" + itemId)
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setProductDetailsView(response.data.data.item)
                    setProductPicture(response.data.data.item.photos)
                    setProductBasket(response.data.data.basketCount)
                    setProductThumbnail(response.data.data.item.photos[0].itemPhoto)
                    setProductOwnerInfo(response.data.data.ownerInfo)


                    console.log(response.data.data);
                    console.log(response.data.data.item);
                    console.log(response.data.data.ownerInfo);
                    console.log(productBasket);
                    console.log(productPicture);


                }
            })
            .catch(res => {
                console.log("fail");
            })
    }
    useEffect(() => {

        ItemProduct();

    }, []);





    const addBasket = (itemId) => {
        axios.post("http://localhost:8080/baskets?itemId="+itemId,
            {},{headers: {
                    Authorization: 'Bearer ' + localStorage.getItem("token")
                }}


        ).then(response => {
            console.log("찜하기 등록 성공")

        })
            .catch(error => {
                console.log(error.response);

            })
    }


    const delBasket = (itemId) => {

        axios.delete("http://localhost:8080/baskets?itemId="+itemId,
            {headers: {
                    Authorization: 'Bearer ' + localStorage.getItem("token")
                }}


        ).then(response => {
            console.log("찜하기 삭제 성공")
        })
            .catch(error => {
                console.log(error.response);

            })
    }

    const onClickBasket = () => {
        if (onClickBasketButton == false) {
            addBasket(itemId);
            setOnClickBasketButton(true);

        }
        else {
            delBasket(itemId);
            setOnClickBasketButton(false);

        }
    }


    // useEffect(() => {
    //     axios.get("/items/"+ 8 +"/review")
    //         .then((response) => {
    //             if (response.status >= 200 && response.status <= 204) {
    //                 console.log(response.data.data);
    //
    //
    //             }
    //         })
    //         .catch(res => {
    //             console.log("fail");
    //         })
    // }, []);



    //card 안 meta에는 바로 스트링을 넣을 수 없어서 임의로 상수 생성
    const trustPoint = "믿음 지수 : "

    //날짜 변환
    function format(date) {
        return date.getFullYear() + "년 "
            + date.getMonth() + "월 "
            + date.getDate() + "일 "
    }


    return (
        <div>

            <header>
                <HeaderPage></HeaderPage>
            </header>


            <Form className="productViewDetailsPage_container"

            >
                <h1 className="productViewDetails_Header">{productDetailsView.itemTitle}</h1>
                <p className="productView">조회수: {productDetailsView.views}회</p>
                <Divider></Divider>

                <Image
                    preview={{ visible: false }}
                    width={620} height={400}
                    src= {productThumbnail}
                    onClick={() => setVisible(true)}
                />
                <div style={{ display: 'none' }}>
                    <Image.PreviewGroup preview={{ visible, onVisibleChange: vis => setVisible(vis) }}>
                        {productPicture.map(picture => {
                            return (
                                <Image src= {picture.itemPhoto}/>
                            ) }
                        )}
                    </Image.PreviewGroup>
                </div>

                <Divider></Divider>
                <p>물품 내용 : </p>
                <p>{productDetailsView.itemContent}</p>
                <Divider></Divider>
                <p>등록 날짜 : {format(new Date(productDetailsView.createDate))}</p>
                <p>품질 : {productDetailsView.itemQuality}</p>
                <p>대여 상태 : {productDetailsView.contractStatus}</p>
                <p>대여 금액 : {productDetailsView.price}원</p>
                <p>보증금 금액 : {productDetailsView.deposit}원</p>
                <p>거래 지역 : {productDetailsView.itemAddress}</p>
                <p>찜한 수 : {productBasket}회</p>


                <Card className="productBasketCard"
                      hoverable
                      onClick={onClickBasket}>
                    해당 물품 찜하기
                    &nbsp;
                    {onClickBasketButton ?
                        <HeartFilled/>:
                        <HeartOutlined/>



                    }


                </Card>



                <Card
                    className="ownerInfoCard"
                    actions={[<HeartOutlined/>]}>
                    <Meta
                        avatar={<Avatar src="https://joeschmoe.io/api/v1/random"/>}
                        title={productOwnerInfo.nickname}
                        description={trustPoint + productOwnerInfo.trustPoint}
                    />

                    <Button className="chattingButton">
                        채팅하기
                    </Button>

                </Card>


                <Divider> </Divider>

                <Form.Item

                    className="productReviews"
                >
                    <p> 제품 리뷰 제목: </p>
                    <p> 제품 리뷰 내용: </p>

                </Form.Item>


            </Form>

            )}
        </div>

    )



};


export default ProductViewDetailsPage;

