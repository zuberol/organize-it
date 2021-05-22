export default function FileCheck() {
  const decksNames = useSelector(state =>
    state.flashcardReducer.decks.map(d => d.title)
  );
  const [flashcard, setFlashcard] = useState({
    name: '',
    file: '',
    // deckName: decksNames.length > 0 ? decksNames[0] : ''
  });

  function handleSubmit(event) {
    event.preventDefault();
    //todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
    fetch(new URL('/api/dev/multipart', BACKEND_BASE_URL), {
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