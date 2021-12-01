import {React, useState} from 'react';
import { useDispatch } from 'react-redux';
import Modal from "@material-ui/core/Modal";
import Backdrop from "@material-ui/core/Backdrop";
import Fade from "@material-ui/core/Fade";
import {BACKEND_BASE_URL} from "../../../utils/config";
import {fetchdecks} from "../../../store/actions";


export function ModifyTask(taskDatum) {
  // const dispatch = useDispatch();
  const [ isModalOpen, setIsModalOpen ] = useState(false);
  const [ task, setTask ] = useState(taskDatum);


  return (
    <>
      <button onClick={()=> setIsModalOpen(!isModalOpen)}>modify Task</button>
      <Modal
        aria-labelledby="transition-modal-title"
        aria-describedby="transition-modal-description"
        open={isModalOpen}
        onClose={handleClose}
        closeAfterTransition
        BackdropComponent={Backdrop}
        BackdropProps={{
          timeout: 300
        }}
      >
        <Fade in={isModalOpen}>
          <div>
            <form onSubmit={onSubmit}>
              <label htmlFor="id">id#</label>
              <input
                name="id"
                onChange={handleChangeBasic}
                defaultValue={task.task_id}
              />
              <label htmlFor="name">Name</label>
              <input
                onChange={handleChangeBasic}
                name="name"
                defaultValue={task.name}
              />
              <label htmlFor="description">Description</label>
              <input
                onChange={handleChangeBasic}
                name="description"
               defaultValue={task.description}
              />
            </form>
          </div>
        </Fade>
      </Modal>
    </>
  );

  function handleChangeBasic(e) {
    setTask({
      ...task,
      [e.target.name]: e.target.value
    });
  }

  function handleClose() {
    setIsModalOpen(false);
  }

  function onSubmit(event) {
    event.preventDefault();
    setIsModalOpen(false);

    fetch(new URL('/api/task', BACKEND_BASE_URL), {
      method: 'POST',
      mode: 'cors',
      body: JSON.stringify(task)
    })
      // .then(() => dispatch(fetchdecks()))
      .catch((e) => console.log("Błąd przy zapisywaniu taska:", e));



  }

}

export function markTaskDone(task, dispatch) {

}
export function createTask() {

}
