import { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import reducer from './store/reducer';
import Routes from './Routes';


export default class App extends Component {
    render() {
        const store = createStore(reducer, applyMiddleware(thunk));
        return (
            <Provider store={store}><Routes /></Provider>
        )
    }
}