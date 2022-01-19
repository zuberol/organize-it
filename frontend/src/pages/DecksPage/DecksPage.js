import * as actionTypes from '../../store/flashcards/actions';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useListView } from '../../common/presenters/ListView';
import { StyledModal } from '../../common/presenters/StyledModal';
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
        <StyledModal title="deck" isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}
          btn={{icon: <FiberNewIcon fontSize="large" />}}
        >
          <DeckForm/>
        </StyledModal>
      </div>
    </main>
  )

}