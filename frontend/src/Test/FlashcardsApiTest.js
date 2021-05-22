import { React, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import { useSelector } from 'react-redux';
import { BACKEND_BASE_URL } from '../../utils/config';



const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    color: 'blue'
  },
  paper: {
    backgroundColor: '#54494B',
    boxShadow: theme.shadows[5],
    padding: "2rem"
  },
}));

export default function AddTaskModal() {
  const classes = useStyles();
  // const isModalOpen = useSelector(state => state.projectsReducer.isModalOpen);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpen = () => {
    setIsModalOpen(true);
  };

  const handleClose = () => {
    setIsModalOpen(false);
  };

  return (
    <main>
      <button type="button" onClick={handleOpen}>
        InheritanceCheck
    </button>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        className={classes.modal}
        open={isModalOpen}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 300
        }}
      >
        <Fade in={isModalOpen}>
          <div className={classes.paper}>
            <FileCheck setIsModalOpen={setIsModalOpen} />
          </div>
        </Fade>
      </Modal>
    </main>
  );
}



function InheritanceCheck() {
  const decksNames = useSelector(state =>
    state.flashcardReducer.decks.map(d => d.title)
  );
  const [flashcard, setFlashcard] = useState({
    author: ''
    // deckName: decksNames.length > 0 ? decksNames[0] : ''
  });

  // todo dzialalo 1
  // fetch(new URL('/api/dev/refsourceSave', BACKEND_BASE_URL), {
  //   method: 'POST',
  //   mode: 'cors',
  //   headers: {
  //     'Content-Type': 'application/json'
  //   },
  //   body: JSON.stringify(
  //     {
  //       "@class": "com.zuber.organizeit.Model.BookReference",
  //       "id": null,
  //       "caption": 'hehe',
  //       "comment": null,
  //       "author": "Twardy Radek"
  //     }
  //   )
  // })

  // todo dzialalo
  // fetch(new URL('/api/dev/refsourceSave', BACKEND_BASE_URL), {
  //   method: 'POST',
  //   mode: 'cors',
  //   headers: {
  //     'Content-Type': 'application/json'
  //   },
  //   body: JSON.stringify(
  //     {
  //       "@class": "com.zuber.organizeit.Model.BookReference",
  //       "id": null,
  //       "caption": 'hehe',
  //       "comment": null,
  //       "author": "Twardy Radek"
  //     }
  //   )
  // })

  fetch(new URL('/api/dev/refsourceSave', BACKEND_BASE_URL), {
    method: 'POST',
    mode: 'cors',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(

        [
          {
            "@class": "com.zuber.organizeit.Model.VideoReference",
            "id": null,
            "caption": null,
            "comment": null,
            "reference_url": "https://www.youtube.com/watch?v=av0y5TAItyk&ab_channel=JWPCREW"
          },
          {
            "@class": "com.zuber.organizeit.Model.BookReference",
            "id": null,
            "caption": null,
            "comment": null,
            "author": "Harry Potter i komnata wpierdolu",
            "page": null
          }
        ]
      
    )
  })




  function handleSubmit(event) {
    event.preventDefault();
    //todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
    fetch(new URL('/api/dev/refsourceSave', BACKEND_BASE_URL), {
      method: 'POST',
      mode: 'cors',
      body: new FormData(event.target)
    })
      .catch((e) => console.error("Błąd przy zapisywaniu inheritance:", e));
  }

  function handleFileChange(e) {
    setFlashcard({ flashcard, file: e.target.files[0] });
  };

  function handleChange(e) {
    setFlashcard({
      ...flashcard,
      [e.target.name]: e.target.value
    });
  }

  return (
    <form onSubmit={handleSubmit}>

      <label htmlFor="author">author</label>
      <input
        name="author"
        type="text"
        onChange={handleChange}
        value={flashcard.author}
      />


      <label htmlFor="@class">@class</label>
      <input
        name="@class"
        type="text"
        onChange={handleChange}
        value={flashcard['@class']}
      />

      <button type="submit">Submit</button>
    </form>
  );
};



function FileCheck() {
  const decksNames = useSelector(state =>
    state.flashcardReducer.decks.map(d => d.title)
  );
  const [flashcard, setFlashcard] = useState({
    name: '',
    file: '',
    // deckName: decksNames.length > 0 ? decksNames[0] : ''
  });



  // fetch(new URL('/api/decks', BACKEND_BASE_URL), {
  //   method: 'GET',
  //   mode: 'cors'
  // })

  function handleSubmit(event) {
    event.preventDefault();
    //todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
    fetch(new URL('/dev/multipart', BACKEND_BASE_URL), {
      method: 'POST',
      mode: 'cors',
      body: new FormData(event.target)
    })
      .catch((e) => console.error("Błąd przy zapisywaniu flashcarda:", e));
  }

  function handleFileChange(e) {
    setFlashcard({ flashcard, file: e.target.files[0] });
  };

  function handleChange(e) {
    setFlashcard({
      ...flashcard,
      [e.target.name]: e.target.value
    });
  }

  return (
    <form onSubmit={handleSubmit}>

      <label htmlFor="name">Name</label>
      <input
        name="name"
        type="text"
        onChange={handleChange}
        value={flashcard.name}
      />


      <label htmlFor="file">file</label>
      <input
        name="file"
        type="file"
        onChange={handleChange}
        value={flashcard.file}
      />

      <button type="submit">Submit</button>
    </form>
  );
};