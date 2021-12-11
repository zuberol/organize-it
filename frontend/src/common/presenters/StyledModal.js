import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import Button from '@mui/material/Button';


export function StyledModal({icon, title, isModalOpen, setIsModalOpen, children}) {
    return (
        <>
            <Button type="button" className="flashcard__button" onClick={() => setIsModalOpen(true)}>
                <FontAwesomeIcon icon={icon} />
                <span>{title}</span>
            </Button>
            <Modal
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
                open={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{
                    timeout: 300
                }}>
                <Fade in={isModalOpen}>
                    <>{children}</>
                </Fade>
            </Modal>
        </>
    )
}