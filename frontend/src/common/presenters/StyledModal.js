import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import Button from '@mui/material/Button';
import { useState } from 'react';
import { TaskForm } from "../../Model/Task";
import { Paper } from '@mui/material';


export function StyledModal(props) {
    const [modalOpen, setModalOpen] = useState(false);
    return (
        <>
            <StyledButton {...props.btn} onClick={() => setModalOpen(true)}/>
            <Modal
                open={modalOpen}
                onClose={() => setModalOpen(false)}
                style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{ timeout: 300 }}>
                    <Fade in={modalOpen} >
                            <Paper elevation={3} sx={{ padding: '10px' }}>
                                <TaskForm {...props.form} />
                            </Paper>
                    </Fade>
            </Modal>
        </>
    )
}

function StyledButton(props) {
    return (
        <Button
            onClick={props.onClick}>
                {props.icon}
        </Button>
    )
}