import { TASK } from '../../config/backendRoutes';
import { Project } from '../../Model/Project';

export const CLOSE_MODAL = "CLOSE_MODAL";
export const OPEN_MODAL = "OPEN_MODAL";
export const INIT_PROJECTS = "INIT_PROJECTS";

export function updateTask(newTask) {
  return (dispatch) => {
    fetch(TASK, {
      method: 'POST',
      mode: 'cors',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newTask)
    })
    .catch(err => {
      console.error(err);
    })
    .then(() => {
      dispatch({type: CLOSE_MODAL});
    })
    .then(() => {
      dispatch(fetchProjects());
    })
    .catch(err => {
      console.error("fetching projects failed: ", err);
    })
  }
}

// todo bugfix
export const saveTask = updateTask;

export function fetchProjects() {
  return (dispatch) => {
    fetch('http://localhost:8080/api/dev/devDTO/projects', {
      method: 'GET',
      mode: 'cors'
    })
    .then(res => res.json())
    .then(projectsRecords => projectsRecords.map(Project.recordToProject))
    .then((projects) => {
      dispatch({type: INIT_PROJECTS, projects: projects});
    })
    .catch(err => {
      console.error(err, "Backend doesn't respond");
      dispatch({type: INIT_PROJECTS, projects: []});
    })
  }
}