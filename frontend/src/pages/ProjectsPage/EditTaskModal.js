import { React, useState} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import EditTaskForm from './EditTaskForm';
import { useSelector } from 'react-redux'


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

export default function EditTaskModal() {
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
    react-transition-group
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
      {/* <EditTaskForm setIsModalOpen={setIsModalOpen}/> */}
      </div>
    </Fade>
    </Modal>
  </main>
  );
}