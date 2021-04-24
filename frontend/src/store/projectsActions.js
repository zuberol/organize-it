import { BACKEND_BASE_URL } from '../utils/config';

export const TASK_SUCCESFULLY_DELETED = "TASK_SUCCESFULLY_DELETED";
export const TASK_SUCCESFULLY_CREATED = "TASK_SUCCESFULLY_CREATED";   //nie potrzebne chyba
export const CLOSE_MODAL = "CLOSE_MODAL";
export const OPEN_MODAL = "OPEN_MODAL";

export const saveTask = (newTask) => {
    console.log(newTask)
    return (dispath) => {
        fetch(new URL('/api/task', BACKEND_BASE_URL), {
            method: 'PUT',
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
            dispath({type: CLOSE_MODAL});
        });
    }
}