import { useState } from "react";
import { useDispatch } from "react-redux";
import { DECK_URL, DECK_URL_NEW } from "../config/backendRoutes";
import { fetchDecks } from "../store/flashcards/actions";


export function DeckForm({
  deckDefaults = {
    id: '',
    name: '',
    flashcardsIds: []
  }, 
  newDeck= true
}) {
  const [deckTO, setDeckTO] =  useState({...deckDefaults});
  const dispatch = useDispatch();
  return (
    <form onSubmit={handleSubmit}>
      <label htmlFor="id" hidden={newDeck}>Deck ID</label>
      <input
        hidden={newDeck}
        name="id"
        type="number"
        onChange={handleChange}
        defaultValue={deckDefaults.id}
      />
      <label htmlFor="name">Name</label>
      <input
        name="name"
        type="text"
        defaultValue={deckDefaults.name}
        onChange={handleChange}
      />
      <label htmlFor="flashcardsIds">Flashcards ids</label>
      <input
        name="flashcardsIds"
        type="text"
        defaultValue={deckDefaults.flashcardsIds.map(_ => _.id).toString()}
        onChange={handleChangeArray}
      />
      <button type="submit">Submit</button>
    </form>
  )

  function handleSubmit(event) {
    event.preventDefault();
    fetch(((newDeck && DECK_URL_NEW) || DECK_URL), {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(deckTO)
    })
    .then(() => dispatch(fetchDecks()))
    .catch(e => console.log(e))
  }

  function handleChange(e) {
    setDeckTO({
      ...deckTO, 
      [e.target.name]: e.target.value
    });
  }

  function handleChangeArray(e) {
    setDeckTO({
      ...deckTO, 
      [e.target.name]: e.target.value.split(',')
    });
  }

}

