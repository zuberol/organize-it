import { useState } from 'react';
import Modal from '@material-ui/core/Modal';
import Backdrop from '@material-ui/core/Backdrop';
import Fade from '@material-ui/core/Fade';
import { makeStyles } from '@material-ui/core/styles';
import { BACKEND_BASE_URL } from '../../../utils/config';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faStickyNote } from '@fortawesome/free-solid-svg-icons'



const useStyles = makeStyles((theme) => ({
    modal: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        color: 'blue'
    },
    paper: {
        backgroundColor: 'tan',
        boxShadow: theme.shadows[5],
        padding: "2rem"
    },
}));

export default function createProject() {
    const classes = useStyles();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [project, setProject] = useState({
        title: '',
        description: '',
        tags: ["dsadas", "dsadas"]
    });

    return (
        <>
            <button type="button" className="flashcard__button" onClick={() => setIsModalOpen(true)}>
                <FontAwesomeIcon icon={faStickyNote} />
                <span>Create project</span>
            </button>
            <Modal
                aria-labelledby="transition-modal-title"
                aria-describedby="transition-modal-description"
                className={classes.modal}
                open={isModalOpen}
                onClose={() => setIsModalOpen(false)}
                closeAfterTransition
                BackdropComponent={Backdrop}
                BackdropProps={{
                    timeout: 300
                }}
            >
                <Fade in={isModalOpen}>
                    <div className={classes.paper}>
                        <div>
                            <form onSubmit={handleSubmit}>
                                <input
                                    name="title"
                                    type="text"
                                    onChange={handleChange}
                                    value={project.title}
                                />
                                <input
                                    name="description"
                                    type="text"
                                    onChange={handleChange}
                                    value={project.description}
                                />
                                <input
                                    name="tags"
                                    type="text"
                                    onChange={handleTagsChange}
                                    value={JSON.stringify(...project.tags).replaceAll(`"`, "")}
                                />
                                <button type="submit">Submit</button>
                            </form>
                        </div>
                    </div>
                </Fade>
            </Modal>
        </>
    )

    function handleSubmit(event) {
        event.preventDefault();
        fetch(new URL('/api/project', BACKEND_BASE_URL), {
            method: 'POST',
            mode: 'cors',
            body: new FormData(event.target)
        })
            .catch((e) => console.error("Błąd przy zapisywaniu projectu:", e));
        setIsModalOpen(false);
    }

    function handleChange(e) {
        setProject({
            ...project,
            [e.target.name]: e.target.value
        });
    }

    function handleTagsChange(e) {
        setProject({
            ...project,
            tags: e.target.value.split(" ")
        });
    }
}