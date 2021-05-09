import { BACKEND_BASE_URL } from './../utils/config.js'
import MOCK_DECKS from '../mock/MockDecks';

export const DELETE_ITEM = 'DELETE_ITEM';
export const INITIALIZE_DECKS = 'INITIALIZE_DECKS';
export const FLASHCARD_DELETED_FROM_DB = 'FLASHCARD_DELETED_FROM_DB';
export const DELETING_FLASHCARD_FAILED = 'DELETING_FLASHCARD_FAILED';

export const fetchdecks = () => {
    return (dispath) => {
        fetch(new URL('/api/decks', BACKEND_BASE_URL), {
            method: 'GET',
            mode: 'cors'
        })
        .then(res => res.json())
        .then((decks) => {
            dispath({type: INITIALIZE_DECKS, decks: decks});
        })
        .catch(err => {
            console.error(err, "Backend doesn't respond");
            dispath({type: INITIALIZE_DECKS, decks: MOCK_DECKS});
        })
    }
}

export const deleteFlashCard = (flashcard) => {
    return (dispath) => {
        fetch(new URL(`/flashcards/${flashcard.id}`, BACKEND_BASE_URL),{
            method: 'DELETE'
        })
        .then(res => {
            if(res.status == 200) dispath({type: FLASHCARD_DELETED_FROM_DB, deletedFlashcard: flashcard});
            else {
                dispath({type: DELETING_FLASHCARD_FAILED, flashcard})
                throw `Error when deleting flashcard. Response code is ${res.status}`;
            }
        })
        .catch(err => console.error(err));
    }
}