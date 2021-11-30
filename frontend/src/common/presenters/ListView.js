import { DELETE_ITEM } from '../../store/flashcards/actions';
import { useDispatch } from 'react-redux';
import './ListView.scss';
import { Link } from 'react-router-dom';


/**
 *
 * @param {Array} flashcardDecks
 * @return {Array<ListItem>} flashcard decks mapped to ListItems
 */
 export function useListView(flashcardDecks) {
    const dispatch = useDispatch();
    return flashcardDecks.map((deck) =>
    <ListItem
      key={deck.deck_id}
      deck={deck}
      deleteItem={() => dispatch({type: DELETE_ITEM, itemToDeleteID: deck.deck_id})}
    />)
}

function ListItem(props) {
    return (
        <article className="simple">
            <Link to={`/play-flashcards/${props.deck.deck_id}`}>
                <h4>{props.deck.title}</h4>
            </Link>
            <div className='deck__infos'>
                <p>{`#Flashcards: ${props.deck.flashcards.length}`}</p>
            </div>
            <div className="control-buttons"></div>
        </article>
    )
}