import { useState } from 'react';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import './Flashcards.scss';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCoffee, faAngleRight, faAngleLeft, faEye, faComment, faStickyNote } from '@fortawesome/free-solid-svg-icons'


const StyledParagraph = styled.section`
  ${({ active }) => !active && `display: none;`}
  ${({ centered }) => centered && `color:blue;`}
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
  width: 100%;
  color:red;
  margin-left: 1rem;
`;



export default function PlayFlashcardsPage() {
  const [currentFlashcardId, setCurrentFlashcardId] = useState(0);
  const [showAnswer, setShowAnswer] = useState(false);
  const [showHint, setShowHint] = useState(false);
  const [isGameOver, setIsGameOver] = useState(false);  //todo zrobic visible checkery?
  const { deck_id: deckIdParam } = useParams();
  const deck = useSelector(state => state.flashcardReducer.decks.find(deck => {
    return (deck.deck_id == deckIdParam);
  }) || []);
  const currentFlashcard = deck.flashcards[currentFlashcardId];
  console.log(currentFlashcard)
  return (
    <main className="flashcard-page_main">
      <h3>{`${deck.title} ${currentFlashcardId + 1}/${deck.flashcards.length}`}</h3>
      <div className="flashcard">
        <div className="flashcard__sections">
          <StyledParagraph active={true}>
            <p>Question:</p>
            <p>{currentFlashcard.question || '-/-?-/-'}</p>
          </StyledParagraph>
          <StyledParagraph active={showAnswer}>
            <p>Shortly:</p>
            <p>{currentFlashcard.short_answer || '-/-?-/-'}</p>
          </StyledParagraph>
          <StyledParagraph active={showAnswer}>
            <p>Longer:</p>
            <p>{currentFlashcard.long_answer || '-/-?-/-'}</p>
          </StyledParagraph >
          <StyledParagraph active={showHint}>
            <p>Hint:</p>
            <p>{currentFlashcardId.hint  || '-/-?-/-'}</p>
          </StyledParagraph>
          <StyledParagraph active={showAnswer} centered>
            <p>Refs:</p>
            {getReferenceSources(currentFlashcard.reference_resources)}
            <p>{`https://www.youtube.com/watch?v=9ckv6-yhnIY&ab_channel=ParticuleYair`}</p>
          </StyledParagraph>
        </div>
        <div className="flashcard__buttons">
          <button className="flashcard__button" onClick={() => {
            if (currentFlashcardId + 1 >= deck.flashcards.length) setIsGameOver(true);
            else {
              setCurrentFlashcardId(currentFlashcardId + 1);
              setShowAnswer(false);
              setShowHint(false);
            }

          }}>
          <FontAwesomeIcon icon={faAngleRight} />
            <span>next</span>
          </button>
          <button className="flashcard__button" onClick={() => {
            if (currentFlashcardId - 1 >= 0) setCurrentFlashcardId(currentFlashcardId - 1);
          }}>
            <FontAwesomeIcon icon={faAngleLeft} />
            <span>previous</span>
          </button>
          <button className="flashcard__button" onClick={() => setShowHint(!showHint)}>
            <FontAwesomeIcon icon={faComment} />
            <span>hint</span>
          </button>
          <button className="flashcard__button" onClick={() => setShowAnswer(!showAnswer)}>
            <FontAwesomeIcon icon={faEye} />
            <span>answer</span>
          </button>
          {/* <button onClick={() => setShowAnswer(!showAnswer)}>+</button> */}
        </div>
      </div>
    </main>
  )
}


function getReferenceSources(referenceResources) {
  referenceResources.map((ref, index) => <StyledParagraph active={true}>{ref.caption}</StyledParagraph>)
}