import FlashcardCreator from '../PlayFlashcardsPage/FlashcardCreator';
import { StyledModal } from '../../common/presenters/StyledModal'
import { faBook, faAddressCard, faAnchor } from '@fortawesome/free-solid-svg-icons'
import { useState } from 'react';
import { TaskForm } from "../../Model/Task";



export default function AddTaskModal() {
  const [isModalOpen, setIsModalOpen] = useState(false)
  return (

    <main style={{ display: "flex", "justifyContent": "center", backgroundColor: '#F1E0C5', 'minHeight': '85vh' }}>
      <StyledModal
        icon={faAddressCard}
        title="Styled button"
        isModalOpen={isModalOpen}
        setIsModalOpen={setIsModalOpen}
      >
        <TaskForm/>
      </StyledModal>
    </main>
  );

}


