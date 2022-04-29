import { combineReducers } from 'redux'
import searchCategoryReducer from './reducers/searchCategory.js'
import LocationReducer from "./reducers/latitude";
import LatitudeReducer from "./reducers/latitude";
import LongitudeReducer from "./reducers/longitude";

const rootReducer = combineReducers({
	value: searchCategoryReducer,
	latitude: LatitudeReducer,
	longitude: LongitudeReducer
})

export default rootReducer
