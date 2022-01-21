import * as actionTypes from '../../store/flashcards/actions';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useListView } from '../../common/presenters/ListView';
import { ModalBtn } from '../../common/presenters/ModalBtn';
import FiberNewIcon from '@mui/icons-material/FiberNew';
import { DeckForm } from "../../Model/Deck";



export default function DecksPage() {
  const availableDecks = useSelector(state => useListView(state.flashcardReducer.decks));
  const dispatch = useDispatch();
  const [ isModalOpen, setIsModalOpen ] = useState(false);
  useEffect(() => {
     dispatch(actionTypes.fetchDecks());
  }, []);

  return (
    <main className="centered-content">
      <h3>Available Decks</h3>
      <div className="page-content">
        {availableDecks}
      </div>
      <div style={{position: 'fixed', right: '40px', bottom: '40px', backgroundColor: 'salmon'}}>
        <ModalBtn title="deck" isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}
          btn={{icon: <FiberNewIcon fontSize="large" />}}
        >
          <DeckForm/>
        </ModalBtn>
      </div>
    </main>
  )

}