import { BACKEND_BASE_URL } from './../utils/config.js'

export const DELETE_ITEM = 'DELETE_ITEM';
export const INITIALIZE_FCSETS = 'INITIALIZE_FCSETS';
export const FLASHCARD_DELETED_FROM_DB = 'FLASHCARD_DELETED_FROM_DB';
export const DELETING_FLASHCARD_FAILED = 'DELETING_FLASHCARD_FAILED';

export const fetchFCSETS = () => {
    return (dispath) => {
        fetch(new URL('/api/flashcarddecks', BACKEND_BASE_URL), {
            method: 'GET',
            mode: 'cors'
        })
        // .then(res => {
        //     if(res.status != 200) throw `error: respone code is ${res.status}`
        //     return res;
        // })
        .then(res => res.json())
        .catch(err => {
            console.log(err);
            dispath({type: INITIALIZE_FCSETS, fcsets: []});
        })
        .then((fcsets) => {
            dispath({type: INITIALIZE_FCSETS, fcsets: fcsets});
        });
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