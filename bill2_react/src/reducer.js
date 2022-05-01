import { combineReducers } from 'redux'
import searchCategoryReducer from './reducers/searchCategory.js'

const rootReducer = combineReducers({
	value: searchCategoryReducer
})

export default rootReducer
