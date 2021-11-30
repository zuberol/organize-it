import * as actionTypes from '../../store/flashcards/actions';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useListView } from '../../common/presenters/ListView';

export default function DecksPage() {
  const availableDecks = useSelector(state => useListView(state.flashcardReducer.decks));
  const dispatch = useDispatch();
  useEffect(() => {
     dispatch(actionTypes.fetchdecks()); //todo zoptymalizowac dzialanie, dziala teraz jak component did mount, NIE JEST ZALEZNE OD decks ze store!!!
  }, []);

  return (
    <main className="centered-content">
      <h3>Available Decks</h3>
      <div className="page-content">
        {availableDecks}
      </div>
    </main>
  )

}