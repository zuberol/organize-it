import { useState } from 'react'
import { useDispatch } from 'react-redux';
import { saveTask } from '../../store/projectsActions';


// todo broken bo nie korzysta z Task.js
export default function EditTaskForm(props) {
  const [note, setNote] = useState("");
  const [parentId, setParentId] = useState("");
  const dispatch = useDispatch();
  
  
  
  return (
    <form onSubmit={submit}>
      <label htmlFor="parentId">Parent id:</label>
      <input name="parentId" type="number" value={parentId} onChange={(e) => setParentId(e.target.value)} />
      <label htmlFor="note">Note: </label>
      <input name="note" value={note} onChange={(e) => setNote(e.target.value)} />
      <button type="submit">submit</button>
    </form>
  )

  function submit(event) {
    event.preventDefault();
    console.log('submitting new task ...');
    dispatch(
      saveTask({
        taskId: null,
        parentId: parentId,
        note: note
      })
    );
    setNote("");
    setParentId("");
    props.setIsModalOpen(false);
  }

}