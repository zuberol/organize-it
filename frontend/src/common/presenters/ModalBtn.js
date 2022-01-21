import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import Button from '@mui/material/Button';
import { useState } from 'react';
import { Paper } from '@mui/material';


export function ModalBtn(props) {
    const [modalOpen, setModalOpen] = useState(false);
    return (
        <>
            <StyledButton {...props.btn} onClick={() => setModalOpen(true)}>
            </StyledButton>
            <Modal
                open={modalOpen}
                onClose={() => setModalOpen(false)}
                style={{display: 'flex', alignItems: 'center', justifyContent: 'center'}}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{ timeout: 300 }}>
                    <Fade in={modalOpen} >
                            <Paper elevation={3} sx={{ padding: '10px' }}>
                                {props.children}
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
                <div
                    style={{display: 'flex', flexDirection: 'column', alignItems: 'center'}}
                >
                    {props.icon}
                    <span style={{fontSize: props.titleSize}}>{props.title}</span>
                </div>
        </Button>
    )
}