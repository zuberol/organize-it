import { React, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import * as actionTypes from '../../store/projectsActions';
import useMaterialTreeView from './useMaterialTreeView';
import useFsTreeView from './fsTreeView/useFsTreeView';
import './ProjectsDashboard.css'


export default function ProjectsDashboardPage() {
  const dispatch = useDispatch();
  const availableProjects = useSelector(state => state.projectsReducer.projects
    // , (prev, next) => prev[0].sub_tasks.length != next[0].sub_tasks.length
    , []
    );
  useEffect(() => {
     dispatch(actionTypes.fetchProjects()); //todo zoptymalizowac dzialanie, dziala teraz jak component did mount, NIE JEST ZALEZNE OD decks ze store!!!
  }, []);

  // const [ProjectsTreeView, expandIndexes, rollIndexes] = useMaterialTreeView(projects);
  // const [ProjectsTreeView, expandIndexes, rollIndexes] = useFsTreeView(projects);
  console.log(availableProjects)

  return (
    <div className="dashboard-wrapper">
      {/* {ProjectsTreeView} */}
      {useFsTreeView(availableProjects[0], dispatch)}
      <div className="dashboard-buttons">
        {/* <button className="" onClick={expandIndexes}>colapse all</button> */}
      </div>
    </div>
  )
}

