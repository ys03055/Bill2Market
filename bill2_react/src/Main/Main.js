import React, {Fragment} from "react";
import "./Main.css";
import {Link} from "react-router-dom";
import HeaderPage from "../header/header";
import ProductListPage from "../productList/productList";

function MainPage()  {
                {/* 초기화면에서 액세스 권한관련 자바 스크립트  */}
    return (
        <Fragment>

            <title>빌리마켓</title>
            <body>
        <header>
            <HeaderPage></HeaderPage>

        </header>

        <main>
            <h3>거리별 물품 보여주기</h3>
            <ProductListPage></ProductListPage>
        </main>

      </body>

    </Fragment>
    )
}

export default MainPage;