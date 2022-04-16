import React, {Fragment} from "react";
import "./Main.css";
import HeaderPage from "../header/header";
import ProductListPage from "../productList/productList";

function MainPage()  {

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