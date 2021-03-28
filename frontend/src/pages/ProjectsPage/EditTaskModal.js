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
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
    color: 'blue'
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
    <div>
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
          timeout: 300,
        }}
      >
        <Fade in={isModalOpen}>
          <div className={classes.paper}>
            <h2 className={classes.paper} id="transition-modal-title">Transition modal</h2>
            <p className={classes.paper} id="transition-modal-description">react-transition-group animates me.</p>
            <EditTaskForm setIsModalOpen={setIsModalOpen}/>
          </div>
        </Fade>
      </Modal>
    </div>
  );
}