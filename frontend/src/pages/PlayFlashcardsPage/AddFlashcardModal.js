import { React, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import { useSelector } from 'react-redux';
import { BACKEND_BASE_URL } from '../../utils/config';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faStickyNote } from '@fortawesome/free-solid-svg-icons'



const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',   //todo dziala to w ogole?
    color: 'blue'
  },
  paper: {
    backgroundColor: 'tan',
    boxShadow: theme.shadows[5],
    padding: "2rem"
  },
}));

export default function AddFlashcardModal() {
  const classes = useStyles();
  // const isModalOpen = useSelector(state => state.tasksReducer.isModalOpen);
  const [isModalOpen, setIsModalOpen] = useState(false);


  return (
    <>
      <button type="button" className="flashcard__button" onClick={() => setIsModalOpen(true)}>
        <FontAwesomeIcon icon={faStickyNote} />
        <span>new</span>
      </button>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 300
        }}
      >
        <Fade in={isModalOpen}>
          <div className={classes.paper}>
            <NewFlashcardForm setIsModalOpen={setIsModalOpen} />
          </div>
        </Fade>
      </Modal>
    </>
  );

  function NewFlashcardForm() {
    const decksNames = useSelector(state =>
      state.flashcardReducer.decks.map(d => d.title)
    );
    const [flashcard, setFlashcard] = useState({
      question: '',
      shortAnswer: '',
      long_answer: '',
      file: '',
      deckName: decksNames.length > 0 ? decksNames[0] : '',
      reference_resources: [

      ]
    });

    return (
      <form onSubmit={handleSubmit}>
        <label htmlFor="question">Question</label>
        <input
          name="question"
          type="text"
          onChange={handleChange}
          value={flashcard.name}
        />

        <label htmlFor="shortAnswer">Short answer</label>
        <input
          name="shortAnswer"
          type="text"
          onChange={handleChange}
          value={flashcard.short_answer}
        />

        <label htmlFor="long_answer">Long answer</label>
        <input
          name="long_answer"
          type="text"
          onChange={handleChange}
          value={flashcard.long_answer}
        />
        <label htmlFor="file">File</label>
        <input
          name="file"
          type="file"
          onChange={handleChange}
          value={flashcard.file}
        />
        <label htmlFor="deckName">Deck name</label>
        <select
          id="deckName"
          name="deckName"
          onChange={handleChange}
        >
          {decksNames.map((i, k) => <option key={k} value={i}>{i}</option>)}
        </select>
        <button type="submit">Submit</button>
      </form>
    );

    function handleSubmit(event) {
      event.preventDefault();
      fetch(new URL('/api/flashcard', BACKEND_BASE_URL), {
        method: 'POST',
        mode: 'cors',
        body: new FormData(event.target)
      })
      .catch((e) => console.error("Błąd przy zapisywaniu flashcarda:", e));
      setIsModalOpen(false);
    }

    function handleChange(e) {
      setFlashcard({
        ...flashcard,
        [e.target.name]: e.target.value
      });
    }

    function handleChange(e, index) {
      setFlashcard({
        ...flashcard,
        [e.target.name]: e.target.value
      });
    }
  };
}