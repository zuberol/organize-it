import { React, useEffect, useState, ReactFragment } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import * as actionTypes from '../../store/projectsActions';
import useMaterialTreeView from './useMaterialTreeView';
import FsTreeView from './fsTreeView/FsTreeView';
import './ProjectsDashboard.css'
import createProjectBtn from './widgets/createProjectWidget';
import useListView from '../../common/presenters/CardAwareListView';
import useCardAwareListView from '../../common/presenters/CardAwareListView';
import withProjectPresenter from '../../Model/ProjectPresenter';
import * as R from 'ramda'

import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';

export default function ProjectsDashboardPage() {
  const dispatch = useDispatch();
  const availableProjects = useSelector(state => state.projectsReducer.projects, (left, right) => R.equals(left, right));
  useEffect(() => {
    dispatch(actionTypes.fetchProjects()); //todo zoptymalizowac dzialanie, dziala teraz jak component did mount, NIE JEST ZALEZNE OD decks ze store!!!
  }, []);
  const [activeProject, setActiveProject] = useState({});
  useEffect(() => {
    const newActiveProject = availableProjects.find(project => project.project_id === activeProject.project_id);
    setActiveProject(newActiveProject || {});
  }, [availableProjects]);
  const [isDrawerOpen, setIsDrawerOpen] = useState(false);

  // const [ProjectsTreeView, expandIndexes, rollIndexes] = useMaterialTreeView(projects);
  // const [ProjectsTreeView, expandIndexes, rollIndexes] = FsTreeView(projects);


  return (
    <div className="dashboard-wrapper">
      {/* {ProjectsTreeView} */}
      <Drawer
        anchor='left'
        open={isDrawerOpen}
      >
        {<ul>
          {availableProjects.map((project, idx) => 
            <span 
              onClick={() => {
                setActiveProject(project);
                setIsDrawerOpen(false);
            
              }}
            key={idx}>{useCardAwareListView(withProjectPresenter(project))}</span>
          )}
        </ul>
        }
      </Drawer>
      {FsTreeView(activeProject, dispatch)}
      <div className="dashboard-buttons">
        {/* <button onClick={expandIndexes}>colapse all</button> */}
        <Button onClick={() => setIsDrawerOpen(true)}>Projects</Button>
        {createProjectBtn()}
      </div>
    </div>
  )
}

