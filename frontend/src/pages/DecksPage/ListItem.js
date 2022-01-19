import './flashcardDecks.css';
import deleteImg from './../../assets/delete.png';
import { Link } from 'react-router-dom';

//todo usunac <a>
export default function ListItem(props) {
  console.log(props.deck)
  return (
    <article>
      <Link to={`/play-flashcards/${props.deck.id}`}>
        <h4>{props.deck.name}</h4>
      </Link>
      <p>{`#Flashcards: ${props.deck.flashcards.length}`}</p>
      <FlashcardCreator />
      {/* <span className="delete-button" onClick={props.deleteItem}>
          <img src={deleteImg}></img>
        </span> */}
      {/* <a href={`/decks/modify/${props.fcSetId}`}>modify it</a> */}
    </article>
  )
}