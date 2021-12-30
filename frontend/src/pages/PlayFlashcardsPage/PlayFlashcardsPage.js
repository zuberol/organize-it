import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import './Flashcards.scss';
import '../../common/styles/commons.scss';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faAngleRight, faAngleLeft, faEye, faComment } from '@fortawesome/free-solid-svg-icons'
import { renderResources } from "../../Model/Project/ReferenceResource";

const StyledParagraph = styled.section`
  ${({ active }) => !!active.reduce((prev, curr) => {
    let shouldVisible = prev && curr && (
      curr.length == undefined || curr.length > 0
    );
    return shouldVisible;
  }, true) || `display: none;`}
  ${({ centered }) => centered && `color:blue;`}
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
  width: 100%;
  color:red;
  padding: 0 1rem;
`;



export default function PlayFlashcardsPage() {
  const [currentFlashcardId, setCurrentFlashcardId] = useState(0);
  const [showAnswer, setShowAnswer] = useState(false);
  const [showHint, setShowHint] = useState(false);
  const [isGameOver, setIsGameOver] = useState(false);
  useEffect(() => {
    document.addEventListener('keydown', handleKeyDownEvents);
    return () => document.removeEventListener('keydown', handleKeyDownEvents);
  });
  const { deckId: deckIdParam } = useParams();
  const deck = useSelector(state => state.flashcardReducer.decks.find(deck => {
    return (deck.deckId == deckIdParam);
  }) || []);
  const currentFlashcard = deck.flashcards[currentFlashcardId];
  return (
    <main className="centered-content">
      <h3>{`${deck.title} ${currentFlashcardId + 1}/${deck.flashcards.length}`}</h3>
      <div className="flashcard">
        <div className="flashcard__sections">
          <StyledParagraph active={[true, currentFlashcard.question]}>
            <p>Question:</p>
            <p>{currentFlashcard.question}</p>
          </StyledParagraph>
          <StyledParagraph active={[showAnswer, currentFlashcard.shortAswer]}>
            <hr/>
            <p>Shortly:</p>
            <p>{currentFlashcard.shortAswer}</p>
          </StyledParagraph>
          <StyledParagraph active={[showAnswer, currentFlashcard.longAnswer]}>
            <hr/>
            <p>Longer:</p>
            <p>{currentFlashcard.longAnswer}</p>
          </StyledParagraph >
          <StyledParagraph active={[showHint, currentFlashcard.hint]}>
            <hr/>
            <p>Hint:</p>
            <p>{currentFlashcard.hint}</p>
          </StyledParagraph>
          <StyledParagraph active={[showAnswer, currentFlashcard.referenceResources]} centered>
            <hr/>
            <p>Refs:</p>
            {renderResources(currentFlashcard.referenceResources)}
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


  function handleKeyDownEvents(event) {
    switch(event.key){
      case 'ArrowLeft': {
        if(currentFlashcardId - 1 >= 0) setCurrentFlashcardId(currentFlashcardId-1);
      }
      break;
      case 'ArrowRight': {
        if(currentFlashcardId + 1 < deck.flashcards.length) setCurrentFlashcardId(currentFlashcardId + 1);
      }
      break;
      case 'ArrowUp': {
        setShowAnswer(false);
        setShowHint(false);
      }
      break;
      case 'ArrowDown': {
        setShowAnswer(true);
        setShowHint(true);
      }
      break;
    }
  };


}