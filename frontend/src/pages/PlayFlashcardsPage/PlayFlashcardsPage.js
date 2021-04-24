import { useState } from 'react';
import { useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import './Flashcards.css';
import AddTaskModal from './AddFlashcardModal';


const StyledParagraph = styled.section`
    ${({ active }) => !active && `display: none;`}
    ${({ centered }) => centered && `color:blue;`}
    border-bottom: 1px solid rgba(0, 0, 0, 0.12);
`;

export default function PlayFlashcardsPage() {
    const [currentFlashcardId, setCurrentFlashcardId] = useState(0);
    const [showAnswer, setShowAnswer] = useState(false);
    const [showHint, setShowHint] = useState(false);
    const [isGameOver, setIsGameOver] = useState(false);    //todo zrobic visible checkery?
    const { deck_id: deckIdParam } = useParams();
    const deck = useSelector(state => state.flashcardReducer.decks.find(deck => {
        return (deck.deck_id == deckIdParam);
    }) || []);
    const currentFlashcard = deck.flashcards[currentFlashcardId];
    console.log(currentFlashcard)
    return (
        <main className="flashcard-page_main">
            <h4>{`${deck.title} ${currentFlashcardId + 1}/${deck.flashcards.length}`}</h4>
            <div className="flashcard">
                <div className="flashcard__buttons">
                    <button onClick={() => {
                        if (currentFlashcardId - 1 >= 0) setCurrentFlashcardId(currentFlashcardId - 1);
                    }}>{'< -'}</button>
                    <button onClick={() => {
                        if (currentFlashcardId + 1 >= deck.flashcards.length) setIsGameOver(true);
                        else {
                            setCurrentFlashcardId(currentFlashcardId + 1);
                            setShowAnswer(false);
                            setShowHint(false);
                        }

                    }}>{'- >'}</button>
                    <button onClick={() => setShowHint(!showHint)}>hint</button>
                    <button onClick={() => setShowAnswer(!showAnswer)}>answer</button>
                    {/* <button onClick={() => setShowAnswer(!showAnswer)}>+</button> */}
                    <AddTaskModal/>
                </div>
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
        </main>
    )
}


function getReferenceSources(referenceSources) {
    referenceSources.map((ref, id) => <StyledParagraph active={true}>{ref}</StyledParagraph>)
}