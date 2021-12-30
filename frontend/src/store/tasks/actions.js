import { dispatch } from 'd3-dispatch';
import { TASK_URL, PROJECTS_URL, TASK_INBOX_URL } from '../../config/backendRoutes';

export const INIT_PROJECTS = "INIT_PROJECTS";
export const INIT_INBOX = "INIT_INBOX";
export const NEW_ACTIVE_PROJECT = "NEW_ACTIVE_PROJECT";
export const TOGGLE_MODAL = "TOGGLE_MODAL";

export function updateTask(task) {
  return (dispatch) => {
    fetch(TASK_URL, {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(task)
    })
    .catch(err => {
      console.error(err);
    })
    .then(() => {
      dispatch(fetchProjects());
    })
    .catch(err => {
      console.error("fetching projects failed: ", err);
    })
  }
}

export function fetchProjects() {
  return (dispatch) => {
    fetch(PROJECTS_URL)
    .then(res => res.json())
    .then((projects) => {
      dispatch({type: INIT_PROJECTS, projects});
    })
    .catch(err => {
      console.error(err, "Backend doesn't respond");
      dispatch({type: INIT_PROJECTS, projects: []});
    })
  }
}