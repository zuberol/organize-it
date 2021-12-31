import FlashcardCreator from '../PlayFlashcardsPage/FlashcardCreator';
import { StyledModal } from '../../common/presenters/StyledModal'
import { faBook, faAddressCard, faAnchor } from '@fortawesome/free-solid-svg-icons'
import { useState } from 'react';
import { TaskForm } from "../../Model/Task";
import { project } from "../../mock/MockProject";
import { ProjectCard } from '../../Model/Project/ProjectPresenters'



export default function AddTaskModal() {
  const [mockProject, setProject] = useState(project)
  return (

    <main style={{ display: "flex", "justifyContent": "center", backgroundColor: '#F1E0C5', 'minHeight': '85vh' }}>

    <div style={{maxWidth: "500px"}}>
      <ProjectCard
          project={mockProject}
        />
    </div>
    </main>
  );

}
