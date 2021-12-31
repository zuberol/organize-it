import { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import flashcardReducer from './store/flashcards/flashcardReducer';
import mealReducer from './store/meal/reducer';
import { tasksReducer } from './store/tasks/reducer';
import { combineReducers } from 'redux'
import Routes from './Routes';


export default class App extends Component {
  render() {
    const rootReducer = combineReducers({
      mealReducer,
      flashcardReducer,
      tasksReducer
    });
    const store = createStore(
      rootReducer, 
      composeWithDevTools(applyMiddleware(thunk)));
    return (
      <Provider store={store}>
        <Routes/>
      </Provider>
    )
  }
}