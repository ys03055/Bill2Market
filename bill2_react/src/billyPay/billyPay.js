import React, {Fragment, useEffect, useState} from 'react'
import './billyPay.css'
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {Button, Modal} from 'antd';
import Loader from "./loadingBillyPay";

function BillyPayPage() {
    const [itemList, setItemList] = useState([])
    const [photo, setPhoto] = useState()
    const [payment, setPayment] = useState('')
    const [price, setPrice] = useState('')
    const [deposit, setDeposit] = useState('')
    const [fees, setFees] = useState('')
    const navigate = new useNavigate()


    const format = (date) => {
        return date.getFullYear() + "년 " + (("00" + (date.getMonth() + 1))).slice(-2) + "월 " + (("00" + date.getDate()).slice(-2)) + "일 ";
    }

    const payButtonClick = () => {
        axios.get('http://localhost:8080/contracts/account/'+3,
            {
                headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                },

            },


        )
            .then((res) => {
                console.log(res)
                //계좌 없을 때
                if(res.data.code === 2) {
                    if(window.confirm('결제를 진행하시려면 계좌 등록이 필요합니다. 계좌를 등록하시겠습니까?')){
                        window.open(res.data.data, '_blank')
                    }
                }
                //계좌 있을 때
                else {
                    navigate('/loading')
                }

            })

    }

    const getItem = () => {
        axios.get('http://localhost:8080/items/' + 4,
            {
                headers: {
                    Authorization: 'Bearer ' + sessionStorage.getItem("token")
                }
            }
            ,
        )
            .then((res) => {
                {
                    console.log(res.data)
                    setItemList(res.data.data.item)
                    setPhoto(res.data.data.item.photos[0].itemPhoto)
                    setPrice(itemList.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","))
                    setDeposit(itemList.deposit.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","))
                    setFees((itemList.price * 0.05).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","))
                    setPayment((itemList.price + itemList.deposit + itemList.price * 0.05).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","))
                    sessionStorage.setItem('price', itemList.price)
                    sessionStorage.setItem('deposit', itemList.price)
                    // sessionStorage.setItem('contractId', itemList.price)
                }

            })
            .catch(res => {

            })
    }

    useEffect(() => {
        getItem()

    }, [itemList.length])

    return (

        <Fragment>

            <div className='payWrap'>
                <div>결제상세</div>

<div className='itemPhoto'>
    <img className="phoneImage" src={photo}/>
</div>

<div className='content'>
    {itemList.itemTitle}
    <br/>
    대여료 : {price}
    <br/>
    보증금 : {deposit}
    <br/>
    계약일 : {format(new Date(itemList.startDate))} ~ {format(new Date(itemList.endDate))}
</div>

<div className='payContent'>
    결제금액 : {payment}원
    <br/>
    대여료 : {price}원
    <br/>
    보증금 : {deposit}원
    <br/>
    빌리페이 수수료 : {fees}원
</div>

보증금은 물품 반납을 완료하신 뒤에 등록하신 계좌로 전액 환급 받으실 수 있습니다.
</div>

<div>
    주문 내용을 확인하였으며, 결제에 동의합니다.
    <br/>
    <Button onClick={payButtonClick}> 결제하기 </Button>
</div>

</Fragment>
)
}

export default BillyPayPage;