import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { NEW_ACTIVE_PROJECT } from "../../store/tasks/actions";
import { fetchProjects } from '../../store/tasks/actions';
import FsTreeView from './fsTreeView/FsTreeView';
import './Projects.scss'
import * as R from 'ramda';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';

import { StyledModal } from '../../common/presenters/StyledModal'
import { faAddressCard, faBackward } from '@fortawesome/free-solid-svg-icons'
import { TaskForm } from "../../Model/Task";
import { DetailedProjectView } from "../../Model/Project/DetailedProjectView";
import { NamedIcon } from '../../common/presenters/NamedIcon';
import { Container } from '@mui/material';

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
      <div className="dashboard-nav">
        {!R.isEmpty(activeProject) && <Button
          onClick={() => dispatch({ type: NEW_ACTIVE_PROJECT })}>
          <NamedIcon iconDef={faBackward} caption="return" />
        </Button>}
        <Drawer anchor='left' open={isDrawerOpen} onClose={() => setIsDrawerOpen(!isDrawerOpen)}>
          {<ul>
            {projectList.map((project) =>
              <li
                onClick={() => {
                  dispatch({ type: NEW_ACTIVE_PROJECT, activeProjectId: project.task_id });
                  setIsDrawerOpen(false);
                }}
                key={project.task_id}>
                {project.name}
              </li>
            )}
          </ul>}
        </Drawer>
      </div>
      <Container>
        <FsTreeView {...{task: activeProject, dispatch}}/>
        {R.isEmpty(activeProject) && projectList.map(
          project => <FsTreeView key={project.task_id} {...{task: project, dispatch}} />)}
      </Container>
      
      <div className="dashboard-buttons">
        <StyledModal icon={faAddressCard} title="Edit task" isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}>
          <TaskForm />
        </StyledModal>
        <StyledModal icon={faAddressCard} title="New task" isModalOpen={isModalOpen}
          setIsModalOpen={setIsModalOpen}>
          <TaskForm newTask={true} />
        </StyledModal>
        <Button onClick={() => setIsDrawerOpen(true)}>Projects</Button>
      </div>
    </div>
  )

}

