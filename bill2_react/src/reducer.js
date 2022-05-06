import { combineReducers } from 'redux'
import searchTextReducer from './reducers/searchText.js'
import searchCategoryReducer from "./reducers/searchCategory";
import LatitudeReducer from "./reducers/latitude";
import LongitudeReducer from "./reducers/longitude";
import allCategoryReducer from "./reducers/allCategory";

const rootReducer = combineReducers({
    searchText: searchTextReducer,
    allCate : allCategoryReducer,
    value: searchCategoryReducer,
    latitude: LatitudeReducer,
    longitude: LongitudeReducer

})

export default rootReducer