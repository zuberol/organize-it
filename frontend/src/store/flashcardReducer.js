import * as actionTypes from './actions';

const initialGlobalState = {
  decks: []
};

const reducer = ( state = initialGlobalState, action ) => {
  if(action.type == actionTypes.DELETE_ITEM) {
    //todo zmienic nazwe filteredFlashcardSet bo  jest mylaca
    const filteredFlashcardSet = state.decks.filter((fcset, index) => {
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
  else if(action.type == actionTypes.DELETING_FLASHCARD_FAILED) {
    return state;
  }
  else if(action.type == actionTypes.INITIALIZE_DECKS) {
    return {
      ...state,
      decks: action.decks
    }
  }
  else {
    // console.log("unknown action: ", action);
  }
  return state;
};

export default reducer;