function ObjectCreationCheck() {
  const decksNames = useSelector(state =>
    state.flashcardReducer.decks.map(d => d.title)
  );
  const [flashcard, setFlashcard] = useState({
    references: [
      ["Book"],

      ["Video"]

    ]
  });

  function handleSubmit(event) {
    event.preventDefault();
    // console.log(flashcard)
    //todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
    fetch(new URL('/api/dev/multipart', BACKEND_BASE_URL), {
      method: 'POST',
      mode: 'cors',
      body: new FormData(event.target)
    })
    .catch((e) => console.error("Błąd przy zapisywaniu flashcarda:", e));
  }


  function handleChange(e, ref_index) {
    const prop = e.target.name;
    const val = e.target.value;
    // console.log({
    //   ...flashcard,
    //   [prop]: val
    // })
    // setFlashcard({
    //   ...flashcard,
    //   // [e.target.name]: e.target.value
    //   [prop]: [e.target.value][index]
    // });
  }

  return (
    <form onSubmit={handleSubmit} name="userForm">
      <label htmlFor="re">Reference source name</label>
      <input
        name="references[1][caption]"
        type="text"
        onChange={handleChange}
      />
      <label htmlFor="references[1][value]">value 1</label>
      <input
        name="references[1][value]"
        type="text"
        onChange={handleChange}
      />

      <label htmlFor="references[2][caption]">Caption</label>
      <input
        name="references[2][caption]"
        type="text"
        onChange={handleChange}
      />
      <label htmlFor="references[2][value]">value 2</label>
      <input
        name="references[1]"
        type="text"
        onChange={e => handleChange(e, 2)}
      />
      <button type="submit">ObjectCreationCheck</button>
    </form>
  );
};