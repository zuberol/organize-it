import ListItem from './ListItem';
import * as actionTypes from '../../store/actions';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';

export default function DecksPage() {
    const availableDecks = useSelector(state => useListItemView(state.flashcardReducer.decks));
    const dispatch = useDispatch();
    useEffect(() => {
       dispatch(actionTypes.fetchdecks()); //todo zoptymalizowac dzialanie, dziala teraz jak component did mount, NIE JEST ZALEZNE OD decks ze store!!!
    }, []);

    return (
        <main className="app-content">    
            <h3>Available Decks</h3>
            <div className="page-content">
                {availableDecks}
            </div>
        </main>
    )

}


/**
 *
 * @param {Array} flashcardDecks
 * @return {Array<ListItem>} flashcard decks mapped to ListItems
 */
function useListItemView(flashcardDecks) {
    const dispatch = useDispatch();
    return flashcardDecks.map((deck) =>
    <ListItem
        key={deck.deck_id}
        deck={deck}
        deleteItem={() => dispatch({type: actionTypes.DELETE_ITEM, itemToDeleteID: deck.deck_id})}
    />)
}