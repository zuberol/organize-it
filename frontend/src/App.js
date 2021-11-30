import { Component } from 'react';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import flashcardReducer from './store/flashcards/flashcardReducer';
import mealReducer from './store/meal/reducer';
import projectsReducer from './store/tasks/reducer';
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