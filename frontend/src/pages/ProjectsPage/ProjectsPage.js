import { useEffect, useState} from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { NEW_ACTIVE_PROJECT } from "../../store/tasks/actions";
import  {fetchProjects} from '../../store/tasks/actions';
import FsTreeView from './fsTreeView/FsTreeView';
import './Projects.css'
import * as R from 'ramda';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';

import { StyledModal } from '../../common/presenters/StyledModal'
import { faAddressCard } from '@fortawesome/free-solid-svg-icons'
import { TaskForm } from "../../Model/Task";

export default function ProjectsPage() {
  const dispatch = useDispatch();
  const projectList = useSelector(state => state.tasksReducer.projects, (left, right) => R.equals(left, right));
  const activeProject = useSelector(state => state.tasksReducer.activeProject, (left, right) => R.equals(left, right))
  useEffect(() => {
    dispatch(fetchProjects());
  }, []);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);


  // const treeData = d3.hierarchy(activeRootTask, task => task.sub_tasks); // todo moze to?
  return (
    <div className="dashboard-wrapper">
      <Drawer
        anchor='left'
        open={isDrawerOpen}
        onClose={()=>setIsDrawerOpen(!isDrawerOpen)}
      >
        {<ul>
          {projectList.map((project) => 
            <li 
              onClick={() => {
                dispatch({type: NEW_ACTIVE_PROJECT, activeProjectId: project.task_id});
                setIsDrawerOpen(false);
              }}
            key={project.task_id}>
              {project.name}
            </li>
          )}
        </ul>}
      </Drawer>
      {FsTreeView(activeProject, dispatch)}
      <div className="dashboard-buttons">
        <StyledModal
          icon={faAddressCard}
          title="Edit task"
          isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}
        >
          <TaskForm/>
        </StyledModal>
        <StyledModal
          icon={faAddressCard}
          title="New task"
          isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}
        >
          <TaskForm newTask={true}/>
        </StyledModal>
        <Button onClick={() => setIsDrawerOpen(true)}>Projects</Button>
      </div>
    </div>
  )

}

