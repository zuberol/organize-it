import * as actionTypes from './actions';

const initialGlobalState = {
    fcsets: []
};

const reducer = ( state = initialGlobalState, action ) => {
    if(action.type == actionTypes.DELETE_ITEM) {
        //todo zmienic nazwe filteredFlashcardSet bo  jest mylaca
        const filteredFlashcardSet = state.fcsets.filter((fcset, index) => {
            return action.itemToDeleteID !== index;
        });
        return {
            ...state,
            fcsets: filteredFlashcardSet
        }
    }
    else if(action.type == actionTypes.FLASHCARD_DELETED_FROM_DB) {
        //const filteredFlashcardSet = state.fcsets
    }
    else if(action.type == actionTypes.DELETING_FLASHCARD_FAILED) {
        return state;
    }
    else if(action.type == actionTypes.INITIALIZE_FCSETS) {
        console.log('INITIALIZE_FCSETS')
        console.log(action.fcsets)
        return {
            ...state,
            fcsets: action.fcsets
        }
    }
    else {
        console.log("unknown action: ", action);
    }
    return state;
};

export default reducer;