import React, {useEffect, useState} from "react";
import {Form, Image, Input, Button, Divider, Tag, Row, Col, Card, Avatar, Rate, Modal} from 'antd';
import "./productViewDetails.css";
import axios from "axios";
import HeaderPage from "../header/header";
import Meta from "antd/es/card/Meta";
import {HeartFilled, HeartOutlined} from "@ant-design/icons";
import {useHistory, useLocation} from "react-router-dom";
import {render} from "react-dom";

import {useNavigate} from "react-router-dom"



function ProductViewDetailsPage () {

    const [itemId,setItemId ] = useState(useLocation().state) ;





    const [productDetailsView, setProductDetailsView] = useState({
        categoryBig: "",
        categoryMiddle: "",
        categorySmall: "",
        contractStatus: "",
        create_date: "",
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
        start_Date: "",
        update_Date: "",
        views: "",


    });

    const [productPicture, setProductPicture] = useState([]);
    const [productThumbnail, setProductThumbnail] = useState("");

    const [productOwnerInfo, setProductOwnerInfo] = useState({
        nickname: "",
        trustPoint: ""
    });

    const [productBasket, setProductBasket] = useState("");

    const [isLike, setIsLike] = useState(false);

    const [productReview, setProductReview] = useState([]);
    const [userReview, setUserReview] = useState([]);

    const [userProduct, setUserProduct] = useState([]);
    const [userProductDetails, setUserProductDetails] = useState([]);

    const [visible, setVisible] = useState(false);

    const [isReviewModalVisible, setIsReviewModalVisible] = useState(false);

    const [isProductModalVisible, setIsProductModalVisible] = useState(false);

    const [last, setLast] = useState(false);

    let [page, setPage] = useState(0);

    const showModal = () => {
        setIsReviewModalVisible(true);
    };

    const showProductModal = () => {
        setIsProductModalVisible(true);
        setPage(0);
        userProductList();
    };

    const handleOk = () => {
        setIsReviewModalVisible(false);
    };

    const handleProductOk = () => {
        setIsProductModalVisible(false);
        setUserProduct([]);

    };

    const handleCancel = () => {
        setIsReviewModalVisible(false);
    };

    const handleProductCancel = () => {
        setIsProductModalVisible(false);
        setUserProduct([]);
    };



    const onClickBasketButton = () => {

        isLike ?
            delBasket(itemId,isLike)
            :addBasket(itemId,isLike)

        console.log(isLike)
    }

    const increasePage = () => {
        setPage(page+1);
        console.log(page)
    };



    const navigate = useNavigate();




    const toProductViewDetailsPage = (itemId) => {
        console.log(itemId)
        setItemId(itemId);
        handleProductCancel();


    }

    const onClickChatButton = () => {navigate("/chat")}   //꼭 안에 감싸기






    // const navigate = useNavigate();


    function ItemProduct (){
        console.log(itemId)
        axios.get("/items/" + itemId , {headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("token")
            }})

            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setProductDetailsView(response.data.data.item)
                    setProductPicture(response.data.data.item.photos)
                    setProductBasket(response.data.data.basketCount)
                    setProductThumbnail(response.data.data.item.photos[0].itemPhoto)
                    setProductOwnerInfo(response.data.data.ownerInfo)
                    setIsLike(response.data.data.isLike)



                    console.log(response.data.data);
                    // console.log(response.data.data.item);
                    // console.log(response.data.data.ownerInfo);
                    // console.log(productBasket);
                    // console.log(productPicture);
                    // console.log(isLike);



                }
            })
            .catch(res => {
                console.log("fail");
            })
    }






    const addBasket = (itemId,isLike) => {
        axios.post("http://localhost:8080/baskets?itemId="+itemId,
            {},{headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}


        ).then(response => {
            console.log("찜하기 등록 성공")
            setIsLike(true)
            setProductBasket(productBasket+1)

        })
            .catch(error => {
                console.log(error.response);
                alert("로그인 후 가능합니다!")

            })
    }


    const delBasket = (itemId,isLike) => {

        axios.delete("http://localhost:8080/baskets?itemId="+itemId,
            {headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }}


        ).then(response => {
            console.log("찜하기 삭제 성공")
            setIsLike(false)
            setProductBasket(productBasket-1)

        })
            .catch(error => {
                console.log(error.response);

            })
    }


    //제품 리뷰
    const productReviewAxios = () => {
        axios.get("/items/"+itemId+"/review?page=0")
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setProductReview(response.data.data.content);
                }
            })
            .catch(res => {
                console.log("fail");
            })
    }

    //판매자 리뷰
    const userReviewAxios = () => {
        axios.get("/items/"+itemId+"/review?page=0")
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setUserReview(response.data.data.content);
                }
            })
            .catch(res => {
                console.log("fail");
            })
    }

    //판매자 물품 보기

    const userProductList = () => {
        // console.log(productDetailsView.ownerId);
        axios.get("/items/owner/"+productDetailsView.ownerId+'?page='+page )
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setUserProduct(response.data.data.content);
                    setLast(response.data.data.last);
                    console.log(response.data.data);
                    console.log(last)
                    console.log(userProductDetails);
                    console.log(page);
                }
            })
            .catch(res => {
                console.log("fail");
            })};

    const userProductListAdd = () => {
        console.log(productDetailsView.ownerId);
        axios.get("/items/owner/"+productDetailsView.ownerId+'?page='+page )
            .then((response) => {
                if (response.status >= 200 && response.status <= 204) {
                    setUserProduct(userProduct.concat(response.data.data.content));
                    setLast(response.data.data.last);
                    console.log(response.data.data);
                    console.log(last)
                    console.log(userProductDetails);
                    console.log(page);
                }
            })
            .catch(res => {
                console.log("fail");
            })};



    useEffect(() => {
        ItemProduct();
        userReviewAxios();
        productReviewAxios();

    }, [itemId]);

    useEffect(() => {
        if (productDetailsView.ownerId != "")
        {userProductListAdd();}
    }, [page]);




    //card 안 meta에는 바로 스트링을 넣을 수 없어서 임의로 상수 생성
    const trustPoint = "믿음 지수 : "

    //날짜 변환
    function format(date) {
        return date.getFullYear() + "년 "
            + (date.getMonth()+1) + "월 "
            + date.getDate() + "일 "
    }



    //대여 상태 변환
    const contractStatusFormat = () => {
        if (productDetailsView.contractStatus === "RENTAL") {
            return  "대여중";
        }
        else if (productDetailsView.contractStatus === "RESERVATION"){
            return "예약중" ;

        }

        else {
            return "대여가능" ;

        }


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
                    className="ImageGroup"
                    preview={{visible: false}}
                    src={productThumbnail}
                    onClick={() => setVisible(true)}
                />
                <div style={{display: 'none'}}>
                    <Image.PreviewGroup preview={{visible, onVisibleChange: vis => setVisible(vis)}}>
                        {productPicture.map(picture => {
                                return (
                                    <Image src={picture.itemPhoto}/>
                                )
                            }
                        )}
                    </Image.PreviewGroup>
                </div>

                <Divider></Divider>

                <p>물품 내용 : </p>
                <p>{productDetailsView.itemContent}</p>
                <Divider></Divider>
                <p>등록 날짜 : {format(new Date(productDetailsView.create_date))}</p>
                <p>품질 : {productDetailsView.itemQuality}</p>
                <p>대여 상태 : {contractStatusFormat("")}</p>
                <p>대여 금액 : {productDetailsView.price}원</p>
                <p>보증금 금액 : {productDetailsView.deposit}원</p>
                <p>거래 지역 : {productDetailsView.itemAddress}</p>
                <p>찜한 수 : {productBasket}회</p>
                <Card className="productBasketCard">
                    해당 물품 찜하기
                    &nbsp;
                    {isLike ?
                        <HeartFilled className="heartFilledButton"
                                     onClick={ () => {onClickBasketButton()}} /> :
                        <HeartOutlined className="heartOutButton"
                                       onClick={ () => {onClickBasketButton()}} />}


                </Card>


                <Card
                    className="ownerInfoCard">
                    <Meta
                        avatar={<Avatar src="https://joeschmoe.io/api/v1/random"/>}
                        title={productOwnerInfo.nickname}
                        description={trustPoint + productOwnerInfo.trustPoint}
                    />

                    <Button className="chattingButton"
                            onClick={onClickChatButton}>
                        채팅하기
                    </Button>
                    <Button  className="userReviewModal" onClick={showModal}>
                        판매자 리뷰 보기
                    </Button>
                    <Modal title= {"<"+productOwnerInfo.nickname + "님에 대한 리뷰>"}
                           visible={isReviewModalVisible}
                           onOk={handleOk}
                           onCancel={handleCancel}
                           footer={[
                               <Button onClick={handleOk}>
                                   닫기
                               </Button> ]}>
                        {userReview.map(review => {
                                return (
                                    <Card className="reviewCardChild" title={review.reviewTitle}>
                                        <Rate className="rate" disabled defaultValue={review.reviewScore} />
                                        <p className="reviewContent">{review.reviewContent}</p>
                                        <p>리뷰 작성자 : {review.writer}</p>
                                        <p>작성일자 : {format(new Date(review.createDate))}</p>
                                    </Card>


                                )
                            }
                        )}
                    </Modal>

                    <Button  className="userProductModal" onClick={ () => {showProductModal()
                        }}>
                        판매 물품 보기
                    </Button>
                    <Modal
                        title= {"<"+productOwnerInfo.nickname + "님이 판매중인 물품>"}
                        visible={isProductModalVisible}
                        onOk={handleProductOk}
                        onCancel={handleProductCancel}
                        footer={[
                            <Button onClick={handleProductOk}>
                                닫기
                            </Button> ]}>
                        {userProduct.map(product => {
                                return (
                                    <Card className="userProductCardChild"
                                          title={product.itemTitle}
                                          hoverable
                                          onClick={ () => {toProductViewDetailsPage(product.itemId) }}>
                                        <img className="productImage" src={product.itemPhoto}/>
                                        <p className="createDate">작성일자 : {format(new Date(product.createDate))}</p>

                                    </Card>
                                )
                            }
                        )}

                        {last === true?
                            <Button disabled>더보기</Button>:
                            <Button onClick={increasePage}>더보기</Button>
                        }

                    </Modal>

                </Card>


                <Divider> </Divider>

                <Form.Item

                    className="productReviews"
                >
                    <Card
                        className="reviewCard"
                        title="<제품 리뷰>">
                        {productReview.map(review => {
                                return (
                                    <Card className="reviewCardChild" title={review.reviewTitle}>
                                        <Rate className="rate" disabled defaultValue={review.reviewScore} />
                                        <p className="reviewContent">{review.reviewContent}</p>
                                        <p>리뷰 작성자 : {review.writer}</p>
                                        <p>작성일자 : {format(new Date(review.createDate))}</p>
                                    </Card>


                                )
                            }
                        )}


                    </Card>
                </Form.Item>


            </Form>

            )}
        </div>

    )



};


export default ProductViewDetailsPage;

