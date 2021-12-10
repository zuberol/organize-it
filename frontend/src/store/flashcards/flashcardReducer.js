import * as actionTypes from './actions';

const initialGlobalState = {
  decks: [],
  snippets: []
};

const reducer = ( state = initialGlobalState, action ) => {
  if(action.type == actionTypes.DELETE_ITEM) {
    //todo zmienic nazwe filteredFlashcardSet bo  jest mylaca
    const filteredFlashcardSet = state.decks.filter((_, index) => {
      return action.itemToDeleteID !== index;
    });
    return {
      ...state,
      decks: filteredFlashcardSet
    }
  }
  else if(action.type == actionTypes.FLASHCARD_DELETED_FROM_DB) {
    //const filteredFlashcardSet = state.decks
  }
  else if (action.type == actionTypes.DELETING_FLASHCARD_FAILED) {
    return state;
  }
  else if (action.type == actionTypes.INITIALIZE_DECKS) {
    return {
      ...state,
      decks: action.decks
    }
  }
  else if(action.type == actionTypes.INIT_SNIPPETS) {
    return {
      ...state,
      snippets: action.snippets
    }
  }
  else {
    // console.log("unknown action: ", action);
  }
  return state;
};

export default reducer;
