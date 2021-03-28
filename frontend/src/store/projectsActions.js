import { BACKEND_BASE_URL } from '../utils/config';

export const DELETE_TASK = "DELETE_TASK";
export const CREATE_TASK = "CREATE_TASK";   //nie potrzebne chyba
export const CLOSE_MODAL = "CLOSE_MODAL";
export const OPEN_MODAL = "OPEN_MODAL";

export const saveTask = (newTask) => {
    return (dispath) => {
        fetch(new URL('/task', BACKEND_BASE_URL), {
            method: 'POST', //TODO set it to PUT
            mode: 'cors',
            body: newTask
        })
        .catch(err => {
            console.log(err);
        })
        .then(() => {
            dispath({type: CLOSE_MODAL});
        });
    }
}