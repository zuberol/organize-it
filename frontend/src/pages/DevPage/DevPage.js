import FlashcardCreator from '../PlayFlashcardsPage/FlashcardCreator';

//todo chaining promises https://gomakethings.com/how-to-use-the-fetch-method-to-make-multiple-api-calls-with-vanilla-javascript/
export default function AddTaskModal() {
  return (
    <main style={{ display: "flex", "justifyContent": "center", backgroundColor: '#F1E0C5', 'minHeight': '85vh' }}>
      <div style={{ display: "flex", "justifyContent": "center", backgroundColor: "#c9b79c", width: "800px" }}>
        <FlashcardCreator />
      </div>
    </main>
  );


}


