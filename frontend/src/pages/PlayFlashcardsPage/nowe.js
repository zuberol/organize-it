import { useSelector } from 'react-redux';
import { BACKEND_BASE_URL } from '../../utils/config';
import '../../common/Form/form.scss';
import '../../../src/static/css/commons.scss';

import { React, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faStickyNote } from '@fortawesome/free-solid-svg-icons'


const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    color: 'blue',
  },
  paper: {
    overflow:'auto',
    backgroundColor: 'tan',
    boxShadow: theme.shadows[5],
    width: '60vw',
    borderRadius: '10px',
    height: '70vh'
  },
}));



export default function FlashcardCreator() {
  const classes = useStyles();
  const [isModalOpen, setIsModalOpen] = useState(false);
  const decks = useSelector(state =>
    state.flashcardReducer.decks
  );
  const [flashcard, setFlashcard] = useState({  // todo Flashcard.empty()
    // '@class': 'com.zuber.organizeit.Model.Flashcard',
    'question': 'wysylanie fc z ref z plikiem',
    'short_answer': '',
    'long_answer': '',
    'reference_resources': [
      {
        "@class": "com.zuber.organizeit.Model.BookReference",
        "id": '',
        "caption": 'test',
        "comment": 'test comment',
        "author": "test authon",
        "title": 'A Great Journey',
        "page": 'test page'
      }
    ]
  });
  const [ref_files, setRefFiles] = useState([])
  const [files, setFiles] = useState('');
  const [picked_deck_id, setPickedDeckId] = useState(decks.length > 0 ? decks[0].deck_id : '')

  // console.log(flashcard)

  return (<>
  <button type="button"  className="iconed" onClick={() => setIsModalOpen(true)}>
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
    <div className={classes.paper}>
      <form onSubmit={handleSubmit} name="flashcardForm" id="flashcardForm">
        <fieldset>
          <label htmlFor="question">Question</label>
          <input
            name="question"
            type="text"
            onChange={handleChangeBasic}
            value={flashcard.name}
          />

          <label htmlFor="short_answer">Short answer</label>
          <input
            name="short_answer"
            type="text"
            onChange={handleChangeBasic}
            value={flashcard.short_answer}
          />

          <label htmlFor="long_answer">Long answer</label>
          <input
            name="long_answer"
            type="text"
            onChange={handleChangeBasic}
            value={flashcard.long_answer}
          />
          <label htmlFor="deckName">Deck name</label>
          <select
            id="deck_name"
            name="deck_name"
            onChange={(e) => setPickedDeckId(e.target.value)}
          >
            {decks.map((deck) => <option key={deck.deck_id} value={deck.deck_id}>{deck.title}</option>)}
          </select>
        </fieldset>
        <fieldset className="reference_resources">
          {renderRefResourceInputs(flashcard.reference_resources)}
        </fieldset>
        <div className="control-buttons">
          <button className="control" type="button" onClick={() => createRefResource("com.zuber.organizeit.Model.VideoReference")}>+ video</button>
          <button className="control" type="button" onClick={() => createRefResource("com.zuber.organizeit.Model.BookReference")}>+ book</button>
          <button className="control" type="button" onClick={() => createRefResource("com.zuber.organizeit.Model.ImageReference")}>+ img</button>
          <button className="submit" type="submit">Submit</button>
        </div>
      </form>
    </div>
  </Modal>
  </>
  );

  function handleSubmit(event) {
    event.preventDefault();
    setIsModalOpen(false);

    console.log(flashcard);


    const formData = new FormData();

    const ref_files = {
      // '@class': 'com.zuber.organizeit.Model.RefFileMetadata',
      'ref_resource_index': '1',
      'ref_resource_associated_files': files
    }

    console.log(ref_files);

    // formData.append('file', document.forms["flashcardForm"].file.files[0])
    formData.append('flashcard', new Blob([JSON.stringify(flashcard)], {
      type: "application/json"
    }));
    // formData.append("files", ref_files);
    formData.append('files', new Blob([ref_files], {
      type: "application/json"
    }));

    fetch(new URL('/api/flashcard', BACKEND_BASE_URL), {
      method: 'POST',
      mode: 'cors',
      body: formData
    })
    .catch((e) => console.log("Błąd przy zapisywaniu flashcarda:", e));
  }

  function createRefResource(res_type) {

    let res_def;
    switch (res_type) {
      case 'com.zuber.organizeit.Model.ImageReference': {
        res_def = {
          "@class": "com.zuber.organizeit.Model.ImageReference",
          "id": '',
          "caption": null,
          "comment": null,
          "reference_url": "",
          // "file": ""
        }
      }
        break;
      case 'com.zuber.organizeit.Model.VideoReference':
        res_def = {
          "@class": "com.zuber.organizeit.Model.VideoReference",
          "id": '',
          "caption": null,
          "comment": null,
          "reference_url": ""
        }

        break;
      default:
        res_def = {
          "@class": "com.zuber.organizeit.Model.BookReference",
          "id": '',
          "caption": null,
          "comment": null,
          "title": '',
          "author": "",
          "page": null
        }
    }
    setFlashcard({
      ...flashcard,
      reference_resources: [...flashcard.reference_resources, res_def]
    })
  }

  function handleChangeBasic(e) {
    setFlashcard({
      ...flashcard,
      [e.target.name]: e.target.value
    });
  }

  function handleChangeReferenceResource(e, res_index) {
    const updating_resource = {
      ...flashcard.reference_resources[res_index],
      [e.target.name]: e.target.value
    }
    const copy_ref_resources = [...flashcard.reference_resources]
    copy_ref_resources[res_index] = updating_resource;

    setFlashcard({
      ...flashcard,
      'reference_resources': copy_ref_resources
    });
  }

  function handleFileUpload(e, res_index) {
    const updatedCopy = [...ref_files];
    updatedCopy[res_index] = {
      'ref_resource_index': res_index,
      'ref_resource_associated_files': e.target.files
    };
    setRefFiles(updatedCopy);
  }

  function renderRefResourceInputs(reference_resources) {
    return reference_resources.map(res => res['@class']).map((res, i) => {
      switch (res) {
        case 'com.zuber.organizeit.Model.ImageReference':
          return <fieldset className="withFile" key={i}>
            <h6>{`${i}# Image reference`}</h6>
            <label>Reference url</label>
            <input
              type="text"
              onChange={(e) => handleChangeReferenceResource(e, i)}
              name="reference_url"
              value={flashcard.reference_resources[i].reference_url}
            />
            <input
              type="file"
              // onChange={(e) => handleFileUpload(e, i)}
              onChange={(e) => setFiles(e.target.files[0])}//setFiles
              // name="file_ref"
            />
          </fieldset>
        case 'com.zuber.organizeit.Model.VideoReference':
          return <fieldset key={i}>
            <h6>{`${i}# Video reference`}</h6>
            <label>Reference url</label>
            <input
              type="text"
              onChange={(e) => handleChangeReferenceResource(e, i)}
              name="reference_url"
              value={flashcard.reference_resources[i].reference_url}
            />
          </fieldset>
        case 'com.zuber.organizeit.Model.BookReference':
          return <fieldset key={i}>
            <h6>{`${i}# Book reference`}</h6>
            <label>Book name</label>
            <input
              type="text"
              onChange={(e) => handleChangeReferenceResource(e, i)}
              name="title"
              value={flashcard.reference_resources[i].title}
            />
            <label>Author</label>
            <input
              type="text"
              onChange={(e) => handleChangeReferenceResource(e, i)}
              name="author"
              value={flashcard.reference_resources[i].author}
            />
          </fieldset>
      }
    })
  }

};