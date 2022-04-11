import {Form, Input, Button, DatePicker, Upload, Modal} from 'antd'
import React, {useState} from "react"
import moment from "moment";
import "./write.css";
import HeaderPage from "../header/header";
import axios from "axios";
import ImgCrop from "antd-img-crop";
import DaumPostCode from "react-daum-postcode";
import {UploadOutlined} from "@ant-design/icons";




function WritePage() {
    //카테고리
    // const [big, setBig] = useState();
    // const [middle, setMiddle] = useState();
    // const [small, setSmall] = useState();
    //제목, 내용
    const [title, setTitle] = useState();
    const [content, setContent] = useState();
    //대여금, 보증금
    const [price, setPrice] = useState();
    const [deposit, setDeposit] = useState();
    //계약날짜
    const [startDate, setStartDate] = useState(moment());
    const [endDate, setEndDate] = useState(moment());
    //물품 상태
    const [quality, setQuality] = useState();
    //물품 이미지
    const [imageList, setImageList] = useState([]);
    //주소
    const [address, setAddress] = useState();
    //모달창 키고 끄기
    const [isModalVisible, setIsModalVisible] = useState(false);

    const setTitleChange = (e) => {  // <- input 값으로 text 변경 함수
        setTitle(e.target.value);
    };
    const setContentChange = (e) => {  // <- input 값으로 text 변경 함수
        setContent(e.target.value);
    };
    const setPriceChange = (e) => {  // <- input 값으로 text 변경 함수
        setPrice(e.target.value);
    };
    const setDepositChange = (e) => {  // <- input 값으로 text 변경 함수
        setDeposit(e.target.value);
    };

    const onPictureChange = ({ fileList: newFileList }) => {
        setImageList(newFileList);
    };

    // const onPictureChange = ({ fileList: newFileList }) => {
    //
    // };


    const getAddress = (data) => {

        let fullAddress = data.address;
        let extraAddress = '';

        if (data.addressType === 'R') {
            if (data.bname !== '') {
                extraAddress += data.bname;
            }
            fullAddress += (extraAddress !== '' ? ` (${extraAddress})` : '');
            setAddress(fullAddress)
        }
        console.log(fullAddress)
    }

    const itemQualityHandleChange = (e) => {
        setQuality(e.target.value)
    }

    const showModal = () => {
        if(address == null) {
            setIsModalVisible(true)
        }
        else {
            setIsModalVisible(false);
        }

    };

    const modalClose = () => {
        setIsModalVisible(false);
    };

    const onPreview = async file => {
        let src = file.url;
        if (!src) {
            src = await new Promise(resolve => {
                const reader = new FileReader();
                reader.readAsDataURL(file.originFileObj);
                reader.onload = () => resolve(reader.result);
            });
        }
        const image = new Image();
        image.src = src;
        const imgWindow = window.open(src);
        imgWindow.document.write(image.outerHTML);

    };

    // const pictureAlert = () =>{
    //     if (imageList.length === 0){
    //         alert("적어도 한장 이상의 사진을 게시해주세요!")
    //     }
    //     console.log(imageList)
    // }

    const disabledDate = (startDate) =>{
        return startDate && startDate < moment().startOf('day');
    }

    const disabledEndDate = (endDate) =>{
        return endDate && endDate < startDate;
    }

    const test = () => {
        // console.log(big)
        // console.log(middle)
        // console.log(small)
        console.log(title)
        console.log(content)
        console.log(price)
        console.log(deposit)
        console.log(address)
        console.log(imageList)
        console.log(quality)
        console.log(startDate.format("YYYY-MM-DD hh-mm-ss"))
        console.log(endDate.format("YYYY-MM-DD hh-mm-ss"))
    }




    const onSubmit = () => {
        const token = localStorage.getItem("token")
        const data = {
             itemTitle : title,
             itemContent : content,
             price : price,
             deposit : deposit,
             // startDate : startDate.format("YYYY-MM-DD hh-mm-ss"),
             // endDate : endDate.format("YYYY-MM-DD hh-mm-ss"),
             itemAddress : address,
             itemQuality : quality,
             contractStatus : "RENTAL"
        }
        const formData = new FormData();
        formData.append('item', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
        imageList.forEach(image => formData.append("itemPhoto", image.originFileObj))


        // formData.append("itemPhoto", imageList[0])

        console.log(localStorage.getItem("token"))
        const option = {
            url : '/items',
            method: 'POST',
            headers: {
                Authorization: 'Bearer ' + token,
                'Content-Type': 'multipart/form-data'
            },
            data: formData

        }

        axios(option)
            .then(res=>{
                console.log(res.data)
            }).catch(res=>{
            alert(res.response.data.message);
        });
    };

    //     axios.post("/items", {
    //         headers: {
    //             Authorization: 'Bearer ' + token,
    //             'Content-Type': 'multipart/form-data'
    //         },data: formData
    //     })
    //         .then(res=>{
    //             console.log(res.data)
    //         }).catch(res=>{
    //         alert(res.response.data.message);
    //     });
    // };

    const onSubmitFailed = (errorInfo) => {  //exception 발생 시 에러 원인 불러오기
        console.log("물품 등록에 실패했습니다",errorInfo);  //서버로 요청하는 값
    };

    return (
        <div>
            <header><HeaderPage/></header>


            <Form className="write_container"
                  onFinish={onSubmit}
                  onFinishFailed={onSubmitFailed}
            >

                <h1 className="h1"> 물품 빌려 주기 </h1>

                <div className="write_wrap">
                    <span>제목</span>
                    <Form.Item
                        className="title"
                        name = "title"
                        rules={[{required: true, message: '제목을 입력해주세요!'}]}
                        >
                        <Input
                            placeholder="제목을 입력해주세요"
                            onChange={setTitleChange}
                        />
                    </Form.Item>
                </div>


                <div className="write_wrap">
                    <span>물품 정보</span>

                    <Form.Item
                        className="content"
                        name = "content"
                        rules={[{required: true, message: '물품 정보을 입력해주세요!'}]}>

                        <Input.TextArea
                            rows={5}
                            placeholder="물품 정보를 입력해주세요"
                            size="large"
                            showCount
                            maxLength={200}
                            onChange = {setContentChange}
                        />
                    </Form.Item>
                </div>


                <div className="write_wrap">
                    <span>대여금</span>
                    <Form.Item className="write_money"
                               name = "price"
                               rules={[{required: true, message: '대여금을 입력해주세요!'}]}>
                        <Input
                            type="number" placeholder="대여금을 입력해주세요"
                            onChange = {setPriceChange}
                        />
                    </Form.Item>
                </div>

                <div className="write_wrap">
                    <span>보증금</span>
                    <Form.Item className="write_money"
                               name = "deposit"
                               rules={[{required: true, message: '보증금을 입력해주세요!'}]}>
                        <Input type="number" placeholder="보증금을 입력해주세요"
                               onChange = {setDepositChange}
                        />
                    </Form.Item>
                </div>



                <div className="write_wrap">
                    <span>카테고리</span>
                    <Form.Item
                        name="cate"
                    >

                    </Form.Item>
                </div>


                <div className="write_wrap">
                    <span>사진</span>
                    <Form.Item
                        className = "itemPhoto"
                        name="itemPhoto">

                        <ImgCrop rotate>


                        <Upload
                        listType="picture"
                        fileList={imageList}
                        onPreview={onPreview}
                        onChange={onPictureChange}
                        beforeUpload={() => false}
                        >
                            <Button icon={<UploadOutlined />}>Upload</Button>
                        </Upload>
                            </ImgCrop>

                        {/*<Input type="file"*/}
                        {/*accept="image/jpg, image/png, image/jpeg"*/}
                        {/*       onChange={onPictureChange}*/}
                        {/*>*/}
                        {/*</Input>*/}
                    </Form.Item>
                </div>


                <div className="write_wrap">
                    <span>주소 찾기</span>
                    <Form.Item
                        name = "address"
                        >
                        <Button type="primary" onClick={showModal}>
                            주소 찾기
                        </Button>
                        <Modal title="현재 주소 찾기"
                               visible={isModalVisible}
                               onOk={modalClose}
                               onCancel={modalClose}
                               >
                            <DaumPostCode
                                onComplete={getAddress}
                            />
                            {address}
                        </Modal>
                        <br/>
                        {address}
                    </Form.Item>
                </div>

                <div className="write_wrap">
                    <span>대여 기간</span>
                    <Form.Item
                        name="date"

                    >
                        <span>시작일</span>
                        <br/>
                        <DatePicker
                            selected={startDate}
                            onChange={date => setStartDate(date)}
                            disabledDate={disabledDate}
                        />
                    <br/>
                        <span>종료일</span>
                        <br/>
                        <DatePicker
                            selected={endDate}
                            onChange={date => setEndDate(date)}
                            disabledDate={disabledEndDate}
                        />
                    </Form.Item>
                </div>

                <div className="write_wrap">
                    <span>품질</span>
                    <Form.Item
                        name="quality"

                    >
                        <label>상</label>
                        <Input
                            type="radio"
                            value="상"
                            checked={quality === "상"}
                            onChange={itemQualityHandleChange}
                        />
                        <label>중</label>
                        <Input
                            type="radio"
                            value="중"
                            checked={quality === "중"}
                            onChange={itemQualityHandleChange}
                        />
                        <label>하</label>
                        <Input
                            type="radio"
                            value="하"
                            checked={quality === "하"}
                            onChange={itemQualityHandleChange}
                        />
                    </Form.Item>
                </div>

                {/*<div className="write_wrap">*/}
                {/*    <span>대여</span>*/}
                {/*    <Form.Item*/}
                {/*        name="quality"*/}

                {/*    >*/}
                {/*        <label>예약중</label>*/}
                {/*        <Input*/}
                {/*            type="radio"*/}
                {/*            value="상"*/}
                {/*            checked={quality === "상"}*/}
                {/*            onChange={itemQualityHandleChange}*/}
                {/*        />*/}
                {/*        <label>중</label>*/}
                {/*        <Input*/}
                {/*            type="radio"*/}
                {/*            value="중"*/}
                {/*            checked={quality === "중"}*/}
                {/*            onChange={itemQualityHandleChange}*/}
                {/*        />*/}
                {/*        <label>하</label>*/}
                {/*        <Input*/}
                {/*            type="radio"*/}
                {/*            value="하"*/}
                {/*            checked={quality === "하"}*/}
                {/*            onChange={itemQualityHandleChange}*/}
                {/*        />*/}
                {/*    </Form.Item>*/}
                {/*</div>*/}

                <Form.Item>
                    <Button type="primary" htmlType="submit" className="button" onClick={test}> 빌려주기 </Button>
                </Form.Item>

            </Form>

        </div>
    )


}

export default WritePage;