import { BACKEND_BASE_URL } from '../utils/config';

export const TASK_SUCCESFULLY_DELETED = "TASK_SUCCESFULLY_DELETED";
export const TASK_SUCCESFULLY_CREATED = "TASK_SUCCESFULLY_CREATED";   //nie potrzebne chyba
export const CLOSE_MODAL = "CLOSE_MODAL";
export const OPEN_MODAL = "OPEN_MODAL";
export const CREATE_TASK = "CREATE_TASK"; //todo
export const INIT_PROJECTS = "INIT_PROJECTS";
export const UPDATE_PROJECTS = "UPDATE_PROJECTS";

export const saveTask = (newTask) => {
  console.log(newTask)
  return (dispatch) => {
    fetch(new URL('/api/task', BACKEND_BASE_URL), {
      method: 'POST',
      mode: 'cors',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newTask)
    })
    .catch(err => {
      console.log(err);
    })
    .then(() => {
      dispatch({type: CLOSE_MODAL});
    })
    .then(() => {
      dispatch(fetchProjects());
    })
    .catch(err => {
      console.log("fetching projects failed: ", err);
    })
  }
}

export const fetchProjects = () => {
  return (dispatch) => {
    fetch('http://localhost:8080/api/projects', {
      method: 'GET',
      mode: 'cors'
    })
    .then(res => res.json())
    .then((tasks) => {
      dispatch({type: INIT_PROJECTS, projects: tasks});
    })
    .catch(err => {
      console.error(err, "Backend doesn't respond");
      dispatch({type: INIT_PROJECTS, projects: []});
    })
  }
}