import { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import flashcardReducer from './store/flashcardReducer';
import mealReducer from './store/mealReducer';
import projectsReducer from './store/projectsReducer';
import { combineReducers } from 'redux'
import Routes from './Routes';


export default class App extends Component {
    render() {
        const rootReducer = combineReducers({
            mealReducer: mealReducer,
            flashcardReducer: flashcardReducer,
            projectsReducer: projectsReducer
        });
        const store = createStore(rootReducer, applyMiddleware(thunk));
        return (
            <Provider store={store}>
                <Routes/>
            </Provider>
        )
    }
}