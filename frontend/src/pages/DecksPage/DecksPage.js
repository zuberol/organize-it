import * as actionTypes from '../../store/actions';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import FlashcardCreator from '../PlayFlashcardsPage/FlashcardCreator';
import './flashcardDecks.scss';
import deleteImg from './../../assets/delete.png';
import { Link } from 'react-router-dom';

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


function ListItem(props) {
  return (
    <article>
      <Link to={`/play-flashcards/${props.deck.deck_id}`}>
        <h4>{props.deck.title}</h4>
      </Link>
      <div className='deck__infos'>
        <p>{`#Flashcards: ${props.deck.flashcards.length}`}</p>
        <FlashcardCreator picked_deck_id={props.deck.deck_id}/>
      </div>
      {/* <span className="delete-button" onClick={props.deleteItem}>
          <img src={deleteImg}></img>
        </span> */}
      {/* <a href={`/decks/modify/${props.fcSetId}`}>modify it</a> */}
    </article>
  )
}