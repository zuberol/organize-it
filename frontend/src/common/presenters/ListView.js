import { DELETE_ITEM } from '../../store/flashcards/actions';
import { useDispatch } from 'react-redux';
import { updateTask } from "../../store/tasks/actions";
import './ListView.scss';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAward } from '@fortawesome/free-solid-svg-icons'

/**
 *
 * @param {Array} flashcardDecks
 * @return {Array<ListItem>} flashcard decks mapped to ListItems
 */
 export function useListView(flashcardDecks = []) {
    const dispatch = useDispatch();
    return flashcardDecks.map((deck) =>
    <ListItem
      key={deck.deckId}
      deck={deck}
      deleteItem={() => dispatch({type: DELETE_ITEM, itemToDeleteID: deck.deckId})}
    />)
}

function ListItem(props) {
    return (
        <article className="simple">
            <Link to={`/play-flashcards/${props.deck.deckId}`}>
                <h4>{props.deck.title}</h4>
            </Link>
            <div className='deck__infos'>
                <p>{`#Flashcards: ${props.deck.flashcards.length}`}</p>
            </div>
            <div className="control-buttons"/>
        </article>
    )
}


export function GenericList(props) {
    const dispatch = useDispatch();
    const Items = props && props.data && props.data.map((di, index) => 
        <li key={index}>
            <div className="caption">
                <h4>{di.name || di.title}</h4>
            </div>
           <div className="details">
                {di.description && <p>{di.description}</p>}
           </div>
           <div className="">
                <button type="button" className="flashcard__button" onClick={() => {
                  dispatch(
                    updateTask({
                      taskId: di.taskId,
                      archived: 'true'
                  }));
                }}>
                    <FontAwesomeIcon icon={faAward} />
                    <span>Remove</span>
                </button>
           </div>
        </li>
    );

    return (
        <ul className="generic-list">
           {Items}
        </ul>
    )

}
