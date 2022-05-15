import MainPage from "./Main/Main"
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LoginPage from "./login/login";
import SignUpPage from "./signUp/signUp";
import SnsSignUpPage from "./signUp/snsSignUp";
import WritePage from "./write/write";
import ProductViewDetailsPage from "./productViewDetails/productViewDetails";
import SearchPage from "./search/search";
import CategorySearchPage from "./categorySearch/categorySearch";
import MyPage from "./myPage/myPage";

function Routers() {
    return (
        <div>
            <BrowserRouter>
                <Routes>
                    <Route path = "/*" element={<MainPage />} />
                    <Route path = "/login" element={<LoginPage />} />
                    <Route path = "/snsSignUp" element={<SnsSignUpPage />} />
                    <Route path = "/signUp" element={<SignUpPage />} />
                    <Route path = "/write" element={<WritePage />} />
                    <Route path = "/productViewDetails" element={<ProductViewDetailsPage />} />
                    <Route path = "/categorySearch" element={<CategorySearchPage />} />
                    <Route path = "/search" element={<SearchPage />} />
                    <Route path = "/MyPage" element={<MyPage />} />
                </Routes>
            </BrowserRouter>
        </div>
    );
}

export default Routers;