import { DECKS_URL, SNIPPETS_URL } from '../../config/backendRoutes.js';

export const DELETE_ITEM = 'DELETE_ITEM';
export const INITIALIZE_DECKS = 'INITIALIZE_DECKS';
export const FLASHCARD_DELETED_FROM_DB = 'FLASHCARD_DELETED_FROM_DB';
export const DELETING_FLASHCARD_FAILED = 'DELETING_FLASHCARD_FAILED';
export const INIT_SNIPPETS = 'INIT_SNIPPETS';

export const fetchDecks = () => {
  return (dispatch) => {
    fetch(DECKS_URL, {
      method: 'GET',
    })
    .then(res => res.json())
    .then((decks) => {
      dispatch({type: INITIALIZE_DECKS, decks: decks});
    })
    .catch(err => {
      console.error(err, "Backend doesn't respond");
      dispatch({type: INITIALIZE_DECKS, decks: []});
    })
  }
}


export const fetchSnippets = () => {
  return (dispatch) => {
    fetch(SNIPPETS_URL)
    .then(res => res.json())
    .then((snippets) => {
      dispatch({type: INIT_SNIPPETS, snippets: snippets});
    })
    .catch(err => {
      console.error(err, "Backend doesn't respond");
      dispatch({type: INIT_SNIPPETS, snippets: []});
    })
  }
}