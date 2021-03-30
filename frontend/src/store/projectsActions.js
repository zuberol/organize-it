import { BACKEND_BASE_URL } from '../utils/config';

export const DELETE_TASK = "DELETE_TASK";
export const CREATE_TASK = "CREATE_TASK";   //nie potrzebne chyba
export const CLOSE_MODAL = "CLOSE_MODAL";
export const OPEN_MODAL = "OPEN_MODAL";


const test1 = {
    "taskId": 13,
    "note": "podstawy fizyki",
    "subTasks": [
        {
            "taskId": 40,
            "note": "40 ogarnac 50",
            "subTasks": [],
            "parentId": "13"
        },
        {
            "taskId": 50,
            "note": "50 code pipeline",
            "subTasks": [],
            "parentId": {
                "taskId": 31,
                "note": "paaaarent task",
                "subTasks": [],
                "parentId": 42
            }
        }
    ],
    "parentId": 3
};

const test2 = {
    "taskId": 13,
    "note": "podstawy fizyki",
    "subTasks": [
        {
            "taskId": 40,
            "note": "40 ogarnac 50",
            "subTasks": [],
            "parentTask": 13
        },
        {
            "taskId": 50,
            "note": "50 code pipeline",
            "subTasks": [],
            "parentTask": 31
        }
    ],
    "parentTask": 3
};





export const saveTask = (newTask) => {
    return (dispath) => {
        fetch(new URL('/api/task', BACKEND_BASE_URL), {
            method: 'PUT', // TODO set it to PUT
            mode: 'cors',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            // body: JSON.stringify(newTask)
            body: JSON.stringify(test2)
        })
        .catch(err => {
            console.log(err);
        })
        .then(() => {
            dispath({type: CLOSE_MODAL});
        });
    }
}

export const fetchTasts = () => {
    return (dispath) => {
        fetch(BACKEND_BASE_URL + '/api/root/?id=1', {
            method: 'GET', // TODO set it to PUT
            mode: 'cors',
            credentials: 'include'
        })
        .catch(err => {
            console.log(err);
        });
    }
}