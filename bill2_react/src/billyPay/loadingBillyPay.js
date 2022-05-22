import React, {useEffect} from 'react';
import axios from "axios";
import {useNavigate} from "react-router-dom";

function Loader() {
    const navigate = useNavigate()


    const data = {
        price : sessionStorage.getItem('price'),
        deposit : sessionStorage.getItem('deposit'),
        contractId: 3,
    }

    const payTransfer = () => {
        const option = {
            url : 'http://localhost:8080/contracts/transfer',
            method: 'POST',
            headers: {
                Authorization: 'Bearer ' + sessionStorage.getItem("token"),
            },
            data: data

        }

        axios(option)
            .then(res=>
            {
                console.log(res.data);
                if(res.data.message === '성공하였습니다.') {
                    alert("결제가 완료되었습니다.")
                    navigate('/myPage')
                }

            })
    }


    useEffect(() => {
        payTransfer()
    },[])
    return (
        <div className="contentWrap">
            결제 중
        </div>
    );
}

export default Loader;
