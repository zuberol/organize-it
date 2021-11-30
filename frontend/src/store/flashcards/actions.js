import { DECKS } from '../../config/backendRoutes.js';

export const DELETE_ITEM = 'DELETE_ITEM';
export const INITIALIZE_DECKS = 'INITIALIZE_DECKS';
export const FLASHCARD_DELETED_FROM_DB = 'FLASHCARD_DELETED_FROM_DB';
export const DELETING_FLASHCARD_FAILED = 'DELETING_FLASHCARD_FAILED';

export const fetchdecks = () => {
  return (dispatch) => {
    fetch(DECKS, {
      method: 'GET',
      mode: 'cors'
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